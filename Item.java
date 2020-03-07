import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 * Item
 */
public class Item implements Utilities{

    String id;
    String name;
    Weight weight;
    Weight depreciation;

    public Item() {
        this.id = "";
        this.name = "";
        this.weight = new Weight();
        this.depreciation = new Weight();
    }

    public Item(String id, String name, Weight weight, Weight depreciation) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.depreciation = depreciation;
    }

    public String getId() {
        return (this.id);
    }

    public String getName() {
        return this.name;
    }

    public Weight getWeight() {
        return this.weight;
    }

    public Weight depreciation() {
        return this.depreciation;
    }
    

    public int add() {
		 	try{
		 		Connection con = null;
		 		String query =  "INSERT INTO STOCK VALUES ("+" '"+this.getId()+"', '"+this.getName()+"', '"+this.getWeight().getString()+"','"+this.depreciation().getString()+"');";
		 		con = Connector.connect();
		 		Statement s = con.createStatement();
		 		s.execute(query);
		 		return(1);
	        }
	        catch(SQLException sq){
	        	sq.printStackTrace();
	        	return(0);
	        }
    }
    
    public static ResultSet getItem(String tablename)  {
    	ResultSet rs = null;
    	try {
    		String query = "SELECT * FROM "+ tablename + ";";
        	Connection c = Connector.connect();
        	Statement s = c.createStatement();
        	 rs = s.executeQuery(query);
        	
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return rs;
    }
    
	public int updateData() {
		
		try {
			Connection con = null;
			String query = "UPDATE Stock SET Name = '"+this.name+"', Weight = '"+this.getWeight().getString()+"', Depreciation = '"+ this.depreciation().getString()+"' WHERE ID ='"+this.id+"';" ;
			con = Connector.connect();
			Statement s = con.createStatement();
			s.execute(query);
			return(1);
		}
		catch(SQLException sq) {
			return(0);
		}
	}
}