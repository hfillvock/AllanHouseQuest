package br.edu.up.allanhousequest.models;

import java.util.ArrayList;
import java.util.List;

public class Character extends Entity implements Interagivel{
    private List<Item> inventario;

    public List<Item> getInventario() {
        return inventario;
    }

    public void setInventario(List<Item> inventario) {
        this.inventario = inventario;
    }

    public Character(String name, int healthPoints, int attackPointss, int defensePoints) {
        super(name, healthPoints, attackPointss, defensePoints);
        this.inventario = new ArrayList<>();
    }

    @Override
    public void attack(Entity target) {
        // Implementação
    }

    @Override
    public void receiveDamage(int damage) {
        // Implementação
    }

    public void useItem(Item item) {
        // Implementação para usar item
    }

    public void useItem(Item item, Entity target) {
        // Sobrecarga de método para usar item em um alvo (polimorfismo)
    }

    @Override
    public void interact() {
        // Implementação da interface Interagivel
    }

    
}
