package eastSidePanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import player.Player;
import player.PlayerList;
import tiles.Property;

/**
 * @author Muhammad Abdulkhuder Aevan Dino sebastian Viro.
 *
 */
public class PlayerProperties extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblName = new JLabel("Name");
	private JLabel lblPicture = new JLabel("");
	private JLabel lblRent = new JLabel("Rent");
	private JLabel lblRentPerLevel = new JLabel("Rent Per Level");
	private JTextArea taLevel = new JTextArea("");

	private JButton btnUpgrade = new JButton("Upgrade");
	private JButton btnDowngrade = new JButton("Downgrade");
	private JButton btnTrade = new JButton("Trade");
	private JButton btnSell = new JButton("Sell");

	private Font font = new Font("ALGERIAN", Font.BOLD, 22);
	private Font fontLevel = new Font("ALGERIAN", Font.BOLD, 50);
	private String plus = "+";
	private PlayerList playerList;
	private int playerAtI, propertyAtI;
	private EastSidePanel eastSidePanel;

	/**
	 * @param playerList
	 * @param playerAtI
	 * @param propertyAtI
	 */
	public PlayerProperties(PlayerList playerList, int playerAtI, int propertyAtI, EastSidePanel eastSidePanel) {

		this.playerList = playerList;
		this.playerAtI = playerAtI;
		this.propertyAtI = propertyAtI;
		this.eastSidePanel = eastSidePanel;

		setBorder(null);

		setOpaque(false);
		setBackground(Color.DARK_GRAY);
		setPreferredSize(new Dimension(330, 607));
		setLayout(null);

		lblRent.setForeground(Color.white);
		lblRentPerLevel.setForeground(Color.white);
		lblRent.setText(
				"The Rent is: " + playerList.getPlayerFromIndex(playerAtI).getPropertyAt(propertyAtI).getTotalRent());
		lblRentPerLevel.setText("The rent per level : "
				+ playerList.getPlayerFromIndex(playerAtI).getPropertyAt(propertyAtI).getRentPerLevel());
		lblRent.setFont(font);
		lblRentPerLevel.setFont(font);

		lblRent.setBounds(10, 92, 330, 64);
		add(lblRent);
		lblRentPerLevel.setBounds(10, 140, 330, 64);
		add(lblRentPerLevel);

		/*
		 * Upgrade Downgrade
		 * Sell Trade
		 * 
		 * x - vågärtt
		 * y - lodrätt
		 * 
		 */

		// Upgrade
		btnUpgrade.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		btnUpgrade.setBounds(-5, 400, 154, 50);
		btnUpgrade.setForeground(Color.WHITE);
		add(btnUpgrade);

		// Downgrade
		btnDowngrade.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		btnDowngrade.setBounds(145, 400, 154, 50);
		btnDowngrade.setForeground(Color.WHITE);
		add(btnDowngrade);

		// Sell
		btnSell.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		btnSell.setBounds(-5, 450, 154, 50);
		btnSell.setForeground(Color.red);
		add(btnSell);

		// Trade
		btnTrade.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		btnTrade.setBounds(145, 450, 154, 50);
		btnTrade.setForeground(Color.WHITE);
		add(btnTrade);

		taLevel.setEditable(false);
		taLevel.setBounds(46, 38, 263, 53);
		taLevel.setOpaque(false);
		taLevel.setFont(fontLevel);
		taLevel.setForeground(Color.white);
		updateLevels(playerList, playerAtI, propertyAtI);
		add(taLevel);

		lblName.setForeground(Color.white);
		lblName.setOpaque(false);
		lblName.setText(playerList.getPlayerFromIndex(playerAtI).getProperty(propertyAtI).getName());

		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(0, 11, 330, 46);
		add(lblName);
		lblName.setFont(font);
		lblPicture.setForeground(Color.WHITE);

		lblPicture.setBorder(null);
		lblPicture.setBounds(0, 0, 330, 400);
		add(lblPicture);

		lblPicture.setFont(font);
		lblPicture.setOpaque(true);
		btnDowngrade.setFont(font);
		btnUpgrade.setFont(font);
		btnTrade.setFont(font);
		btnSell.setFont(font);

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(
					playerList.getPlayerFromIndex(playerAtI).getProperty(propertyAtI).getPicture().toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image resizedImg = img.getScaledInstance(lblPicture.getWidth(), lblPicture.getHeight(), Image.SCALE_SMOOTH);

		lblPicture.setIcon(new ImageIcon(resizedImg));

		btnUpgrade.addActionListener(this);
		btnDowngrade.addActionListener(this);
		btnSell.addActionListener(this);
		btnTrade.addActionListener(this);

	}

	/**
	 * What happens when a button is pressed
	 */
	public void actionPerformed(ActionEvent e) {

		// Sell properties
		if (e.getSource() == btnSell) {
			sellProperties();

		}
		// upgrade properties
		if (e.getSource() == btnUpgrade) {
			upgradeProperties();

		}
		// downgrade properties
		if (e.getSource() == btnDowngrade) {
			downgradeProperties();

		}

		// trade with a certain player
		if (e.getSource() == btnTrade) {
			tradeProperties();
		}
	}

	/**
	 * Trades a property
	 * 
	 * @return a string that says you have traded a property.
	 * @autor Petter Carlsson.
	 */
	private void tradeProperties() {
		int otherPlayerInt = 0;
		int whichPropertyToGive = 0;
		int whichPropertyYouWant = 0;
		int offer = 0;
		int type = 0;
		int confirm;

		do {
			StringBuilder playerOptions = new StringBuilder("Which player do you want to trade with?\n");
			for (int i = 0; i < playerList.getLength(); i++) {
				playerOptions.append((i + 1) + " for " + playerList.getPlayerFromIndex(i).getName() + "\n");
			}
			playerOptions.append("Enter the player number:");

			otherPlayerInt = (Integer.parseInt(JOptionPane.showInputDialog(null, playerOptions.toString())) - 1);
		} while (otherPlayerInt == playerList.getActivePlayer().getPlayerIndex()
				|| otherPlayerInt > playerList.getLength() - 1);

		Player activePlayer = playerList.getActivePlayer();
		Player otherPlayer = playerList.getPlayerFromIndex(otherPlayerInt);

		if (otherPlayer.getProperties().size() > 0) {

			do {
				type = (Integer.parseInt(JOptionPane.showInputDialog(null,
						"Pick a trade type\n 1 = Property for property \n 2 = Money for property\n 3 = Both")));
			} while (type < 0 || type > 3);

			if (type == 1 || type == 3) {

				do {
					StringBuilder propertyOptions = new StringBuilder("Which property do you want to give away?\n");
					for (int i = 0; i < activePlayer.getProperties().size(); i++) {
						propertyOptions
								.append((i + 1) + " for " + activePlayer.getProperties().get(i).getName() + "\n");
					}
					propertyOptions.append("Enter the property number:");

					whichPropertyToGive = (Integer
							.parseInt(JOptionPane.showInputDialog(null, propertyOptions.toString())) - 1);
				} while (whichPropertyToGive < 0 || whichPropertyToGive >= activePlayer.getProperties().size());

			}

			if (type == 2 || type == 3) {
				do {
					offer = (Integer.parseInt(JOptionPane.showInputDialog(null,
							"How much do you offer " + otherPlayer.getName() + "?")));
				} while (offer > activePlayer.getBalance());

			}
			do {
				StringBuilder propertyOptions = new StringBuilder("Which property do you want?\n");
				for (int i = 0; i < otherPlayer.getProperties().size(); i++) {
					propertyOptions.append((i + 1) + " for " + otherPlayer.getProperties().get(i).getName() + "\n");
				}
				propertyOptions.append("Enter the property number:");

				whichPropertyYouWant = (Integer.parseInt(JOptionPane.showInputDialog(null, propertyOptions.toString()))
						- 1);
			} while (whichPropertyYouWant < 0 || whichPropertyYouWant >= otherPlayer.getProperties().size());

			Property activePlayerProperty = playerList.getActivePlayer().getPropertyAt(whichPropertyToGive);

			Property otherPlayersProperty = playerList.getPlayerFromIndex(otherPlayerInt)
					.getPropertyAt(whichPropertyYouWant);

			if (type == 1 || type == 3) {
				confirm = JOptionPane.showConfirmDialog(null,
						otherPlayer.getName() + " Are you okay with this trade?" + "\n You are getting " + offer
								+ " Gold coins" + "\n and are trading away " + otherPlayersProperty.getName()
								+ "\n for "
								+ activePlayerProperty.getName());

				if (confirm == 0) {

					activePlayer.removeProperty(activePlayerProperty);
					otherPlayer.removeProperty(otherPlayersProperty);

					activePlayer.decreaseBalace(offer);
					activePlayer.decreaseNetWorth(offer);

					otherPlayer.increaseBalance(offer);
					otherPlayer.increaseNetWorth(offer);

					activePlayerProperty.setOwner(otherPlayer);
					activePlayer.addNewProperty(otherPlayersProperty);

					otherPlayersProperty.setOwner(activePlayer);
					otherPlayer.addNewProperty(activePlayerProperty);

					eastSidePanel.addPlayerList(playerList);

					JOptionPane.showMessageDialog(null, "Trade Complete! Omedato gosaimasu!!!");

				}

			}

			if (type == 2) {
				confirm = JOptionPane.showConfirmDialog(null, otherPlayer.getName() + " Are you okay with this trade?"
						+ "\n You are getting " + offer + " Gold coins for " + otherPlayersProperty.getName());

				if (confirm == 0) {

					otherPlayer.removeProperty(otherPlayersProperty);
					activePlayerProperty.setOwner(otherPlayer);
					activePlayer.addNewProperty(otherPlayersProperty);

					activePlayer.decreaseBalace(offer);
					activePlayer.decreaseNetWorth(offer);

					otherPlayer.increaseBalance(offer);
					otherPlayer.increaseNetWorth(offer);

					eastSidePanel.addPlayerList(playerList);

					JOptionPane.showMessageDialog(null, "Trade Complete! Omedato gosaimasu!!!");

				}
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Trade can not be done! The player you picked does not own any properties!");
		}
	}

	/**
	 * Downgrades a property
	 * 
	 * @return a string that says you have downgraded a property.
	 * @autor Petter Carlsson.
	 */
	private String downgradeProperties() {
		Property tempProperty = playerList.getPlayerFromIndex(playerAtI).getPropertyAt(propertyAtI);
		tempProperty.decreaseLevel();
		String tempRes = taLevel.getText();

		if (tempRes.length() > tempProperty.getLevel()) {
			tempRes = tempRes.substring(0, tempRes.length() - 1);
			taLevel.setText(tempRes);
			eastSidePanel.addPlayerList(playerList);
			return "You have downgraded a property";
		}
		return "You didn't meat the requirement to downgraded a property";
	}

	/**
	 * Upgrades a property
	 * 
	 * @return a string that says you have upgraded a property.
	 * @autor Petter Carlsson.
	 */
	private String upgradeProperties() {
		Property tempProperty = playerList.getPlayerFromIndex(playerAtI).getPropertyAt(propertyAtI);
		tempProperty.increaseLevel();
		String tempRes = taLevel.getText();

		if (tempRes.length() < tempProperty.getLevel()) {
			taLevel.append(plus);
			eastSidePanel.addPlayerList(playerList);
			return "You have upgraded a property";
		}
		return "You didn't meat the requirement to upgraded a property";
	}

	/**
	 * Sells a property
	 * 
	 * @return a string that says you have sold a property.
	 * @autor Petter Carlsson.
	 */
	private String sellProperties() {
		Property tempProperty = playerList.getPlayerFromIndex(playerAtI).getPropertyAt(propertyAtI);

		playerList.getPlayerFromIndex(playerAtI).sellProperty(tempProperty);

		return "You have sold a property";
	}

	/**
	 * @param playerList
	 * @param playerIndex
	 * @param propertyIndex updates levels shown adds a plus to the picture
	 */
	public void updateLevels(PlayerList playerList, int playerIndex, int propertyIndex) {
		int lvl = playerList.getPlayerFromIndex(playerIndex).getPropertyAt(propertyIndex).getLevel();

		for (int i = 0; i < lvl; i++) {

			taLevel.append(plus);
		}

	}

	/**
	 * @param property updates levels shown adds a plus to the picture
	 */
	public void updateLevels(Property property) {
		int lvl = property.getLevel();

		for (int i = 0; i < lvl; i++) {

			taLevel.append(plus);

		}

	}

	public String getLblNameText() {
		return lblName.getText();
	}

}