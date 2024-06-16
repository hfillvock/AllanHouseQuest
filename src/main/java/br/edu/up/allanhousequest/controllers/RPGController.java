package br.edu.up.allanhousequest.controllers;

import java.util.ArrayList;
import java.util.List;

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
            generateEncounter();
        }
    }

    public void generateRoom(Player player) {
    	int roomLevel = player.getLevel();
    	List<Monster> roomMonsters = new ArrayList<>();
    
    	Random random  = new Random();
    	
    	// Preenchimento da lista de monstros com o nível equivalente ao do jogador
    	for (Monster monster : monsters) {
    		if (monster.getLevel() <= player.getLevel()) {
    			roomMonsters.add(monster);
    		}
        }
    	
    	// Geração da sala
    	while (player.getLevel() == roomLevel) {
    		Monster monster = roomMonsters.get(random.nextInt(roomMonsters.size()));
    		System.out.println();
    		System.out.println("Você encontrou um " + monster.getName() + "!");
    
    		//Batalha
    		startBattle(player, monster);
    
    		// Verificação do nível do jogador
    		if (player.getLevel() > roomLevel) {
    			System.out.println();
    			System.out.println("Parabéns! Você subiu de nível para o nível " + player.getLevel() + ".");
    			
    			// Próxima escolha.
    			System.out.println();
    			System.out.println("O que deseja fazer?");
    			System.out.println("1 - Abrir baú");
    			System.out.println("2 - Usar Item");
    			System.out.println("3 - Seguir para a próxima sala");
    			System.out.println("4 - Salvar jogo e sair");
    			
    			int action = Utils.scanInt()
    			Utils.clearScannerBuffer();
    			
    			switch (action) {
    		    case 1:
    		        // Implementar lógica de abrir baú.
    		        break;
    		    case 2:
    		        // Implementar lógica de uso de item.
    		        break;
    		    case 3:
    		        //generateRoom(player); ?
    		        break;
    		    case 4:
    		        // Implementar lógica de salvamento do jogo.
    		        break;
    		    default:
    		        System.out.println("Opção Inválida. Tente novamente.");
    		        continue;
    			}
    		}
    	}
    
    }

    // Versão 2
    public void startBattle(Player player, Monster monster) {
		
		//monster.getDescription();
		while(player.getHitPoints() > 0 && monster.getHitPoints() > 0) {
			
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
		    	// Implementar lógica de uso de item.
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
}
