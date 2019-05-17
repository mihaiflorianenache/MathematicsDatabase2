package org.fasttrackit.Service;

import org.fasttrackit.Domain.Anzahl;
import org.fasttrackit.Domain.NumberMirrorNumber;
import org.fasttrackit.Persistence.NumberRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Stack;

public class NumberService {

    private NumberRepository numberRepository=new NumberRepository();

    public void insertNumber(Anzahl anzahl) throws SQLException {
        numberRepository.insertNumber(anzahl);
    }

    public Stack<Anzahl> getNumber() throws SQLException {
        return numberRepository.getNumber();
    }

    public void insertMirrorNumber(int initialNumber, int mirrorNumber) throws SQLException {
        numberRepository.insertMirrorNumber(initialNumber,mirrorNumber);
    }

    public List<NumberMirrorNumber> numarOglinditulNumarului() throws SQLException {
        return numberRepository.numarOglinditulNumarului();
    }

    public void palindromValue(boolean value,int numar,int oglinditulNumarului) throws SQLException {
        numberRepository.insertPalindromValue(value,numar,oglinditulNumarului);
    }

    public void deleteAllRecords() throws SQLException {
        numberRepository.deleteAllRecords();
    }
}
