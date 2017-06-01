package ru.spbau.mit.inventory;

import java.util.ArrayList;

/**
 * Class for game inventory
 */
public class Inventory {
    private int capacity;
    private int size = 0;
    private ArrayList<Item> items = new ArrayList<>();

    /**
     * Constructor for creating inventory object
     */
    public Inventory() {
        capacity = 11;
    }

    /**
     * The method adds a feature to inventory
     * @param item feature
     */
    public void add(Item item) {
        size++;
        items.add(item);
    }

    /**
     * The method removes the feature from inventory
     *
     * @param index index in ArrayList
     */
    public void remove(int index) {
        size--;
        items.remove(index);
    }

    /**
     * Current number of features in inventory
     */
    public int getSize() {
        return size;
    }

    /**
     * Inventory capacity
     */
    public int getCapacity() {
        return capacity;
    }
}
