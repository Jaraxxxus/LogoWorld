import java.util.logging.Logger;

/** Команда
 * class <p>  ComDraw </p>
 * Включает режим оставления "следов" при перемещении объекта
 * Формат вызова <b> draw </b>
 * */
public class ComDraw implements Command {
    private final LogoWorld lw;
    private static final Logger log = Logger.getLogger(String.valueOf(ComDraw.class));
    public ComDraw(LogoWorld lw, String args) {
        this.lw = lw;
        log.info("Init Draw-class ");

    }

    @Override
    public void setArg(LogoWorld lw, String args) {

    }

    @Override
    public void execute() {
        lw.drawtrack = true;

    }

}
