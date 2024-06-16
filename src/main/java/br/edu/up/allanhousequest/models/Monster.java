package br.edu.up.allanhousequest.models;

import br.edu.up.allanhousequest.utils.Utils;

public class Monster extends Entity {

	private int damageDice;
	private int damageDiceQuantity;
	private int damageModifier;
	
	//Constructor
	public Monster(String name, int level, int hitPoints, int attackModifier, int defenseValue, int damageDice, int damageDiceQuantity, int damageModifier) {
		super(name, level, hitPoints, attackModifier, defenseValue);
		this.damageDice = damageDice;
		this.damageDiceQuantity = damageDiceQuantity;
		this.damageModifier = damageModifier;
	}
	
	//Getters and Setters

	public int getDamageDice() {
		return damageDice;
	}

	public void setDamageDice(int damageDice) {
		this.damageDice = damageDice;
	}

	public int getDamageDiceQuantity() {
		return damageDiceQuantity;
	}

	public void setDamageDiceQuantity(int damageDiceQuantity) {
		this.damageDiceQuantity = damageDiceQuantity;
	}

	public int getDamageModifier() {
		return damageModifier;
	}

	public void setDamageModifier(int damageModifier) {
		this.damageModifier = damageModifier;
	}
	
	//Methods
	
	public void attack(Entity target) { //desatualizado
		int diceRoll = Utils.diceRoll();

        System.out.println(getName() + " atacou " + target.getName() + "!");
		System.out.println((diceRoll + getattackModifier()) + " (" + diceRoll + "+" + getattackModifier() + ") vs " + target.getDefenseValue());
        
		if (diceRoll + getattackModifier() >= target.getDefenseValue()) {
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
