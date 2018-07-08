package pl.coderslab.warsztat_01.zadanie_04;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        System.out.println(throwTheDice("3D6+10"));

    }

    private static int throwTheDice(String dice) {

        String[] tab = dice.split("D|\\+|-");
        int x, y, z, diceThrow;

        x = tab[0].equals("") ? 1 : Integer.parseInt(tab[0]);
        y = Integer.parseInt(tab[1]);
        z = tab.length == 3 ? Integer.parseInt(tab[2]) : 0;

        if (dice.contains("+")) {
            diceThrow = getDice(y, x) + z;
        } else if (dice.contains("-")) {
            diceThrow = getDice(y, x) - z;
        } else {
            diceThrow = getDice(y, x);
        }

        return diceThrow;


    }

    private static int getDice(int kindOfDice, int countOfThrow) {
        Random random = new Random();
        int throwDice = 0;
        
        for (int i = 0; i < countOfThrow; i++) {
            throwDice += random.nextInt(kindOfDice) + 1;
        }

        System.out.println("Suma z rzutów kostą: " + throwDice);
        return throwDice;
    }
}
