package br.edu.up.allanhousequest.models;

import br.edu.up.allanhousequest.utils.Utils;

public class Potion extends Item{
    private int potionDice;
    private int potionDiceQuantity;

    //Constructor
    public Potion(String name, int level, int potionDice, int potionDiceQuantity) {
        super(name, level);
        this.potionDice = potionDice;
        this.potionDiceQuantity = potionDiceQuantity;
    }

    //Getters and Setters
    public int getPotionDice() {
        return potionDice;
    }

    public void setPotionDice(int potionDice) {
        this.potionDice = potionDice;
    }

    public int getPotionDiceQuantity() {
        return potionDiceQuantity;
    }

    public void setPotionDiceQuantity(int potionDiceQuantity) {
        this.potionDiceQuantity = potionDiceQuantity;
    }

    //Methods
    @Override
    public void useItem(Player player) {
        player.setHitPoints(player.getHitPoints() + Utils.diceRoll(potionDiceQuantity, potionDice));
    }

    @Override
    public String toString() {
        return super.getName() + "\nNível: " + super.getLevel() + "\nDado: " + potionDice + "\nQuant. dados: " + potionDiceQuantity;
    }
}
