package br.edu.up.allanhousequest.controllers;

import br.edu.up.allanhousequest.models.Monster;
import br.edu.up.allanhousequest.models.RPGModel;
import br.edu.up.allanhousequest.views.RPGView;

public class RPGController {
    private RPGModel model;
    private RPGView view;

    public static Boolean isRunning;

    public RPGController(RPGModel model, RPGView view) {
        this.model = model;
        this.view = view;
    }

    public void startGame() {

    }

    public void gameLoop() {

    }

    public void startBattle(Character character, Monster monster) {
        // Implementação da lógica de batalha
    }
    
}
