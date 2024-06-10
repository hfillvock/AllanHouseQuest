package br.edu.up.allanhousequest.controllers;

import java.util.List;

import br.edu.up.allanhousequest.daos.DAOFactory;
import br.edu.up.allanhousequest.daos.ItemDAO;
import br.edu.up.allanhousequest.daos.MonsterDAO;
import br.edu.up.allanhousequest.daos.PlayerDAO;
import br.edu.up.allanhousequest.models.Item;
import br.edu.up.allanhousequest.models.Monster;
import br.edu.up.allanhousequest.models.Player;
import br.edu.up.allanhousequest.models.RPGModel;
import br.edu.up.allanhousequest.views.RPGView;

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

        System.out.println("Loaded Player: " + player);
        System.out.println("Loaded Monsters: " + monsters);
        System.out.println("Loaded Items: " + items);
    }

    public void startGame() {
        // Criar ou carregar player, e chamar o gameLoop
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
    
}
