package br.edu.up.allanhousequest.views;

import java.util.List;
import br.edu.up.allanhousequest.models.Item;
import br.edu.up.allanhousequest.models.Monster;
import br.edu.up.allanhousequest.models.Player;

public class PlayerView {

    // display player info
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
    
}
