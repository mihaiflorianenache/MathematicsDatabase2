package org.fasttrackit.Persistence;

import org.fasttrackit.Domain.Anzahl;

import java.sql.*;
import java.util.Stack;

public class NumberRepository {
    public void insertNumber(Anzahl anzahl) throws SQLException {
        try (Connection connection = DatabaseConfiguration.getConnection()){
            String insertNumber="INSERT INTO matematica(`Numar`) VALUES (?)";

            PreparedStatement preparedStatement=connection.prepareStatement(insertNumber);
            preparedStatement.setInt(1,anzahl.getNumber());
            preparedStatement.executeUpdate();
        }
    }

    public Stack<Anzahl> getNumber() throws SQLException {
        try(Connection connection=DatabaseConfiguration.getConnection()){
            String getNumber="SELECT id,numar FROM matematica ";

            Statement statement=connection.createStatement();
            statement.execute(getNumber);

            ResultSet resultSet=statement.executeQuery(getNumber);
            Stack<Anzahl> stackNumber=new Stack<>();

            while(resultSet.next()){
                Anzahl anzahl=new Anzahl();
                anzahl.setId(resultSet.getInt("id"));
                anzahl.setNumber(resultSet.getInt("Numar"));
                stackNumber.add(anzahl);
            }
            return stackNumber;
        }
    }

    public void insertMirrorNumber(int initialNumber, int mirrorNumber) throws SQLException {
        try(Connection connection=DatabaseConfiguration.getConnection()){
            String insertMirrorNumber="INSERT INTO matematica (`Oglinditul Numarului`) VALUES(?) WHERE Numar="+initialNumber;

            PreparedStatement preparedStatement=connection.prepareStatement(insertMirrorNumber);
            preparedStatement.setInt(1,mirrorNumber);
            preparedStatement.executeUpdate();
        }
    }
}
