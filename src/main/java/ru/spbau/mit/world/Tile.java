package ru.spbau.mit.world;

import java.awt.Color;
import asciiPanel.AsciiPanel;

/**
 * Description of the cell on the map
 * */
public enum Tile {
	FLOOR((char)250, new Color(205, 133, 63)),
	WALL((char)177, new Color(205, 133, 63)),
	BOUNDS('x', AsciiPanel.brightBlack);
	
	private char glyph;
	public char glyph() { return glyph; }
	
	private Color color;
	public Color color() { return color; }
	
	Tile(char glyph, Color color){
		this.glyph = glyph;
		this.color = color;
	}

	/**
	 * Check is current Tile is ground or not
	 * @return true if it is ground otherwise false
	 */
	public boolean isGround() {
		return this != WALL && this != BOUNDS;
	}

	/**
	 * Check is current Tile is Diggible or not
	 * @return true if it is Wall otherwise false
	 */
	public boolean isDiggable() {
		return this == Tile.WALL;
	}
}
