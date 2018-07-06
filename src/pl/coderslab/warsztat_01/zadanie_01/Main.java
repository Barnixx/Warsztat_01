package pl.coderslab.warsztat_01.zadanie_01;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        int number = random.nextInt(101);
        Scanner scanner = new Scanner(System.in);
        boolean youWin = false;
        int userNumber;
        do {
            System.out.println("Zgadnij liczbę");
            try {
                userNumber = Integer.parseInt(scanner.nextLine());
                String check = checkNumber(userNumber, number);
                System.out.println(check);
                youWin = check.equals("Zgadłeś!");

            } catch (NumberFormatException e) {
                System.out.println("To nie jest liczba!");
            }
        } while (!youWin);
    }

    private static String checkNumber(int userNumber, int number) {

        if (userNumber > number)
            return "Za dużo!";
        else if (userNumber < number)
            return "Za mało!";
        else
            return "Zgadłeś!";
    }
}
