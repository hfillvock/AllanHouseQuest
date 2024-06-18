package br.edu.up.allanhousequest.models;

public class Armour extends Item{
    private int armourClass;

    public Armour(String name, int armourClass){
        super(name);
        this.armourClass = armourClass;
    }
    public int getarmourClass(){
        return armourClass;
    }
    public void setarmourClass(int armourcClass){
        this.armourClass = armourcClass;
    }
    public void useItem(){
        
    }
}
