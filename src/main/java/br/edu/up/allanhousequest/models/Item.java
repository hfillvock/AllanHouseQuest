package br.edu.up.allanhousequest.models;

import java.io.Serializable;

public abstract class Item implements Serializable{
	
	private String name;
	
	//Constructor
	public Item(String name) {
		this.name = name;
	}
	
	//Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Item [name=" + name +"]";
	}
	
	public abstract void useItem();
}
