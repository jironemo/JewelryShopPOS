import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AddSaleController {
	@FXML 
	JFXButton exit;
	public AddSaleController() {}
	public void exit() {
		((Stage)exit.getScene().getWindow()).close();
	}
}