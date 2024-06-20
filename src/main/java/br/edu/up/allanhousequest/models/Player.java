package br.edu.up.allanhousequest.models;

import java.util.ArrayList;
import java.util.List;

import br.edu.up.allanhousequest.utils.Utils;

public class Player extends Entity {

    private int experiencePoints;
    private List<Item> inventory;
    private Item equippedWeapon;
    private Item equippedArmour;

    // Constructor
    public Player(String name, int level, int hitPoints, int attackModifier, int defenseValue) {
        super(name, level, hitPoints, attackModifier, defenseValue);
        this.experiencePoints = calculateExperienceRequired(level); // Inicializa com pontos de experiência equivalentes ao nível
        inventory = new ArrayList<>();
    }

    // Getters and Setters
    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public Item getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Item equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public Item getEquippedArmour() {
        return equippedArmour;
    }

    public void setEquippedArmour(Item equippedArmour) {
        this.equippedArmour = equippedArmour;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    // Método para calcular os pontos de experiência necessários para atingir um certo nível
    public int calculateExperienceRequired(int level) {
        // Padrão: O dobro dos pontos de experiência do nível anterior
        return (int) (Math.pow(2, level - 1) * 10);
    }

    // Methods
    @Override
    public void attack(Entity target) {
        int rolledDice = Utils.diceRoll(1, 20);

        System.out.println(getName() + " atacou " + target.getName() + "!");
        System.out.println((rolledDice + getAttackModifier()) + " (" + rolledDice + "+" + getAttackModifier() + ") vs " + target.getDefenseValue());

        int damage = (equippedWeapon != null) ? equippedWeapon.getValue() : getAttackModifier();
        if (rolledDice + getAttackModifier() >= target.getDefenseValue()) {
            target.receiveDamage(damage);
            System.out.println("Ataque bem-sucedido! " + damage + " pontos de dano causados!");
        } else {
            System.out.println("Ataque mal-sucedido...");
        }
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void useItem(Item item) {
        if (item.getType().equals("HealthPotion")) {
            this.setHitPoints(this.getHitPoints() + item.getValue());
            System.out.println("Você recuperou " + item.getValue() + " pontos de vida.");
        } else if (item.getType().equals("Weapon")) {
            this.setEquippedWeapon(item);
            System.out.println("Você equipou a arma: " + item.getName());
        } else if (item.getType().equals("Armour")) {
            this.setEquippedArmour(item);
            System.out.println("Você equipou a armadura: " + item.getName());
        } else {
            System.out.println("Tipo de item desconhecido.");
        }
    }

    @Override
    public String toString() {
        return super.getName() + "\nNível: " + super.getLevel() + "\nHP: " + super.getHitPoints() + "\nXP: " + experiencePoints;
    }
}
