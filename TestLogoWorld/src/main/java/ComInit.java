/** Команда
 * <p> ComInit  </p> Инициализирует поле
 * и помещает объект в позицию
 * Формат вызова
 *<b> init height width x y </b>
 * */

import java.util.ArrayList;
import java.util.logging.Logger;

public class ComInit implements Command {
    private static final Logger log = Logger.getLogger(String.valueOf(ComInit.class));
    private final LogoWorld lw;
    static final char  SPACE    = '_';
    static final char  TURTLE    = 15;
    private boolean badArgs;

    public ComInit(LogoWorld lw, String sendArgs){
        this.lw = lw;
        badArgs = false;
        log.info("Init Init-Class");
    }

    @Override
    public void setArg(LogoWorld lw, String sendArgs) {
        String[] str = sendArgs.split(" ");
        badArgs = false;
        if (str.length < 4) {
            System.out.println("Must be more arguments...");
            badArgs = true;
        }
        if (!badArgs) {
            log.info("Chek out Arguments ");
            int width = Integer.parseInt(str[1]);
            int height = Integer.parseInt(str[0]);
            int x = Integer.parseInt(str[2]);
            int y = Integer.parseInt(str[3]);


            if (lw.field != null && (lw.field.size() > 0)) {
                lw.field.clear();
            }
            //отобразить поле и поместить черепашку
            if (x < 0 || x >= height) {
                System.out.println("coordinate out of dimension");
                badArgs = true;
            }
            if (y < 0 || y >= width) {
                System.out.println("coordinate out of dimension");
                badArgs = true;
            }

            if (!badArgs) {
                log.info("Init  field`s dimension && field Array ");
                lw.curx = x;
                lw.cury = y;
                lw.width = width;
                lw.height = height;
                lw.field = new ArrayList<>(width * height);
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {

                        if (i == x && j == y) {
                            lw.field.add(TURTLE);
                        } else {
                            lw.field.add(SPACE);

                        }
                    }
                }
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
