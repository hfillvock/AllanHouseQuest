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

import br.edu.up.allanhousequest.models.Monster;
import br.edu.up.allanhousequest.utils.Utils;

public class FileMonsterDAO implements MonsterDAO {
    private static final String FILE_PATH = "monsters.dat";
    private static File file;

    public FileMonsterDAO() {
        try {
            file = new File(FILE_PATH);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Utils.logger.info("Arquivo de monstros inicializado.");
    }

    @Override
    public void saveMonsters(List<Monster> monsters) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(monsters);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Utils.logger.info("Arquivo de monstros salvo.");
    }

    @Override
    public List<Monster> loadMonsters() {
        List<Monster> monsters = new ArrayList<>();

        if (file.length() == 0) {
            Utils.logger.info("Arquivo de monstros vazio, nada foi carregado.");
            return monsters;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            monsters = (List<Monster>) in.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        Utils.logger.info("Arquivo de monstros carregado.");
        return monsters;
    }
}

