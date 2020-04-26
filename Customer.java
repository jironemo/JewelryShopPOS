
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Customer
 */
public class Customer implements Utilities {
	private int id;
	private String name, address, phonenumber;

	public Customer() {
		this.id = 0;
		this.name = "";
		this.address = "";
		this.phonenumber = "";
	}

	public Customer(String name, String address, String phonenumber) {
		this.id = getLatestID();
		this.name = name;
		this.address = address;
		this.phonenumber = phonenumber;
		;
	}

	public static int getLatestID() {
		Connection c =  new Connector().connect();
		int result = 0;
		String sql = "SELECT MAX(ID) FROM Customer";
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt(1) + 1;
				c.close();
			} else {
				result = 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	@Override
	public int add() {
		try {
			Connection con = null;
			String pattern = "INSERT INTO Customer (Cus_name,Cus_phone,Cus_addr) VALUES('%s','%s','%s')";
			String query = String.format(pattern, this.name, this.phonenumber, this.address);
			con =  new Connector().connect();
			Statement s = con.createStatement();
			s.execute(query);
			con.close();
			return (1);

		} catch (SQLException sq) {
			sq.printStackTrace();
			return (0);
		}
	}

	@Override
	public int updateData() {
		// TODO Auto-generated method stub
		try {
			Connection con = null;
			String query = "UPDATE Stock SET Cus_Name = '" + this.getName() + "', Cus_Addr = '" + this.getAddress()
					+ "', Cus_Phone = '" + this.phonenumber + "' WHERE ID ='" + this.id + "';";
			con =  new Connector().connect();
			Statement s = con.createStatement();
			s.execute(query);
			con.close();
			return (1);
		} catch (SQLException sq) {
			return (0);
		}
	}

	public int getCusID(String name) {
		int cus_id = 0;
		String sql = "SELECT ID from Customer where Cus_Name = '%s'";
		sql = String.format(sql, name);
		Connection c =  new Connector().connect();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			rs.next();
			cus_id = rs.getInt(1);
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cus_id;

	}
}
