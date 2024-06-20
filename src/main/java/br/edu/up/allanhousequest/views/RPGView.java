package br.edu.up.allanhousequest.views;

import java.util.List;
import java.util.Scanner;

import br.edu.up.allanhousequest.models.Item;
import br.edu.up.allanhousequest.models.Monster;
import br.edu.up.allanhousequest.models.Player;
import br.edu.up.allanhousequest.models.RPGModel;
import br.edu.up.allanhousequest.utils.Utils;

public class RPGView {

    public char mainMenu() {
        System.out.println("\r\n" + //
                        "                                                                                                  \r\n" + //
                        "    _    _     _        _    _   _   _   _  ___  _   _ ____  _____    ___  _   _ _____ ____ _____ \r\n" + //
                        "   / \\  | |   | |      / \\  | \\ | | | | | |/ _ \\| | | / ___|| ____|  / _ \\| | | | ____/ ___|_   _|\r\n" + //
                        "  / _ \\ | |   | |     / _ \\ |  \\| | | |_| | | | | | | \\___ \\|  _|   | | | | | | |  _| \\___ \\ | |  \r\n" + //
                        " / ___ \\| |___| |___ / ___ \\| |\\  | |  _  | |_| | |_| |___) | |___  | |_| | |_| | |___ ___) || |  \r\n" + //
                        "/_/   \\_|_____|_____/_/   \\_|_| \\_| |_| |_|\\___/ \\___/|____/|_____|  \\__\\_\\\\___/|_____|____/ |_|  \r\n" + //
                        "                                                                                                  \r\n" + //
                        "");
        
        Utils.printDivider();

        Utils.printCentered("[I]niciar jogo.");
        Utils.printCentered("[C]adastrar entidade.");
        Utils.printCentered("[S]air.");
        return Utils.scanFirstChar();
    }

    public void endGame() {
        Utils.printDivider();
        Utils.printCentered("Fim de jogo.");
    }

    public void listPlayers(List<Player> players) {
        Utils.printDivider();

        Utils.printCentered("Listando personagens: \n");

        for (int i = 0; i < players.size(); i++) {
            System.out.println("Personagem " + (i + 1) + ":\n" + players.get(i).toString() + "\n");
        }
    }

    public int selectPlayer(List<Player> players) {
        Utils.printDivider();

        Utils.printCentered("Escolhendo personagem.");
        Utils.printCentered("Insira o índice do personagem.");

        return Utils.scanInt();
    }

    public void saveGame(boolean saved) {
        if (saved) {
            Utils.printCentered("Jogo salvo.");
            return;
        }
    }

    public void loadGame(boolean loaded) {
        if (loaded) {
            Utils.printCentered("Jogo carregado.");
            return;
        }
    }

    /* player displays (sei lá se vai usar isso)

    public void displayPlayerInfo(Player player) {
        System.out.println("Player: " + player.getName());
        System.out.println("Level: " + player.getLevel());
        System.out.println("XP: " + player.getExperiencePoints() + "/" + player.calculateExperienceRequired(player.getLevel()));
    }

    public void displayEncounter(List<Monster> monsters, List<Item> items) {
        // display monsters and items encountered
        System.out.println("Monstros encontrados: " + monsters.size());
        for (Monster monster : monsters) {
            System.out.println("Monstro: " + monster.getName());
        }

        System.out.println("Itens achados: " + items.size());
        for (Item item : items) {
            System.out.println("Item: " + item.getName());
        }
    }

    // display whose turn is
    public void displayBattleTurn(Player player, Monster monster, int damage, boolean playerTurn) {
        if (playerTurn) {
            System.out.println(player.getName() + " causou " + damage + " de dano em " + monster.getName());
        } else {
            System.out.println(monster.getName() + " causou " + damage + " de dano a " + player.getName());
        }
    }

    // display the battle result
    public void displayBattleResult(Player player, Monster monster, boolean playerWon) {
        if (playerWon) {
            System.out.println(player.getName() + " derrotou " + monster.getName());
        } else {
            System.out.println(player.getName() + " foi morto por " + monster.getName());
        }
    }
    */

    //Room Generation Displays
    public void displayMonsterEncounter(Monster monster) {
        Utils.printDivider();
        Utils.printCentered("Você encontrou um " + monster.getName() + "!");
    }

    public void displayLevelUp(int level) {
        Utils.printDivider();
        Utils.printCentered("Parabéns! Você subiu de nível para o nível " + level + ".");
    }

    public char displayOptions() {
        Utils.printDivider();

        Utils.printCentered("O que deseja fazer?");
        Utils.printCentered("[A]brir baú");
        Utils.printCentered("[U]sar Item");
        Utils.printCentered("[S]alvar jogo e sair.");
        Utils.printCentered("[C]ontinuar.");

        return Utils.scanFirstChar();
    }

    public void displayInvalidOption() {
        Utils.printCentered("Opção inválida. Tente novamente.");
    }

    public char displayYourBattleTurn() {
        Utils.printDivider();

        Utils.printCentered("Seu Turno! Escolha sua ação: ");
        Utils.printCentered("[A]tacar");
        Utils.printCentered("[U]sar Item");

        return Utils.scanFirstChar();
    }

    public void displayDefeatedMonsterMessage(Monster monster) {
        Utils.printCentered("Você derrotou o " + monster.getName() + "!");
        Utils.printCentered("Você recebeu " + monster.getExperiencePoints() + " pontos de experiência!");
    }

    public void displayAttackResult() {

    }

    public void displayPlayerDiedMessage(Monster monster) {
        Utils.printCentered("Você foi derrotado pelo " + monster.getName() + "...");
    }

    //Chest Displays
    public void displayChestEmpty() {
        Utils.printCentered("O baú está vazio.");
    }

    public void displayChestItems(List<Item> items) {
        Utils.printCentered("Você encontrou os seguintes itens no baú:");
        for (Item item : items) {
            Utils.printCentered(item.getName());
        }
    }

    // createNew
    
    public char createNewEntity() {
        Utils.printDivider();

        Utils.printCentered("Que tipo de entidade deseja criar?\n");
        Utils.printCentered("[P]ersonagem");
        Utils.printCentered("[M]onstro");
        Utils.printCentered("[I]tem");

        return Utils.scanFirstChar();
    }

    public Player createNewPlayer() {
        Utils.printDivider();

        Utils.printCentered("Criando personagem.");
        Utils.printCentered("Insira o nome do personagem: ");
        String name = Utils.scanLine();

        Player player = new Player(name, 0, 100, 10, 10); // mudar valores
        return player;
    }

    public Item createNewItem() { //precisa mudar pra vários createNew Potion, Armour, Weapon e tal
        Utils.printDivider();

        Utils.printCentered("Criando item.");
        Utils.printCentered("Insira o nome do item: ");
        String name = Utils.scanLine();

        Item item = new Item(name);
        return item;
    }
    
    public Monster createNewMonster() {
        Utils.printDivider();

        Utils.printCentered("Criando monstro.");
        Utils.printCentered("Insira o nome do monstro: ");
        String name = Utils.scanLine();

        Utils.printCentered("Insira o nível do monstro: ");
        int level = Utils.scanInt();

        Utils.printCentered("Insira o hp do monstro: ");
        int hitPoints = Utils.scanInt();

        Utils.printCentered("Insira o modificador de ataque do monstro: ");
        int attackModifier = Utils.scanInt();

        Utils.printCentered("Insira o valor de defesa do monstro: ");
        int defenseValue = Utils.scanInt();

        Utils.printCentered("Insira o valor de experiência do monstro: ");
        int experiencePoints = Utils.scanInt();

        Utils.printCentered("Insira o valor do dado de ataque monstro: ");
        int damageDice = Utils.scanInt();

        Utils.printCentered("Insira a quantidade de dados de ataque do monstro: ");
        int damageDiceQuantity = Utils.scanInt();

        Utils.printCentered("Insira o modificador de dano do monstro: ");
        int damageModifier = Utils.scanInt();

        Monster monster = new Monster(name, level, hitPoints, attackModifier, defenseValue, experiencePoints, damageDice, damageDiceQuantity, damageModifier);
        return monster;
    }
}
