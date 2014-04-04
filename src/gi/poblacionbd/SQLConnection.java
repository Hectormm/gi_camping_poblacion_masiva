/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gi.poblacionbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author saraer
 */
public class SQLConnection {
    
    private Connection connection;

    public SQLConnection(){
 
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println("No se encuentra jdbc.Driver");
		e.printStackTrace();
		return;
	}
 
	try {
		connection = DriverManager.getConnection("jdbc:mysql://bbdd.dlsi.ua.es/gi_camping","gi_camping", ".gi_camping.");
 
	} catch (SQLException e) {
		System.out.println("Connection Failed!");
		e.printStackTrace();
		return;
	}
 
	if (connection == null) {
		System.out.println("Failed to make connection!");
	}

    }
    
    public void executeQuery(String query) {
        
        try {
            ArrayList<String[]> datos=new ArrayList<>();
            
            java.sql.Statement stmt = connection.createStatement();
            System.out.println("The SQL query is: " + query); // Echo For debugging
            System.out.println();
            
            stmt.executeUpdate(query);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
}
