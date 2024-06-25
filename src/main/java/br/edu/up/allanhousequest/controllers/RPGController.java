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
        loadGame();

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

                    Utils.logger.info("Jogo iniciado com jogador" + model.getCurrentPlayer().getName() + ".");
                    gameLoop();
                    pass = true;
                    break;
                case 'a': manageEntities(); break;
                case 's': saveGame(); pass = true; break;
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
        Utils.logger.info("Jogo encerrado.");
        model.setIsRunning(false);
        view.endGame();
    }

    // -----------------------------------------------------------
    
    public void generateRoom() {
        Room room = new Room(model.getCurrentPlayer().getLevel());
        Chest chest = generateChest();

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
                    openChest(chest);
                    pass = true;
                    break;
                case 'u':
                    useItem();
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
                        useItem();
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
            } else {
                callMonsterAttack(monster, model.getCurrentPlayer()); // Monster Turn
            }
    
            // Verificação de derrota do jogador.
            if (model.getCurrentPlayer().getHitPoints() <= 0) {
                view.displayPlayerDiedMessage(monster);
                endGame();
                break;
            }
        }
    }
    
    private void callPlayerAttack(Player player, Monster monster) {
        int rolledDice = Utils.diceRoll(1, 20);

        view.displayInitialAttackStats(player, monster, rolledDice);

        int damage = Utils.diceRoll(player.getEquippedWeapon().getDamageDiceQuantity(), player.getEquippedWeapon().getDamageDice());

        if (rolledDice + player.getAttackModifier() >= monster.getDefenseValue()) {
            monster.receiveDamage(damage);
            view.displayAttackResult(true, damage);

        } else {
            view.displayAttackResult(false, 0);
        }
    }

    private void callMonsterAttack(Monster monster, Player player) {
        int rolledDice = Utils.diceRoll(1, 20);

        view.displayInitialAttackStats(monster, player, rolledDice);

        int damage = Utils.diceRoll(monster.getDamageDiceQuantity(), monster.getDamageDice()) + monster.getDamageModifier();

        if (rolledDice + monster.getAttackModifier()>= player.getDefenseValue()) {
            player.receiveDamage(damage);
            view.displayAttackResult(true, damage);

        } else {
            view.displayAttackResult(false, 0);
        }
    }

    private Chest generateChest() {
        Chest chest = new Chest(model.getCurrentPlayer().getLevel());
    
         // Preenchimento da lista de itens compatíveis com o nível equivalente ao do jogador
        for (Item item : model.getItems()) {
            if (item.getLevel() <= chest.getLevel()) {
                chest.getPossibleItems().add(item);
            }
        }
    
        int numberOfItems = Utils.random.nextInt(4) + 1;

        for (int i = 0; i < numberOfItems; i++) {
            Item randomItem = chest.getPossibleItems().get(Utils.random.nextInt(chest.getPossibleItems().size()));
            chest.getItems().add(randomItem);
        }

        return chest;
    }
    
    public void openChest(Chest chest) {
        if (chest.getItems().isEmpty()) {
            view.displayChestEmpty();
        } else {
            view.displayChestItems(chest.getItems());
            for (Item item : chest.getItems()) {
                model.getCurrentPlayer().getInventory().add(item);
            }
        }
    }

    public void useItem() {    
        if (model.getCurrentPlayer().getInventory().isEmpty()) {
            view.noItemsAvailable();
            return;
        }
        
        view.listItems(model.getCurrentPlayer().getInventory());

        int i = view.selectItem();

        while (!isValidItemIndex(i)) {
            i = view.selectItem();
        }

        Item selectedItem = model.getCurrentPlayer().getInventory().get(i);

        Armour armour = new Armour(null, 0, 0);
        Weapon weapon = new Weapon(null, 0, 0, 0);
        Potion potion = new Potion(null, 0, 0, 0);

        if (selectedItem.getClass() == armour.getClass()) {
            model.getCurrentPlayer().setEquippedArmour(selectedItem);
            view.displayEquippedArmour();
        } else if (selectedItem.getClass() == weapon.getClass()) {
            model.getCurrentPlayer().setEquippedWeapon(selectedItem);
            view.displayEquippedWeapon();
        } else if (selectedItem.getClass() == potion.getClass()) {
            Potion selectedPotion = (Potion) selectedItem;

            int healAmount = Utils.diceRoll(selectedPotion.getPotionDiceQuantity(), selectedPotion.getPotionDice());

            model.getCurrentPlayer().setHitPoints(model.getCurrentPlayer().getHitPoints() + healAmount);
            model.getCurrentPlayer().getInventory().remove(i);
            
            view.displayHealAmount(healAmount);
        }
    }

    // manage

    public boolean isValidPlayerIndex(int i) {
        if (i < 0 || i >= model.getPlayers().size()) {
            view.displayInvalidOption();
            return false;
        }
        return true;
    }

    public boolean isValidMonsterIndex(int i) {
        if (i < 0 || i >= model.getMonsters().size()) {
            view.displayInvalidOption();
            return false;
        }
        return true;
    }

    public boolean isValidItemIndex(int i) {
        if (i < 0 || i >= model.getItems().size()) {
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
