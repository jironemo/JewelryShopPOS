
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


	public DashboardController() {} 
	
	public void initialize() {
		if(b == true) {
			id.setCellValueFactory(new PropertyValueFactory<Item, String>("Id"));
			name.setCellValueFactory(new PropertyValueFactory<Item, String>("Name"));
			weight.setCellValueFactory(new PropertyValueFactory<Item,String>("Weight"));
			depreciation.setCellValueFactory(new PropertyValueFactory<Item,String>("Depreciation"));
			try {
				ResultSet rs = Item.getItem("Stock");
				ObservableList<Item> data = FXCollections.observableArrayList();
			    rs = Item.getItem("Stock");
			    while (rs.next()) {
			    	Item row = new Item(rs.getString(1),rs.getString(2),new Weight(rs.getString(3)),new Weight(rs.getString(4)));
			        data.add(row);
				}
			    stocks.setItems(data);
				System.out.println(((Item) stocks.getItems().get(1)).getName());
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				b = false;
			}
		}
	}
	
	
	public void callAddSale() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainMenu.class.getResource("AddSale.fxml"));
		Scene scene = new Scene(loader.load());
		Stage s = new Stage();
		s.setScene(scene);
		s.initStyle(StageStyle.UNDECORATED);
		s.show();
	}
}
