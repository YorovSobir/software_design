package ru.spbau.mit.screens;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import asciiPanel.AsciiPanel;
import ru.spbau.mit.factory.ItemFactory;
import ru.spbau.mit.inventory.Item;
import ru.spbau.mit.mob.Mob;
import ru.spbau.mit.factory.MobFactory;
import ru.spbau.mit.mob.Player;
import ru.spbau.mit.world.World;
import ru.spbau.mit.world.WorldBuilder;

/**
 * Class for representing the main Screen of game
 */
public class PlayScreen implements Screen {
	private World world;
	private Player player;
	private int screenWidth;
	private int screenHeight;
	private ArrayList<String> messages;

	/**
	 * Constructor for creating main Screen
	 */
	public PlayScreen(){
		screenWidth = 80;
		screenHeight = 23;
		messages = new ArrayList<>();
		createWorld();
		MobFactory mobFactory = new MobFactory(world);
		createMobs(mobFactory);
		ItemFactory itemFactory = new ItemFactory(world);
		createItems(itemFactory);
	}

	/**
	 * The method for creating Items for game
	 * @param itemFactory - factory for creating items
	 */
	private void createItems(ItemFactory itemFactory) {
		Random rnd = new Random();
		for(int i = 0; i < 20; i++) {
			int idx = rnd.nextInt(itemFactory.getItemTypes().size());
			Item item = itemFactory.getItem(itemFactory.getItemTypes().get(idx));
			world.registerItem(item);
		}
	}

	/**
	 * The method for creating Mobs for game
	 * @param mobFactory - factory for creating mobs
	 */
	private void createMobs(MobFactory mobFactory){
		Random rnd = new Random();
		player = (Player) mobFactory.getMob("player", messages);
		world.registerMob(player);
		for (int i = 0; i < 20; i++){
			int idx = rnd.nextInt(mobFactory.getMobTypes().size());
			Mob m = mobFactory.getMob(mobFactory.getMobTypes().get(idx), messages);
			world.registerMob(m);
		}
	}

	/**
	 * Method for creating new World of game
	 */
	private void createWorld() {
		world = new WorldBuilder(screenWidth - 16, screenHeight + 50)
				.makeCaves()
				.build();
	}

	/**
	 * get scroll on axes X
	 * @return scroll on axes Y
	 */
	public int getScrollX() { return Math.max(0,
            Math.min(player.getPosition().getX() - screenWidth / 2,
                    world.width() - screenWidth)); }

	/**
	 * get scroll on axes Y
	 * @return scroll on axes Y
	 */
	public int getScrollY() { return Math.max(0,
            Math.min(player.getPosition().getY() - screenHeight / 2,
                    world.height() - screenHeight)); }

	/**
	 * Drawing the game scene
	 * */
	@Override
	public void displayOutput(AsciiPanel terminal) {
		int left = getScrollX();
		int top = getScrollY(); 
		
		displayTiles(terminal, left, top);
		displayMessages(terminal, messages);

		String health = String.format("%d/%d hp",
                player.getAttr().getCurrentHP(),
                player.getAttr().getMaxHP());
		String dmg = String.format("%d dmg", player.getAttr().getAttackValue());
		String def = String.format("%d def", player.getAttr().getDefenseValue());
		terminal.write(health, screenWidth - 12, 2);
        terminal.write(dmg, screenWidth - 12, 3);
        terminal.write(def, screenWidth - 12, 4);
        displayHelp(terminal);
	}

    private void displayHelp(AsciiPanel terminal) {
	    terminal.write("=============", screenWidth - 15, 7);
	    terminal.write("@ - Player", screenWidth - 15, 8);
        terminal.write("o - Orc", screenWidth - 15, 9);
        terminal.write("t - Troll", screenWidth - 15, 10);
        terminal.write("d - Dragon", screenWidth - 15, 11);
        terminal.write("^ - Power", screenWidth - 15, 12);
        terminal.write("# - Defence", screenWidth - 15, 13);
        terminal.write("+ - Health", screenWidth - 15, 14);
        terminal.write("=============", screenWidth - 15, 15);
        terminal.write("[g] - get item", screenWidth - 15, 16);
        terminal.write("kill all mobs", screenWidth - 15, 18);
        terminal.write("to win!", screenWidth - 15, 19);
    }

    private void displayMessages(AsciiPanel terminal, List<String> messages) {
		int top = screenHeight - messages.size() + 1;
		for (int i = 0; i < messages.size(); i++){
			terminal.writeCenter(messages.get(i), top + i);
		}
		messages.clear();
	}

	private void displayTiles(AsciiPanel terminal, int left, int top) {
		for (int x = 0; x < screenWidth - 16; x++){
			for (int y = 0; y < screenHeight; y++){
				int wx = x + left;
				int wy = y + top;

				Mob mob = world.checkMob(wx, wy);
				if (mob != null) {
					terminal.write(
							mob.getAttr().getGlyph(),
							mob.getPosition().getX() - left,
							mob.getPosition().getY() - top,
							mob.getAttr().getColor());
					continue;
				}
				Item item = world.checkItem(wx, wy);
				if (item != null) {
					terminal.write(
							item.getGlyph(),
							item.getPos().getX() - left,
							item.getPos().getY() - top,
							item.getColor());
					continue;
				}
				terminal.write(world.glyph(wx, wy), x, y, world.color(wx, wy));
			}
		}
	}
	/**
	 * Keyboard processor
	 * */
	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch (key.getKeyCode()){
		case KeyEvent.VK_ESCAPE: return new LoseScreen();
		case KeyEvent.VK_LEFT: player.moveBy(-1, 0); break;
		case KeyEvent.VK_RIGHT: player.moveBy( 1, 0); break;
		case KeyEvent.VK_UP: player.moveBy( 0,-1); break;
		case KeyEvent.VK_DOWN: player.moveBy( 0, 1); break;
		case KeyEvent.VK_G: player.addItem(world.checkItem(player.getPosition().getX(), player.getPosition().getY()));
		}
		world.update();
		if (player.getAttr().getCurrentHP()  < 1)
			return new LoseScreen();
		if (world.getCountMob() == 1) {
			return new WinScreen();
		}
		return this;
	}
}
