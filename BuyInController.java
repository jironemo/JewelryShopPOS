import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.control.Alert.AlertType;
public class BuyInController {

	@FXML
	JFXButton confirm,exit;
	@FXML
	JFXTextField cus_name,cus_phone,cus_addr,item_name,weight_kyat,weight_pel,weight_yway,item_price;
	
	public void initialize() {
		
	}
	
	
	public void addBuyIn() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		Alert alert2 = new Alert(AlertType.ERROR);
		alert.getDialogPane().setStyle("-fx-font-family:Zawgyi-One");
		alert2.getDialogPane().setStyle("-fx-font-family:Zawgyi-One");
		if(!(someFieldsBlank())) {
			Connection c = new Connector().connect();

			String add_buyin = "INSERT INTO BuyIn values ('%s','%s','%s');";
			String add_seller = "INSERT INTO Customer values ('%s','%s','%s');";
			String add_item = "INSERT INTO Stock values ('%s','%s','%s','%s');";
			try {
				Statement s = c.createStatement();
				
				s.execute(add_buyin);
				s.execute(add_seller);
				s.execute(add_item);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			alert2.setTitle("Error");
			alert2.setHeaderText("အခ်က္အလက္မျပည့္စံုပါ");
			alert2.setContentText("ေနရာလြတ္မ်ား႐ွိေနပါသည္");
			alert2.show();
		}
	}
	
	public void exit() {
		((Stage)exit.getScene().getWindow()).close();
	}
	
	public boolean someFieldsBlank() {
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
		if (item_price.getText() == null || item_price.getText().trim().equals("")) {
			a = true;
		}
		return a;
	}

}
