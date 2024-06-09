package br.edu.up.allanhousequest.models;

public class Item {
	
	private String name;
	private String type;
	private int value;
	
	//Constructor
	public Item(String name, String type, int value) {
		this.name = name;
		this.type = type;
		this.value = value;
	}
	
	//Getters and Setters
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

}
