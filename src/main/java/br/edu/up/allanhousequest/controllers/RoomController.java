package br.edu.up.allanhousequest.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.edu.up.allanhousequest.models.*;
import br.edu.up.allanhousequest.utils.Utils;
import br.edu.up.allanhousequest.views.*;
import br.edu.up.allanhousequest.controllers.ChestController;

public class RoomController {
    private Room model;
    private RoomView view;
    private boolean isRunning;

    public RoomController(Room model, RoomView view) {
        this.model = model;
        this.view = view;
        this.isRunning = true;
    }

    public void generateRoom(Player player, RPGModel rpgModel) {
        int roomLevel = player.getLevel();
        List<Monster> roomMonsters = new ArrayList<>();
        Chest chest = new Chest();

        Random random = new Random();

        // Preenchimento da lista de monstros com o nível equivalente ao do jogador
        for (Monster monster : rpgModel.getMonsters()) {
            if (monster.getLevel() <= player.getLevel()) {
                roomMonsters.add(monster);
            }
        }

        // Geração da sala
        while (player.getLevel() == roomLevel && isRunning) {
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
                        generateRoom(player);
                        break;
                    case 4:
                        saveGame();
                        isRunning = false;
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
}
