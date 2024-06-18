package br.edu.up.allanhousequest.utils;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

public class Utils {
    
    public static Scanner scanner = new Scanner(System.in);
    public static StringUtils strUtils = new StringUtils();

    public static int diceRoll(int diceQuantity, int diceNumber) {
        Random random  = new Random();
        int diceRoll = 0;
        
        for (int i = 0; i < diceQuantity; i++) {
            diceRoll += random.nextInt(diceNumber) + 1;
        }
        
        return diceRoll;
    }

    public static char scanFirstChar() {
        System.out.print("\n> ");
        char readChar = scanner.next().toLowerCase().charAt(0);

        clearScannerBuffer();

        return readChar;
    }

    public static int scanInt() {
        while (true) {
            try {
                System.out.print("\n> ");
                int readInt = scanner.nextInt();
                return readInt;
            } catch (InputMismatchException e) {
                printCentered("Entrada inválida, apenas números inteiros são aceitos.");
            } finally {
                clearScannerBuffer();
            }
        }
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
        System.out.println("\n");
    }

    public static void printCentered(String text) {
        int width = 96;
        String centeredText = StringUtils.center(text, width);
        System.out.println(centeredText);
    }

    public static void clearScannerBuffer() {
        scanner.nextLine();
    }
}
