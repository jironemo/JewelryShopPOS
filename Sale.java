import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Sale implements Utilities{
	String id;
	String itemID;
	String customerID;
	Date dateofSale;
	
	static String latestId = getLatestID();
	public Sale( String item,String customer) {
		
		this.id = latestId;
		this.itemID = item;
		this.customerID = customer;
		this.dateofSale = new Date();
	}
	@Override
	public int add() {

		// TODO Auto-generated method stub
		String sql = String.format("INSERT INTO TABLE Sale values ('%s','%s','%s',",this.id,this.itemID,this.customerID);
		sql.concat(this.dateofSale+");");
		try {
		Connection c = Connector.connect();
		Statement s =c.createStatement();
	
			s.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public static String getLatestID() {
		Connection c = Connector.connect();
		String result = null;
		String sql = "SELECT MAX(ID) FROM Sale";
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()) {
				result = (rs.getString(1));
			}
			else {
				result = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public int updateData() {
		// TODO Auto-generated method stub
		return 0;
	}

}
