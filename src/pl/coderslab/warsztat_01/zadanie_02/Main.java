package pl.coderslab.warsztat_01.zadanie_02;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Integer> userNumbers = createUserNumbers(6);
        System.out.println("Twoje liczby: \n" + userNumbers);
        ArrayList<Integer> lottoNumbers = createLottoNumbers(6);
        System.out.println("Wylosowane liczby: \n" + lottoNumbers);

        System.out.println(theResultOfTheDraw(lottoNumbers, userNumbers));

    }

    private static ArrayList<Integer> createLottoNumbers(int count) {
        ArrayList<Integer> lottoNumbers = new ArrayList<>();
        int number;

        while (!(lottoNumbers.size() == count)) {
            number = RandomUtils.nextInt(1, 49);
            if (!lottoNumbers.contains(number)) {
                lottoNumbers.add(number);
            }
        }
        lottoNumbers.sort(Comparator.naturalOrder());

        return lottoNumbers;
    }

    private static ArrayList<Integer> createUserNumbers(int count) {
        ArrayList<Integer> userNumbers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj 6 liczb z zakresu 1-49");

        do {
            try {
                System.out.println("Podaj liczbę");
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= 1 && input <= 49) {
                    if (!(userNumbers.contains(input))) {
                        userNumbers.add(input);
                    } else {
                        System.out.println("Liczba została już dodana");
                    }

                } else {
                    System.out.println("Liczba nie znajduję się w przedzialę");
                }

            } catch (NumberFormatException e) {
                System.out.println("To nie liczba!");
            }
        } while (!(userNumbers.size() == count));

        userNumbers.sort(Comparator.naturalOrder());

        return userNumbers;
    }

    private static String theResultOfTheDraw(ArrayList<Integer> lottoNumbers, ArrayList<Integer> userNumbers) {

        int count = 0;
        String result;

        for (int i = 0; i < lottoNumbers.size(); i++) {
            if (lottoNumbers.get(i).equals(userNumbers.get(i))) {
                count++;
            }
        }

        System.out.println("Trafiłeś " + count + " liczb");
        switch (count) {
            case 3:
                result = "Trafiłeś trójkę!";
                break;
            case 4:
                result = "Trafiłeś czwórkę!";
                break;
            case 5:
                result = "Trafiłeś piątkę!";
                break;
            case 6:
                result = "Trafiłeś szóstkę";
                break;

            default:
                result = "Przegrałeś :(";
                break;

        }
        return result;
    }
}


//        for(int i = 0; i < lottoNumbers.length; i++){
//            boolean isExist = false;
//
//            number = RandomUtils.nextInt(1, 49);
//
//            for(int j = 0; j < i; j++){
//                if(lottoNumbers[j] == number){
//                    isExist = true;
//                    i--;
//                }
//            }
//            if(isExist == false){
//                lottoNumbers[i] = number;
//            }
//
//        }
//        System.out.println(Arrays.toString(lottoNumbers));
