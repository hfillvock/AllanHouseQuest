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
        if (model.getCurrentPlayer() != null) {
            model.addPlayer(model.getCurrentPlayer());
        }
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
                    if (model.getPlayers().isEmpty()) {
                        createNewPlayer();
                        model.setCurrentPlayer(model.getPlayers().get(0));
                    } else {
                        view.listPlayers(model.getPlayers());
                        
                        int i = view.selectPlayer();

                        while (!isValidPlayerIndex(i)) {
                            i = view.selectPlayer();
                        }

                        model.selectPlayer((i));
                    }

                    gameLoop();
                    pass = true;
                    break;
                case 'a': manageEntities(); break;
                case 's': pass = true; break;
                default: view.displayInvalidOption(); break;
            }
        }
    }
    
    public void gameLoop() {
        model.setIsRunning(true);

        view.startingText(model.getCurrentPlayer());

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
            // Escolhe um monstro aleatório dentre os possíveis da sala e inicia batalha
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
                        callPlayerAttack(model.getCurrentPlayer(), monster);
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
            callMonsterAttack(monster, model.getCurrentPlayer());
    
            // Verificação de derrota do jogador.
            if (model.getCurrentPlayer().getHitPoints() <= 0) {
                view.displayPlayerDiedMessage(monster);
                endGame();
                break;
            }
        }
    }
    
    private void callPlayerAttack(Player player, Monster monster) { // CORRIGIR CÁLCULO DE ATAQUE E MENSAGENS
        int rolledDice = Utils.diceRoll(1, 20);

        view.displayInitialAttackStats(player, monster, rolledDice);

        // calcula o dano, CORRIGIR!!!!!!!
        int damage = Utils.diceRoll(getEquippedWeapon().getDamageDiceQuantity(), getEquippedWeapon().getDamageDice());

        // chama os respectivos views caso o ataque tenha sucesso ou não
        if (rolledDice + getAttackModifier() >= monster.getDefenseValue()) {
            monster.receiveDamage(damage);
            view.displayAttackResult(true, damage);

        } else {
            view.displayAttackResult(false, 0);
        }
    }

    /* ARRUMAR O CÁLCULO DE ATAQUE PRA QUANDO FOR O MONSTRO ATACANDO E TALVEZ FAZER OS MÉTODOS DE VIEW DIFERENTES
    private void callMonsterAttack(Monster monster, Player player) {
        int rolledDice = Utils.diceRoll(1, 20);

        view.displayInitialAttackStats(monster, player, rolledDice);

        // calcula o dano só que pro monstro
        int damage = Utils.diceRoll(getEquippedWeapon().getDamageDiceQuantity(), getEquippedWeapon().getDamageDice());

        if (rolledDice + getAttackModifier() >= player.getDefenseValue()) {
            player.receiveDamage(damage);
            view.displayAttackResult(true, damage);

        } else {
            view.displayAttackResult(false, 0);
        }
    }
    */

    private Chest generateChest(Player player, RPGModel model) {
        int chestLevel = player.getLevel();
    
        List<Item> chestPossibleItems = new ArrayList<>();
        List<Item> chestItems = new ArrayList<>();
    
         // Preenchimento da lista de itens compatíveis com o nível equivalente ao do jogador
            for (Item item : model.getItems()) {
                if (item.getLevel() <= chestLevel) {
                    chestPossibleItems.add(item);
                }
            }
    
            Random random = new Random();
    
            // Define a quantidade de itens no baú, por exemplo, entre 1 e 4 itens
            int numberOfItems = random.nextInt(4) + 1;
    
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
            item.useItem(player);
            playerItems.remove(choice);
            System.out.println("Você usou o item: " + item.getName());
        }
    }

    // manage

    public boolean isValidPlayerIndex(int i) {
        if (i < 0 || i > model.getPlayers().size()) {
            view.displayInvalidOption();
            return false;
        }
        return true;
    }

    public boolean isValidMonsterIndex(int i) {
        if (i < 0 || i > model.getMonsters().size()) {
            view.displayInvalidOption();
            return false;
        }
        return true;
    }

    public boolean isValidItemIndex(int i) {
        if (i < 0 || i > model.getItems().size()) {
            view.displayInvalidOption();
            return false;
        }
        return true;
    }
    
    public void manageEntities() {
        boolean pass = false;

        while (!pass) {
            char choice = view.manageEntities();
    
            switch (choice) {
                case 'c': createNewEntity(); break;
                case 'r': removeEntity(); break;
                case 'e': editEntity(); break;
                case 'l': listEntity(); break;
                case 'v': pass = true; break;
                default: view.displayInvalidOption();
            }
        }
    }
    
    // create

    public void createNewEntity() {
        boolean pass = false;

        while (!pass) {
            char choice = view.createNewEntity();
    
            switch (choice) {
                case 'p': createNewPlayer(); break;
                case 'm': createNewMonster(); break;
                case 'i': createNewItem(); break;
                case 'v': pass = true; break;
                default: view.displayInvalidOption();
            }
        }
    }

    public void createNewPlayer() {
        model.getPlayers().add(view.createNewPlayer());
    }

    public void createNewMonster() {
        model.getMonsters().add(view.createNewMonster());
    }

    public void createNewItem() {
        model.getItems().add(view.createNewItem());
    }
    
    // remove

    public void removeEntity() {
        boolean pass = false;

        while (!pass) {
            char choice = view.removeEntity();
    
            switch (choice) {
                case 'p': removePlayer(); break;
                case 'm': removeMonster(); break;
                case 'i': removeItem(); break;
                case 'v': pass = true; break;
                default: view.displayInvalidOption();
            }
        }
    }

    public void removePlayer() {
        if (model.getPlayers().isEmpty()) {
            view.noEntitiesMessage();
            return;
        }

        int i = view.removePlayer();

        while (!isValidPlayerIndex(i)) {
            i = view.removePlayer();
        }

        model.getPlayers().remove(i);
    }

    public void removeMonster() {
        if (model.getMonsters().isEmpty()) {
            view.noEntitiesMessage();
            return;
        }

        int i = view.removeMonster();

        while (!isValidMonsterIndex(i)) {
            i = view.removeMonster();
        }

        model.getMonsters().remove(i);
    }

    public void removeItem() {
        if (model.getItems().isEmpty()) {
            view.noEntitiesMessage();
            return;
        }

        int i = view.removeItem();

        while (!isValidItemIndex(i)) {
            i = view.removeItem();
        }

        model.getItems().remove(i);
    }

    // edit

    public void editEntity() {
        boolean pass = false;

        while (!pass) {
            char choice = view.editEntity();
    
            switch (choice) {
                case 'p': editPlayer(); break;
                case 'm': editMonster(); break;
                case 'i': editItem(); break;
                case 'v': pass = true; break;
                default: view.displayInvalidOption();
            }
        }
    }

    public void editPlayer() {
        if (model.getPlayers().isEmpty()) {
            view.noEntitiesMessage();
            return;
        }

        int i = view.editPlayer();

        while (!isValidPlayerIndex(i)) {
            i = view.editPlayer();
        }

        Player newPlayer = view.createNewPlayer();

        model.getPlayers().set(i, newPlayer);
    }

    public void editMonster() {
        if (model.getMonsters().isEmpty()) {
            view.noEntitiesMessage();
            return;
        }

        int i = view.editMonster();

        while (!isValidMonsterIndex(i)) {
            i = view.editMonster();
        }

        Monster newMonster = view.createNewMonster();

        model.getMonsters().set(i, newMonster);
    }

    public void editItem() {
        if (model.getItems().isEmpty()) {
            view.noEntitiesMessage();
            return;
        }

        int i = view.editItem();

        while (!isValidItemIndex(i)) {
            i = view.editItem();
        }

        Item newItem = view.createNewItem();

        model.getItems().set(i, newItem);
    }

    // list

    public void listEntity() {
        boolean pass = false;

        while (!pass) {
            char choice = view.listEntity();
    
            switch (choice) {
                case 'p': listPlayers(); break;
                case 'm': listMonsters(); break;
                case 'i': listItems(); break;
                case 'v': pass = true; break;
                default: view.displayInvalidOption();
            }
        }
    }

    public void listPlayers() {
        if (model.getPlayers().isEmpty()) {
            view.noEntitiesMessage();
            return;
        }

        view.listPlayers(model.getPlayers());
    }

    public void listMonsters() {
        if (model.getMonsters().isEmpty()) {
            view.noEntitiesMessage();
            return;
        }

        view.listMonsters(model.getMonsters());
    }

    public void listItems() {
        if (model.getItems().isEmpty()) {
            view.noEntitiesMessage();
            return;
        }

        view.listItems(model.getItems());
    }
}
