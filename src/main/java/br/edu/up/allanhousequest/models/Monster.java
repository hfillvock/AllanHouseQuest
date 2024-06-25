package br.edu.up.allanhousequest.models;

public class Monster extends Entity {

	private int experiencePoints;
	private int damageDice;
	private int damageDiceQuantity;
	private int damageModifier;
	
	//Constructor
	public Monster(String name, int level, int hitPoints, int attackModifier, int defenseValue, int experiencePoints, int damageDice, int damageDiceQuantity, int damageModifier) {
		super(name, level, hitPoints, attackModifier, defenseValue);
		this.experiencePoints = experiencePoints;
		this.damageDice = damageDice;
		this.damageDiceQuantity = damageDiceQuantity;
		this.damageModifier = damageModifier;
	}
	
	//Getters and Setters
	public int getExperiencePoints() {
		return experiencePoints;
	}

	public void setExperiencePoints(int experiencePoints) {
		this.experiencePoints = experiencePoints;
	}

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

	@Override
    public String toString() {
        return super.getName() + "\nNível: " + super.getLevel()+ "\nHP: " + super.getHitPoints() + "\nATK MOD: " + super.getAttackModifier() + "\nDEF: " + super.getDefenseValue() + "\nXP: " + experiencePoints + "\nDado: " + damageDice + "\nQuant. dados: " + damageDiceQuantity + "\nDMG MOD: " + damageModifier;
    }
}
