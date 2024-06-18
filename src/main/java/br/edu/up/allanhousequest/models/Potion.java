package br.edu.up.allanhousequest.models;

public class Potion extends Item{
    private int potionDice;
    private int potionDiceQuantity;

    public Potion(String name, int potionDice, int potionDiceQuantity){
        super(name);
        this.potionDice = potionDice;
        this.potionDiceQuantity = potionDiceQuantity;
    }
    public int getpotionDice(){
        return potionDice;
    }
    public void setdamageDice(int potionDice){
        this.potionDice = potionDice;
    }
    public int getpotionDiceQuantity(){
        return potionDiceQuantity;
    }
    public void setdamageDiceQuantity(int potionDiceQuantity){
        this.potionDiceQuantity = potionDiceQuantity;
    }
    public void useItem(){
        
    }

}
