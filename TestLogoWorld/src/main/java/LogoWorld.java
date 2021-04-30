/** Receiver класс
 * класс <p> LogoWorld </p>
 * описывается полями координат текущей и предыдущей, также
 * высотой и шириной поля. <b> curx, cury, oldx, oldy, width, height </b>
 * Содержит список-поле <b> arr </b> символов для отображения в консоли
 * Имеет метод отображения поля в консоли <p> printField() </p>
 * */
import java.util.ArrayList;

public class LogoWorld {
    int curx;
    int cury;
    int oldx;
    int oldy;
    int width;
    int height;
    boolean drawtrack = false;
    public ArrayList<Character> field ;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final char  SPACE    = '_';
    public static final char  TURTLE    = 15;
    public static final char  TRACK    = '.';
    public LogoWorld(){

    }

    public void printField() {
        Character ch;
        for (int i = 0; i < this.height; i++ ) {

            for (int j = 0; j < this.width; j++) {
                ch = field.get(i * width + j);
                if(ch == TRACK) {
                    System.out.printf(ANSI_RED + TRACK + ANSI_RESET);
                }else if(ch == TURTLE) {
                    System.out.printf(ANSI_CYAN + TURTLE + ANSI_RESET);
                } else {
                    System.out.printf("_");
                }

            }

            System.out.println("");

        }

    }


}
