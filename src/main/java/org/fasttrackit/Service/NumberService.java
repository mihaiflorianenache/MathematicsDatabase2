package org.fasttrackit.Service;

import org.fasttrackit.Domain.Anzahl;
import org.fasttrackit.Persistence.NumberRepository;

import java.sql.SQLException;
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
}
