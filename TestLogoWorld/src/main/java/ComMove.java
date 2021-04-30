import java.util.logging.Logger;

/** Команда
 * <p> ComMove </p> команда для перемещения объекта по полю
 * задается след. форматом <b> move U|D|L|R steps </b>
 *
 */

public class ComMove implements Command {
    private final LogoWorld lw;
    private boolean badArgs;
    static final char  LEFT  = 'L';
    static final char  RIGHT = 'R';
    static final char  DOWN  = 'D';
    static final char  UP    = 'U';
    static final char  SPACE    = '_';
    static final char  TURTLE    = 15;
    static final char  TRACK    = '.';
    private static final Logger log = Logger.getLogger(String.valueOf(ComMove.class));


    public ComMove(LogoWorld lw, String sendArgs){
        badArgs = false;
        this.lw = lw;
        log.info("Init Move-class ");
    }


    @Override
    public void setArg(LogoWorld lw, String sendArgs) {
        String[] str = sendArgs.split(" ");
        badArgs = false;
        log.info("Chek out Arguments ");
        if (str.length < 2) {
            System.out.println("Must be more arguments...");
            badArgs = true;
        }
        int steps = Integer.parseInt(str[1]);
        if (steps < 0) {
            System.out.println("bad value of steps");
            badArgs = true;
        }
        char mode = str[0].toUpperCase().charAt(0);
        if (mode != 'U' && mode != 'D' && mode != 'L' && mode != 'R' ) {
            System.out.println("bad value of mode");
            badArgs = true;
        }

        if(!badArgs) {
            int loops = 0;

            //расчет координат
            lw.oldx = lw.curx;
            lw.oldy = lw.cury;
            switch (mode) {
                case UP:
                    steps = steps % lw.height;  //лишние циклы
                    loops = steps / lw.height;
                    lw.curx = (lw.curx - steps < 0) ? lw.height + lw.curx - steps : lw.curx - steps;
                    break;
                case DOWN:
                    steps = steps % lw.height;  //лишние циклы
                    loops = steps / lw.height;
                    lw.curx = (lw.curx + steps >= lw.height) ? lw.curx + steps - lw.height : lw.curx + steps;
                    break;
                case LEFT:
                    steps = steps % lw.width;
                    loops = steps / lw.width;
                    lw.cury = (lw.cury - steps < 0) ? lw.width + lw.cury - steps : lw.cury - steps;
                    break;
                case RIGHT:
                    steps = steps % lw.width;
                    loops = steps / lw.width;
                    lw.cury = (lw.cury + steps >= lw.width) ? lw.cury + steps - lw.width : lw.cury + steps;
                    break;

                default:
                    // default statements

            }
            fillArr(lw, mode, (loops != 0));
        }

    }
    static void fillArr(LogoWorld lw, char mode, boolean loop) {
        //заполнение массива следами



        char ch = (lw.drawtrack) ? TRACK: SPACE;
        if (lw.drawtrack) {

            switch (mode) {
                case UP:
                    if (loop) { //петля полная тогда вся строка/ столбец
                        for (int i = 0; i < lw.height; i++) {
                            lw.field.set(i * lw.width + lw.cury, ch);
                        }

                    } else {
                        if (lw.curx > lw.oldx) {  //через границу смещение

                            for (int i = 0; i <= lw.oldx; i++) {
                                lw.field.set(i * lw.width + lw.cury, ch);
                            }
                            for (int i = lw.curx; i < lw.height; i++) {
                                lw.field.set(i * lw.width + lw.cury, ch);
                            }
                        } else {  //смещение в пределах поля
                            for (int i = lw.curx; i < lw.oldx; i++) {
                                lw.field.set(i * lw.width + lw.cury, ch);
                            }
                        }

                    }
                    break;
                case DOWN:
                    if (loop) { //петля полная тогда вся строка/ столбец
                        for (int i = 0; i < lw.height; i++) {
                            lw.field.set(i * lw.width + lw.cury, ch);
                        }

                    } else {
                        if (lw.curx < lw.oldx) {  //через границу смещение

                            for (int i = 0; i <= lw.oldx; i++) {
                                lw.field.set(i * lw.width + lw.cury, ch);
                            }
                            for (int i = lw.curx; i < lw.height; i++) {
                                lw.field.set(i * lw.width + lw.cury, ch);
                            }
                        } else {  //смещение в пределах поля
                            for (int i = lw.oldx; i < lw.curx; i++) {
                                lw.field.set(i * lw.width + lw.cury, ch);
                            }
                        }

                    }
                    break;
                case LEFT:
                    if (loop) { //петля полная тогда вся строка/ столбец
                        for (int i = 0; i < lw.width; i++) {
                            lw.field.set(lw.curx * lw.width + i, ch);
                        }

                    } else {
                        if (lw.cury > lw.oldy) {  //через границу смещение

                            for (int i = 0; i <= lw.oldy; i++) {
                                lw.field.set(lw.curx * lw.width + i, ch);
                            }
                            for (int i = lw.cury; i < lw.width; i++) {
                                lw.field.set(lw.curx * lw.width + i, ch);
                            }
                        } else {  //смещение в пределах поля
                            for (int i = lw.cury; i < lw.oldy; i++) {
                                lw.field.set(lw.curx * lw.width + i, ch);
                            }
                        }

                    }
                    break;
                case RIGHT:
                    if (loop) { //петля полная тогда вся строка/ столбец
                        for (int i = 0; i < lw.width; i++) {
                            lw.field.set(lw.curx * lw.width + i, ch);
                        }

                    } else {
                        if (lw.cury < lw.oldy) {  //через границу смещение

                            for (int i = 0; i <= lw.cury; i++) {
                                lw.field.set(lw.curx * lw.width + i, ch);
                            }
                            for (int i = lw.oldy; i < lw.width; i++) {
                                lw.field.set(lw.curx * lw.width + i, ch);
                            }
                        } else {  //смещение в пределах поля
                            for (int i = lw.oldy; i < lw.cury; i++) {
                                lw.field.set(lw.curx * lw.width + i, ch);
                            }
                        }

                    }
                    break;
            }
        } else {//не режим следов
            lw.field.set(lw.oldx * lw.width + lw.oldy, SPACE );
        }

        lw.field.set(lw.curx * lw.width + lw.cury, TURTLE );
    }


    @Override
    public void execute() {

        if (!this.badArgs) {
            lw.printField();
        }
    }



}
