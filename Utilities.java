import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Utilities {

	public int addItem(Item i) {
		 try{
	            Connection con = null;
	                String query =  "INSERT INTO STOCK VALUES ("+" '"+i.getId()+"', '"+i.getName()+"', '"+i.getWeight().getString()+"','"+i.depreciation().getString()+"');";
	            con = Connector.connect();
	            Statement s = con.createStatement();
	            s.execute(query);
	            return(1);
	        }
	        catch(SQLException sq){
	            return(0);
	        }
	}

	public int deleteData(String id,String table) {
		// TODO Auto-generated method stub
		try {
			  Connection con = null;
              String query =  "DELETE FROM"+table+"WHERE Item_ID = '" + id +"';";
          con = Connector.connect();
          Statement s = con.createStatement();
          s.execute(query);
			return 1;
			}
		catch(SQLException sq){
			return 0;
		}
		}
	}
