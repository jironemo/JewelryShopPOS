import java.io.*;
import java.util.*;
import java.lang.*;
import java.sql.*;
/**
 * Connector
 */
public class Connector {

    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName(org.sqlite.JDBC.class.getCanonicalName());
            // db parameters
            String url = "jdbc:sqlite:shop.db";
            // create a connection to the database
                
                conn = DriverManager.getConnection(url, new Properties());

        } catch (SQLException  | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } 
        return(conn);  

    }
    /**
     * @param args the command line arguments
     */
}