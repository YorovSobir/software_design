package ru.spbau.mit.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

/**
 * Class for representing Lose Screen
 */
public class LoseScreen implements Screen {

	/**
	 * Override method for display message in terminal
	 * @param terminal - current terminal
	 */
	@Override
	public void displayOutput(AsciiPanel terminal) {
		terminal.writeCenter("============================", terminal.getHeightInCharacters() / 2 - 2);
		terminal.writeCenter("You lost!", terminal.getHeightInCharacters() / 2);
		terminal.writeCenter("but you are the best player i ever seen",
				terminal.getHeightInCharacters() / 2 + 1);
		terminal.writeCenter("============================", terminal.getHeightInCharacters() / 2 + 3);
		terminal.writeCenter("-- press [enter] to restart --", 22);
	}

	/**
	 * The method for processing input key
	 * @param key - input key
	 * @return
	 */
	@Override
	public Screen respondToUserInput(KeyEvent key) {
		return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen() : this;
	}
}
