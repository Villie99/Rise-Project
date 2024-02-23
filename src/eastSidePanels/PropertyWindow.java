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

/**
 * @author Muhammad Abdulkhuder, Aevan Dino.
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

		tab.removeAll();

		tab.setForeground(Color.white);

		Player currPlayer = playerList.getPlayerFromIndex(getPlayerAt());

		for (int i =0 ; i< currPlayer.getAmountOfTaverns(); i++){
			currPlayer.addNewProperty(new Property(true));
		}

		size = new int[currPlayer.getProperties().size()];
		

		for (int i = 0; i < size.length; i++) {

			new PropertyWindow();
			playerProperties = new PlayerProperties(playerList, getPlayerAt(), i, eastSidePanel);
			String nameText = playerProperties.getLblNameText();

			tab.addTab(nameText, playerProperties);
			tab.setBackgroundAt(i, playerList.getPlayerFromIndex(getPlayerAt()).getProperty(i).getColor());

		}

		ArrayList<Property> properties = playerList.getPlayerFromIndex(getPlayerAt()).getProperties();
		for (int i =0 ; i < properties.size(); i++){
			if(properties.get(i).isTavern()){
				properties.remove(i);
			}
		}
		currPlayer.setProperties(properties);

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

}
