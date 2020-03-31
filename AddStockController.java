import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
public class AddStockController {
@FXML 
JFXButton exit;
@FXML
JFXTextField item_name,item_weight,item_depreciation;
public void exit() {
		((Stage)exit.getScene().getWindow()).close();
	}
	
	public void addStock() {
		if(someFieldsNULL() == false) {
			String sql = String.format("INSERT INTO Stock (Name,Weight,Depreciation,stock_status) values ('%s','%s','%s','stock');",item_name.getText(),item_weight.getText(),item_depreciation.getText());
			Connection c = Connector.connect();
			Statement s;
			try {
				s = c.createStatement();
				s.execute(sql);
				c.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			exit();
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.getDialogPane().setStyle("-fx-font-family:Zawgyi-One");
			alert.setTitle("Error");
			alert.setHeaderText("မေအာင္ျမင္ပါ");
			alert.setContentText("အခ်က္အလက္မျပည့္စံုပါ");
			alert.show();
		}
	}
	
	private boolean someFieldsNULL() {
		boolean a = false;
		if(item_name.getText()== null||item_name.getText().trim().equals("")) {
			a = true;
		}
		if(item_weight.getText()== null||item_weight.getText().trim().equals("")) {
			a = true;
		}
		if(item_depreciation.getText()== null||item_depreciation.getText().trim().equals("")) {
			a = true;
		}
		return a;
	}
}
