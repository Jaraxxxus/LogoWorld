import java.util.Scanner;
import java.util.logging.Logger;


/**
     * Client
     * Организация ввода команд с консоли с передача их в переключатель <b>Switch </b>
     *
     * */

public class Client {

    private static final Logger log = Logger.getLogger(String.valueOf(Client.class));
    //private static final Logger log = (Logger) LogManager.getLogger();
        public static void main(String[] args) throws IllegalAccessException {
            boolean bQuit = false;
//log.warning(" Warn Start...");


            log.info("Start...");
            Switch comSwitch = new Switch();
            Scanner in = new Scanner(System.in);
            while (!bQuit) {
                System.out.print("Введите команду: ");

                if (in.hasNextLine()) {
                    String str = in.nextLine();
                    comSwitch.execute(str);


                }
                else bQuit = true;

            }
            log.info("End...");
        }
    }

