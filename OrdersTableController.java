import com.jfoenix.controls.JFXTextField;
import java.sql.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
public class OrdersTableController{
	@FXML
	TableView<OrderInfo> order_table;
	@FXML
	TableColumn<OrderInfo, String> order_id_col,cus_name_col,cus_phone_col,item_name_col,item_description_col,dueDate_col;
	@FXML
	JFXTextField item_name_txt,item_weight_txt,item_depre_txt,cus_name_txt,cus_phone_txt,dateOfOrder_txt,dueDate_txt;
	@FXML
	TextArea item_description_txt;
	public void initialize() {
		loadTable();
	}
	
	private void loadTable() {
		
		ObservableList<OrderInfo> list = FXCollections.observableArrayList();
		String query = "select [Order].id, Customer.Cus_Name,Customer.Cus_Phone," + 
				"Stock.Name,[Order].item_description,[Order].DueDate " + 
				"from ([Order] INNER JOIN Stock) INNER JOIN Customer where [Order].Item_ID = "
				+ "Stock.ID and [Order].Customer_ID = Customer.ID;";
		
		
		order_id_col.setCellValueFactory(new PropertyValueFactory<OrderInfo, String>("order_id"));
		cus_name_col.setCellValueFactory(new PropertyValueFactory<OrderInfo, String>("cus_name"));
		cus_phone_col.setCellValueFactory(new PropertyValueFactory<OrderInfo, String>("cus_phone"));
		item_name_col.setCellValueFactory(new PropertyValueFactory<OrderInfo, String>("item_name"));
		item_description_col.setCellValueFactory(new PropertyValueFactory<OrderInfo, String>("item_description"));
		dueDate_col.setCellValueFactory(new PropertyValueFactory<OrderInfo,String>("duedate"));

		Connection con = new Connector().connect();
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				OrderInfo i = new OrderInfo(rs.getInt("id"),rs.getString("Cus_Name"),rs.getString("Cus_Phone"),rs.getString("Name"),rs.getString("item_description"),rs.getString("dueDate"));
				list.add(i);
			}
			order_table.setItems(list);
			con.close();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	
	}

	public void ShowSelectedData() {
		Connection c = new Connector().connect();
		OrderInfo f = order_table.getSelectionModel().getSelectedItem();
		String get_items_id_and_dOfOrder = "SELECT Item_ID,DateOfOrder from [Order] where [Order].id= " + f.order_id + ";";
		String item_id,dateOfOrder;
		try {
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery(get_items_id_and_dOfOrder);
			r.next();
			item_id = r.getString("item_id");
			dateOfOrder = r.getString("DateOfOrder");
				if(f  != null) {
					String sql_for_item = "SELECT weight,depreciation from Stock where id =" + item_id +";";
					ResultSet rs = s.executeQuery(sql_for_item);
					rs.next();
					item_name_txt.setText(f.item_name);
					item_weight_txt.setText(rs.getString("Weight"));
					item_depre_txt.setText(rs.getString("Depreciation"));
					cus_name_txt.setText(f.cus_name);
					cus_phone_txt.setText(f.cus_phone);
					dateOfOrder_txt.setText(dateOfOrder);
					item_description_txt.setText(f.item_description);
					dueDate_txt.setText(f.duedate);
				}		
				c.close();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}