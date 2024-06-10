package br.edu.up.allanhousequest.models;

import java.io.Serializable;

public class Item implements Serializable{
	
	private int id;
	private String name;
	private String type;
	private int value;
	
	//Constructor
	public Item(int id, String name, String type, int value) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.value = value;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", type=" + type + ", value=" + value + "]";
	}

}
