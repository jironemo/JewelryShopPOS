
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Item
 */
public class Item implements Utilities {

	int id;
	String name;
	Weight weight;
	long price;
	String stock;

	public String getStock() {
		return stock; 
	}

	public void setStock_status(String stock) {
		this.stock = stock;
	}

	public Item() {
		this.id = 0;
		this.name = "";
		this.weight = new Weight();
		this.price = 0;
	}

	public Item(int  id, String name, Weight weight, long price, String stock) {
		this.id = id;
		this.name = name;
		this.weight = weight;
		this.price = price;
		this.stock = stock;
	}
	
	public Item(String name, Weight weight, long price, String stock) {
		this.id = getLatestID();
		this.name = name;
		this.weight = weight;
		this.price = price;
		this.stock = stock;
	}

	public Item(Item selectedItem) {
		// TODO Auto-generated constructor stub
		this.id = selectedItem.id;
		this.name = selectedItem.name;
		this.weight = selectedItem.weight;
		this.price = selectedItem.price;
		this.stock = selectedItem.stock;
	}

	public Item(String itemID) {
		// TODO Auto-generated constructor stub
		Connection c =  new Connector().connect();
		try {
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM Stock where id = " + itemID);
			r.next();
			this.id = r.getInt("ID");
			this.name = r.getString("Name");
			this.weight = new Weight(r.getString("Weight"));
			this.price = r.getLong("Price");
			this.stock = r.getString("stock_status");
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}

	}

	public String getId() {
		return (Integer.toString(id));
	}

	public String getName() {
		return this.name;
	}

	public String getWeight() {
		return this.weight.getString();
	}

	public String getPrice() {
		return	Long.toString(this.price);
	}

	public int add() {
		try {
			Connection con = null;
			String query = "INSERT INTO STOCK VALUES (" + " '" + this.getId() + "', '" + this.getName() + "', '"
					+ this.getWeight() + "','" + this.getPrice() + "','stock');";
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

	public static ResultSet getItems(String tablename) {
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM " + tablename + ";";
			Connection c =  new Connector().connect();
			Statement s = c.createStatement();
			rs = s.executeQuery(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public int updateData() {

		try {
			Connection con = null;
			String query = "UPDATE Stock SET Name = '" + this.name + "', Weight = '" + this.getWeight()
					+ "', price = '" + this.getPrice() + "' WHERE ID ='" + this.id + "';";
			con =  new Connector().connect();
			Statement s = con.createStatement();
			s.execute(query);
			con.close();
			return (1);
		} catch (SQLException sq) {
			return (0);
		}
	}

	public static int setSold(String itemID) {
		int success = 0;
		try {
			Connection con =  new Connector().connect();
			String query = "UPDATE Stock set stock_status = 'sold' where id = " + itemID;
			Statement s = con.createStatement();
			s.execute(query);
			con.close();
			success = 1;
		} catch (SQLException se) {
			se.printStackTrace();
			success = 0;
		}
		return success;
	}

	public String toString() {
		String string;
		string = this.id + " " + this.name + " " + this.getWeight() + " " + this.getPrice();
		return string;

	}

	public static String get(String column, int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT " + column + " FROM Stock where id = " + id;
		String result = "";
		Connection c =  new Connector().connect();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean notSold() {
		// TODO Auto-generated method stub
		String stock_data = null;
		boolean k = false;
		try {
			stock_data = get("stock_status", id);
			if (stock_data.equals("stock")) { 
				k = (true);
			} else
				k = false;
		} catch (Exception e) {
			k = false;
		}
		return (k);
	}

	public static String getItemFromName(String name) {
		String sql = String.format("SELECT ID from Stock WHERE Name = '%s';", name);
		String id = null;
		try {
			Connection con =  new Connector().connect();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			rs.next();
			id = rs.getString(1);

			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return id;
	}


	public static int getLatestID() {
		Connection c =  new Connector().connect();
		int result = 0;
		String sql = "SELECT MAX(ID) FROM Stock";
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

}