package database;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDB {
	 public Connection Connection()
	    {
	        try 
	        {
	            
	            Class.forName("org.h2.Driver");
	            String techworld3g = "jdbc:h2:tcp://localhost/~/test";
	            Connection myConnection = DriverManager.getConnection(techworld3g,"sa", "");
	          
	            return myConnection;
	            
	        } catch (ClassNotFoundException | SQLException ex) {Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);}
	        return null;
	    }
}
