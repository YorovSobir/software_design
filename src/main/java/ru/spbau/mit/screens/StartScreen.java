package ru.spbau.mit.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

/**
 * Class for representing Start screen of game
 */
public class StartScreen implements Screen {

    /**
     * Override method for display message on terminal
     * @param terminal - current terminal
     */
	@Override
	public void displayOutput(AsciiPanel terminal) {
		terminal.writeCenter("============================", terminal.getHeightInCharacters() / 2 - 2);
		terminal.writeCenter("Rougelike game", terminal.getHeightInCharacters() / 2);
		terminal.writeCenter("============================", terminal.getHeightInCharacters() / 2 + 2);
		terminal.writeCenter("-- press [enter] to start --", 22);
	}

    /**
     * Override method for processing input key
     * @param key - input key
     * @return
     */
	@Override
	public Screen respondToUserInput(KeyEvent key) {
		return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen() : this;
	}
}
