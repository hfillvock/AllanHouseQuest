package br.edu.up.allanhousequest.models;

import java.io.Serializable;

public abstract class Entity implements Serializable {
	
	private int id;
	private String name;
	private int hitPoints;
	private int attackValue;
	private int defenseValue;
	
	//Constructor
	public Entity(int id, String name, int hitPoints, int attackValue, int defenseValue) {
		this.id = id;
		this.name = name;
		this.hitPoints = hitPoints;
		this.attackValue = attackValue;
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

	public int getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	public int getAttackValue() {
		return attackValue;
	}

	public void setAttackValue(int attackValue) {
		this.attackValue = attackValue;
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
