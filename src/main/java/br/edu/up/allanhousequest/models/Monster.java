package br.edu.up.allanhousequest.models;

import java.io.Serializable;

import br.edu.up.allanhousequest.controllers.RandomController;

public class Monster extends Entity implements Serializable {
	
	private int damageValue;
	
	//Constructor
	public Monster(String name, int hitPoints, int attackValue, int defenseValue, int damageValue) {
		super(name, hitPoints, attackValue, defenseValue);
		this.damageValue = damageValue;
	}
	
	//Getters and Setters
	public int getDamageValue() {
		return damageValue;
	}

	public void setDamageValue(int damageValue) {
		this.damageValue = damageValue;
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
				+ "\nDamage: "
				+ getDamageValue()
				+ "\n----------\n";
		}
	
	//Methods
	public void attack(Entity target) {
		int diceRoll = RandomController.diceRoll();

        System.out.println(getName() + " atacou " + target.getName() + "!");
		System.out.println((diceRoll + getAttackValue()) + " (" + diceRoll + "+" + getAttackValue() + ") vs " + target.getDefenseValue());
        
		if (diceRoll + getAttackValue() >= target.getDefenseValue()) {
			target.receiveDamage(damageValue);
			System.out.println("Droga! O ataque acertou e causou " + damageValue + " pontos de dano.");
		} else {
			System.out.println("Ufa! Você conseguiu desviar do ataque.");
		}
	}
		
	public void receiveDamage(int damage) {
		setHitPoints(getHitPoints() - damage);
	}

}
