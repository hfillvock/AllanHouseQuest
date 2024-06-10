package br.edu.up.allanhousequest.models;

import java.util.ArrayList;
import java.util.List;

public class RPGModel {
    private List<Player> players;
    private List<Monster> monsters;
    private List<Item> items;
    
    private Player currentPlayer;
    
    public RPGModel() {
        this.players = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.items = new ArrayList<>();
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
}
