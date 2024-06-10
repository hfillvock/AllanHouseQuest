package br.edu.up.allanhousequest.utils;

import java.util.Random;

public class Utils {
    
    public static int diceRoll() {
        Random random  = new Random();
        int diceRoll = random.nextInt(20) + 1;

        return diceRoll;
    }
}
