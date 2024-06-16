package br.edu.up.allanhousequest.daos;

import java.util.List;

import br.edu.up.allanhousequest.models.Item;

public interface ItemDAO {
    void saveItems(List<Item> items);
    List<Item> loadItems();
}