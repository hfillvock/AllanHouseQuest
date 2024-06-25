package br.edu.up.allanhousequest.models;

import java.util.ArrayList;
import java.util.List;

import br.edu.up.allanhousequest.daos.ItemDAO;
import br.edu.up.allanhousequest.daos.MonsterDAO;
import br.edu.up.allanhousequest.daos.PlayerDAO;
import br.edu.up.allanhousequest.factories.DAOFactory;
import br.edu.up.allanhousequest.utils.Utils;

public class RPGModel {
    private List<Player> players;
    private List<Monster> monsters;
    private List<Item> items;
    private Player currentPlayer;
    private Boolean isRunning;

    private PlayerDAO playerDAO;
    private MonsterDAO monsterDAO;
    private ItemDAO itemDAO;
    
    public RPGModel() {
        this.players = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.items = new ArrayList<>();
        this.playerDAO = DAOFactory.getPlayerDAO();
        this.monsterDAO = DAOFactory.getMonsterDAO();
        this.itemDAO = DAOFactory.getItemDAO();
    }

    // métodos

    public boolean saveGame() {
        try {
            playerDAO.savePlayers(this.getPlayers());
            monsterDAO.saveMonsters(this.getMonsters());
            itemDAO.saveItems(this.getItems());
            Utils.logger.info("Jogo salvo.");
            return true;            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
        
    public boolean loadGame() {
        this.setPlayers(playerDAO.loadPlayers());
        this.setMonsters(monsterDAO.loadMonsters());
        this.setItems(itemDAO.loadItems());

        if (this.getPlayers().isEmpty() & this.getMonsters().isEmpty() & this.getItems().isEmpty()) {
            return false;
        } else {
            Utils.logger.info("Jogo carregado.");
            return true;
        }
    }

    public void selectPlayer(int i) {
        Player player = getPlayers().get(i);
        setCurrentPlayer(player);
    }

    // players
    
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }
    
    public List<Player> getPlayers() {
        return players;
    }

    // monsters

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }
    
    public void removeMonster(Monster monster) {
        monsters.remove(monster);
    }
    
    public List<Monster> getMonsters() {
        return monsters;
    }
    
    // items

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    // current player

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
   
    // isrunning

    public Boolean getIsRunning() {
        return isRunning;
    }

    public void setIsRunning(Boolean isRunning) {
        this.isRunning = isRunning;
    } 
}
