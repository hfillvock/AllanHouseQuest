package br.edu.up.allanhousequest.models;

import br.edu.up.allanhousequest.utils.Utils;

public class Monster extends Entity {
	
	private int id;
	private int damageValue;
	
	//Constructor
	public Monster(int id, String name, int hitPoints, int attackValue, int defenseValue, int damageValue) {
		super(id, name, hitPoints, attackValue, defenseValue);
		this.damageValue = damageValue;
	}
	
	//Getters and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
		int diceRoll = Utils.diceRoll();

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
