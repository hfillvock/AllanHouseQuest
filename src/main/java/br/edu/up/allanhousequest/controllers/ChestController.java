package br.edu.up.allanhousequest.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.edu.up.allanhousequest.models.Chest;
import br.edu.up.allanhousequest.models.Item;
import br.edu.up.allanhousequest.models.Player;
import br.edu.up.allanhousequest.models.RPGModel;
import br.edu.up.allanhousequest.views.ChestView;

public class ChestController {
    private Chest model;
    private ChestView view;

    public ChestController(Chest model, ChestView view) {
        this.view = view;
        this.model = model;
    }

    public void openChest(Player player) {
        List<Item> chestItems = model.getItems();

        if (chestItems.isEmpty()) {
            view.displayChestEmpty();
        } else {
            view.displayChestItems(chestItems);
            for (Item item : chestItems) {
                player.addItem(item);
            }
        }
    }

    private void fillChest(Player playerModel,RPGModel rpgModel) {
        int chestLevel = playerModel.getLevel();

        List<Item> chestPossibleItems = new ArrayList<>();
        List<Item> chestItems = new ArrayList<>();
        
        // Preenchimento da lista de itens compatíveis com o nível equivalente ao do jogador
        for (Item item : rpgModel.getItems()) {
            // Implementar a lógica que correlaciona o nível do jogador com o "nível" dos itens
            chestPossibleItems.add(item);
        }

        Random random = new Random();

        // Define a quantidade de itens no baú, por exemplo, entre 1 e 3 itens
        int numberOfItems = random.nextInt(3) + 1;

        for (int i = 0; i < numberOfItems; i++) {
            Item randomItem = chestPossibleItems.get(random.nextInt(chestPossibleItems.size()));
            chestItems.add(randomItem);
        }

        model.setItems(chestItems);
    }
}
