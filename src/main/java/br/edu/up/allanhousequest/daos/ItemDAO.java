package br.edu.up.allanhousequest.daos;

import java.util.List;

import br.edu.up.allanhousequest.models.Item;

public interface ItemDAO {
    void saveItem(Item item);
    Item loadItem(int id);
    List<Item> loadAllItems();
}