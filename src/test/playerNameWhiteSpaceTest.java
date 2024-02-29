package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import javax.swing.JTextField;

import startMenu.StartingScreen;

public class playerNameWhiteSpaceTest {

    @Test
    public void testPlayerNameWhitOutWhiteSpace() {
        StartingScreen start = new StartingScreen();
        start.setTfPlayer1(new JTextField("Test Player"));
        boolean restult = start.runNameCheck();
        assertEquals(true, restult);
    }

    @Test
    public void testPlayerNameWhitWhiteSpace() {
        StartingScreen start = new StartingScreen();
        start.setTfPlayer1(new JTextField("  "));
        boolean restult = start.runNameCheck();
        assertEquals(false, restult);
    }
    
}
