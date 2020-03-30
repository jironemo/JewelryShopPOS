import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StockTableController {

	@FXML
	TableView<Item> stocks;
	@FXML
	TableColumn<Item, String> id,name,weight,depreciation,stock;
	
	public void initialize() {
		getAllData();
	}
	
	public void getAllData() {
		ObservableList<Item> list = FXCollections.observableArrayList();
		
		 	id.setCellValueFactory(new PropertyValueFactory<Item, String>("id"));
	        name.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
	        weight.setCellValueFactory( new PropertyValueFactory<Item, String>("weight"));
	        depreciation.setCellValueFactory(  new PropertyValueFactory<Item, String>("depreciation"));
	        stock.setCellValueFactory(new PropertyValueFactory<Item,String>("stock"));
	        
		String sql = "SELECT * FROM Stock;";
		Connection c = Connector.connect();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				list.add(new Item(rs.getString("ID"),rs.getString("Name"),new Weight(rs.getString("Weight")),new Weight(rs.getString("Depreciation")),rs.getString("stock_status")));
			}
			stocks.setItems(list);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
