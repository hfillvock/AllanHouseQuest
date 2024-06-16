package br.edu.up.allanhousequest.views;

import br.edu.up.allanhousequest.models.Monster;
import br.edu.up.allanhousequest.utils.Utils;

public class MonsterView {

    public static Monster createNewMonster() {
        Utils.printDivider();

        Utils.printCentered("Criando monstro.");

        Utils.printCentered("Insira o nome do monstro: ");
        String name = Utils.scanLine();

        Utils.printCentered("Insira o nível do monstro: ");
        int level = Utils.scanInt();

        Utils.printCentered("Insira o hp do monstro: ");
        int hitPoints = Utils.scanInt();

        Utils.printCentered("Insira o modificador de ataque do monstro: ");
        int attackModifier = Utils.scanInt();

        Utils.printCentered("Insira o valor de defesa do monstro: ");
        int defenseValue = Utils.scanInt();

        Utils.printCentered("Insira o valor de experiência do monstro: ");
        int experiencePoints = Utils.scanInt();

        Utils.printCentered("Insira o valor do dado de ataque monstro: ");
        int damageDice = Utils.scanInt();

        Utils.printCentered("Insira a quantidade de dados de ataque do monstro: ");
        int damageDiceQuantity = Utils.scanInt();

        Utils.printCentered("Insira o modificador de dano do monstro: ");
        int damageModifier = Utils.scanInt();

        Monster monster = new Monster(name, level, hitPoints, attackModifier, defenseValue, experiencePoints, damageDice, damageDiceQuantity, damageModifier);
        return monster;
    }

}
