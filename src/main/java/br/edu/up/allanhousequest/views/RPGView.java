package br.edu.up.allanhousequest.views;

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
            System.out.println(i + ":\n" + model.getPlayers().get(i));
        }
    }

    public int selectPlayer() {
        Utils.printDivider();

        Utils.printCentered("Escolhendo personagem.");

        Utils.printCentered("Insira o índice do personagem.");
        int i = Utils.scanInt();
        
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
}
