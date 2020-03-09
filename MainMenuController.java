
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;

public class MainMenuController{
	@FXML
	Label label;
	@FXML
	TableView stocks;
	@FXML
	TableColumn<Item, String> id,name,weight,depreciation;
	 



	public MainMenuController() {} 
	public void initialize() {
		Font f = new Font("Myanmar Text", 12);
	}
	public void buttonClick()  {
			id.setCellValueFactory(new PropertyValueFactory<Item, String>("Id"));
			name.setCellValueFactory(new PropertyValueFactory<Item, String>("Name"));
			weight.setCellValueFactory(new PropertyValueFactory<Item,String>("Weight"));
			depreciation.setCellValueFactory(new PropertyValueFactory<Item,String>("Depreciation"));
		try {
			Connection c = Connector.connect();
			PreparedStatement s = c.prepareStatement("UPDATE  Stock SET name = 'ဆြဲႀကိဳး' WHERE id = 'I0002'");
			s.execute();
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
}
}