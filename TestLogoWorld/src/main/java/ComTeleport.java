import java.util.logging.Logger;

/** Команда
 *class <p> ComTeleport</p>
 * формат вызова <b> teleport x y  </b>
 * перемещает объект на позицию x y
 * */

public class ComTeleport implements Command {
    private final LogoWorld lw;
    static final char  SPACE    = '_';
    static final char  TURTLE    = 15;
    static final char  TRACK    = '.';
    private boolean badArgs;
    private static final Logger log = Logger.getLogger(String.valueOf(ComTeleport.class));

    public ComTeleport(LogoWorld lw, String args ) {
        badArgs = false;
        this.lw = lw;
        log.info("Init Teleport-class ");
    }



    @Override
    public void setArg(LogoWorld lw, String sendArgs) {
        String[] str = sendArgs.split(" ");
        badArgs = false;

        if (str.length< 2){
            System.out.println("Must be more arguments...");
            badArgs = true;
        }
        if (!badArgs) {
            log.info("Chek out Arguments ");
            int x = Integer.parseInt(str[0]);
            int y = Integer.parseInt(str[1]);

            if (x < 0 || x >= this.lw.height) {
                System.out.println("coordinate out of dimension");
                badArgs = true;
            }
            if (y < 0 || y >= this.lw.width) {
                System.out.println("coordinate out of dimension");
                badArgs = true;
            }
            if (!badArgs) {
                char ch = (lw.drawtrack) ? TRACK : SPACE;
                lw.field.set(lw.curx * lw.width + lw.cury, ch);

                lw.curx = x;
                lw.cury = y;
                lw.field.set(lw.curx * lw.width + lw.cury, TURTLE);
            }
        }

    }

    @Override
    public void execute() {
        if (!this.badArgs) {
            lw.printField();
        }
    }
}
