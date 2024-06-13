package br.edu.up.allanhousequest.controllers;

import br.edu.up.allanhousequest.models.Player;
import br.edu.up.allanhousequest.views.PlayerView;

public class PlayerController {
    private Player model;
    private PlayerView view;

    public PlayerController(Player model, PlayerView view) {
        this.model = model;
        this.view = view;
    }

    public void attack() {

    }

    public void receiveDamage(){
        
    }
}
