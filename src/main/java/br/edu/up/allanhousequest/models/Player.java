package br.edu.up.allanhousequest.models;

import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {

    private int experiencePoints;
    private int experienceRequired;
    private List<Item> inventory;
    private Weapon equippedWeapon;
    private Armour equippedArmour;

    // Constructor
    public Player(String name, int level) {
        super(name, level);
        this.setHitPoints(10 * level);
        this.setTotalHitPoints(this.getHitPoints());
        this.setAttackModifier((level/2) * 5);
        this.experiencePoints = 0;
        
        this.experienceRequired = calculateExperienceRequired(level); // Inicializa com pontos de experiência equivalentes ao nível
        
        inventory = new ArrayList<>();
        this.equippedWeapon = new Weapon("Espada Curta", 1, 6, 1);
        this.equippedArmour = new Armour("Armadura de Couro", 1, 12);
        inventory.add(equippedWeapon);
        inventory.add(equippedArmour);
        
        this.setDefenseValue(getEquippedArmour().getarmourClass());
    }

    // Getters and Setters

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Item equippedWeapon) {
        this.equippedWeapon = (Weapon) equippedWeapon;
    }

    public Armour getEquippedArmour() {
        return equippedArmour;
    }

    public void setEquippedArmour(Item equippedArmour) {
        this.equippedArmour = (Armour) equippedArmour;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    // Methods
    public static int calculateExperienceRequired(int level) {
        // Padrão: O dobro dos pontos de experiência do nível anterior
        return (int) (Math.pow(2, level - 1) * 10);
    }
    
    public void addItem(Item item) {
        inventory.add(item);
    }
    
    @Override
    public String toString() {
        return super.getName() + "\nNível: " + super.getLevel() + "\nHP: " + super.getHitPoints() + "\nXP: " + experiencePoints;
    }

    public int getExperienceRequired() {
        return experienceRequired;
    }

    public void setExperienceRequired(int experienceRequired) {
        this.experienceRequired = experienceRequired;
    }
}
