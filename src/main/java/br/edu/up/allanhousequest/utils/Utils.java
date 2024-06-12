package br.edu.up.allanhousequest.utils;

import java.util.Random;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

public class Utils {
    
    public static Scanner scanner = new Scanner(System.in);
    public static StringUtils strUtils = new StringUtils();

    public static int diceRoll() {
        Random random  = new Random();
        int diceRoll = random.nextInt(20) + 1;

        return diceRoll;
    }

    public static int scanInt() {
        System.out.print("\n> ");

        return scanner.nextInt();
    }

    public static String scanLine() {
        System.out.print("\n> ");

        return scanner.nextLine();
    }

    public static void printDivider() {
        System.out.println();
        for (int i = 0; i < 96; i++) {
            System.out.print("#");
        }
        System.out.println();

    }

    public static void printCentered(String text) {
        int width = 96;
        String centeredText = StringUtils.center(text, width);
        System.out.println(centeredText);
    }

}
