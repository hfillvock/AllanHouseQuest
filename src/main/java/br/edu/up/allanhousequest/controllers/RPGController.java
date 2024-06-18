package br.edu.up.allanhousequest.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import br.edu.up.allanhousequest.factories.*;
import br.edu.up.allanhousequest.daos.*;
import br.edu.up.allanhousequest.models.*;
import br.edu.up.allanhousequest.utils.Utils;
import br.edu.up.allanhousequest.views.*;

public class RPGController {
    private RPGModel model;
    private RPGView view;

    private PlayerDAO playerDAO;
    private MonsterDAO monsterDAO;
    private ItemDAO itemDAO;

    public Boolean isRunning;

    public RPGController(RPGModel model, RPGView view) {
        this.model = model;
        this.view = view;
        this.isRunning = false;
        this.playerDAO = DAOFactory.getPlayerDAO();
        this.monsterDAO = DAOFactory.getMonsterDAO();
        this.itemDAO = DAOFactory.getItemDAO();
    }

    public void saveGame() {
        playerDAO.savePlayers(this.model.getPlayers());
        monsterDAO.saveMonsters(this.model.getMonsters());
        itemDAO.saveItems(this.model.getItems());
    }

    public void loadGame() {
        this.model.setPlayers(playerDAO.loadPlayers());
        this.model.setMonsters(monsterDAO.loadMonsters());
        this.model.setItems(itemDAO.loadItems());
    }

    public void startGame() {
        char choice = view.mainMenu();

        switch (choice) {
            case 'i':
                if (hasPlayers()) {
                    view.listPlayers(model);
        
                    selectPlayer(view.selectPlayer());
                } else {
                    model.setCurrentPlayer(createNewPlayer());
                }
        
                gameLoop();
                break;
            case 'c':
                choice = view.createNewEntity();

                switch (choice) {
                    case 'p':
                        createNewPlayer();
                        break;
                    case 'm':
                        createNewMonster();
                        break;
                    case 'i':
                        createNewItem();
                        break;
                    default:
                        break;
                }
            default:
                break;
        }
    }
    
    public boolean hasPlayers() {
        if (model.getPlayers().isEmpty()) {
            return false;
        }
        return true;
    }

    public Player createNewPlayer() {
        String name = view.createNewPlayer();

        Player addedPlayer = new Player(name, 0, 100, 10, 10); //alterar valores

        model.addPlayer(addedPlayer);
        return addedPlayer;
    }

    public Monster createNewMonster() {
        Monster addedMonster = view.createNewMonster();
        model.addMonster(addedMonster);

        return addedMonster;
    }

    public Item createNewItem() {
        Item addedItem = view.createNewItem();
        model.addItem(addedItem);

        return addedItem;
    }

    private void selectPlayer(int i) {
        Player player = model.getPlayers().get(i);
        model.setCurrentPlayer(player);
    }
    
    public void gameLoop() {
        while(isRunning == true) {
            generateRoom(model.getCurrentPlayer());
        }
    }
    
    public void useItem(Player player) {
        List<Item> playerItems = player.getInventory();
    
        if (playerItems.isEmpty()) {
            System.out.println("Você não tem itens para usar.");
            return;
        }
    
        System.out.println("Escolha um item para usar:");
        for (int i = 0; i < playerItems.size(); i++) {
            System.out.println((i + 1) + " - " + playerItems.get(i).getName());
        }
    
        int choice = Utils.scanInt() - 1;
        Utils.clearScannerBuffer();
    
        if (choice < 0 || choice >= playerItems.size()) {
            System.out.println("Opção inválida. Tente novamente.");
        } else {
            Item item = playerItems.get(choice);
            player.useItem(item);
            playerItems.remove(choice);
            System.out.println("Você usou o item: " + item.getName());
        }
    }

}
