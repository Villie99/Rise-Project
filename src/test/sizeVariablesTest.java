package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import startMenu.StartingScreen;

public class sizeVariablesTest {

    @Test
    public void testSizeVariableWidth() {
        StartingScreen start = new StartingScreen();
        int result = start.getWidth();
        assertEquals(result, 900);
    }

    @Test
    public void testSizeVariableHeight() {
        StartingScreen start = new StartingScreen();
        int result = start.getHeight();
        assertEquals(result, 830);
    }

    
}
