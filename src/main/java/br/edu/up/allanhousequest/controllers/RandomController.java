package br.edu.up.allanhousequest.controllers;

import java.util.Random;

public class RandomController {
    
    public static int diceRoll() {
        Random random  = new Random();
        int diceRoll = random.nextInt(20) + 1;

        return diceRoll;
    }

    // métodos para auxiliar na geração de encontro aleatório
}
