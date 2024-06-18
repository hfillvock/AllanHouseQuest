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

    public RPGController(RPGModel model, RPGView view) {
        this.model = model;
        this.view = view;
    }

    public void saveGame() {
        view.saveGame(model.saveGame());
    }

    public void loadGame() {
        view.loadGame(model.loadGame());
    }

    public void startGame() {
        char choice = view.mainMenu();

        switch (choice) {
            case 'i':
                if (hasPlayers()) {
                    view.listPlayers(model);
        
                    model.selectPlayer(view.selectPlayer());
                } else {
                    model.setCurrentPlayer(PlayerController.createNewPlayer());
					model.addPlayer(model.getCurrentPlayer());
                }
        
                gameLoop();
                break;
            case 'c':
                choice = view.createNewEntity();

                switch (choice) {
                    case 'p': model.addPlayer(PlayerController.createNewPlayer()); break;
                    case 'm': model.addMonster(MonsterController.createNewMonster()); break;
                    case 'i': model.addItem(ItemController.createNewItem()); break;
                }
                break;
        }
    }

    public boolean hasPlayers() {
        if (model.getPlayers().isEmpty()) {
            return false;
        }
        return true;
    }
    
    public void gameLoop() {
        while(model.getIsRunning() == true) {
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
