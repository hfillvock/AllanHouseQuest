package br.edu.up.allanhousequest.daos;

import java.io.File;
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
    private static File file;

    public FileItemDAO() {
        try {
            file = new File(FILE_PATH);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveItems(List<Item> items) {
        if (!items.isEmpty()) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
                out.writeObject(items);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Item> loadItems() {
        List<Item> items = new ArrayList<>();

        if (file.length() == 0) {
            return items;
        }

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

