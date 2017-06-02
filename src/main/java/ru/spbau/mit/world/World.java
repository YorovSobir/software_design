package ru.spbau.mit.world;

import ru.spbau.mit.inventory.Item;
import ru.spbau.mit.mob.Mob;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/**
 * Game world
 * */
public class World {
	private Tile[][] tiles;
	private int width;
	public int width() { return width; }
	
	private int height;
	public int height() { return height; }
	
	private ArrayList<Mob> mobs;
	private ArrayList<Item> items;

	/**
	 * Constructor for creating new World
	 * @param tiles - Tiles of world
	 */
	public World(Tile[][] tiles){
		this.tiles = tiles;
		this.width = tiles.length;
		this.height = tiles[0].length;
		this.mobs = new ArrayList<>();
		this.items = new ArrayList<>();
	}

	/**
	 * Check the existence of the mob in the cell
	 * */
	public Mob checkMob(int x, int y){
		for (Mob mob : mobs){
			if (mob.getPosition().getX() == x && mob.getPosition().getY() == y)
				return mob;
		}
		return null;
	}
	/**
	 * Check the existence of the item in the cell
	 * */
	public Item checkItem(int x, int y) {
		for (Item item : items) {
			if (item.getPos().getX() == x && item.getPos().getY() == y) {
				return item;
			}
		}
		return null;
	}

	/**
	 * Get cell type
	 * */
	public Tile tile(int x, int y){
		if (x < 0 || x >= width || y < 0 || y >= height - 3)
			return Tile.BOUNDS;
		else
			return tiles[x][y];
	}

	/**
	 * get symbol of current cell
	 * @param x - x coordinate of cell
	 * @param y - y coordinate of cell
	 * @return symbol of cell
	 */
	public char glyph(int x, int y){
		return tile(x, y).glyph();
	}

	/**
	 * get color of current cell
	 * @param x - x coordinate of cell
	 * @param y - y coordinate of cell
	 * @return color of cell
	 */
	public Color color(int x, int y){
		return tile(x, y).color();
	}

	/**
	 * The method removes the wall on the map
	 * */
	public void dig(int x, int y) {
		if (tile(x,y).isDiggable())
			tiles[x][y] = Tile.FLOOR;
	}

	/**
	 * Method returns free space on the map
	 * */
	public Point getEmptyLocation(){
		int x;
		int y;
		do {
			x = (int)(Math.random() * width);
			y = (int)(Math.random() * height);
		} 
		while (!tile(x,y).isGround() || checkMob(x,y) != null);
		return new Point(x, y);
	}

	/**
	 * The method adds the item to the map
	 * */
	public void registerItem(Item item) {
		items.add(item);
	}
	/**
	 * The method adds the mob to the map
	 * */
	public void registerMob(Mob mob) {
		mobs.add(mob);
	}

	/**
	 * The method updates the state of the map
	 * */
	public void update(Random rnd){
		Iterator<Mob> iterator = mobs.iterator();
		while(iterator.hasNext()) {
			Mob mob = iterator.next();
			mob.update(rnd);
			if (mob.getAttr().getCurrentHP() < 1) {
				iterator.remove();
			}
		}
	}
	/**
	 * Removes a item from the map
	 * */
	public void removeItem(Item item) {
		items.remove(item);
	}

	/**
	 * Returns the number of mobs on the map
	 * */
	public int getCountMob() {
		return mobs.size();
	}

	public void addAtEmptySpace(Item item, int x, int y){
		if (item == null)
			return;
		List<Point> points = new ArrayList<>();
		List<Point> checked = new ArrayList<>();

		points.add(new Point(x, y));

		while (!points.isEmpty()){
			Point p = points.remove(0);
			checked.add(p);

			int y1 = p.getY();
			int x1 = p.getX();
			if (!tile(x1, y1).isGround())
				continue;

			if (checkItem(x1, y1) == null && checkMob(x1, y1) == null){
			    item.setPos(new Point(x1, y1));
			    registerItem(item);
				return;
			} else {
				List<Point> neighbors = p.neighbors8();
				neighbors.removeAll(checked);
				points.addAll(neighbors);
			}
		}
	}
}
