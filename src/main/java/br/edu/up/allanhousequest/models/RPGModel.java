package br.edu.up.allanhousequest.models;

import java.util.ArrayList;
import java.util.List;

public class RPGModel {
    private List<Character> characters;
    private List<Monster> monsters;
    private List<Item> items;
    private Character player;

    public RPGModel() {
        this.characters = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public void removeCharacter(Character character) {
        characters.remove(character);
    }

    public List<Character> listCharacters() {
        return characters;
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public void removeMonster(Monster monster) {
        monsters.remove(monster);
    }

    public List<Monster> listMonsters() {
        return monsters;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> listItens() {
        return items;
    }
}
