import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Item
 */
public class Item implements Utilities{

    String id;
    String name;
    Weight weight;
    Weight depreciation;
    String stock;

    public String getStock() {
		return stock;
	}

	public void setStock_status(String stock) {
		this.stock = stock;
	}

	public Item() {
        this.id = "";
        this.name = "";
        this.weight = new Weight();
        this.depreciation = new Weight();
    }

    public Item(String id, String name, Weight weight, Weight depreciation,String stock) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.depreciation = depreciation;
        this.stock = stock;
    }

    public String getId() {
        return (this.id);
    }

    public String getName() {
        return this.name;
    }

    public String getWeight() {
        return this.weight.getString();
    }

    public String getDepreciation() {
        return this.depreciation.getString();
    }
    

    public int add() {
		 	try{
		 		Connection con = null;
		 		String query =  "INSERT INTO STOCK VALUES ("+" '"+this.getId()+"', '"+this.getName()+"', '"+this.getWeight()+"','"+this.getDepreciation()+"');";
		 		con = Connector.connect();
		 		Statement s = con.createStatement();
		 		s.execute(query);
		 		con.close();
		 		return(1);
	        }
	        catch(SQLException sq){
	        	sq.printStackTrace();
	        	return(0);
	        }
    }
     
    public static ResultSet getItems(String tablename)  {
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
			String query = "UPDATE Stock SET Name = '"+this.name+"', Weight = '"+this.getWeight()+"', Depreciation = '"+ this.getDepreciation()+"' WHERE ID ='"+this.id+"';" ;
			con = Connector.connect();
			Statement s = con.createStatement();
			s.execute(query);
			con.close();
			return(1);
		}
		catch(SQLException sq) {
			return(0);
		}
	}
	
	public static int setSold(int id) {
		int success = 0;
		try {
		Connection con = Connector.connect();
		String query = "UPDATE Stock set stock_status = 'sold' where id = " + id;
		Statement s = con.createStatement();
		s.execute(query);
		con.close();
		success = 1;
		}catch(SQLException se) {
			se.printStackTrace();
			success = 0;
		}
		return success;
	}

	public static String get(String column, int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT " + column + " FROM Stock where id = " + id;
		String result = "";
		Connection c = Connector.connect();
		try {
			Statement s  = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
				while(rs.next()) {
					result = rs.getString(0);
					System.out.println(result);
				}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}