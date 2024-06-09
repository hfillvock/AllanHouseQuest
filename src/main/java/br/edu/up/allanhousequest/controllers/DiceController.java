package br.edu.up.allanhousequest.controllers;

import java.util.Random;

public class DiceController {
    
    public static int diceRoll() {
        Random random  = new Random();
        int diceRoll = random.nextInt(20) + 1;

        return diceRoll;
    }
}
