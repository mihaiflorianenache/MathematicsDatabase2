package org.fasttrackit;

import org.fasttrackit.Domain.Number;

import java.io.IOException;
import java.sql.SQLException;

public class App 
{
    public static void main( String[] args ) throws SQLException, IOException {
        Number number=new Number();
        number.callIntroduceNumber();
    }
}
