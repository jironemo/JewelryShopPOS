import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Sale implements Utilities{
	String id;
	String itemID;
	int customerID;
	Date dateofSale;
	
	static String latestId = getLatestID();
	public Sale( String itemID,int customerID) {
		
		 this.id = latestId;
		this.itemID = itemID;
		this.customerID = customerID;
		this.dateofSale = new Date();
	}
	@Override
	public int add() {
		// TODO Auto-generated method stub
			String sql = String.format("INSERT INTO Sale(Item_ID,Cus_ID,DateOfSale) values ('%s','%s',DATE());",this.id,this.itemID,this.customerID);
			try {
			Connection c = Connector.connect();
			Statement s =c.createStatement();
				s.execute(sql);
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;
	}
	
	public static String getLatestID() {
		Connection c = Connector.connect();
		int result = 0;
		String sql = "SELECT MAX(ID) FROM Sale";
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()) {
				result = rs.getInt(1)+1;
				c.close();
			}
			else {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Integer.toString(result);
	}
	
	@Override
	public int updateData() {
		// TODO Auto-generated method stub
		return 0;
	}

}
