import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Sale implements Utilities{
	int id;
	int itemID;
	int customerID;
	Date dateofSale;
	
	static int latestId = getLatestID();
	public Sale( int itemID,int customerID) {
		
		this.id = latestId;
		this.itemID = itemID;
		this.customerID = customerID;
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
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int getLatestID() {
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
		return result;
	}
	
	@Override
	public int updateData() {
		// TODO Auto-generated method stub
		return 0;
	}

}
