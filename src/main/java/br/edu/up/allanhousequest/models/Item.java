package br.edu.up.allanhousequest.models;

import java.io.Serializable;

public abstract class Item implements Serializable{
	
	private String name;
	private int level;
	
	//Constructor
	public Item(String name, int level) {
		this.name = name;
		this.level = level;
	}
	
	//Getters and Setters
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

	@Override
	public String toString() {
		return "Item [name=" + name +"]";
	}
	
	//Methods
	public abstract void useItem(Player player);
}
