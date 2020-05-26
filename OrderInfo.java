

public class OrderInfo {
	int order_id;
	String cus_name, cus_phone, item_name, item_description,duedate;
	Weight depreciation;

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id; 
	}

	public Weight getDepreciation() {
		return depreciation;
	}

	public void setDepreciation(Weight depreciation) {
		this.depreciation = depreciation;
	}

	public String getCus_name() {
		return cus_name;
	}

	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}

	public String getCus_phone() {
		return cus_phone;
	}

	public void setCus_phone(String cus_phone) {
		this.cus_phone = cus_phone;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_description() {
		return item_description;
	}

	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}

	public String getDuedate() {
		return duedate;
	}
	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}

	public OrderInfo(int i, String string, String string2, String string3, String string4, String string5) {
		this.order_id  = i;
		this.cus_name = string;
		this.cus_phone = string2;
		this.item_name = string3;
		this.item_description = string4;
		this.duedate = string5;
	}
}
