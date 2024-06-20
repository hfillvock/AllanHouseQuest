package br.edu.up.allanhousequest.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        model.addPlayer(model.getCurrentPlayer());
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
                    model.selectPlayer(view.selectPlayer(model) - 1);
                } else {
                    model.setCurrentPlayer(PlayerController.createNewPlayer());
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
        /*
        while(model.getIsRunning() == true) {
            generateRoom(model.getCurrentPlayer());
        }
        */
    }
    
    public void generateRoom(Player player, RPGModel model) {
        int roomLevel = player.getLevel();
        List<Monster> roomMonsters = new ArrayList<>();
        Chest chest = generateChest(player, model);

        Random random = new Random();

        // Preenchimento da lista de monstros com o nível equivalente ao do jogador
        for (Monster monster : model.getMonsters()) {
            if (monster.getLevel() <= player.getLevel()) {
                roomMonsters.add(monster);
            }
        }

        // Geração da sala
        while (player.getLevel() == roomLevel) {
            Monster monster = roomMonsters.get(random.nextInt(roomMonsters.size()));
            view.displayMonsterEncounter(monster);

            // Batalha
            startBattle(player, monster);

            // Verificação do nível do jogador
            if (player.getLevel() > roomLevel) {
                view.displayLevelUp(player.getLevel());
                view.displayOptions();

                int action = view.getUserAction();

                switch (action) {
                    case 1:
                        openChest(player);
                        break;
                    case 2:
                        useItem(player);
                        break;
                    case 3:
                        generateRoom(player, model);
                        break;
                    case 4:
                        saveGame();
                        break;
                    default:
                        view.displayInvalidOption();
                        break;
                }
            }
        }
    }

    public void startBattle(Player player, Monster monster) {
        while (player.getHitPoints() > 0 && monster.getHitPoints() > 0) {
            // Player Turn
            System.out.println();
            System.out.println("Seu Turno! Escolha sua ação: ");
            System.out.println("1 - Atacar");
            System.out.println("2 - Usar Item");
    
            int action = Utils.scanInt();
            Utils.clearScannerBuffer();
    
            switch (action) {
                case 1:
                    player.attack(monster);
                    break;
                case 2:
                    useItem(player);
                    break;
                default:
                    System.out.println("Opção Inválida. Tente novamente.");
                    continue;
            }
    
            // Verificação de derrota do monstro.
            if (monster.getHitPoints() <= 0) {
                System.out.println("Você derrotou o " + monster.getName() + "!");
                System.out.println("Você recebeu " + monster.getExperiencePoints() + " pontos de experiência!");
                player.setExperiencePoints(player.getExperiencePoints() + monster.getExperiencePoints());
                break;
            }
    
            // Monster Turn
            monster.attack(player);
    
            // Verificação de derrota do jogador.
            if (player.getHitPoints() <= 0) {
                System.out.println("Você foi derrotado pelo " + monster.getName() + "...");
                break;
            }
        }
    }

    private Chest generateChest(Player player, RPGModel model) {
        int chestLevel = player.getLevel();
    
        List<Item> chestPossibleItems = new ArrayList<>();
        List<Item> chestItems = new ArrayList<>();
    
         // Preenchimento da lista de itens compatíveis com o nível equivalente ao do jogador
            for (Item item : model.getItems()) {
                // Implementar a lógica que correlaciona o nível do jogador com o "nível" dos itens
                chestPossibleItems.add(item);
            }
    
            Random random = new Random();
    
            // Define a quantidade de itens no baú, por exemplo, entre 1 e 3 itens
            int numberOfItems = random.nextInt(3) + 1;
    
            for (int i = 0; i < numberOfItems; i++) {
                Item randomItem = chestPossibleItems.get(random.nextInt(chestPossibleItems.size()));
                chestItems.add(randomItem);
            }
    
            return new Chest(chestItems);
    }
    
    public void openChest(Player player) {
            List<Item> chestItems = model.getItems();
    
            if (chestItems.isEmpty()) {
                view.displayChestEmpty();
            } else {
                view.displayChestItems(chestItems);
                for (Item item : chestItems) {
                    player.addItem(item);
                }
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
