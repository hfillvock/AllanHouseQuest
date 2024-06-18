package br.edu.up.allanhousequest.models;

public class Weapon extends Item{
    private int damageDice;
    private int damageDiceQuantity;

    public Weapon(String name, int damageDice, int damageDiceQuantity){
        super(name);
        this.damageDice = damageDice;
        this.damageDiceQuantity = damageDiceQuantity;
    }
    public int getdamageDice(){
        return damageDice;
    }
    public void setdamageDice(int damageDice){
        this.damageDice = damageDice;
    }
    public int getdamageDiceQuantity(){
        return damageDiceQuantity;
    }
    public void setdamageDiceQuantity(int damageDiceQuantity){
        this.damageDiceQuantity = damageDiceQuantity;
    }
    public void useItem(){
        
    }

}
