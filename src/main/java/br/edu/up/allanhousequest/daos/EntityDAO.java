package br.edu.up.allanhousequest.daos;

import java.util.List;
import br.edu.up.allanhousequest.models.*;
import br.edu.up.allanhousequest.utils.*;

public class EntityDAO {
    
    public static void initializeEntities(RPGModel rpgModel) {
        rpgModel.setPlayers(loadPlayers());
        rpgModel.setMonsters(loadMonsters());
        rpgModel.setItems(loadItems());
    }

    public static void savePlayers(List<Player> players) {
        FileUtils.savePlayersFile(FileUtils.playersFile, players);
    }

    public static void saveMonsters(List<Monster> monsters) {
        FileUtils.saveMonstersFile(FileUtils.monstersFile, monsters);
    }

    public static void saveItems(List<Item> items) {
        FileUtils.saveItemsFile(FileUtils.itemsFile, items);
    }

    public static List<Player> loadPlayers() {
        List<Player> players = FileUtils.readPlayersFile(FileUtils.playersFile);
        return players;
    }

    public static List<Monster> loadMonsters() {
        List<Monster> monsters = FileUtils.readMonstersFile(FileUtils.monstersFile);
        return monsters;
    }

    public static List<Item> loadItems() {
        List<Item> items = FileUtils.readItemsFile(FileUtils.itemsFile);
        return items;
    }

    public void listPlayers() {
        // Implementação para listar personagens
    }

    // Outros métodos de CRUD
}
