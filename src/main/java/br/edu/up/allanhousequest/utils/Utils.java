package br.edu.up.allanhousequest.utils;

import java.util.Random;
import java.util.Scanner;

public class Utils {
    
    public static Scanner scanner = new Scanner(System.in);

    public static int diceRoll() {
        Random random  = new Random();
        int diceRoll = random.nextInt(20) + 1;

        return diceRoll;
    }

    public static void printDivider() {
        System.out.println();
        for (int i = 0; i < 48; i++) {
            System.out.print("#");
        }
        System.out.println();

    }
}
