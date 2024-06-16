package br.edu.up.allanhousequest.models;

import java.util.ArrayList;
import java.util.List;

import br.edu.up.allanhousequest.utils.Utils;

public class Player extends Entity {

	private int experiencePoints;
	private List<Item> inventory;
	private Item equippedWeapon;
	private Item equippedArmour;
	private int testedebobo;
	
	//Constructor
	public Player(String name, int level, int hitPoints, int attackModifier, int defenseValue) {
		super(name, level, hitPoints, attackModifier, defenseValue);
		this.experiencePoints = calculateExperienceRequired(level); // Inicializa com pontos de experiência equivalentes ao nível
		this.inventory = new ArrayList<>();
	}
	
	//Getters and Setters
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

	// Método para calcular os pontos de experiência necessários para atingir um certo nível
	public static int calculateExperienceRequired(int level) {
        	// Padrão: O dobro dos pontos de experiência do nível anterior
        	return (int) (Math.pow(2, level - 1) * 10);
    	}
	
	//Methods
	@Override
	public void attack(Entity target) {
		int diceRoll = Utils.diceRoll(1, 20);
        
        System.out.println(getName() + " atacou " + target.getName() + "!");
		System.out.println((diceRoll + getAttackModifier()) + " (" + diceRoll + "+" + getAttackModifier() + ") vs " + target.getDefenseValue());
        
		if (diceRoll + getAttackModifier() >= target.getDefenseValue()) {
			target.receiveDamage(equippedWeapon.getValue());
			System.out.println("Ataque bem-sucedido! " + equippedWeapon.getValue() + " pontos de dano causados!");
		} else {
			System.out.println("Ataque mal-sucedido...");
		}
	}	
}

