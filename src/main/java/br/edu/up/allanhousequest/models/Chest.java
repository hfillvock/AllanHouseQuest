package br.edu.up.allanhousequest.models;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;


public class Chest implements Serializable {
    private List<Item> possibleItems;
    private List<Item> items;
    private int level;

    // Constructors
    public Chest(int level) {
        possibleItems = new ArrayList<>();
        items = new ArrayList<>();
        this.level = level;
    }

    public List<Item> getPossibleItems() {
        return possibleItems;
    }

    public void setPossibleItems(List<Item> possibleItems) {
        this.possibleItems = possibleItems;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    
}
