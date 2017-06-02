package ru.spbau.mit.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

/**
 * Interface for game's screen
 */
public interface Screen {
	/**
	 * The method for display current screen to terminal
	 * @param terminal - current terminal
	 */
	void displayOutput(AsciiPanel terminal);

	/**
	 * The method for processing key input
	 * @param key - input key
	 * @return new Screen or null
	 */
	Screen respondToUserInput(KeyEvent key);
}
