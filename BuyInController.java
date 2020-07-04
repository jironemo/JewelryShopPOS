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
		int cus_id = 0, item_id =0;
		alert.getDialogPane().setStyle("-fx-font-family:Zawgyi-One");
		alert2.getDialogPane().setStyle("-fx-font-family:Zawgyi-One");
		if(!(someFieldsBlank())) {
			Connection c = new Connector().connect();

			String add_buyin = "INSERT INTO BuyIn (Customer_ID,Item_ID,DateOfBuy)  values ('%s','%s',DATE());";
			try {
				Statement s = c.createStatement();
				Customer cus = new Customer(cus_name.getText(),cus_phone.getText(),cus_addr.getText());
				cus.add(); 
				Item i = new Item(item_name.getText(),new Weight(weight_kyat.getText()+","+weight_pel.getText()+","+weight_yway.getText()),Long.parseLong(item_price.getText()),"stock");
				i.add();
				cus_id = Customer.getLatestID() -1;
				item_id = Item.getLatestID() - 1;
				add_buyin = String.format(add_buyin, cus_id,item_id); 
				s.execute(add_buyin);
				exit();
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
