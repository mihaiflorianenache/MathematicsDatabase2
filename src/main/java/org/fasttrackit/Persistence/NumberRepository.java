package org.fasttrackit.Persistence;

import org.fasttrackit.Domain.Anzahl;
import org.fasttrackit.Domain.NumberMirrorNumber;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
            String insertMirrorNumber="UPDATE matematica SET OglinditulNumarului="+mirrorNumber+" WHERE Numar="+initialNumber;

            Statement statement=connection.createStatement();
            statement.execute(insertMirrorNumber);
        }
    }

    public List<NumberMirrorNumber> numarOglinditulNumarului() throws SQLException {
        try(Connection connection=DatabaseConfiguration.getConnection()){
            String getNumberMirrorNumber="SELECT Numar,OglinditulNumarului FROM matematica";

            Statement statement=connection.createStatement();
            statement.execute(getNumberMirrorNumber);

            ResultSet resultSet=statement.executeQuery(getNumberMirrorNumber);
            List<NumberMirrorNumber> listNumberMirrorNumber=new ArrayList<>();

            while(resultSet.next()){
                NumberMirrorNumber numberMirrorNumber=new NumberMirrorNumber();
                numberMirrorNumber.setNumar(resultSet.getInt("Numar"));
                numberMirrorNumber.setOglinditulNumarului(resultSet.getInt("OglinditulNumarului"));
                listNumberMirrorNumber.add(numberMirrorNumber);
            }
            return listNumberMirrorNumber;
        }
    }

    public void insertPalindromValue(boolean value,int numar,int oglinditulNumarului) throws SQLException {
        try(Connection connection=DatabaseConfiguration.getConnection()){
            String insertPalindromValue="UPDATE matematica SET Palindrom="+value+" WHERE Numar="+numar+" AND OglinditulNumarului="+oglinditulNumarului;
            Statement statement=connection.createStatement();
            statement.execute(insertPalindromValue);
        }
    }

    public void deleteAllRecords() throws SQLException {
        try(Connection connection=DatabaseConfiguration.getConnection()){
            String deleteRecords="DELETE FROM matematica";
            Statement statement=connection.createStatement();
            statement.execute(deleteRecords);
        }
    }

    public void deleteRecord(int number)throws SQLException{
        try(Connection connection=DatabaseConfiguration.getConnection()){
            String deleteRecords="DELETE FROM matematica WHERE Numar="+number;
            Statement statement=connection.createStatement();
            statement.execute(deleteRecords);
        }
    }

    public void primValue(boolean value,int numar,int oglinditulNumarului) throws SQLException {
        try(Connection connection=DatabaseConfiguration.getConnection()){
            String insertPrimValue="UPDATE matematica SET Prim="+value+" WHERE Numar="+numar+" AND OglinditulNumarului= "+oglinditulNumarului;
            Statement statement=connection.createStatement();
            statement.execute(insertPrimValue);
        }
    }
}
