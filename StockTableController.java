
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.StageStyle;

public class StockTableController {

	@FXML
	TableView<Item> stocks;
	@FXML
	TableColumn<Item, String> id, name, weight, depreciation, stock;

	@FXML
	JFXToggleButton stockToggle;
	@FXML
	JFXButton update_btn, delete_btn;

	@FXML
	JFXTextField item_id, item_name, item_weight, item_depreciation;

	Item k = null;

	public void initialize() {
		getOnlyUnsold();
	}

	public void getAllData() {
		ObservableList<Item> list = FXCollections.observableArrayList();

		id.setCellValueFactory(new PropertyValueFactory<Item, String>("id"));
		name.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
		weight.setCellValueFactory(new PropertyValueFactory<Item, String>("weight"));
		depreciation.setCellValueFactory(new PropertyValueFactory<Item, String>("depreciation"));
		stock.setCellValueFactory(new PropertyValueFactory<Item, String>("stock"));

		String sql = "SELECT * FROM Stock";
		Connection c =  new Connector().connect();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				list.add(new Item(rs.getString("ID"), rs.getString("Name"), new Weight(rs.getString("Weight")),
						new Weight(rs.getString("Depreciation")), rs.getString("stock_status")));
			}
			stocks.setItems(list);
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void toggleAction() {
		boolean selected = stockToggle.isSelected();
		if (selected == true) {
			this.getAllData();
			stocks.getSortOrder().add(stock);

			this.delete_btn.setDisable(true);
			this.update_btn.setDisable(true);
			stocks.setFocusTraversable(false);
		} else {
			this.getOnlyUnsold();
			stocks.setFocusTraversable(true);
		}
		refreshBoxes();
	}

	public void getOnlyUnsold() {
		ObservableList<Item> list = FXCollections.observableArrayList();

		id.setCellValueFactory(new PropertyValueFactory<Item, String>("id"));
		name.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
		weight.setCellValueFactory(new PropertyValueFactory<Item, String>("weight"));
		depreciation.setCellValueFactory(new PropertyValueFactory<Item, String>("depreciation"));
		stock.setCellValueFactory(new PropertyValueFactory<Item, String>("stock"));

		String sql = "SELECT * FROM Stock where stock_status = 'stock';";
		Connection c =  new Connector().connect();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				list.add(new Item(rs.getString("ID"), rs.getString("Name"), new Weight(rs.getString("Weight")),
						new Weight(rs.getString("Depreciation")), rs.getString("stock_status")));

			}
			c.close();
			stocks.setItems(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getSelectedData() {

		if (stocks.getSelectionModel().getSelectedItem() != null) {
			k = new Item(stocks.getSelectionModel().getSelectedItem());
			if (k.stock.equals("sold") || k.stock.equals("ordered")) {
				refreshBoxes();
				delete_btn.setDisable(true);
				update_btn.setDisable(true);
			} else {
				item_id.setText(k.id);
				item_name.setText(k.name);
				item_weight.setText(k.getWeight());
				item_depreciation.setText(k.getDepreciation());
				delete_btn.setDisable(false);
				update_btn.setDisable(false);
			}
		}

	}

	public void updateClick() {
		String sql = "UPDATE Stock set Name = '" + item_name.getText() + "', Weight = '" + item_weight.getText()
				+ "', Depreciation = '" + item_depreciation.getText() + "' where id = " + item_id.getText() + "";
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.getDialogPane().setStyle("-fx-font-family:Zawgyi-One;");
		Optional<ButtonType> c = alert.showAndWait();
		if (c.get() == ButtonType.OK) {
			Connection con =  new Connector().connect();
			try {
				Statement s = con.createStatement();
				s.execute(sql);
				toggleAction();
				refreshBoxes();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			delete_btn.setDisable(true);
			update_btn.setDisable(true);
		} else {
		}
	}

	public void callAddStock() {
		try {
			Utilities.openForm("AddStock.fxml", StageStyle.UNDECORATED);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void refreshTable() {
		toggleAction();
	}

	public void deleteClick() {
		String sql = "DELETE FROM Stock WHERE id = " + item_id.getText();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		Optional<ButtonType> c = alert.showAndWait();
		if (c.get() == ButtonType.OK) {
			Connection con =  new Connector().connect();
			try {
				Statement s = con.createStatement();
				s.execute(sql);
				toggleAction();
				refreshBoxes();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			delete_btn.setDisable(true);
			update_btn.setDisable(true);
		}
	}

	private void refreshBoxes() {
		item_id.setText(null);
		item_name.setText(null);
		item_weight.setText(null);
		item_depreciation.setText(null);
	}
}
