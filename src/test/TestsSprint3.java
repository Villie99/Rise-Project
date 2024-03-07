package test;

import static org.junit.Assert.assertEquals;

import javax.swing.ImageIcon;

import org.junit.Test;

import dice.Dice;
import eastSidePanels.EastSidePanel;
import eastSidePanels.PlayerInfoPanel;
import eastSidePanels.PropertyWindow;
import player.PlayerList;
import westSidePanel.WestSidePanel;


public class TestsSprint3 {

    @Test
    public void TF44testGetSet() {
        WestSidePanel westSidePanel = new WestSidePanel();
        westSidePanel.setScoresText("Test");
        assertEquals("Test", westSidePanel.getScoresText());
    }

    @Test
    public void TF44updateScores() {
        //Create stubs for player list
        PlayerList playerList = new PlayerList();
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));

        //create dice object and set testing conding to true in order to avoid avoid drawing the playerList from the eastSidePanel
        Dice dice = new Dice(playerList);
        dice.setTesting(true);

        //create a westSidePanel and set it to the dice object
        WestSidePanel westSidePanel = new WestSidePanel();
        dice.setWestPanel(westSidePanel);

        //input a player list and test the method update scores
        dice.setPlayerList(playerList);
        dice.updateScores();

        //the result of update scores should be stored in the westSidePanel class
        assertEquals("Scores Leaderboard\nTestPlayer: "+
        "Net Worth: 1500\nTestPlayer: Net Worth: 1500\n",
        westSidePanel.getScoresText());
    }

    @Test
    public void TF44updateScores_Modified_Net_Worth() {
        //Create stubs for player list
        PlayerList playerList = new PlayerList();
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));

        //create dice object and set testing conding to true in order to avoid avoid drawing the playerList from the eastSidePanel
        Dice dice = new Dice(playerList);
        dice.setTesting(true);

        //create a westSidePanel and set it to the dice object
        WestSidePanel westSidePanel = new WestSidePanel();
        dice.setWestPanel(westSidePanel);

        //modify a player's net worth
        playerList.getActivePlayer().setNetWorth(2000);

        //input a player list and test the method update scores
        dice.setPlayerList(playerList);
        dice.updateScores();

        //the result of update scores should be stored in the westSidePanel class
        assertEquals("Scores Leaderboard\nTestPlayer: "+
        "Net Worth: 2000\nTestPlayer: Net Worth: 1500\n",
        westSidePanel.getScoresText());
    }

    @Test
    public void TF44updateScores_No_Players() {
        //Create stubs for player list
        PlayerList playerList = new PlayerList();

        //create dice object and set testing conding to true in order to avoid avoid drawing the playerList from the eastSidePanel
        Dice dice = new Dice(playerList);
        dice.setTesting(true);

        //create a westSidePanel and set it to the dice object
        WestSidePanel westSidePanel = new WestSidePanel();
        dice.setWestPanel(westSidePanel);

        //input a player list and test the method update scores
        dice.setPlayerList(playerList);
        dice.updateScores();

        //the result of update scores should be stored in the westSidePanel class
        assertEquals("Scores Leaderboard\n",
        westSidePanel.getScoresText());
    }

    @Test
    public void TF44updateScores_Four_Players_Modified_Net_Worth() {
        //Create stubs for player list
        PlayerList playerList = new PlayerList();
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));

        //create dice object and set testing conding to true in order to avoid avoid drawing the playerList from the eastSidePanel
        Dice dice = new Dice(playerList);
        dice.setTesting(true);

        //create a westSidePanel and set it to the dice object
        WestSidePanel westSidePanel = new WestSidePanel();
        dice.setWestPanel(westSidePanel);

        //modify a player's net worth
        playerList.getActivePlayer().setNetWorth(2000);

        //input a player list and test the method update scores
        dice.setPlayerList(playerList);
        dice.updateScores();

        //the result of update scores should be stored in the westSidePanel class
        assertEquals("Scores Leaderboard\nTestPlayer: "+
        "Net Worth: 2000\nTestPlayer: Net Worth: 1500\n" +
        "TestPlayer: Net Worth: 1500\nTestPlayer: Net Worth: 1500\n",
        westSidePanel.getScoresText());
    }


    @Test
    public void TF44updateScores_Four_Players() {
        //Create stubs for player list
        PlayerList playerList = new PlayerList();
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));

        //create dice object and set testing conding to true in order to avoid avoid drawing the playerList from the eastSidePanel
        Dice dice = new Dice(playerList);
        dice.setTesting(true);

        //create a westSidePanel and set it to the dice object
        WestSidePanel westSidePanel = new WestSidePanel();
        dice.setWestPanel(westSidePanel);

        //input a player list and test the method update scores
        dice.setPlayerList(playerList);
        dice.updateScores();

        //the result of update scores should be stored in the westSidePanel class
        assertEquals("Scores Leaderboard\nTestPlayer: "+
        "Net Worth: 1500\nTestPlayer: Net Worth: 1500\n" +
        "TestPlayer: Net Worth: 1500\nTestPlayer: Net Worth: 1500\n",
        westSidePanel.getScoresText());
    }
    


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
    public void TF45jailHitEqualsToGetOut_1() {
        PlayerList playerList = new PlayerList();
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.getActivePlayer().setPlayerIsInJail(true);;

        Dice dice = new Dice(playerList);

        dice.tryPlayOutOfJail(1, 1);

        assertEquals(false, dice.getPlayerList().getActivePlayer().isPlayerInJail());
    }

    @Test
    public void TF45jailHitEqualsToGetOut_2() {
        PlayerList playerList = new PlayerList();
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.getActivePlayer().setPlayerIsInJail(true);;

        Dice dice = new Dice(playerList);

        dice.tryPlayOutOfJail(3, 3);

        assertEquals(false, dice.getPlayerList().getActivePlayer().isPlayerInJail());
    }

    @Test
    public void TF45jailHitEqualsToGetOut_3() {
        PlayerList playerList = new PlayerList();
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.getActivePlayer().setPlayerIsInJail(true);;

        Dice dice = new Dice(playerList);

        dice.tryPlayOutOfJail(6, 6);

        assertEquals(false, dice.getPlayerList().getActivePlayer().isPlayerInJail());
    }

    @Test
    public void TF45jailDidNotHitEqualsRemainsInstitutionalized_1() {
        PlayerList playerList = new PlayerList();
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.getActivePlayer().setPlayerIsInJail(true);;

        Dice dice = new Dice(playerList);

        dice.tryPlayOutOfJail(6, 1);

        assertEquals(true, dice.getPlayerList().getActivePlayer().isPlayerInJail());
    }

    @Test
    public void TF45jailDidNotHitEqualsRemainsInstitutionalized_2() {
        PlayerList playerList = new PlayerList();
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.getActivePlayer().setPlayerIsInJail(true);;

        Dice dice = new Dice(playerList);

        dice.tryPlayOutOfJail(1, 6);

        assertEquals(true, dice.getPlayerList().getActivePlayer().isPlayerInJail());
    }

    @Test
    public void TF45jailDidNotHitEqualsRemainsInstitutionalized_3() {
        PlayerList playerList = new PlayerList();
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));
        playerList.getActivePlayer().setPlayerIsInJail(true);;

        Dice dice = new Dice(playerList);

        dice.tryPlayOutOfJail(3, 4);

        assertEquals(true, dice.getPlayerList().getActivePlayer().isPlayerInJail());
    }

    @Test
    public void TF46_playerName_shows_eastSidePanel() {
        PlayerList playerList = new PlayerList();
        playerList.addNewPlayer("TestPlayer", new ImageIcon("images/playerRedPeasant.png"));

        EastSidePanel eastSidePanel = new EastSidePanel();
        PlayerInfoPanel playerInfoPanel  = eastSidePanel.TabTest(playerList);
    
        assertEquals(playerInfoPanel.getPlayerNameText(), "TESTPLAYER");
    }


    
}
