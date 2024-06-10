package br.edu.up.allanhousequest.daos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import br.edu.up.allanhousequest.models.*;

public class FileMonsterDAO implements MonsterDAO {
    private static final String FILE_PATH = "monsters.dat";

    @Override
    public void saveMonster(Monster monster) {
        List<Monster> monsters = loadAllMonsters();
        monsters.removeIf(m -> m.getId() == monster.getId());
        monsters.add(monster);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(monsters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Monster loadMonster(int id) {
        List<Monster> monsters = loadAllMonsters();
        return monsters.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Monster> loadAllMonsters() {
        List<Monster> monsters = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            monsters = (List<Monster>) in.readObject();
        } catch (FileNotFoundException e) {
            // File not found, return empty list
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return monsters;
    }
}

