package test;

import static org.junit.Assert.assertEquals;

import javax.swing.ImageIcon;

import org.junit.Test;
import player.*;

public class playerImgIconOnRankUpTest {
    @Test
    public void testPlayerRankUpToKnightFromPeasant() {
        Player player = new Player("TestPlayer", new ImageIcon("images/playerRedPeasant.png"), 0);
        player.setNetWorth(2000);
        String result = player.checkPlayerRank();
        assertEquals("Upgraded icon to red knight from peasant", result);
    }

    @Test
    public void testPlayerRankUpToLordFromKnight() {
        Player player = new Player("TestPlayer", new ImageIcon("images/playerRedPeasant.png"), 0);
        player.setNetWorth(2000);
        player.checkPlayerRank();
        player.setNetWorth(4000);
        String result = player.checkPlayerRank();
        assertEquals("Upgraded icon to red lord from knight", result);
    }

    @Test
    public void testPlayerRankUpToKingFromLord() {
        Player player = new Player("TestPlayer", new ImageIcon("images/playerRedPeasant.png"), 0);
        player.setNetWorth(2000);
        player.checkPlayerRank();
        player.setNetWorth(4000);
        player.checkPlayerRank();
        player.setNetWorth(7500);
        String result = player.checkPlayerRank();
        assertEquals("Upgraded icon to red king from lord", result);
    }    
}
