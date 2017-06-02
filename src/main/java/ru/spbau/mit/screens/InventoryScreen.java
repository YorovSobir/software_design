package ru.spbau.mit.screens;

import asciiPanel.AsciiPanel;
import ru.spbau.mit.inventory.Item;
import ru.spbau.mit.mob.Player;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for representing inventory menu
 */
public class InventoryScreen implements Screen {
    private final String letters = "abcdefghijklmnopqrstuvwxyz";
    private PlayScreen playScreen;
    private Player player;

    /**
     * Constructor for creating Inventory Screen
     * @param player - player which inventories we want to show
     * @param playScreen - main screen of game
     */
    public InventoryScreen(Player player, PlayScreen playScreen) {
        this.playScreen = playScreen;
        this.player = player;
    }

    /**
     * show drop menu for inventory
     * @param terminal - current terminal
     */
    @Override
    public void displayOutput(AsciiPanel terminal) {
        List<String> lines = getList();

        int y = (terminal.getHeightInCharacters() - lines.size()) / 2;
        int x = terminal.getWidthInCharacters() / 3;

        for (String line : lines){
            terminal.write(line, x, y++);
        }

        terminal.write("What would you like to drop?", x, y);
        terminal.repaint();
    }

    /**
     * Keyboard processor
     * @param key - input key
     * @return - PlayScreen or this
     */
    @Override
    public Screen respondToUserInput(KeyEvent key) {
        char c = key.getKeyChar();

        List<Item> items = player.getInventory().getItems();

        if (letters.indexOf(c) > -1
                && items.size() > letters.indexOf(c)
                && items.get(letters.indexOf(c)) != null)
            return drop(items.get(letters.indexOf(c)));
        else if (key.getKeyCode() == KeyEvent.VK_ESCAPE)
            return playScreen;
        else
            return this;
    }

    private Screen drop(Item item) {
        player.dropItem(item);
        return playScreen;
    }

    private List<String> getList() {
        List<String> lines = new ArrayList<>();
        List<Item> inventory = player.getInventory().getItems();

        for (int i = 0; i < inventory.size(); i++){
            Item item = inventory.get(i);
            String line = letters.charAt(i) + " - " + item.getGlyph() + " " + item.getName();
            lines.add(line);
        }
        return lines;
    }
}
