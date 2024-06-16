package br.edu.up.allanhousequest.views;

import br.edu.up.allanhousequest.models.Item;
import br.edu.up.allanhousequest.utils.Utils;

public class ItemView {

    public static Item createNewItem() {
        Utils.printDivider();

        Utils.printCentered("Criando item.");

        Utils.printCentered("Insira o nome do item: ");
        String name = Utils.scanLine();

        Utils.printCentered("Insira o tipo do item: ");
        String type = Utils.scanLine();

        Utils.printCentered("Insira o valor do item: ");
        int value = Utils.scanInt();

        Item item = new Item(name, type, value);
        return item;
    }
}
