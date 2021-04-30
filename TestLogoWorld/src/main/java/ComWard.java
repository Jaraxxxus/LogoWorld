import java.util.logging.Logger;

/** Команда
 * class <p>  ComWard </p>
 * Отключает режим оставления "следов" при перемещении объекта
 * Формат вызова <b> ward </b>
 * */

public class ComWard implements Command {
    private final LogoWorld lw;
    private static final Logger log = Logger.getLogger(String.valueOf(ComWard.class));
    public ComWard(LogoWorld lw, String args) {
        this.lw = lw;
        log.info("Init Ward-class ");
    }


    @Override
    public void setArg(LogoWorld lw, String args) {

    }

    @Override
    public void execute() {
        lw.drawtrack = false;
    }
}
