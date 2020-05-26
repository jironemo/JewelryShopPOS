


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddOrderController {
	@FXML
	JFXButton exit;
	@FXML
	JFXTextField cus_name, cus_addr, cus_phone, item_name;
	@FXML
	TextField weight_kyat, weight_pel, weight_yway, depre_kyat, depre_pel, depre_yway,item_price_est;
	@FXML
	TextArea description;
	@FXML
	DatePicker dp_due;

	public void exit() {
		((Stage) exit.getScene().getWindow()).close();
	}

	public void addData() {
		if (!(someFieldsNULL())) {
			Customer c = new Customer();
			Item i = new Item();
			c.setName(cus_name.getText());
			c.setPhonenumber(cus_phone.getText());
			c.setAddress(cus_addr.getText());
			i.name = item_name.getText();
			i.weight = new Weight(weight_kyat.getText() + "," + weight_pel.getText() + "," + weight_pel.getText());
			i.price = Long.parseLong(item_price_est.getText());
			i.stock = "ordered";
			c.add();
			addOrderedItem(i);
			addOrder(c);
			exit();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.getDialogPane().setStyle("-fx-font-family:Zawgyi-One");
			alert.setHeaderText("မေအာင္ျမင္ပါ");
			alert.setContentText("အခ်က္အလက္မျပည့္စံုပါ");
			alert.show();
		}
	}

	public void addOrder(Customer c) {
		String pattern = "INSERT Into [Order] (Customer_ID,Item_ID,DateOfOrder,DueDate,item_description,depreciation) values (%d,'%s',DATE(),'%tF','%s','%s')";
		String sql = String.format(pattern, c.getCusID(cus_name.getText()), Item.getItemFromName(item_name.getText()),
				dp_due.getValue(), description.getText(),new Weight(depre_kyat.getText()+","+depre_pel.getText()+","+depre_yway.getText()).getString());
		try {
			Connection con = new Connector().connect();
			Statement s = con.createStatement();
			s.execute(sql);
			System.out.println("Success");
			
			String get  = "SELECT * FROM [Order]";
			Statement s1 = con.createStatement();
			ResultSet rs = s1.executeQuery(get);
			while(rs.next()) {
				System.out.println(rs.getInt(1));
			}
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

	public void addOrderedItem(Item i) {
		String sql = String.format(
				"INSERT INTO Stock (Name,Weight,Price,stock_status) values ('%s','%s','%s','%s')", i.name,
				i.weight.getString(), i.getPrice(), i.stock);
		Connection con =  new Connector().connect();
		try {
			Statement s = con.createStatement(); 
			s.execute(sql);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean someFieldsNULL() {
		boolean a = false;
		if (cus_phone.getText() == null || cus_phone.getText().trim().equals("")) {
			a = true;
		}
		if (cus_name.getText() == null || cus_name.getText().trim().equals("")) {
			a = true;
		}
		if (cus_addr.getText() == null || cus_addr.getText().trim().equals("")) {
			a = true;
		}

		if (item_name.getText() == null || item_name.getText().trim().equals("")) {
			a = true;
		}
		if (weight_kyat.getText() == null || weight_kyat.getText().trim().equals("")) {
			a = true;
		}
		if (weight_pel.getText() == null || weight_pel.getText().trim().equals("")) {
			a = true;
		}
		if (weight_yway.getText() == null || weight_yway.getText().trim().equals("")) {
			a = true;
		}
		if (depre_kyat.getText() == null || depre_kyat.getText().trim().equals("")) {
			a = true;
		}
		if (depre_pel.getText() == null || depre_pel.getText().trim().equals("")) {
			a = true;
		}
		if (depre_yway.getText() == null || depre_yway.getText().trim().equals("")) {
			a = true;
		}
		if (dp_due.getValue() == null) {
			a = true;
		}
		if (description.getText() == null || description.getText().trim().equals("")) {
			a = true;
		}
		return a;
	}
}
