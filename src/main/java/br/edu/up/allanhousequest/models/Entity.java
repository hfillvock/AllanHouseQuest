package br.edu.up.allanhousequest.models;

public abstract class Entity {
    
    private String name;
    private int HealthPoints;
    private int AttackPoints;
    private int DefensePoints;


    public Entity(String name, int healthPoints, int attackPointss, int defensePoints) {
        this.name = name;
        this.HealthPoints = healthPoints;
        this.AttackPoints = attackPointss;
        this.DefensePoints = defensePoints;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getHealthPoints() {
        return HealthPoints;
    }


    public void setHealthPoints(int healthPoints) {
        HealthPoints = healthPoints;
    }


    public int getAttackPointss() {
        return AttackPoints;
    }


    public void setAttackPointss(int attackPoints) {
        AttackPoints = attackPoints;
    }


    public int getDefensePoints() {
        return DefensePoints;
    }


    public void setDefensePoints(int defensePoints) {
        DefensePoints = defensePoints;
    }

    public abstract void attack(Entity target);
    public abstract void receiveDamage(int damage);

}
