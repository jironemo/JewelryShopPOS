import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class AddPawnDealController { 

	@FXML 
	JFXButton confirm,exit;
	
	@FXML
	JFXTextField cus_name,cus_phone,cus_addr,item_name,loan,weight_kyat,weight_pel,weight_yway;
	
	@FXML
	DatePicker dp_dueDate;
	public void initialize() {
		
	}
	
	
	
	public void addPawnDeal() {
		String c_name = cus_name.getText();
		String c_phone = cus_phone.getText();
		String c_addr = cus_addr.getText();
		String i_name = item_name.getText();
		Long loan_ = Long.parseLong(loan.getText());
		String weightStr = weight_kyat.getText() +"," + weight_pel.getText() + "," + weight_yway.getText();
		Customer c = new Customer(c_name,c_phone,c_addr);
		Item s = new Item(i_name,new Weight(weightStr),loan_,"stock");
		c.add();
		s.add();
		String sql = "INSERT INTO PawnDeal (Item_ID,Cus_ID,DateOfPawn,DueDate) Values ('%s','%s',DATE(),'%tF');";
		sql = String.format(sql, s.getId(),c.getID(),dp_dueDate.getValue());
		Connection con = new Connector().connect();
		try {
			Statement st = con.createStatement();
			st.execute(sql);
			exit();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}
	public void exit() {
		((Stage) exit.getScene().getWindow()).close();
	}
}
