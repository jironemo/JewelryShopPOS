
import java.util.*;
import java.net.URL;
import java.sql.*;

/**
 * Connector
 */
public class Connector {

	public   Connection connect() {
		Connection conn = null;
		try {
			Class.forName(org.sqlite.JDBC.class.getCanonicalName());
			// db parameters
			


			String url = "jdbc:sqlite::resource:textfiles/shop.db";
			System.out.println(url);
			// create a connection to the database
			conn = DriverManager.getConnection(url, new Properties());
			
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return (conn); 

	}
	/**
	 * @param args the command line arguments
	 */
	public URL url() {

		
		URL u = getClass().getResource("/shop.db");
		return u;
	}

}