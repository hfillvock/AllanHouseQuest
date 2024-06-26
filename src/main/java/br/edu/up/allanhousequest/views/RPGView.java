package br.edu.up.allanhousequest.views;

import java.util.List;

import br.edu.up.allanhousequest.models.Armour;
import br.edu.up.allanhousequest.models.Entity;
import br.edu.up.allanhousequest.models.Item;
import br.edu.up.allanhousequest.models.Monster;
import br.edu.up.allanhousequest.models.Player;
import br.edu.up.allanhousequest.models.Potion;
import br.edu.up.allanhousequest.models.Weapon;
import br.edu.up.allanhousequest.utils.Utils;

public class RPGView {

    public void warningQuery() {
        Utils.printDivider();

        Utils.printCentered("ATENÇÃO! esse é um jogo DIFÍCIL! confirme que você não vai chorar jogando inserindo qualquer tecla: ");
        Utils.scanLine();
    }

    public char mainMenu() {
        Utils.printDivider();

        System.out.println("\r\n" + //
                        "                                                          _/                                                            \r\n" + //
                        "         _/    _/_/    _/      _/    _/_/      _/_/_/  _/          _/_/      _/    _/  _/_/_/_/    _/_/_/  _/_/_/_/_/   \r\n" + //
                        "        _/  _/    _/  _/_/    _/  _/    _/  _/                  _/    _/    _/    _/  _/        _/            _/        \r\n" + //
                        "       _/  _/    _/  _/  _/  _/  _/_/_/_/    _/_/              _/  _/_/    _/    _/  _/_/_/      _/_/        _/         \r\n" + //
                        "_/    _/  _/    _/  _/    _/_/  _/    _/        _/            _/    _/    _/    _/  _/              _/      _/          \r\n" + //
                        " _/_/      _/_/    _/      _/  _/    _/  _/_/_/                _/_/  _/    _/_/    _/_/_/_/  _/_/_/        _/           \r\n" + //
                        "                                                                                                                        \r\n" + //
                        "                                                                                                                        \r\n" + //
                        "");
        
        Utils.printDivider();

        Utils.printCentered("[I]niciar jogo.");
        Utils.printCentered("[A]dministrar entidades.");
        Utils.printCentered("[S]air.");
        return Utils.scanFirstChar();
    }

    public void startingText(Player player) {
        Utils.printDivider();

        Utils.printCentered("Bem vindo a casa de Allan House, " + player.getName() + ".\n");
        Utils.printCentered("Insira qualquer tecla para mostrar que você é fera:");

        Utils.scanLine();
    }

    public void endGame(Player player) {
        Utils.printDivider();

        Utils.printCentered(player.getName() + " não foi capaz de desvendar o mistério. ");
        Utils.printDivider();
    }

    public int selectPlayer() {
        Utils.printDivider();

        Utils.printCentered("Escolhendo personagem.");
        Utils.printCentered("Insira o índice do personagem.");

        return (Utils.scanInt() - 1);
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

        Utils.printCentered("O que deseja fazer?\n");
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

        Utils.printCentered("Seu turno! Escolha sua ação: \n");
        Utils.printCentered("[A]tacar");
        Utils.printCentered("[U]sar Item");

        return Utils.scanFirstChar();
    }

    public void displayDefeatedMonsterMessage(boolean almostDead, Monster monster) {
        if (almostDead) {
            Utils.printCentered("Você está malacabado, mas derrotou o " + monster.getName() + "!");
            Utils.printCentered("Você recebeu " + monster.getExperiencePoints() + " de experiência!");
            return;
        }
        Utils.printCentered("Você derrotou o " + monster.getName() + "!");
        Utils.printCentered("Você recebeu " + monster.getExperiencePoints() + " de experiência!");
    }

    public void displayInitialAttackStats(Entity attacker, Entity target, int rolledDice) {
        Utils.printCentered(attacker.getName() + " atacou " + target.getName() + "!");
        Utils.printCentered((rolledDice + attacker.getAttackModifier()) + " (" + rolledDice + "+" + attacker.getAttackModifier() + ") vs " + target.getDefenseValue());
    }

    public void displayPlayerAttackResult(boolean successfullyHit, int damage) {
        if (successfullyHit) {
            Utils.printCentered("Ataque bem-sucedido! Você causou " + damage + " pontos de dano!\n");
            return;
        }
        Utils.printCentered("Ataque mal-sucedido...\n");
    }

    public void dislayCriticalAttack() {
        Utils.printCentered("Você conseguiu um ataque crítico!!!");
    }

    public void displayMonsterAttackResult(boolean successfullyHit, int damage) {
        if (successfullyHit) {
            Utils.printCentered("Ataque bem-sucedido... Você recebeu " + damage + " pontos de dano!\n");
            return;
        }
        Utils.printCentered("Ataque mal-sucedido!\n");
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

    public char manageEntities() {
        Utils.printDivider();

        Utils.printCentered("Administrando entidades.\n");
        Utils.printCentered("[C]riar");
        Utils.printCentered("[R]emover");
        Utils.printCentered("[E]ditar");
        Utils.printCentered("[L]istar");
        Utils.printCentered("[V]oltar");

        return Utils.scanFirstChar();
    }
    
    public char createNewEntity() {
        Utils.printDivider();

        Utils.printCentered("Criando entidade.\n");
        Utils.printCentered("[P]ersonagem");
        Utils.printCentered("[M]onstro");
        Utils.printCentered("[I]tem");
        Utils.printCentered("[V]oltar");

        return Utils.scanFirstChar();
    }

    public Player createNewPlayer() {
        Utils.printDivider();

        Utils.printCentered("Criando personagem.");
        Utils.printCentered("Insira o nome do personagem: ");
        String name = Utils.scanLine();

        Player player = new Player(name, 1);
        return player;
    }

    public Item createNewItem() {
        int choice = -1;

        Utils.printDivider();

        Utils.printCentered("Criando item.");

        while (choice < 1 || choice > 3) {
            Utils.printCentered("Que tipo de item deseja criar?\n");
            Utils.printCentered("1 - Armadura");
            Utils.printCentered("2 - Arma");
            Utils.printCentered("3 - Poção");
            choice = Utils.scanInt();

            if (choice < 1 || choice > 3) {
                displayInvalidOption();
            }
        }
        
        Utils.printCentered("Insira o nome do item: ");
        String name = Utils.scanLine();

        Utils.printCentered("Insira o nível do item: ");
        int level = Utils.scanInt();

        switch (choice) {
            case 1:
                Utils.printCentered("Insira a classe da armadura:");
                int armourClass = Utils.scanInt();
                
                return new Armour(name, level, armourClass);
            case 2:
                Utils.printCentered("Insira o dado de dano da arma:");
                int damageDice = Utils.scanInt();
                
                Utils.printCentered("Insira a quantidade de dados da arma:");
                int damageDiceQuantity = Utils.scanInt();

                return new Weapon(name, level, damageDice, damageDiceQuantity);
            case 3:
                Utils.printCentered("Insira o dado da poção:");
                int potionDice = Utils.scanInt();
                
                Utils.printCentered("Insira a quantidade de dados da poção:");
                int potionDiceQuantity = Utils.scanInt();

                return new Potion(name, level, potionDice, potionDiceQuantity);
        }
        
        return null;
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

    public char removeEntity() {
        Utils.printDivider();

        Utils.printCentered("Removendo entidade.\n");
        Utils.printCentered("[P]ersonagem");
        Utils.printCentered("[M]onstro");
        Utils.printCentered("[I]tem");
        Utils.printCentered("[V]oltar");

        return Utils.scanFirstChar();
    }

    public int removePlayer() {
        Utils.printDivider();

        Utils.printCentered("Insira o índice do jogador a remover:\n");

        return (Utils.scanInt() - 1);
    }

    public int removeMonster() {
        Utils.printDivider();

        Utils.printCentered("Insira o índice do monstro a remover:\n");

        return (Utils.scanInt() - 1);
    }

    public int removeItem() {
        Utils.printDivider();

        Utils.printCentered("Insira o índice do item a remover:\n");

        return (Utils.scanInt() - 1);
    }

    public char editEntity() {
        Utils.printDivider();

        Utils.printCentered("Editando entidade.\n");
        Utils.printCentered("[P]ersonagem");
        Utils.printCentered("[M]onstro");
        Utils.printCentered("[I]tem");
        Utils.printCentered("[V]oltar");

        return Utils.scanFirstChar();
    }

    public int editPlayer() {
        Utils.printDivider();

        Utils.printCentered("Insira o índice do personagem a editar:\n");

        return (Utils.scanInt() - 1);
    }

    public int editMonster() {
        Utils.printDivider();

        Utils.printCentered("Insira o índice do monstro a editar:\n");

        return (Utils.scanInt() - 1);
    }

    public int editItem() {
        Utils.printDivider();

        Utils.printCentered("Insira o índice do item a editar:\n");

        return (Utils.scanInt() - 1);
    }

    public char listEntity() {
        Utils.printDivider();

        Utils.printCentered("Listando entidades.\n");
        Utils.printCentered("[P]ersonagem");
        Utils.printCentered("[M]onstro");
        Utils.printCentered("[I]tem");
        Utils.printCentered("[V]oltar");

        return Utils.scanFirstChar();
    }

    public void listPlayers(List<Player> players) {
        Utils.printDivider();

        Utils.printCentered("Listando personagens: \n");

        for (int i = 0; i < players.size(); i++) {
            System.out.println("Personagem " + (i + 1) + ":\n" + players.get(i) + "\n");
        }
    }
    
    public void listMonsters(List<Monster> monsters) {
        Utils.printDivider();

        Utils.printCentered("Listando monstros: \n");

        for (int i = 0; i < monsters.size(); i++) {
            System.out.println("Monstro " + (i + 1) + ":\n" + monsters.get(i) + "\n");
        }
    }

    public void listItems(List<Item> items) {
        Utils.printDivider();

        Utils.printCentered("Listando itens: \n");

        for (int i = 0; i < items.size(); i++) {
            System.out.println("Item " + (i + 1) + ":\n" + items.get(i) + "\n");
        }
    }

    public void noEntitiesMessage() {
        Utils.printCentered("Não há entidades na lista.");
    }

    public void noItemsAvailable() {
        Utils.printCentered("Não há itens disponíveis.");
    }

    public int selectItem() {
        Utils.printDivider();

        Utils.printCentered("Selecionando item. ");
        Utils.printCentered("Insira o índice do item:\n");

        return (Utils.scanInt() - 1);
    }

    public void displayEquippedArmour() {
        Utils.printCentered("A armadura foi equipada.\n");
    }

    public void displayEquippedWeapon() {
        Utils.printCentered("A arma foi equipada.\n");
    }

    public void displayHealAmount(int healAmount) {
        Utils.printCentered("Curado em " + healAmount + " HP.\n");
    }

    public void displayHealthBars(Player player, Monster monster) {
        String playerHealth = Utils.barLine(player.getHitPoints(), player.getTotalHitPoints());
        String monsterHealth = Utils.barLine(monster.getHitPoints(), monster.getTotalHitPoints());

        Utils.printCentered(player.getName() + ": " + playerHealth + "          " + monster.getName() + ": " + monsterHealth);
    }

    public void displayAlreadyOpenedChest() {
        Utils.printCentered("Você já abriu um baú neste nível.");
    }

    public char selectOrCreateMenu() {
        Utils.printDivider();

        Utils.printCentered("Deseja selecionar um personagem existente ou criar um novo?\n");
        Utils.printCentered("[S]elecionar");
        Utils.printCentered("[C]riar");

        return Utils.scanFirstChar();
    }
}
