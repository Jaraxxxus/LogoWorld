/**
 * Invoker- класс  <p>Switch </p>
 * Переключает выполнение команд
 * Инициализирует объект класса LogoWorld, над объектами которого
 * будут совершаться манипуляции классами-командами
 * метод для запуска команды <b>execute(String commandNameWithArgs) </b>
 * */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Logger;

public class Switch {
    private static final Logger log = Logger.getLogger(String.valueOf(Switch.class));
    private boolean bInit;
    private final HashMap<String, Command> commandMap = new HashMap<>();
    final HashMap<String, String> availableCommand = new HashMap<>();
    //private static final Logger logger = LogManager.getLogger(String.valueOf(Main.class));

    public LogoWorld lw;
    {

        try {

            String propFileName = "config.properties";
                    /*System.getProperty("user.dir") +
                    File.separator + "src" + File.separator+ "main"+
                    File.separator +"resources" +
                    File.separator + "config.properties";*/


            FileInputStream fis;
            Properties properties = new Properties();





            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);


            properties.load(inputStream);


            this.availableCommand.put("INIT", properties.getProperty("INIT"));
            this.availableCommand.put("MOVE", properties.getProperty("MOVE"));
            this.availableCommand.put("DRAW", properties.getProperty("DRAW"));
            this.availableCommand.put("WARD", properties.getProperty("WARD"));
            this.availableCommand.put("TELEPORT", properties.getProperty("TELEPORT"));


        } catch (FileNotFoundException e) {

            //log.error("Это сообщение ошибки");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public Switch(){
        lw = new LogoWorld();
        bInit = false;
        log.info("Init Class- Switch");
    }




    public void register(String commandName, Command command) {
        commandMap.put(commandName, command) ;
        log.info("Register command-Class");
    }
    public void execute(String commandNameWithArgs) throws IllegalAccessException {
        //парсить строку
        String strArgs;
        String[] inputArgs = commandNameWithArgs.split(" ", 2);
        inputArgs[0] = inputArgs[0].toUpperCase();
        if (inputArgs.length > 1) {
            strArgs = inputArgs[1];
        }
        else
            strArgs = "";

        Command command = commandMap.get(inputArgs[0]);

        if (command == null) {
            if (!bInit && !inputArgs[0].equals("INIT") ) {
                System.out.println("First command must be INIT");
                log.info("First command must be INIT");
            }
            else {
                //Загрузим класс
                try {
                    Class clazz = Class.forName(availableCommand.get(inputArgs[0]));
                    System.out.println(availableCommand.get(inputArgs[0]));
                    Class[] params = {LogoWorld.class, String.class};
                    command = (Command) clazz.getConstructor(params).newInstance(lw, strArgs);
                    register(inputArgs[0], command);
                    if (!bInit) {
                        bInit = inputArgs[0].equals("INIT");
                    }
                } catch (ClassNotFoundException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        }
        if (command == null) {
            log.info("Error no command registered for");
            throw new IllegalStateException( " no command registered for " + inputArgs[0]);

        }
        log.info("SetArg && Execute command");
        command.setArg(lw, strArgs);
        command.execute();

    }
}
