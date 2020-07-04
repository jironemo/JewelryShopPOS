
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class SalesTableController {

	@FXML
	TableView<SaleData> sale;
	@FXML
	TableColumn<SaleData, String> id, item_id, cus_name, item_name;

	@FXML
	JFXTextField text_id, text_itemid, text_itemname, text_customername, text_customerphone;
	@FXML
	TextArea text_customeraddr;

	public void initialize() {
		loadTable();
	} 

	public void loadTable() {
		String sql = "SELECT Sale.id,Stock.id,Stock.name,Customer.Cus_Name FROM (Sale INNER JOIN Customer ON sale.Cus_ID = Customer.ID) INNER JOIN Stock on Stock.ID = Sale.Item_ID";
		ObservableList<SaleData> list = FXCollections.observableArrayList();
		Connection c =  new Connector().connect();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			id.setCellValueFactory(new PropertyValueFactory<SaleData, String>("Id"));
			item_id.setCellValueFactory(new PropertyValueFactory<SaleData, String>("itemID"));
			cus_name.setCellValueFactory(new PropertyValueFactory<SaleData, String>("customerName"));
			item_name.setCellValueFactory(new PropertyValueFactory<SaleData, String>("itemName"));
			while (rs.next()) {
				SaleData sd = new SaleData();
				sd.setSaleData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(sd);
			}
			sale.setItems(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setTextFieldsData() {

		if (sale.getSelectionModel().getSelectedItem() != null) {
			SaleData k = new SaleData(sale.getSelectionModel().getSelectedItem());
			text_id.setText(k.id);
			text_itemid.setText(k.itemID);
			text_itemname.setText(k.itemName);
			text_customername.setText(k.customerName);
			setCustomerData(k.customerName);
		}
	}

	private void setCustomerData(String name) {
		String sql = "Select Cus_Phone,Cus_Addr from Customer where Cus_Name = '" + name + "';";
		Connection c =  new Connector().connect();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				text_customerphone.setText(rs.getString(1));
				text_customeraddr.setText(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
