package br.edu.up.allanhousequest.views;

import java.util.Scanner;

import br.edu.up.allanhousequest.models.*;

public class RoomView {
    public void displayMonsterEncounter(Monster monster) {
        System.out.println();
        System.out.println("Você encontrou um " + monster.getName() + "!");
    }

    public void displayLevelUp(int level) {
        System.out.println();
        System.out.println("Parabéns! Você subiu de nível para o nível " + level + ".");
    }

    public void displayOptions() {
        System.out.println();
        System.out.println("O que deseja fazer?");
        System.out.println("1 - Abrir baú");
        System.out.println("2 - Usar Item");
        System.out.println("3 - Seguir para a próxima sala");
        System.out.println("4 - Salvar jogo e sair");
    }

    public int getUserAction() {
        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();
        scanner.nextLine();
        return action;
    }

    public void displayInvalidOption() {
        System.out.println("Opção Inválida. Tente novamente.");
    }
}
