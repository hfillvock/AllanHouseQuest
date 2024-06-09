package br.edu.up.allanhousequest.utils;

import br.edu.up.allanhousequest.models.*;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class FileUtils {

    public static File playersFile = new File("src/main/java/br/edu/up/allanhousequest/saves/savedplayers.ser");
    public static File monstersFile = new File("src/main/java/br/edu/up/allanhousequest/saves/savedmonsters.ser");
    public static File itemsFile = new File("src/main/java/br/edu/up/allanhousequest/saves/saveditems.ser");
    
    public static void initializeFiles() {

        if (!playersFile.exists()) {
            try {
                playersFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!monstersFile.exists()) {
            try {
                monstersFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!itemsFile.exists()) {
            try {
                itemsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void savePlayersFile(File file, List<Player> players) {

        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(players);
            oos.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void saveMonstersFile(File file, List<Monster> monsters) {

        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(monsters);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveItemsFile(File file, List<Item> items) {

        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(items);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<Player> readPlayersFile(File file) {

        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            List<Player> players = null;
            
            players = (ArrayList) ois.readObject();
            fis.close();

            return players;
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Monster> readMonstersFile(File file) {

        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            List<Monster> monsters = null;
            
            monsters = (ArrayList) ois.readObject();
            fis.close();

            return monsters;
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Item> readItemsFile(File file) {

        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            List<Item> items = null;
            
            items = (ArrayList) ois.readObject();
            fis.close();

            return items;
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
