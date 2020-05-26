import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AddPawnDealController {

	@FXML 
	JFXButton confirm,exit;
	public void initialize() {
		
	}
	
	public void exit() {
		((Stage) exit.getScene().getWindow()).close();
	}
}
