package pl.coderslab.warsztat_01.zadanie_03;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Pomyśl liczbę od 0 do 100, \n a ja zgadne w max 10 próbach");
        int min = 0;
        int max = 1000;
        guessNumber(min, max);
    }

    private static void guessNumber(int min, int max) {
        int guess = ((max - min) / 2) + min;

        System.out.println("Zgaduje: " + guess);

        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        switch (answer) {
            case "więcej":
                guessNumber(guess, max);
                break;
            case "mniej":
                guessNumber(min, guess);
                break;
            case "trafiłeś":
                System.out.println("Wygrałem");
                break;
            default:
                System.out.println("Nie oszukuj!");
                guessNumber(min, max);
                break;
        }
    }
}
