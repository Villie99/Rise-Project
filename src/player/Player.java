package player;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import eastSidePanels.PropertyWindow;
import messageGui.WinGui;
import tiles.Property;
import tiles.Tavern;
import tiles.Tile;

/**
 * Player class deals with everything that has to do with a player.
 * 
 * @author AevanDino, Seth �berg, Muhammad Hasan, Sebastian Viro, Petter
 *         Carlsson, Villie Brandt
 */
public class Player {

    private String name;
    private Boolean isAlive;

    private ImageIcon playerIcon;
    private int counter;
    private int playerIndex;
    private int playerJailCounter = 0;
    private boolean playerIsInJail = false;
    private Color playerColor;

    private PlayerRanks playerRank;

    private int balance;
    private int netWorth;
    private boolean playerPassedgo = false;

    private ArrayList<Property> propertiesOwned;
    private ArrayList<Tile> tilesOwned;

    private ArrayList<Tavern> tavernsOwned;
    private int owned = 0;
    private boolean updateTavernNbr = false;

    /**
     * Constructor for adding a new player, new players are created by the
     * playerList class and are automatically set at index 0 on the board with the
     * counter variable set to 0
     * 
     * @param inPlayerName chosen Name
     * @param playerIcon   imageIcon from ColorIconMap
     * @param playerIndex  index of player (for example if second player the
     *                     playerIndex is 1)
     */

    public Player(String inPlayerName, ImageIcon playerIcon, int playerIndex) {

        setName(inPlayerName);
        this.playerIcon = playerIcon;
        setIsAlive(true);
        this.playerIndex = playerIndex;

        setBalance(1500);
        setNetWorth(1500);
        setPlayerRank(playerRank.PEASANT);
        this.playerIndex = playerIndex;
        this.tavernsOwned = new ArrayList<>();
        this.propertiesOwned = new ArrayList<>();

        counter = 0;
    }

    public Player(String inPlayerName, ImageIcon playerIcon, Color playerColor, int playerIndex) {
        this.playerColor = playerColor;
        setName(inPlayerName);
        this.playerIcon = playerIcon;
        setIsAlive(true);
        this.playerIndex = playerIndex;

        setBalance(1500);
        setNetWorth(1500);
        setPlayerRank(playerRank.PEASANT);
        this.playerIndex = playerIndex;
        this.tavernsOwned = new ArrayList<>();
        this.propertiesOwned = new ArrayList<>();

        counter = 0;
    }

    /**
     * Keep track of how many turns a user has been in jail, if 3 the player gets
     * out of jail if less than 3 the "roll dice" button is to be inactivated and
     * the end turn activated
     * 
     * @return playerJailCounter
     */
    public int getJailCounter() {
        return playerJailCounter;
    }

    /**
     * method used for increasing or resetting the jailCounter of a player
     * 
     * @param amount
     */
    public void setJailCounter(int amount) {
        this.playerJailCounter = amount;
    }

    /**
     * Increase number of turns spent in jail by one
     */
    public void increaseJailCounter() {
        this.playerJailCounter++;
    }

    /**
     * @param isInJail if player is sent to jail send true, if player is not in jail
     *                 anymore set to false
     */
    public void setPlayerIsInJail(boolean isInJail) {
        this.playerIsInJail = isInJail;
    }

    /**
     * @return Return either true or false if player in in jail or not
     */
    public Boolean isPlayerInJail() {
        return this.playerIsInJail;
    }

    /**
     * @return name, the players name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param playerName, the player name to set
     */
    public void setName(String playerName) {
        this.name = playerName;
    }

    /**
     * Set the playerIndex of the player (the index the user has in the playerList
     * array)
     * 
     * @param index
     */
    public void setPlayerIndex(int index) {
        this.playerIndex = index;
    }

    /**
     * @return the playerIndex of a player
     */
    public int getPlayerIndex() {
        return playerIndex;
    }

    /**
     * Get the position a player has on the board from 0-39
     * 
     * @return counter
     */
    public int getPosition() {
        return counter;
    }

    /**
     * Move player to a specific index on the board
     * 
     * @param newPosition
     */
    public void setPositionInSpecificIndex(int newPosition) {
        this.counter = newPosition;
    }

    /**
     * method used to move the player by either one or many steps
     * 
     * @param amountOfStepsToMove
     */
    public void setPosition(int amountOfStepsToMove) {

        for (int i = 0; i < amountOfStepsToMove; i++) {

            if (counter < 39) {
                counter++;
            } else {
                counter = 0;
                playerPassedgo = true;
            }
        }
    }

    /**
     * @return playerPassedgo, boolean to keep track if user has passed go
     */
    public boolean passedGo() {
        return playerPassedgo;
    }

    /**
     * reset has passedGo variable to false
     */
    public void resetPassedGo() {
        playerPassedgo = false;
    }

    /**
     * @return balance, the player balance
     */
    public int getBalance() {
        return this.balance;
    }

    /**
     * @return playerIcon, the image of a player
     */
    public ImageIcon getImage() {
        return playerIcon;
    }

    /**
     * @param icon, the image of a player
     * @return playerIcon, the image of a player
     */
    public ImageIcon setIcon(ImageIcon icon) {
        return this.playerIcon = icon;
    }

    /**
     * @param playerBalance the playerBalance to set
     */
    public void setBalance(int playerBalance) {
        this.balance = playerBalance;
    }

    /**
     * @param decrease amount to decrease players balance by
     */
    public void decreaseBalace(int decrease) {
        this.balance -= decrease;
    }

    /**
     * @param income increase players balance by an amount
     */
    public void increaseBalance(int income) {
        this.balance += income;
    }

    /**
     * @return the playerIsAlive
     */
    public Boolean isAlive() {
        return isAlive;
    }

    /**
     * @param playerIsAlive the playerIsAlive to set
     */
    public void setIsAlive(Boolean playerIsAlive) {
        this.isAlive = playerIsAlive;
    }

    public String isAliveString() {

        if (isAlive == true) {
            return "This player is alive and well";
        } else
            return "The plauge has taken another soul";
    }

    /**
     * @return playerRank the rank of the player
     */
    public PlayerRanks getPlayerRank() {
        return this.playerRank;
    }

    /**
     * @param playerRank set the rank of this player
     */
    public void setPlayerRank(PlayerRanks playerRank) {
        this.playerRank = playerRank;
    }

    /**
     * @return the netWorth
     */
    public int getNetWorth() {
        return this.netWorth;
    }

    /**
     * @param netWorth the netWorth to set
     */
    public void setNetWorth(int netWorth) {
        this.netWorth = netWorth;
    }

    /**
     * @param decrease
     */
    public void decreaseNetWorth(int decrease) {
        this.netWorth -= decrease;
    }

    /**
     * @param income
     */
    public void increaseNetWorth(int income) {
        this.netWorth += income;
    }

    /**
     * Adds newly purchased property to ownedProperties array
     * 
     * @param newProperty, the newly bought property.
     */
    public void addNewProperty(Property newProperty) {
        this.propertiesOwned.add(newProperty);
    }

    public void removeProperty(Property property) {

        this.propertiesOwned.remove(property);
        property.setOwner(null);

    }

    public void sellProperty(Property property) {

        int total = (property.getPrice() + (property.getLevel() * property.getLevelPrice()));

        int res = JOptionPane.showConfirmDialog(null,
                "Do you really want to sell " + property.getName() + " for: " + total);

        if (res == 0) {
            increaseBalance(total);
            this.propertiesOwned.remove(property);
            property.setOwner(null);
            property.clearProperty();
        }

    }

    public void addNewTavern(Tavern newTavern) {
        updateTavernNbr = true;
        owned++;
        this.tavernsOwned.add(newTavern);
    }

    public int getOwned() {
        System.out.println("player" + name + " OWNED Tavs " + owned);
        return owned;
    }

    /**
     * If user has two taverns the event will differ
     * 
     * @return amount of taverns
     */
    public int getAmountOfTaverns() {
        return tavernsOwned.size();
    }

    /**
     * If user is eliminated reset all users properties and taverns by setting the
     * amount of houses to 0 and remove the owner
     */
    public void clearPlayer() {
        for (int i = 0; i < propertiesOwned.size(); i++) {
            propertiesOwned.get(i).clearProperty();

        }
        for (int i = 0; i < tavernsOwned.size(); i++) {
            tavernsOwned.get(i).clearTavern();
        }
    }

    public Property getPropertyAt(int pos) {

        return this.propertiesOwned.get(pos);
    }

    /**
     * Gets property at specified position
     * 
     * @param pos
     * @return
     * 
     */
    public Tile getProperty(int pos) {
        return this.propertiesOwned.get(pos);
    }

    /**
     * Updates player rank
     * 
     * @author Petter Carlsson & Villie Brandt
     */
    public String checkPlayerRank() {

        int netWorth = getNetWorth();

        if (netWorth >= 2000 && netWorth < 4000) {
            setPlayerRank(PlayerRanks.KNIGHT);
            return rankIconChange(PlayerRanks.KNIGHT);
        }

        if (netWorth >= 4000 && netWorth < 7500) {
            setPlayerRank(PlayerRanks.LORD);
            return rankIconChange(PlayerRanks.LORD);
        }
        if (netWorth >= 7500) {
            setPlayerRank(PlayerRanks.KINGS);
            return rankIconChange(PlayerRanks.KINGS);
        }

        if (netWorth < 2000) {
            setPlayerRank(PlayerRanks.PEASANT);
            return rankIconChange(PlayerRanks.PEASANT);
        }

        return "Nothing happened";
    }

    /**
     * Upgrades or downgrades the player icon based on the specified rank
     * 
     * @param playerRank The rank to upgrade or downgrade to
     * @return A string indicating the action taken
     * 
     * @author Petter Carlsson & Villie Brandt, Uppdated by Simon Flenman
     */
    public String rankIconChange(PlayerRanks playerRank) {
        String currentImage = getImage().toString();
        switch (playerRank) {
            case PEASANT:
                switch (currentImage) {
                    case "images/playerRedKnight.png": // Knight bild
                        setIcon(new ImageIcon("images/playerRedPeasant.png")); // Peasant bild
                        return "Downgraded icon to red peasant from knight";
                    case "images/playerMagentaKnight.png":
                        setIcon(new ImageIcon("images/playerMagentaPeasant.png"));
                        return "Downgraded icon to magenta peasant from knight";
                    case "images/playerOrangeKnight.png":
                        setIcon(new ImageIcon("images/playerOrangePeasant.png"));
                        return "Downgraded icon to orange peasant from knight";
                    case "images/playerYellowKnight.png":
                        setIcon(new ImageIcon("images/playerYellowPeasant.png"));
                        return "Downgraded icon to yellow peasant from knight";
                    case "images/playerGreenKnight.png":
                        setIcon(new ImageIcon("images/playerGreenPeasant.png"));
                        return "Downgraded icon to green peasant from knight";
                    case "images/playerCyanKnight.png":
                        setIcon(new ImageIcon("images/playerCyanPeasant.png"));
                        return "Downgraded icon to cyan peasant from knight";
                }
                break;

            case KNIGHT:
                switch (currentImage) {
                    case "images/playerRedPeasant.png": // Peasant bilden
                        setIcon(new ImageIcon("images/playerRedKnight.png")); // Knight Bild
                        return "Upgraded icon to red knight from peasant";
                    case "images/playerMagentaPeasant.png":
                        setIcon(new ImageIcon("images/playerMagentaKnight.png"));
                        return "Upgraded icon to magenta knight from peasant";
                    case "images/playerOrangePeasant.png":
                        setIcon(new ImageIcon("images/playerOrangeKnight.png"));
                        return "Upgraded icon to orange knight from peasant";
                    case "images/playerYellowPeasant.png":
                        setIcon(new ImageIcon("images/playerYellowKnight.png"));
                        return "Upgraded icon to yellow knight from peasant";
                    case "images/playerGreenPeasant.png":
                        setIcon(new ImageIcon("images/playerGreenKnight.png"));
                        return "Upgraded icon to green knight from peasant";
                    case "images/playerCyanPeasant.png":
                        setIcon(new ImageIcon("images/playerCyanKnight.png"));
                        return "Upgraded icon to cyan knight from peasant";

                    case "images/playerRedLord.png": // Lord bilden
                        setIcon(new ImageIcon("images/playerRedKnight.png")); // Knight bilden
                        return "Downgraded icon to red knight from lord";
                    case "images/playerMagentaLord.png":
                        setIcon(new ImageIcon("images/playerMagentaKnight.png"));
                        return "Downgraded icon to magenta knight from lord";
                    case "images/playerOrangeLord.png":
                        setIcon(new ImageIcon("images/playerOrangeKnight.png"));
                        return "Downgraded icon to orange knight from lord";
                    case "images/playerYellowLord.png":
                        setIcon(new ImageIcon("images/playerYellowKnight.png"));
                        return "Downgraded icon to yellow knight from lord";
                    case "images/playerGreenLord.png":
                        setIcon(new ImageIcon("images/playerGreenKnight.png"));
                        return "Downgraded icon to green knight from lord";
                    case "images/playerCyanLord.png":
                        setIcon(new ImageIcon("images/playerCyanKnight.png"));
                        return "Downgraded icon to cyan knight from lord";
                }
                break;

            case LORD:
                switch (currentImage) {
                    case "images/playerRedKnight.png": // Knight bilden
                        setIcon(new ImageIcon("images/playerRedLord.png"));// Lord Bilden
                        return "Upgraded icon to red lord from knight";
                    case "images/playerMagentaKnight.png":
                        setIcon(new ImageIcon("images/playerMagentaLord.png"));
                        return "Upgraded icon to magenta lord from knight";
                    case "images/playerOrangeKnight.png":
                        setIcon(new ImageIcon("images/playerOrangeLord.png"));
                        return "Upgraded icon to orange lord from knight";
                    case "images/playerYellowKnight.png":
                        setIcon(new ImageIcon("images/playerYellowLord.png"));
                        return "Upgraded icon to yellow lord from knight";
                    case "images/playerGreenKnight.png":
                        setIcon(new ImageIcon("images/playerGreenLord.png"));
                        return "Upgraded icon to green lord from knight";
                    case "images/playerCyanKnight.png":
                        setIcon(new ImageIcon("images/playerCyanLord.png"));
                        return "Upgraded icon to cyan lord from knight";

                    case "images/playerRedKing.png": // King bilden
                        setIcon(new ImageIcon("images/playerRedLord.png")); // Lord bilden
                        return "Downgraded icon to red lord from king";
                    case "images/playerMagentaKing.png":
                        setIcon(new ImageIcon("images/playerMagentaLord.png"));
                        return "Downgraded icon to magenta lord from king";
                    case "images/playerOrangeKing.png":
                        setIcon(new ImageIcon("images/playerOrangeLord.png"));
                        return "Downgraded icon to orange lord from king";
                    case "images/playerYellowKing.png":
                        setIcon(new ImageIcon("images/playerYellowLord.png"));
                        return "Downgraded icon to yellow lord from king";
                    case "images/playerGreenKing.png":
                        setIcon(new ImageIcon("images/playerGreenLord.png"));
                        return "Downgraded icon to green lord from king";
                    case "images/playerCyanKing.png":
                        setIcon(new ImageIcon("images/playerCyanLord.png"));
                        return "Downgraded icon to cyan lord from king";
                }
                break;

            case KINGS:
                switch (currentImage) {
                    case "images/playerRedLord.png":
                        setIcon(new ImageIcon("image/playerRedKing"));
                        return "Upgraded icon to red king from lord";
                    case "images/playerMagentaLord.png":
                        setIcon(new ImageIcon("images/playerMagentaKing.png"));
                        return "Upgraded icon to magenta king from lord";
                    case "images/playerOrangeLord.png":
                        setIcon(new ImageIcon("images/playerOrangeKing.png"));
                        return "Upgraded icon to orange king from lord";
                    case "images/playerYellowLord.png":
                        setIcon(new ImageIcon("images/playerYellowKing.png"));
                        return "Upgraded icon to yellow king from lord";
                    case "images/playerGreenLord.png":
                        setIcon(new ImageIcon("images/playerGreenKing.png"));
                        return "Upgraded icon to green king from lord";
                    case "images/playerCyanLord.png":
                        setIcon(new ImageIcon("images/playerCyanKing.png"));
                        return "Upgraded icon to cyan king from lord";
                }
                break;
        }

        return "Nothing happened";
    }

    /**
     * @return propertiesOwned, returns entire ArrayList of properties owned.
     */
    public ArrayList<Property> getProperties() {
        return this.propertiesOwned;
    }

    /**
     * @return all taverns owned by player
     */
    public ArrayList<Tavern> getTaverns() {
        return this.tavernsOwned;
    }

    /**
     * Returns the players color
     * 
     * @return playerColor
     */
    public Color getPlayerColor() {
        return playerColor;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.propertiesOwned = properties;
    }

    public String getNameText() {
        return name;
    }

    public boolean isUpdateTavernNbr() {
        return updateTavernNbr;
    }

    public void setUpdateTavernNbr(boolean updateTavernNbr) {
        this.updateTavernNbr = updateTavernNbr;
    }

}