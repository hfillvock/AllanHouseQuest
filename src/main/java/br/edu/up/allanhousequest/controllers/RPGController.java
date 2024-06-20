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

    // -----------------------------------------------------------

    public void saveGame() {
        model.addPlayer(model.getCurrentPlayer());
        view.saveGame(model.saveGame());
    }

    public void loadGame() {
        view.loadGame(model.loadGame());
    }

    // -----------------------------------------------------------

    public void startGame() {
        boolean pass = false;

        while (!pass) {
            char choice = view.mainMenu();

            switch (choice) {
                case 'i':
                    model.setIsRunning(true);

                    if (!model.getPlayers().isEmpty()) {
                        view.listPlayers(model.getPlayers());
                        
                        int i = -1;
                        while (i < model.getPlayers().size() || i > model.getPlayers().size()) {
                            i = view.selectPlayer(model.getPlayers());

                            if (i < model.getPlayers().size() || i > model.getPlayers().size()) {
                                view.displayInvalidOption();
                            }
                        }

                        model.selectPlayer((i - 1));
                    } else {
                        model.setCurrentPlayer(createNewPlayer());
                    }
            
                    gameLoop();
                    break;
                case 'c': createNewEntity(); break;
                case 's': pass = true; break;
                default: view.displayInvalidOption(); break;
            }
        }
    }
    
    public void gameLoop() {
        while(model.getIsRunning() == true) {
            generateRoom();
        }
    }

    public void endGame() {
        model.setIsRunning(false);
        view.endGame();
    }

    // -----------------------------------------------------------
    
    public void generateRoom() {
        Room room = new Room(model.getCurrentPlayer().getLevel());
        // Chest chest = generateChest(model.getCurrentPlayer(), model); pode ser em outro lugar

        // Preenchimento da lista de monstros com o nível equivalente ao do jogador
        room.fillWithMonsters(model.getMonsters());

        while (model.getCurrentPlayer().getLevel() <= room.getLevel()) {
            // escolhe um monstro aleatório dentre os possíveis da sala e inicia batalha
            Monster pickedMonster = room.getMonsters().get(Utils.random.nextInt(room.getMonsters().size()));
            startBattle(pickedMonster);
        }

        view.displayLevelUp(model.getCurrentPlayer().getLevel());

        boolean pass = false;

        while (!pass) {
            char choice = view.displayOptions();

            switch (choice) {
                case 'a':
                    openChest(model.getCurrentPlayer());
                    pass = true;
                    break;
                case 'u':
                    useItem(model.getCurrentPlayer());
                    pass = true;    
                    break;
                case 's':
                    saveGame();
                    pass = true;
                    break;
                case 'c': return; // gameLoop toma conta de gerar mais uma sala
                default: view.displayInvalidOption(); break;
            }
        }
    }

    public void startBattle(Monster monster) {
        view.displayMonsterEncounter(monster);

        while (model.getCurrentPlayer().getHitPoints() > 0 || monster.getHitPoints() > 0) {
            // Player Turn
            boolean pass = false;

            while (!pass) {
                char choice = view.displayYourBattleTurn();
            
                switch (choice) {
                    case 'a':
                        callAttack(model.getCurrentPlayer(), monster);
                        pass = true;
                        break;
                    case 'u':
                        useItem(model.getCurrentPlayer());
                        pass = true;
                        break;
                    default: view.displayInvalidOption(); break;
                }
            }
    
            // Verificação de derrota do monstro.
            if (monster.getHitPoints() <= 0) {
                view.displayDefeatedMonsterMessage(monster);
                model.getCurrentPlayer().setExperiencePoints(model.getCurrentPlayer().getExperiencePoints() + monster.getExperiencePoints());
                break;
            }
    
            // Monster Turn
            callAttack(monster, model.getCurrentPlayer());
    
            // Verificação de derrota do jogador.
            if (model.getCurrentPlayer().getHitPoints() <= 0) {
                view.displayPlayerDiedMessage(monster);
                endGame();
                break;
            }
        }
    }

    private void callAttack(Entity attacker, Entity target) {
        // diceRoll(); talvez
        view.displayAttackResult(attacker.attack(target)); // pede pra displayAttackResult imprimir um texto com alguma variável retornada do attack da entidade
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

    // createNew
    
    public void createNewEntity() {
        while (true) {
            char choice = view.createNewEntity();
    
            switch (choice) {
                case 'p': model.addPlayer(createNewPlayer()); break;
                case 'm': model.addMonster(createNewMonster()); break;
                case 'i': model.addItem(createNewItem()); break;
                default: view.displayInvalidOption();
            }
        }
    }

    public Player createNewPlayer() {
        Player addedPlayer = view.createNewPlayer();
        return addedPlayer;
    }

    public Monster createNewMonster() {
        Monster addedMonster = view.createNewMonster();
        return addedMonster;
    }

    public Item createNewItem() {
        Item addedItem = view.createNewItem();
        return addedItem;
    }

}
