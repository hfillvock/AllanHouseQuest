package br.edu.up.allanhousequest.models;


public class Monster extends Entity{
    
    public Monster(String name, int healthPoints, int attackPointss, int defensePoints) {
        super(name, healthPoints, attackPointss, defensePoints);
    }

    @Override
    public void attack(Entity target) {
        // Implementação
    }

    @Override
    public void receiveDamage(int damage) {
        // Implementação
    }

    
}
