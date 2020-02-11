import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Customer
 */
public class Customer implements Utilities{

    private String id,name, address, phonenumber;

    public Customer() {
    	this.id = "";
        this.name = "";
        this.address = "";
        this.phonenumber = "";
    }
    public Customer(String id, String name, String address,String phonenumber) {
    	this.id = id;
        this.name = name;
        this.address = address;
        this.phonenumber = phonenumber;;
    }
    public String getID() {
		return id;
	}

    public void setID(String id) {
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
	public int add(Object i) {
		try{
	 		Connection con = null;
	 		String query =  "INSERT INTO CUSTOMER VALUES ("+" '"+((Customer)i).getID()+"', '"+((Customer)i).getName()+"', '"+((Customer)i).getAddress()+"','"+((Customer)i).getPhonenumber()+"');";
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
	@Override
	public int updateData(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
  
}