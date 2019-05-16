package org.fasttrackit.Domain;

import org.fasttrackit.Service.NumberService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class Number {

    private NumberService numberService = new NumberService();
    private Anzahl anzahl = new Anzahl();
    private Stack<Anzahl> stackNumber = new Stack<>();

    public void callIntroduceNumber() throws SQLException, IOException {
        /*int numberNumbers=numberOfNumbers();
        int i;
        for(i=0;i<numberNumbers;i++) {
            introduceNumber();
        }*/
        showNumbers();
        algorithms();
    }

    private void introduceNumber() throws IOException, SQLException {
        System.out.println("Introduce a number");
        Scanner scanner = new Scanner(System.in);
        try {
            int number = scanner.nextInt();
            anzahl.setNumber(number);
            numberService.insertNumber(anzahl);
        } catch (InputMismatchException exception) {
            System.out.println("You must to introduce a number");
            introduceNumber();
        }
    }

    private int numberOfNumbers() throws IOException {
        System.out.println("How many numbers you want to introduce ?");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int numberNumbers = Integer.parseInt(bufferedReader.readLine());
            if (numberNumbers < 1) return numberOfNumbers();
            return numberNumbers;
        } catch (NumberFormatException exception) {
            System.out.println("You must to introduce a number");
            return numberOfNumbers();
        }
    }

    private void showNumbers() throws SQLException {
        int i;
        for (Anzahl anzahl : numberService.getNumber()) {
            stackNumber.add(anzahl);
        }

        System.out.println("Numbers are");
        for (i = 0; i < stackNumber.size(); i++) {
            System.out.println("Id= " + stackNumber.get(i).getId() + " ,Number= " + stackNumber.get(i).getNumber());
        }
    }

    private void algorithms() throws SQLException {
        int i;
        int mirrorNumber;
        for (i = 0; i < stackNumber.size(); i++) {
            mirrorNumber = mirrorNumber(stackNumber.get(i).getNumber());
            numberService.insertMirrorNumber(stackNumber.get(i).getNumber(),mirrorNumber);
        }
    }

    private int mirrorNumber(int number) {
        int nr;
        int newNumber = 0;
        int factor;
        if(number==0) return 0;
        while (number != 0) {
            nr = number % 10;
            factor = digitsNumber(number) - 1;
            number = number / 10;
            newNumber = newNumber + nr * power(10, factor);
        }
        return newNumber;
    }

    private int power(int base, int exponent) {
        int result = 1;
        int i;
        for (i = 0; i < exponent; i++) {
            result = result * base;
        }
        return result;
    }

    private int digitsNumber(int number) {
        int numberDigit = 0;
        int newNumber = number;
        if (number == 0) return 1;
        while (newNumber != 0) {
            newNumber = newNumber / 10;
            numberDigit++;
        }
        return numberDigit;
    }
}
