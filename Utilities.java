import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public interface Utilities {

	public int add(Object i);

	public static int deleteData(String id,String table) {
		// TODO Auto-generated method stub
		try{
			Connection con = null;
            String query =  "DELETE FROM "+table+" WHERE ID= '" + id +"';";
            con = Connector.connect();
            Statement s = con.createStatement();
            s.execute(query);
			return 1;
		}
		catch(SQLException sq){
			sq.printStackTrace();
			return 0;
		}
	}
	
	public  int updateData(Object o) ;
}
