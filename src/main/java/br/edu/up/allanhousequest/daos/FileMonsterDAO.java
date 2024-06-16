package br.edu.up.allanhousequest.daos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import br.edu.up.allanhousequest.models.Monster;

public class FileMonsterDAO implements MonsterDAO {
    private static final String FILE_PATH = "monsters.dat";

    @Override
    public void saveMonsters(List<Monster> monsters) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(monsters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Monster> loadMonsters() {
        List<Monster> monsters = new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            monsters = (List<Monster>) in.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return monsters;
    }
}

