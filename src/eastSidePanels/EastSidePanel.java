package eastSidePanels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

import player.Player;
import player.PlayerList;

/**
 * this class add tabs that displays informations about the players
 * in tabs
 * @author Abdulkhuder Muhammad, Sebastian Viro, Petter Carlsson.
 *
 */
public class EastSidePanel extends JPanel {

	private static final long serialVersionUID = 3397908521882247649L;
	private PlayerList playerList;
	private JTabbedPane tab;
	private PlayerInfoPanel playerInfoPnl;
	private JTextArea textAreaScores; // New label


	private int currentPlayer = 0;

	/**
	 * @param playerList
	 * this method is also used to update the information displayed
	 */
	public void addPlayerList(PlayerList playerList) {
		this.playerList = playerList;
		addtabs();
	}

	/**
	 * Draws the GUI
	 */
	public EastSidePanel() {
		setPreferredSize(new Dimension(345, 860));
		setOpaque(false);
		setLayout(null);
		UIManager.put("TabbedPane.contentOpaque", false);
		UIManager.put("TabbedPane.selected", Color.cyan);
	
		tab = new JTabbedPane();
		tab.setBounds(0, 0, 355, 660); // Increased height to make more space for lblScores
		tab.setBackground(new Color(0, 0, 0));
	
		textAreaScores = new JTextArea("Scores");
		textAreaScores.setBounds(10, 750, 450, 450); // Adjusted bounds for textAreaScores
		textAreaScores.setForeground(Color.white);
		textAreaScores.setBackground(Color.black);
		textAreaScores.setEditable(false); // Make it non-editable
		textAreaScores.setLineWrap(true); // Enable line wrapping
		textAreaScores.setWrapStyleWord(true); // Wrap at word boundaries


		add(textAreaScores);
		
	
		add(tab);


	}
	

	/**
	 * this method adds tabs according to the amount of players
	 */
	public void addtabs() {

		tab.removeAll();

		for (int i = 0; i < playerList.getLength(); i++) {
			new EastSidePanel();
			playerInfoPnl = new PlayerInfoPanel(playerList, i, this);
			playerInfoPnl.setOpaque(false);
			String name = playerList.getPlayerFromIndex(i).getName();
			tab.addTab(name, playerInfoPnl);
			tab.setOpaque(false);
		}

		tab.setSelectedIndex(currentPlayer);
		tab.setForeground(Color.white);
		tab.setBackground(new Color(157, 0, 0));
		tab.setBackgroundAt(currentPlayer, new Color(0, 157, 0));
	}

	/**
	 * this method is used to display the correct color
	 * the active players turn should be green and the others should be red.
	 */
	public void setTab() {
		tab.setBackgroundAt(currentPlayer, null);

		currentPlayer++;
		if (currentPlayer > playerList.getLength() - 1) {
			currentPlayer = 0;

			tab.setSelectedIndex(currentPlayer);
			tab.setForeground(Color.white);
			tab.setBackground(new Color(157, 0, 0));
			tab.setBackgroundAt(currentPlayer, new Color(0, 157, 0));

		} else

			tab.setSelectedIndex(currentPlayer);
		tab.setForeground(Color.white);
		tab.setBackground(new Color(157, 0, 0));
		tab.setBackgroundAt(currentPlayer, new Color(0, 157, 0));

	}
	

	public int getTab() {
		return currentPlayer;
	}

	public void setScoresText(String scoresText) {
		textAreaScores.setText(scoresText);
	}

	public void updateScores() {
		StringBuilder scoresText = new StringBuilder("Scores Leaderboard\n");
	
		for (int i = 0; i < playerList.getLength(); i++) {

			Player currentPlayer = playerList.getPlayerFromIndex(i);

			String name = currentPlayer.getNameText();

			if(currentPlayer.isPlayerInJail()){
				name = name + " (In Jail " + (5 - currentPlayer.getJailCounter()) + " turns)";
			}
			scoresText.append(name); // Add player label
			scoresText.append(": Net Worth: ").append(currentPlayer.getNetWorth()).append("\n");
			// Add any other information you want to display for each player
		}
	
		setScoresText(scoresText.toString());
	}
	
	
	

}
