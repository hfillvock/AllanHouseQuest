package br.edu.up.allanhousequest.views;

import java.util.List;

import br.edu.up.allanhousequest.models.Item;

public class ChestView {
    public void displayChestEmpty() {
        System.out.println("O baú está vazio.");
    }

    public void displayChestItems(List<Item> items) {
        System.out.println("Você encontrou os seguintes itens no baú:");
        for (Item item : items) {
            System.out.println(item.getName());
        }
    }
}