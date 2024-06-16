package br.edu.up.allanhousequest.controllers;

import br.edu.up.allanhousequest.models.Item;
import br.edu.up.allanhousequest.views.ItemView;

public class ItemController {
    private Item model;
    private ItemView view;

    public static Item createNewItem() {
        Item addedItem = ItemView.createNewItem();
        return addedItem;
    }

}
