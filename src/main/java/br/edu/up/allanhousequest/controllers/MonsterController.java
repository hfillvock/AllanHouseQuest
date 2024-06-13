package br.edu.up.allanhousequest.controllers;

import br.edu.up.allanhousequest.models.Monster;
import br.edu.up.allanhousequest.views.MonsterView;

public class MonsterController {
    private Monster model;
    private MonsterView view;

    public MonsterController(Monster model, MonsterView view) {
        this.model = model;
        this.view = view;
    }

    public void attack() {

    }

    public void receiveDamage(){
        
    }
}
