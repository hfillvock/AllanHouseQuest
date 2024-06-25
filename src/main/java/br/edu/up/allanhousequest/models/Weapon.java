package br.edu.up.allanhousequest.models;

public class Weapon extends Item{
    private int damageDice;
    private int damageDiceQuantity;

    //Constructor
    public Weapon(String name, int level, int damageDice, int damageDiceQuantity){
        super(name, level);
        this.damageDice = damageDice;
        this.damageDiceQuantity = damageDiceQuantity;
    }

    //Getters and Setters
    public int getDamageDice(){
        return damageDice;
    }

    public void setDamageDice(int damageDice){
        this.damageDice = damageDice;
    }

    public int getDamageDiceQuantity(){
        return damageDiceQuantity;
    }

    public void setdamageDiceQuantity(int damageDiceQuantity){
        this.damageDiceQuantity = damageDiceQuantity;
    }
    
    @Override
    public String toString() {
        return super.getName() + "\nNível: " + super.getLevel() + "\nDado: " + damageDice + "\nQuant. dados: " + damageDiceQuantity;
    }
}
