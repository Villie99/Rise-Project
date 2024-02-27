package test;

import static org.junit.Assert.assertEquals;

import javax.swing.ImageIcon;

import org.junit.Test;
import player.*;

public class playerImgIconOnRankUpTest {
    @Test
    public void testPlayerRankKnightBoundary() {
        Player player = new Player("TestPlayer", new ImageIcon("images/playerRedPeasant.png"), 0);
        player.setNetWorth(1999);
        player.checkPlayerRank();
        assertEquals(PlayerRanks.PEASANT, player.getPlayerRank());
    }

    @Test
    public void testPlayerRankKnightBoundaryUpper() {
        Player player = new Player("TestPlayer", new ImageIcon("images/playerRedPeasant.png"), 0);
        player.setNetWorth(2000);
        player.checkPlayerRank();
        assertEquals(PlayerRanks.KNIGHT, player.getPlayerRank());
    }

    @Test
    public void testPlayerRankLordBoundary() {
        Player player = new Player("TestPlayer", new ImageIcon("images/playerRedPeasant.png"), 0);
        player.setNetWorth(3999);
        player.checkPlayerRank();
        assertEquals(PlayerRanks.KNIGHT, player.getPlayerRank());
    }

    @Test
    public void testPlayerRankLordBoundaryUpper() {
        Player player = new Player("TestPlayer", new ImageIcon("images/playerRedPeasant.png"), 0);
        player.setNetWorth(4000);
        player.checkPlayerRank();
        assertEquals(PlayerRanks.LORD, player.getPlayerRank());
    }

    @Test
    public void testPlayerRankKingBoundary() {
        Player player = new Player("TestPlayer", new ImageIcon("images/playerRedPeasant.png"), 0);
        player.setNetWorth(7499);
        player.checkPlayerRank();
        assertEquals(PlayerRanks.LORD, player.getPlayerRank());
    }

    @Test
    public void testPlayerRankKingBoundaryUpper() {
        Player player = new Player("TestPlayer", new ImageIcon("images/playerRedPeasant.png"), 0);
        player.setNetWorth(7500);
        player.checkPlayerRank();
        assertEquals(PlayerRanks.KINGS, player.getPlayerRank());
    }

    @Test
    public void testPlayerRankNoRankChange() {
        Player player = new Player("TestPlayer", new ImageIcon("images/playerRedPeasant.png"), 0);
        player.setNetWorth(1000);
        player.checkPlayerRank();
        assertEquals(PlayerRanks.PEASANT, player.getPlayerRank());
    }

    @Test
    public void testPlayerRankKnightNoChange() {
        Player player = new Player("TestPlayer", new ImageIcon("images/playerRedPeasant.png"), 0);
        player.setNetWorth(3000);
        player.checkPlayerRank();
        assertEquals(PlayerRanks.KNIGHT, player.getPlayerRank());
    }
}
