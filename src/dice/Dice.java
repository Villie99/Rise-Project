package dice;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import board.Board;
import cheat.CheatGui; //May be needed for testing in future 
import eastSidePanels.EastSidePanel;
import events.ManageEvents;
import player.Player;
import player.PlayerList;
import player.PlayerRanks;
import westSidePanel.WestSidePanel;

/**
 * @author Muhammad Abdulkhuder, Aevan Dino, Sebastian Viro, Seth Oberg
 *
 */
public class Dice extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private ShowPlayersTurn showPlayersTurn;
	private Board board;
	private PlayerList playerList;
	private WestSidePanel westSidePnl;
	private EastSidePanel eastSidePnl = new EastSidePanel();

	private Thread movePlayerThread;
	private ManageEvents manageEvents;

	private JButton btnEndTurn = new JButton("End Turn");
	private JButton btnRollDice = new JButton("Roll Dice");

	private JLabel lblDice1 = new JLabel();
	private JLabel lblDice2 = new JLabel();

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private ImageIcon faceToShow, showDice;
	private Image resizedImage;

	private int diceWidth = (screenSize.width) / 20;
	private int diceHeight = (screenSize.height) / 10;
	private int roll;

	private int determinism = 3; // Important for testing purposes
	private boolean testing = false; // Important for testing purposes

	/**
	 * @param playerList method used for updating the list of players
	 */
	public void addPlayerList(PlayerList playerList) {
		updateScores();

		this.playerList = playerList;

		showPlayersTurn.uppdateGUI(playerList.getActivePlayer().getName(),
				playerList.getActivePlayer().getPlayerColor());

		manageEvents = new ManageEvents(board, playerList, westSidePnl, this, eastSidePnl);

	}

	/**
	 * @param board         The board object
	 * @param playerList    a list containing all the players in the game
	 * @param westSidePanel panel containing all the information about the tiles and
	 *                      the history of all the events
	 * @param eastSidePnl   panel containing all the information about the players
	 *                      and their properties
	 */
	public Dice(Board board, PlayerList playerList, WestSidePanel westSidePanel, EastSidePanel eastSidePnl) {
		this.board = board;
		this.playerList = playerList;
		this.westSidePnl = westSidePanel;
		this.eastSidePnl = eastSidePnl;

		initializePanel();

	}

	/**
	 * calls the method that initializes the panel
	 */
	public Dice() {

		initializePanel();

	}

	public Dice(PlayerList playerList) { // for testing important
		this.playerList = playerList;
		westSidePnl = new WestSidePanel();
	}

	/**
	 * initializes Panel
	 */
	public void initializePanel() {

		setPreferredSize(new Dimension(650, 120));
		setLayout(new FlowLayout());
		setOpaque(false);

		showPlayersTurn = new ShowPlayersTurn("Player");
		add(showPlayersTurn);

		add(lblDice1);

		add(lblDice2);

		btnRollDice.setFont(new Font("Algerian", Font.PLAIN, 14));
		add(btnRollDice);

		btnRollDice.addActionListener(this);

		faceToShow = new ImageIcon("DicePictures/faceValue1White.png");
		resizedImage = faceToShow.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH);
		showDice = new ImageIcon(resizedImage);
		lblDice2.setIcon(showDice);
		lblDice1.setIcon(showDice);

		btnEndTurn.setFont(new Font("Algerian", Font.PLAIN, 14));
		btnEndTurn.addActionListener(this);

		add(btnEndTurn);
		btnEndTurn.setEnabled(false);

	}

	/**
	 * Action Listener that handles what happens if the buttons are pressed
	 */
	public void actionPerformed(ActionEvent e) {
		updateScores();

		if (e.getSource() == btnRollDice) {
			int faceValueDiceOne = (int) (Math.random() * (7 - 1) + 1);
			int faceValueDiceTwo = (int) (Math.random() * (7 - 1) + 1);

			playerList.getActivePlayer().checkPlayerRank();

			switch (faceValueDiceOne) {
				case 1:
					faceToShow = new ImageIcon("DicePictures/faceValue1White.png");
					break;

				case 2:
					faceToShow = new ImageIcon("DicePictures/faceValue2White.png");
					break;

				case 3:
					faceToShow = new ImageIcon("DicePictures/faceValue3White.png");
					break;

				case 4:
					faceToShow = new ImageIcon("DicePictures/faceValue4White.png");
					break;

				case 5:
					faceToShow = new ImageIcon("DicePictures/faceValue5White.png");
					break;

				case 6:
					faceToShow = new ImageIcon("DicePictures/faceValue6White.png");
					break;
			}

			resizedImage = faceToShow.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH);
			showDice = new ImageIcon(resizedImage);
			lblDice1.setIcon(showDice);

			switch (faceValueDiceTwo) {
				case 1:
					faceToShow = new ImageIcon("DicePictures/faceValue1White.png");
					break;

				case 2:
					faceToShow = new ImageIcon("DicePictures/faceValue2White.png");
					break;

				case 3:
					faceToShow = new ImageIcon("DicePictures/faceValue3White.png");
					break;

				case 4:
					faceToShow = new ImageIcon("DicePictures/faceValue4White.png");
					break;

				case 5:
					faceToShow = new ImageIcon("DicePictures/faceValue5White.png");
					break;

				case 6:
					faceToShow = new ImageIcon("DicePictures/faceValue6White.png");
					break;
			}

			// for testing purposes, affects the code only if instance variable Determinism
			// is initialized less than 2
			if (determinism < 2) {
				faceValueDiceOne = 25;
				faceValueDiceTwo = 5;
				determinism++;
			}

			/**
			 * This if statement checks if the player is in jail
			 * and gives them a chance to play out of jail
			 * If scoring equals on the dices the jailcounter will be set to zero and jail
			 * status to false
			 */
			if (playerList.getActivePlayer().isPlayerInJail() == true) {
				resizedImage = faceToShow.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH);
				showDice = new ImageIcon(resizedImage);
				lblDice2.setIcon(showDice);

				tryPlayOutOfJail(faceValueDiceOne, faceValueDiceTwo);

				if (!playerList.getActivePlayer().isPlayerInJail()) {
					activateRollDice();
				}

			} else {

				if (faceValueDiceOne == faceValueDiceTwo) {
					setRoll(((faceValueDiceOne + faceValueDiceTwo) * 2));
					westSidePnl
							.append(playerList.getActivePlayer().getName() + " Rolled a dubble: " + getRoll() + "\n");
				} else {
					setRoll(((faceValueDiceOne + faceValueDiceTwo)));
					westSidePnl.append(playerList.getActivePlayer().getName() + " Rolled a: " + getRoll() + "\n");
				}

				resizedImage = faceToShow.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH);
				showDice = new ImageIcon(resizedImage);
				lblDice2.setIcon(showDice);

				playerList.getActivePlayer().checkPlayerRank();
				manageEvents.setRoll(this);

				movePlayerThread = new Thread(new LoopThread(getRoll()));
				movePlayerThread.start();

				goEvent();

				eastSidePnl.addPlayerList(playerList);

				btnRollDice.setEnabled(false);
			}

		}

		/**
		 * When a player ends their turn
		 * If the next player is in jail they will not have the ability to roll the
		 * dice and will only have the ability to end their turn if they have not paid
		 * the bail
		 * If the player is not in jail they can roll the dice
		 */
		if (e.getSource() == btnEndTurn) {
			updateScores();
			// Player activePlayer = playerList.getActivePlayer();
			playerList.getActivePlayer().checkPlayerRank();
			playerList.getActivePlayer().rankIconChange(playerList.getActivePlayer().getPlayerRank());

			playerList.switchToNextPlayer();

			showPlayersTurn.uppdateGUI(playerList.getActivePlayer().getName(),
					playerList.getActivePlayer().getPlayerColor());

			if (playerList.getActivePlayer().isPlayerInJail() == true) {
				btnRollDice.setEnabled(false);
				btnEndTurn.setEnabled(true);
				manageEvents.newEvent(board.getDestinationTile(playerList.getActivePlayer().getPosition()),
						playerList.getActivePlayer());
			}

			else if (playerList.getActivePlayer().isPlayerInJail() == false) {
				btnRollDice.setEnabled(true);
				btnEndTurn.setEnabled(false);
			}

			eastSidePnl.addPlayerList(playerList);
			eastSidePnl.setTab();
		}

	}

	public void tryPlayOutOfJail(int faceValueDiceOne, int faceValueDiceTwo) {

		if (faceValueDiceOne == faceValueDiceTwo) {
			westSidePnl.append(playerList.getActivePlayer().getName() + " you got FREE from jail!\n");
			playerList.getActivePlayer().setPlayerIsInJail(false);
			playerList.getActivePlayer().setJailCounter(0);
		}
		if (faceValueDiceOne != faceValueDiceTwo) {
			westSidePnl.append(playerList.getActivePlayer().getName() + " did NOT hit equals: remains in jail\n");
		}

		btnRollDice.setEnabled(false);
		btnEndTurn.setEnabled(true);

	}

	/**
	 * @param Cheat method used for Testing
	 *              it moves the player to a specific index
	 */
	public void moveWCheat(int i) {

		setRoll(i);
		playerList.getActivePlayer().checkPlayerRank();
		board.removePlayer(playerList.getActivePlayer());
		playerList.getActivePlayer().setPosition(getRoll());
		board.setPlayer(playerList.getActivePlayer());

		manageEvents.setRoll(this);
		goEvent();
		manageEvents.newEvent(board.getDestinationTile(playerList.getActivePlayer().getPosition()),
				playerList.getActivePlayer());
		eastSidePnl.addPlayerList(playerList);
	}

	/**
	 * To free the prisoner
	 */
	public void activateRollDice() {
		updateScores();

		btnRollDice.setEnabled(true);
		btnEndTurn.setEnabled(false);
	}

	/**
	 * Ends the turn if player is eliminated
	 */
	public void endTurnIfPlayerEliminated() {
		updateScores();

		btnRollDice.setEnabled(true);
		btnEndTurn.setEnabled(false);
	}

	/**
	 * @param playerList
	 */
	public void setPlayerList(PlayerList playerList) {
		this.playerList = playerList;

		updateScores();

	}

	/**
	 * @return number of total roll
	 */
	public int getRoll() {
		return roll;
	}

	/**
	 * @param sets number of total roll
	 */
	public void setRoll(int roll) {
		this.roll = roll;
	}

	/**
	 * @author Seth �berg, Muhammad Abdulkhuder
	 *         Moves the player with a thread.
	 *
	 */
	private class LoopThread implements Runnable {
		public LoopThread(int index) {
			setRoll(index);
		}

		public void run() {
			updateScores();

			for (int i = 0; i < getRoll(); i++) {
				board.removePlayer(playerList.getActivePlayer());
				playerList.getActivePlayer().setPosition(1);
				board.setPlayer(playerList.getActivePlayer());

				if (i == (getRoll() - 1)) {
					manageEvents.newEvent(board.getDestinationTile(playerList.getActivePlayer().getPosition()),
							playerList.getActivePlayer());
					eastSidePnl.addPlayerList(playerList);
					btnEndTurn.setEnabled(true);

				}

				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}

	/**
	 * If a player passes go.
	 */
	private void goEvent() {

		if (playerList.getActivePlayer().passedGo()) {

			playerList.getActivePlayer().increaseBalance(200);
			playerList.getActivePlayer().increaseNetWorth(200);

			westSidePnl.append("Passed Go and received 200 GC\n");
			playerList.getActivePlayer().resetPassedGo();
		}
	}

	/**
	 * Score equals on the dice to get out of jail
	 */
	public void attemptToGetOutOfJail() {

		westSidePnl.append(playerList.getActivePlayer().getName() + " try to hit equals so you can escape jail\n");

		btnRollDice.setEnabled(true);
		btnEndTurn.setEnabled(false);

	}

	public void setWestPanel(WestSidePanel westSidePnl) {
		this.westSidePnl = westSidePnl;
	}

	public void updateScores() {
		if (!testing) {
			playerList = eastSidePnl.getPlayerList();

		}

		StringBuilder scoresText = new StringBuilder("Scores Leaderboard\n");

		for (int i = 0; i < playerList.getLength(); i++) {

			Player currentPlayer = playerList.getPlayerFromIndex(i);

			String name = currentPlayer.getNameText();

			if (currentPlayer.isPlayerInJail()) {
				name = name + " (In Jail " + (5 - currentPlayer.getJailCounter()) + " turns)";
			}
			scoresText.append(name); // Add player label
			scoresText.append(": Net Worth: ").append(currentPlayer.getNetWorth()).append("\n");
			// Add any other information you want to display for each player
		}

		westSidePnl.setScoresText(scoresText.toString());
	}

	public PlayerList getPlayerList() { // Important for testing purposes

		return playerList;

	}

	public void setTesting(boolean testing) { // Important for testing purposes
		this.testing = testing;
	}

}