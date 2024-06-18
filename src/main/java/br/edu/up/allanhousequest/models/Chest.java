package br.edu.up.allanhousequest.models;

import java.util.List;
import java.io.Serializable;


public class Chest implements Serializable{
    private List<Item> items;

    // Construtor
    public Chest() {
    }

    // Getters e Setters
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Chest [items=" + items + "]";
    }
}
