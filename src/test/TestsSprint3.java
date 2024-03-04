package test;

import static org.junit.Assert.assertEquals;

import javax.swing.ImageIcon;

import org.junit.Test;

import dice.Dice;
import player.*;


public class TestsSprint3 {

    @Test
    public void TF45samePlayer() {
        PlayerList playerList = new PlayerList();
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.getActivePlayer();

        Dice dice = new Dice(playerList);

        dice.tryPlayOutOfJail(3, 3);

        assertEquals(dice.getPlayerList().getActivePlayer(), playerList.getActivePlayer());
    }

    @Test
    public void TF45jailHitEqualsToGetOut() {
        PlayerList playerList = new PlayerList();
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.getActivePlayer().setPlayerIsInJail(true);;

        Dice dice = new Dice(playerList);

        dice.tryPlayOutOfJail(3, 3);

        assertEquals(false, dice.getPlayerList().getActivePlayer().isPlayerInJail());
    }

    @Test
    public void TF45jailDidNotHitEqualsRemainsInstitutionalized() {
        PlayerList playerList = new PlayerList();
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.getActivePlayer().setPlayerIsInJail(true);;

        Dice dice = new Dice(playerList);

        dice.tryPlayOutOfJail(6, 3);

        assertEquals(true, dice.getPlayerList().getActivePlayer().isPlayerInJail());
    }

    
}
