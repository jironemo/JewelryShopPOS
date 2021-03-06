

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.stage.StageStyle;

public class DashboardController {

	@FXML
	JFXTextField goldval;
	@FXML
	JFXButton editGold;
	Boolean b = true;

	public DashboardController()  { 
	}

	public void initialize() {	
		try {
			goldval.setText(Utilities.getgold());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void enableGoldVal() {
		goldval.setEditable(true);
		goldval.requestFocus();
		editGold.setOpacity(1);
	}

	public void callAddSale() throws IOException {
		Utilities.openForm("AddSale.fxml", StageStyle.UNDECORATED);

	}

	public void callOrdersTable() throws IOException{
		Utilities.openForm("OrdersTable.fxml", StageStyle.DECORATED);
	}
	public void updateGold() {
		Utilities.setGold(goldval.getText());
		goldval.setEditable(false);
		editGold.setOpacity(0);
	}

	public void callAddOrder() throws IOException {
		Utilities.openForm("AddOrder.fxml", StageStyle.UNDECORATED);
	}

	public void openSalesTable() throws IOException { 
		Utilities.openForm("SalesTable.fxml", StageStyle.DECORATED);
	}

	public void openStocks() throws IOException {
		Utilities.openForm("StockTable.fxml", StageStyle.DECORATED);
	}
	public void openBuyIn() throws IOException{
		Utilities.openForm("BuyIn.fxml", StageStyle.UNDECORATED);
	}
	public void callAddPawn() throws IOException{
		Utilities.openForm("AddPawnDeal.fxml", StageStyle.UNDECORATED);
	}

}
