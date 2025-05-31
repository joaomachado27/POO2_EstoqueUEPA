/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author caladoapelo
 */
public class ConnectionFactory {
    
    public Connection getConnection() {
        try {
    
            Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/estoqueuepa","root","1234");
        }
        catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }   
}