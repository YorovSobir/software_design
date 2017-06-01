package ru.spbau.mit.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

/**
 * Class for representing Win Screen
 */
public class WinScreen implements Screen {

	/**
	 * Override method to congratulate player
	 * @param terminal - current terminal
	 */
	@Override
	public void displayOutput(AsciiPanel terminal) {
		terminal.writeCenter("============================", terminal.getHeightInCharacters() / 2 - 2);
		terminal.writeCenter("You won!", terminal.getHeightInCharacters() / 2);
		terminal.writeCenter("============================", terminal.getHeightInCharacters() / 2 + 2);
		terminal.writeCenter("-- press [enter] to restart --", 22);
	}

	/**
	 * Override method for process input key
	 * @param key - input key
	 * @return new Screen or current
	 */
	@Override
	public Screen respondToUserInput(KeyEvent key) {
		return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen() : this;
	}
}
