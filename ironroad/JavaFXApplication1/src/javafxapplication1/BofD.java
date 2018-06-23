/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author bio
 */
public class BofD {
    
    private static Connection connection=null;
    public static Statement statement=null;
    
    public static ResultSet make(String s) {
        //connectBD();
        ResultSet rs;
        System.out.println("НАЧАЛО5");
        if(statement==null)
        {
            System.out.println("НАЧАЛО32");
            connectBD();
        }
        try{
            rs=statement.executeQuery(s);
        }
        catch(SQLException e){
            System.out.println("Низкий запрос");
            return null;
        }
        
        return rs;
    }
    
    private static void connectBD() {
        System.out.println("НАЧАЛО1");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Не найден драйвер");
            e.printStackTrace();
            return;
        }
        System.out.println("НАЧАЛО2");
        try {
             connection = DriverManager.getConnection(
                     "jdbc:mysql://localhost:3306/ItineraryMachinist","pma","");
             System.out.println("НАЧАЛО");
             statement=connection.createStatement();
        }
        catch (SQLException e) {
            System.out.println("Не удалось установить соединение!");
            e.printStackTrace();
            return;
        }
        System.out.println("НАЧАЛО3");
    }
}
