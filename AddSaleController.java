import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class AddSaleController {
	@FXML 
	JFXButton exit;
	@FXML
	JFXTextField cus_name,cus_phone,cus_addr,item_id;
	@FXML
	TextArea item_desc;
	public AddSaleController() {}
	
	public void initialize() {
		if(item_desc.isFocused()) {
			updateItemDesc();
		}
		item_id.setOnKeyPressed(new EventHandler<KeyEvent>()
	    {
	        @Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.ENTER) || ke.getCode().equals(KeyCode.TAB))
	            {
	                updateItemDesc();
	            }
	        }
	    });
	}
	public void exit() {
		DashboardController.pagetracker = 1;
		((Stage)exit.getScene().getWindow()).close();
	}
	public void addData() {
		Sale s = new Sale(cus_name.getText(),item_id.getText());
	}
	public void updateItemDesc() {

		item_desc.setText("");
		try {
			Connection c = Connector.connect();
			String sql = "SELECT * FROM Stock where ID = '"+item_id.getText().toUpperCase()+"'";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if(rs.next() != false) {
				item_desc.setText("ပစၥည္းအမည္: "+rs.getString(2)+"\n"+"ပစၥည္းအေလးခ်ိန္: "+
						new Weight(rs.getString(3)).getKyat()+" က်ပ္ "+new Weight(rs.getString(3)).getPel()+
						" ပဲ"+new Weight(rs.getString(3)).getYway()+" ေ႐ြး "+"\n အေလ်ာ့တြက္: "+new Weight(rs.getString(4)).getKyat()+" က်ပ္ "+new Weight(rs.getString(4)).getPel()+
						" ပဲ"+new Weight(rs.getString(4)).getYway()+" ေ႐ြး ");
			}
			else {
				item_desc.setText("ပစၥည္းမရွိပါ ျပန္လည္စစ္ေဆးေပးပါ");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}