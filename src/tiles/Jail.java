package tiles;

import java.awt.Color;

import javax.swing.ImageIcon;

/**
 * Class for jail tile. 
 * @author Sebastian Viro, Aevan Dino	
 */
public class Jail implements Tile {
	private ImageIcon img = new ImageIcon("tilePics/jail.png");
	
	/**
	 * Returns name of tile
	 */
	public String getName() {
		return "JAIL";
	}

	/**
	 * returns false, because it is not purchasable
	 */
	public Boolean getPurchaseable() {
		return Boolean.FALSE;
	}
	
	/**
	 * Returns Color.White
	 */
	public Color getColor() {
		return Color.WHITE;
	}
	
	/**
	 * returns information about tile
	 */
	public String getTileInfo() {
		return "JAIL + \nPlayer can choose to spend five rounds here"
				+ "\nor pay the bail.";
	}

	/**
	 * returns null
	 */
	public String getTitle() {
		return null;
	}
	
	/**
	 * returns image
	 */
	public ImageIcon getPicture(){
		return img;
	}
}
