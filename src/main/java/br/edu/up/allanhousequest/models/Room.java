package br.edu.up.allanhousequest.models;

import java.util.List;

public class Room {
    private List<Monster> monsters;

    public Room(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }
}
