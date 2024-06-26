package br.edu.up.allanhousequest;

import br.edu.up.allanhousequest.controllers.RPGController;
import br.edu.up.allanhousequest.factories.RPGFactory;

public class Main {

    public static void main(String[] args) {
        RPGController RPG = RPGFactory.getRPG();
        
        RPG.startGame();
    }
}