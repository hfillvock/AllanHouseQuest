package br.edu.up.allanhousequest.controllers;

import java.util.List;

import br.edu.up.allanhousequest.factories.*;
import br.edu.up.allanhousequest.daos.*;
import br.edu.up.allanhousequest.models.*;
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

    public void saveGame(Player player, List<Monster> monsters, List<Item> items) {
        playerDAO.savePlayer(player);

        for (Monster monster : monsters) {
            monsterDAO.saveMonster(monster);
        }
        for (Item item : items) {
            itemDAO.saveItem(item);
        }
        
        System.out.println("Game saved.");
    }

    public void loadGame(int playerId) {
        Player player = playerDAO.loadPlayer(playerId);
        List<Monster> monsters = monsterDAO.loadAllMonsters();
        List<Item> items = itemDAO.loadAllItems();
    }

    public void startGame() {
        char choice = view.mainMenu();

        switch (choice) {
            case 'i':
                if (hasPlayers()) {
                    view.listPlayers(model);
        
                    selectPlayer(view.selectPlayer());
                } else {
                    createNewPlayer();
                }
        
                gameLoop();
                break;
            case 'c':
                choice = view.createNewEntity();

                switch (choice) {
                    case 'p':
                        break;
                    case 'm':
                        break;
                    case 'i':
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

    public void createNewPlayer() {
        String name = view.createNewPlayer();

        Player addedPlayer = new Player(0, name, 100, 10, 5); //alterar valores

        model.addPlayer(addedPlayer);
        model.setCurrentPlayer(addedPlayer);
    }

    private void selectPlayer(int i) {
        Player player = model.getPlayers().get(i);
        model.setCurrentPlayer(player);
    }
    
    public void gameLoop() {
        while(isRunning == true) {
            generateEncounter();
        }
    }

    public void generateEncounter() {
        // Gerar encontro aleatório com monstros, interagível, ou sei lá o que
    }

    public void startBattle(Player player, Monster monster) {
        // Implementação da lógica de batalha
    }

    public void generateEncounter() {
        Player player = model.getCurrentPlayer();
        int playerLevel = player.getLevel();
    
        // Generates a random number of monsters and items based on the player's level
        Random random = new Random();
        int numberOfMonsters = random.nextInt(playerLevel + 1) + 1; // Entre 1 e (playerLevel + 1)
        int numberOfItems = random.nextInt(playerLevel + 1); // Entre 0 e playerLevel
    
        // Select random monsters and items
        List<Monster> monsters = model.getRandomMonsters(numberOfMonsters);
        List<Item> items = model.getRandomItems(numberOfItems);
    
        // Adds to the model
        model.setEncounterMonsters(monsters);
        model.setEncounterItems(items);
    
        view.displayEncounter(monsters, items);
    }

    public void startBattle(Player player, Monster monster) {
        while (player.getHitPoints() > 0 && monster.getHitPoints() > 0) {
            // Player turn
            int playerDamage = Math.max(0, player.getAttackValue() - monster.getDefenseValue());
            monster.setHitPoints(monster.getHitPoints() - playerDamage);
            view.displayBattleTurn(player, monster, playerDamage, true);
            if (monster.getHitPoints() <= 0) {
                view.displayBattleResult(player, monster, true);
                break;
            }

            // Monster turn
            int monsterDamage = Math.max(0, monster.getAttackValue() - player.getDefenseValue());
            player.setHitPoints(player.getHitPoints() - monsterDamage);
            view.displayBattleTurn(player, monster, monsterDamage, false);
            if (player.getHitPoints() <= 0) {
                view.displayBattleResult(player, monster, false);
                break;
            }
        }

        // After battle: Update player status and remove monster from encounter list
        if (player.getHitPoints() > 0) {
            player.gainXp(monster.getXp());
            model.dieEncounterMonster(monster);
        }
    }
}
