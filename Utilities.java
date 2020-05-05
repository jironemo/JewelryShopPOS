
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public interface Utilities {
	public int add();

	public static int deleteData(String id, String table) {
		// TODO Auto-generated method stub
		try {
			String query = "DELETE FROM " + table + " WHERE ID= '" + id + "';";

			Connection con =  null;
			con = new Connector().connect();
			Statement s = con.createStatement();
			s.execute(query);
			con.close();
			return 1;
		} catch (SQLException sq) {
			sq.printStackTrace();
			return 0;
		}
	}

	public int updateData();

	public static void openForm(String formname, StageStyle c) throws IOException {
		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(MainMenu.class.getResource("/fxmls/"+formname));
		Scene scene = new Scene(loader.load());
		Stage s = new Stage(c);
		s.initModality(Modality.APPLICATION_MODAL); 
		s.setScene(scene);
		s.centerOnScreen();
		Image e = new Image("file:Neckband-icon.png");
		s.getIcons().add(e);
		s.setResizable(false);
		s.show();
	}

	public static String getgold() {
		int k = 0;
		try {
			String sql = "select goldval from Gold where id = 1";
			Connection con =  null;
			con = new Connector().connect();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			rs.next();
			k = rs.getInt(1);
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Integer.toString(k);

	}

	public static void setGold(String text) {
		String sql = "UPDATE  goldval set value = " + text + " where id = 1";
		try {
			Connection con = new Connector().connect();
			Statement s = con.createStatement();
			s.execute(sql);
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
