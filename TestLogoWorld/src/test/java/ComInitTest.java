import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class ComInitTest {

    LogoWorld lw = new LogoWorld();
    String sendArgs;
    int actual;
    int expected;


    @org.junit.jupiter.api.Test
    void InitsetArg() {


        sendArgs = "6 4 2 3";
        ComInit command = new ComInit(lw, sendArgs);
        command.setArg(lw, sendArgs);
        actual = lw.field.size();
        expected = 24;

        assertEquals(actual, expected);
        assertEquals(lw.curx, 2);
        assertEquals(lw.cury, 3);

        sendArgs = "16 8 4 6";
        command.setArg(lw, sendArgs);
        assertEquals(lw.field.size(), 128);
        assertEquals(lw.curx, 4);
        assertEquals(lw.cury, 6);
    }

    @org.junit.jupiter.api.Test
    void TeleportsetArg() {

//-------------------------
        sendArgs = "6 4 2 3";
        ComInit command = new ComInit(lw, sendArgs);
        command.setArg(lw, sendArgs);
        sendArgs = "1 1";
        ComTeleport command1 = new ComTeleport(lw, sendArgs);
        command1.setArg(lw, sendArgs);


        assertEquals(lw.curx, 1);
        assertEquals(lw.cury, 1);

        sendArgs = "5 3";
        command1.setArg(lw, sendArgs);
        assertEquals(lw.curx, 5);
        assertEquals(lw.cury, 3);

    }

    @org.junit.jupiter.api.Test
    void MovesetArg() {

//---------------------------
        sendArgs = "6 4 1 1";
        ComInit command = new ComInit(lw, sendArgs);
        command.setArg(lw, sendArgs);
        sendArgs = "D 16";

        ComMove command1 = new ComMove(lw, sendArgs);
        command1.setArg(lw, sendArgs);

        assertEquals(lw.curx, 5);
        assertEquals(lw.cury, 1);

        sendArgs = "U 11";
        command1.setArg(lw, sendArgs);
        assertEquals(lw.curx, 0);
        assertEquals(lw.cury, 1);

        sendArgs = "L 10";
        command1.setArg(lw, sendArgs);
        assertEquals(lw.curx, 0);
        assertEquals(lw.cury, 3);

        sendArgs = "R 7";
        command1.setArg(lw, sendArgs);
        assertEquals(lw.curx, 0);
        assertEquals(lw.cury, 2);

    }

    @org.junit.jupiter.api.Test
    void DrawsetArg() {
        sendArgs = "6 4 1 1";
        ComInit command = new ComInit(lw, sendArgs);
        command.setArg(lw, sendArgs);
        sendArgs = "";
        ComDraw command1 = new ComDraw(lw, sendArgs);
        command1.execute();
        assertTrue(lw.drawtrack);



    }

    @org.junit.jupiter.api.Test

    void WardsetArg() {
        sendArgs = "6 4 1 1";
        ComInit command = new ComInit(lw, sendArgs);
        command.setArg(lw, sendArgs);
        sendArgs = "";
        ComWard command1 = new ComWard(lw, sendArgs);
        command1.execute();
        assertFalse(lw.drawtrack);



    }
 }