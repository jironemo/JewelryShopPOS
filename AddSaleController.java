

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
	

	
	//constructor for no reason what so ever;
	public AddSaleController() {}
	
	//method to work once the scene is initialized
	public void initialize() {
		// method that will work when TAB Key is pressed on item id text field;
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
	
	
	///exit this stage/ AddSale form
	public void exit() {
		((Stage)exit.getScene().getWindow()).close();
	}
	
	
	///add sale data to Database
	public void addData() {
		Boolean b = new Boolean(someFieldsNULL());
		Alert alert = new Alert(AlertType.CONFIRMATION);
		Alert alert2 = new Alert(AlertType.ERROR);
		alert.getDialogPane().setStyle("-fx-font-family:Zawgyi-One");
		alert2.getDialogPane().setStyle("-fx-font-family:Zawgyi-One");
		if(someFieldsNULL() == false) {
			String id = this.item_id.getText();
			Item k = new Item(id);
			if(k.notSold()) {
				alert.setContentText("အေရာင္းစာရင္းထည့္သြင္းမည္။");
				Optional<ButtonType> result = alert.showAndWait();
				if(result.get() == ButtonType.OK) {
					Customer buyer = new Customer(cus_name.getText(),cus_addr.getText(),cus_phone.getText());
					buyer.add();
					Sale sale = new Sale(k.id,buyer.getID());
					sale.add();
					Item.setSold(item_id.getText());
					alert2.setAlertType(AlertType.INFORMATION);
					alert2.setTitle("Successful!");
					alert2.setContentText("အေရာင္းစာရင္းထည့္သြင္းၿပီးပါၿပီ");
					alert2.show();
				}
			}	else {
				alert2.setAlertType(AlertType.ERROR);
				alert2.setTitle("ပစၥည္းမရွိပါ");
				alert2.setContentText("ပစၥည္းရွာမေတြ႕ပါ");
				alert2.show();
			}
			exportReceipt();
			exit();
		}else {
			alert2.setAlertType(AlertType.ERROR);
			alert2.setTitle("ပစၥည္းမရွိပါ");
			alert2.setContentText("အခ်က္အလက္မျပည့္စံုပါ");
			alert2.show();
		}

	}
	
	public void exportReceipt() {
		try {
			 int id = Integer.parseInt(item_id.getText());
			String[] findText = {"$date","$cusname","$phone","$cusaddr","$itemid","$itemname","$itemWeight","$itemDepreciation"};
			String[] replaceText = {new Date().toString() ,cus_name.getText(),cus_phone.getText(),cus_addr.getText(),item_id.getText(),Item.get("Name",id),Item.get("Weight", id),Item.get("Depreciation", id)};
		FileInputStream fis = new FileInputStream("ReceiptTemplate.doc");
			HWPFDocument in = new HWPFDocument(fis);
			HWPFDocument out = replaceText(in, findText,replaceText);
			FileOutputStream fos = new FileOutputStream(System.getProperty("user.home") + "//Desktop//Receipt.doc");
			out.write(fos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private HWPFDocument replaceText(HWPFDocument doc, String findText[], String replaceText[]) {
        Range r = doc.getRange();
        for(int a = 0; a < Array.getLength(findText);a++) {
            for (int i = 0; i < r.numSections(); ++i) {
                Section s = r.getSection(i);
                for (int j = 0; j < s.numParagraphs(); j++) {
                    Paragraph p = s.getParagraph(j);
                    for (int k = 0; k < p.numCharacterRuns(); k++) {
                        CharacterRun run = p.getCharacterRun(k);
                        String text = run.text();
                        if (text.contains(findText[a])) {
                            run.replaceText(findText[a], replaceText[a]);
                        }
                    }
                }
            }
        }
        return doc;
    }
	public void updateItemDesc() {
		///refresh the item description field
		
		item_desc.setText("");
			try {
				int id = Integer.parseInt((item_id.getText()));
				//get a connection and data from Stock;
				Connection c = Connector.connect();
				String sql = "SELECT * FROM Stock where ID = "+id+" and stock_status = 'stock';";
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);
				
				if(rs.next() != false) {
					item_desc.setText("ပစၥည္းအမည္: "+rs.getString(2)+"\n"+"ပစၥည္းအေလးခ်ိန္: "+
							new Weight(rs.getString(3)).getKyat()+" က်ပ္ "+new Weight(rs.getString(3)).getPel()+
							" ပဲ"+new Weight(rs.getString(3)).getYway()+" ေ႐ြး "+"\n အေလ်ာ့တြက္: "+
							new Weight(rs.getString(4)).getKyat()+" က်ပ္ "+new Weight(rs.getString(4)).getPel()+
							" ပဲ"+new Weight(rs.getString(4)).getYway()+" ေ႐ြး ");
				}
				else {
					item_desc.setText("ပစၥည္းမရွိပါ ျပန္လည္စစ္ေဆးေပးပါ");
				}
				c.close();
			}
			catch(Exception e) {
			}
	}
	
	
	private boolean someFieldsNULL() {
		boolean a = false;
		if(cus_phone.getText()== null||cus_phone.getText().trim().equals("")) {
			a = true;
		}
		if(cus_name.getText()== null || cus_name.getText().trim().equals("")) {
			a = true;
		}
		if(cus_addr.getText()== null || cus_addr.getText().trim().equals("")) {
			a = true;
		}

		if(item_id.getText() == null ||item_id.getText().trim().equals("")) {
						a =  true;
		}
		return a;
	}
}