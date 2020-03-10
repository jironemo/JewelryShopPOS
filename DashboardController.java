
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class DashboardController{
	@FXML
	TableView<Item> stocks;
	@FXML
	TableColumn<Item, String> id,name,weight,depreciation;
	 
	Boolean b = true;
	public static int pagetracker = 1 ;

	public DashboardController() {} 
	
	public void initialize() {
		
	}
	
	
	public void callAddSale() throws IOException {
	if(pagetracker == 1) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainMenu.class.getResource("AddSale.fxml"));
		Scene scene = new Scene(loader.load());
		Stage s = new Stage();
		s.setScene(scene);
		s.initStyle(StageStyle.UNDECORATED);
		s.show();
		pagetracker = 0;
	}
	}
}
