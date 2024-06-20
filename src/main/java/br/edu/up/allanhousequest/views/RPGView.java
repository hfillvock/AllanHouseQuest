package br.edu.up.allanhousequest.views;

import java.util.List;
import java.util.Scanner;

import br.edu.up.allanhousequest.models.Item;
import br.edu.up.allanhousequest.models.Monster;
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

        while (true) {
            Utils.printDivider();

            Utils.printCentered("[I]niciar jogo.");
            Utils.printCentered("[C]adastrar entidade.");
            char choice = Utils.scanFirstChar();

            switch (choice) {
                case 'i':
                case 'c': return choice;
                default: Utils.printCentered("Entrada inválida. Tentando denovo: ");
            }
        }
    }

    public void listPlayers(RPGModel model) {
        Utils.printDivider();

        Utils.printCentered("Listando personagens: \n");

        for (int i = 0; i < model.getPlayers().size(); i++) {
            System.out.println("Personagem " + (i + 1) + ":\n" + model.getPlayers().get(i).toString() + "\n");
        }
    }

    public int selectPlayer(RPGModel model) {
        int i = 0;

        while (i < 1 || i > model.getPlayers().size()) {
            Utils.printDivider();

            Utils.printCentered("Escolhendo personagem.");
            Utils.printCentered("Insira o índice do personagem.");
            i = Utils.scanInt();

            if (i < 1 || i > model.getPlayers().size()) {
                Utils.printCentered("Entrada inválida. Tentando denovo:");
            }
        }
        return i;
    }

    public char createNewEntity() {
        while (true) {
            Utils.printDivider();

            Utils.printCentered("Que tipo de entidade deseja criar?\n");
            Utils.printCentered("[P]ersonagem");
            Utils.printCentered("[M]onstro");
            Utils.printCentered("[I]tem");
            char choice = Utils.scanFirstChar();

            switch (choice) {
                case 'p':
                case 'm':
                case 'i': return choice;
                default: Utils.printCentered("Entrada inválida. Tentando denovo: ");
            }
        }
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
        System.out.println();
        System.out.println("Você encontrou um " + monster.getName() + "!");
    }

    public void displayLevelUp(int level) {
        System.out.println();
        System.out.println("Parabéns! Você subiu de nível para o nível " + level + ".");
    }

    public void displayOptions() {
        System.out.println();
        System.out.println("O que deseja fazer?");
        System.out.println("1 - Abrir baú");
        System.out.println("2 - Usar Item");
        System.out.println("3 - Seguir para a próxima sala");
        System.out.println("4 - Salvar jogo e sair");
    }

    public int getUserAction() {
        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();
        scanner.nextLine();
        return action;
    }

    public void displayInvalidOption() {
        System.out.println("Opção Inválida. Tente novamente.");
    }

    //Chest Displays
    public void displayChestEmpty() {
        System.out.println("O baú está vazio.");
    }

    public void displayChestItems(List<Item> items) {
        System.out.println("Você encontrou os seguintes itens no baú:");
        for (Item item : items) {
            System.out.println(item.getName());
        }
    }
}
