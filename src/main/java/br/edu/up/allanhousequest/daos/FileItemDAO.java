package br.edu.up.allanhousequest.daos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import br.edu.up.allanhousequest.models.Item;

public class FileItemDAO implements ItemDAO {
    private static final String FILE_PATH = "items.dat";

    @Override
    public void saveItem(Item item) {
        List<Item> items = loadAllItems();
        items.removeIf(i -> i.getId() == item.getId());
        items.add(item);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Item loadItem(int id) {
        List<Item> items = loadAllItems();
        return items.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Item> loadAllItems() {
        List<Item> items = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            items = (List<Item>) in.readObject();
        } catch (FileNotFoundException e) {
            // File not found, return empty list
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return items;
    }
}

