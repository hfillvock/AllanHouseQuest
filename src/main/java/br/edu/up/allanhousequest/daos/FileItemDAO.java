package br.edu.up.allanhousequest.daos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import br.edu.up.allanhousequest.models.Item;

public class FileItemDAO implements ItemDAO {
    private static final String FILE_PATH = "items.dat";

    @Override
    public void saveItems(List<Item> items) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Item> loadItems() {
        List<Item> items = new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            items = (List<Item>) in.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return items;
    }
}

