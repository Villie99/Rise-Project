package test;

import static org.junit.Assert.assertEquals;

import javax.swing.ImageIcon;

import org.junit.Test;
import player.*;

public class playerImgIconRankDowngradeTest {
    

    @Test
    public void testPlayerRankDownFromKnightToPeasant() {
        Player player = new Player("TestPlayer", new ImageIcon("images/playerRedKnight.png"), 0);
        player.setNetWorth(2000);
        player.checkPlayerRank();
        player.setNetWorth(1000);
        String result = player.checkPlayerRank();
        assertEquals("Downgraded icon to red peasant from knight", result);
        
    }
     @Test
    public void testPlayerRankDownFromLordToKnight() {
        Player player = new Player("TestPlayer", new ImageIcon("images/playerRedLord.png"), 0);
        player.setNetWorth(4000);
        player.checkPlayerRank();
        player.setNetWorth(7500);
        String result = player.checkPlayerRank();
        assertEquals("Downgraded icon to red knight from lord", result);
        
    }
}
