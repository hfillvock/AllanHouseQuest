package br.edu.up.allanhousequest.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.edu.up.allanhousequest.controllers.RandomController;

public class Player extends Entity implements Serializable{
	
	private List<Item> inventory;
	private Item equippedWeapon;
	private Item equippedArmour;
	
	//Constructor
	public Player(String name, int hitPoints, int attackValue, int defenseValue) {
		super(name, hitPoints, attackValue, defenseValue);
		this.inventory = new ArrayList<>();
	}
	
	//Getters and Setters
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
	
	//To String
	@Override
	public String toString() {
		return "----------\n"
				+ getName()
				+ "\nHit Points: "
				+ getHitPoints()
				+ "\nAttack: "
				+ getAttackValue()
				+ "\nDefense: "
				+ getDefenseValue()
				+ "\n----------\n";
	}
	
	//Methods
	@Override
	public void attack(Entity target) {
		int diceRoll = RandomController.diceRoll();
        
        System.out.println(getName() + " atacou " + target.getName() + "!");
		System.out.println((diceRoll + getAttackValue()) + " (" + diceRoll + "+" + getAttackValue() + ") vs " + target.getDefenseValue());
        
		if (diceRoll + getAttackValue() >= target.getDefenseValue()) {
			target.receiveDamage(equippedWeapon.getValue());
			System.out.println("Ataque bem-sucedido! " + equippedWeapon.getValue() + " pontos de dano causados!");
		} else {
			System.out.println("Ataque mal-sucedido...");
		}
	}
	
	@Override
	public void receiveDamage(int damage) {
		setHitPoints(getHitPoints() - damage);
	}	

	public void equipWeapon(Item item) {
	}
	
	public void equipArmour(Item item) {
	}
}

