package br.edu.up.allanhousequest.models;

import java.io.Serializable;

public abstract class Entity implements Serializable {
	
	private int id;
	private String name;
	private int level;
	private int hitPoints;
	private int attackModifier;
	private int defenseValue;
	
	//Constructor
	public Entity(int id, String name, int level, int hitPoints, int attackModifier, int defenseValue) {
		this.id = id;
		this.name = name;
		this.level = level;
		this.hitPoints = hitPoints;
		this.attackModifier = attackModifier;
		this.defenseValue = defenseValue;
	}
	
	//Getters and Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	public int getattackModifier() {
		return attackModifier;
	}

	public void setattackModifier(int attackModifier) {
		this.attackModifier = attackModifier;
	}

	public int getDefenseValue() {
		return defenseValue;
	}

	public void setDefenseValue(int defenseValue) {
		this.defenseValue = defenseValue;
	}
	
	//Abstract Methods
	public abstract void attack(Entity target);
	
	public abstract void receiveDamage(int damage);
}
