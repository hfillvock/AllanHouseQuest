package br.edu.up.allanhousequest.controllers;

import br.edu.up.allanhousequest.models.Monster;
import br.edu.up.allanhousequest.models.RPGModel;
import br.edu.up.allanhousequest.views.RPGView;

public class RPGController {
    private RPGModel model;
    private RPGView view;

    public Boolean isRunning;

    public RPGController(RPGModel model, RPGView view) {
        this.model = model;
        this.view = view;
        this.isRunning = false;
    }

    public void startGame() {
        // Criar ou carregar player, e chamar o gameLoop
    }

    public void gameLoop() {
        while(isRunning == true) {
            generateEncounter();
        }
    }

    public void generateEncounter() {
        // Gerar encontro aleatório com monstros, interagível, ou sei lá o que, por meio de RandomController
    }

    public void startBattle(Character character, Monster monster) {
        // Implementação da lógica de batalha
    }
    
}
