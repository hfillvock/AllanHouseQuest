package br.edu.up.allanhousequest.models;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int level;
    private List<Monster> monsters;

    public Room(int level) {
        this.level = level;
        monsters = new ArrayList<>();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    // métodos

    // Preenchimento da lista de monstros com o nível equivalente ao do jogador
    public void fillWithMonsters(List<Monster> allMonsters) {
        for (Monster monster : allMonsters) {
            if (monster.getLevel() <= level) {
                monsters.add(monster);
            }
        }
    }
}
