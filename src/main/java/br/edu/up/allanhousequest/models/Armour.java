package br.edu.up.allanhousequest.models;

public class Armour extends Item{
    private int armourClass;

    //Constructor
    public Armour(String name, int level, int armourClass){
        super(name, level);
        this.armourClass = armourClass;
    }

    //Getters and Setters
    public int getarmourClass(){
        return armourClass; 
    }
    public void setarmourClass(int armourcClass){
        this.armourClass = armourcClass;
    }
    
    //Methods
    @Override
    public void useItem(Player player) {
        player.setEquippedArmour(this);
    }
}
