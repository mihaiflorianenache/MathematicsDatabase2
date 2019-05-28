package org.fasttrackit.Domain;

import org.fasttrackit.Service.NumberService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.*;

public class Number {

    private NumberService numberService = new NumberService();
    private Anzahl anzahl = new Anzahl();
    private Stack<Anzahl> stackNumber = new Stack<>();
    private List<NumberMirrorNumber> pairNumberMirrorNumber=new ArrayList<>();

    public void callIntroduceNumber() throws SQLException, IOException {

        if(numberService.getNumber().size()>0){
            deleteRecords();
        }

        //cel putin un numar trebuie introdus
        int numberNumbers=numberOfNumbers();
        int i;
        for(i=0;i<numberNumbers;i++) {
            introduceNumber();
        }
        showNumbers();
        algorithms();

        showPairNumberMirrorNumber();
        palindrom();
        prim();
    }

    private String deleteRecords() throws SQLException {
        System.out.println("Do you want to delete some records from table ?Y/N");
        BufferedReader deleteRecords=new BufferedReader(new InputStreamReader(System.in));
        try {
            String answer = deleteRecords.readLine();
            if(answer.equalsIgnoreCase("y"))
                delete();
            else
                if(answer.equalsIgnoreCase("n"))
                    System.out.println("You will not delete any record from table");

                else
                {
                    System.out.println("You must to choose between Y and N");
                    return deleteRecords();
                }
        }catch(IOException exception){
            System.out.println("The answer can not be read because of the error "+exception.getMessage());
        }
        return null;
    }

    private String delete() throws IOException, SQLException {
        System.out.println("Do you want to delete 1-all records or 2-some records ?");
        BufferedReader choice=new BufferedReader(new InputStreamReader(System.in));
        try {
            int choiceNumber = Integer.parseInt(choice.readLine());
            if(choiceNumber<1 || choiceNumber>2) delete();
            else
                if(choiceNumber==1){
                    numberService.deleteAllRecords();
                }
                else
                    if(choiceNumber==2) {
                        deleteRecord();
                    }
        }catch(NumberFormatException exception){
            System.out.println("You must to choice a digit between 1 and 2");
            return delete();
        }
        return null;
    }

    private int deleteRecord() throws SQLException {
        System.out.println("Choice between follow records for deleting");
        int i=0;
        for(Anzahl anzahl : numberService.getNumber()){
            if(i!=numberService.getNumber().size()-1) System.out.print((i+1)+"-"+anzahl.getNumber()+", ");
            else System.out.print((i+1)+"-"+anzahl.getNumber());
            i++;
        }
        Scanner choice=new Scanner(System.in);
        try {
            int j = choice.nextInt();
            if(j<1 || j>numberService.getNumber().size())
                return deleteRecord();
            else
                numberService.deleteRecord(numberService.getNumber().get(j-1).getNumber());
        }catch(InputMismatchException exception){
            System.out.println("You must to choice between a choice from above");
            return deleteRecord();
        }
        return 0;
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

    private int numberOfNumbers() throws IOException,SQLException {
        System.out.println("How many numbers you want to introduce ?");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int numberNumbers = Integer.parseInt(bufferedReader.readLine());
            if (numberNumbers < 1 && numberService.getNumber().size()==0) return numberOfNumbers();
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

    private void showPairNumberMirrorNumber() throws SQLException {
        for(NumberMirrorNumber numberMirrorNumber:numberService.numarOglinditulNumarului()){
            pairNumberMirrorNumber.add(numberMirrorNumber);
        }

        System.out.println("Pairs(Number,Mirror Number) are");
        int i;
        for(i=0;i<pairNumberMirrorNumber.size();i++){
            System.out.println(pairNumberMirrorNumber.get(i).getNumar()+" "+pairNumberMirrorNumber.get(i).getOglinditulNumarului());
        }
    }

    private void palindrom() throws SQLException {
        int i;
        for(i=0;i<pairNumberMirrorNumber.size();i++){
            if(pairNumberMirrorNumber.get(i).getNumar()==pairNumberMirrorNumber.get(i).getOglinditulNumarului()){
                numberService.palindromValue(true,pairNumberMirrorNumber.get(i).getNumar(),pairNumberMirrorNumber.get(i).getOglinditulNumarului());
            }
            else{
                numberService.palindromValue(false,pairNumberMirrorNumber.get(i).getNumar(),pairNumberMirrorNumber.get(i).getOglinditulNumarului());
            }
        }
    }

    private void prim() throws SQLException {
        int i;
        int number;
        String prim;
        for(i=0;i<pairNumberMirrorNumber.size();i++){
            if(pairNumberMirrorNumber.get(i).getNumar()<0) {
                number=pairNumberMirrorNumber.get(i).getNumar() * (-1);
                prim = primeNumber(number);
            }
            else {
                prim = primeNumber(pairNumberMirrorNumber.get(i).getNumar());
            }

            if(prim.equals("is prime")) {
                numberService.primValue(true,pairNumberMirrorNumber.get(i).getNumar(),pairNumberMirrorNumber.get(i).getOglinditulNumarului());
            }
            else{
                numberService.primValue(false,pairNumberMirrorNumber.get(i).getNumar(),pairNumberMirrorNumber.get(i).getOglinditulNumarului());
            }
        }
    }

    String primeNumber(int number) {
        boolean state = true;
        int i;
        for (i = 2; i < 1 + number / 2; i++) {
            if (number % i == 0) {
                state = false;
                break;
            }
        }
        if (number == 1 || number == 0) return "is not prime";
        else if (state == true) return "is prime";
        else return "is not prime";
    }
}
