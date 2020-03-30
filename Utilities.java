import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public interface Utilities {

	public int add();

	public static int deleteData(String id,String table) {
		// TODO Auto-generated method stub
		try{
			Connection con = null;
            String query =  "DELETE FROM "+table+" WHERE ID= '" + id +"';";
            con = Connector.connect();
            Statement s = con.createStatement();
            s.execute(query);
            con.close();
			return 1;
		}
		catch(SQLException sq){
			sq.printStackTrace();
			return 0;
		}
	}
	
	public  int updateData() ;

	
	public static String getgold() {
		int k = 0;
		try {
			File f = new File("goldval");
			Scanner sc = new Scanner(f);
			k = sc.nextInt();
			sc.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return (Integer.toString(k));
	}

	public static void setGold(String text) {
		// TODO Auto-generated method stub
		try {
			FileWriter fw = new FileWriter("goldval");
			fw.write(text);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
