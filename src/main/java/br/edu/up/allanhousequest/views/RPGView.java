package br.edu.up.allanhousequest.views;

import br.edu.up.allanhousequest.models.RPGModel;
import br.edu.up.allanhousequest.utils.Utils;

public class RPGView {

    public void mainMenu() {
        System.out.println("\r\n" + //
                        "                                                                                                  \r\n" + //
                        "    _    _     _        _    _   _   _   _  ___  _   _ ____  _____    ___  _   _ _____ ____ _____ \r\n" + //
                        "   / \\  | |   | |      / \\  | \\ | | | | | |/ _ \\| | | / ___|| ____|  / _ \\| | | | ____/ ___|_   _|\r\n" + //
                        "  / _ \\ | |   | |     / _ \\ |  \\| | | |_| | | | | | | \\___ \\|  _|   | | | | | | |  _| \\___ \\ | |  \r\n" + //
                        " / ___ \\| |___| |___ / ___ \\| |\\  | |  _  | |_| | |_| |___) | |___  | |_| | |_| | |___ ___) || |  \r\n" + //
                        "/_/   \\_|_____|_____/_/   \\_|_| \\_| |_| |_|\\___/ \\___/|____/|_____|  \\__\\_\\\\___/|_____|____/ |_|  \r\n" + //
                        "                                                                                                  \r\n" + //
                        "");
    }

    public String createNewPlayer() {
        Utils.printDivider();

        Utils.printCentered("Criando personagem.");

        Utils.printCentered("Insira o nome do personagem: ");
        String name = Utils.scanLine();

        return name;
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
}
