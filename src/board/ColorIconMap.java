package board;

import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * @author Seth Oberg 
 * Get a image icon object from a string value
 */
public class ColorIconMap {
	private HashMap<String, ImageIcon> colorMap = new HashMap<String, ImageIcon>();

	/**
	 * Adds all the colors to a hashmap
	 */
	public ColorIconMap() {
		addColorsToMap();
	}

	/**
	 * The method that adds all the colors to a hashmap
	 */
	private void addColorsToMap() {
		colorMap.put("MAGENTA", new ImageIcon("images/playerMagentaPeasant.png"));
		colorMap.put("RED", new ImageIcon("images/playerRedPeasant.png"));
		colorMap.put("ORANGE", new ImageIcon("images/playerOrangePeasant.png"));
		colorMap.put("YELLOW", new ImageIcon("images/playerYellowPeasan.png"));
		colorMap.put("GREEN", new ImageIcon("images/playerGreenPeasant.png"));
		colorMap.put("CYAN", new ImageIcon("images/playerCyanPeasant.png"));
		colorMap.put("PURPLE", new ImageIcon("images/playerPurple.jpg"));
	}

	/**
	 * @param chosenColor
	 * @return ImageIcon
	 */
	public ImageIcon getColorFromMap(String chosenColor) {
		return colorMap.get(chosenColor);
	}

}
