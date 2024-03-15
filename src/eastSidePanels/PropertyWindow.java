package eastSidePanels;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import player.Player;
import player.PlayerList;
import tiles.Property;
import tiles.Tavern;

/**
 * @author Muhammad Abdulkhuder, Aevan Dino, Brunillda Maloku.
 */
public class PropertyWindow extends JPanel {

	
	private static final long serialVersionUID = 1L;
	private PlayerList playerList;
	private JTabbedPane tab;
	private PlayerProperties playerProperties;
	private EastSidePanel eastSidePanel;

	private int playerAt;

	private int[] size;

	/**
	 * @param eastSidePanel
	 * 
	 *this method is used to update the panel
	 */
	
	public PropertyWindow(EastSidePanel eastSidePanel) {

		this.eastSidePanel = eastSidePanel;

		setPreferredSize(new Dimension(330, 600));
		setOpaque(false);
		setLayout(null);
		UIManager.put("TabbedPane.contentOpaque", false);
		UIManager.put("TabbedPane.selected", Color.cyan);

		tab = new JTabbedPane();
		tab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tab.setBorder(null);
		tab.setBounds(0, 0, 330, 600);
		add(tab);

	}

	
	public void addPlayerList(PlayerList playerList) {

		this.playerList = playerList;
		addtabs();

	}

	/**
	 * this method loops the amount of players and adds tabs according to the number of 
	 * properties
	 */
	public PropertyWindow() {

		setPreferredSize(new Dimension(330, 600));
		setOpaque(false);
		setLayout(null);
		UIManager.put("TabbedPane.contentOpaque", false);
		UIManager.put("TabbedPane.selected", Color.cyan);

		tab = new JTabbedPane();
		tab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tab.setBorder(null);
		tab.setBounds(0, 0, 330, 600);
		add(tab);

	}


	/**
	 * this method loops the amount of players and adds tabs according to the number of 
	 * properties
	 */
	public void addtabs() {

		Player currPlayer = playerList.getPlayerFromIndex(getPlayerAt());

		if (currPlayer.isUpdateTavernNbr()) {
			currPlayer.setUpdateTavernNbr(false);
			addTav();
		}

		tab.removeAll();

		tab.setForeground(Color.white);


		ArrayList<Property> properties = currPlayer.getProperties();
		
		for (int i = 0; i < properties.size(); i++) {

			new PropertyWindow();
			playerProperties = new PlayerProperties(playerList, getPlayerAt(), i, eastSidePanel);
			String nameText = playerProperties.getLblNameText();

			tab.addTab(nameText, playerProperties);
			tab.setBackgroundAt(i, properties.get(i).getColor());
		}

	}

	/**
	 * This method adds new Taverns to the PropertyWindow list of properties.
	 */

	public void addTav() {

		tab.removeAll();

		tab.setForeground(Color.white);

		Player currPlayer = playerList.getPlayerFromIndex(getPlayerAt());

		ArrayList<Property> properties = currPlayer.getProperties();
		ArrayList<Tavern> taverns = currPlayer.getTaverns();

		for (int i = 0 ; i < currPlayer.getOwned(); i++){
			properties.add(new Property(true));
			properties.get(i).setName(taverns.get(i).getName());
		}

		for (int i = 0; i < properties.size(); i++) {
			new PropertyWindow();
			playerProperties = new PlayerProperties(playerList, getPlayerAt(), i, eastSidePanel);
			String nameText = playerProperties.getLblNameText();

			tab.addTab(nameText, playerProperties);
			tab.setBackgroundAt(i, properties.get(i).getColor());
		}

	}

	public JTabbedPane getTab() {
		return tab;
	}

	/**
	 * @return playerAT
	 */
	public int getPlayerAt() {
		return playerAt;
	} 

	/**
	 * @param playerAt
	 */
	public void setPlayerAt(int playerAt) {
		this.playerAt = playerAt;
	}

	public EastSidePanel getEastSidePanel() {
		return eastSidePanel;
	}

	public PlayerProperties getPlayerProperties() {
		return playerProperties;
	}
	


}
