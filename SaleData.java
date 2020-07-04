

public class SaleData {
	String id = "", itemID = "", customerName = "", itemName = "";

	public SaleData() {

	}

	public SaleData(SaleData selected) {
		this.id = selected.id;
		this.itemID = selected.itemID;
		this.customerName = selected.customerName;
		this.itemName = selected.itemName;
	}

	public void setSaleData(String a, String b, String c, String d) {
		this.id = a; 
		this.itemID = b;
		this.itemName = c;
		this.customerName = d;
	}

	public String getId() {
		return id;
	}

	void setId(String id) {
		this.id = id;
	}

	public String getItemID() {
		return itemID;
	}

	void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getCustomerName() {
		return customerName;
	}

	void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getItemName() {
		return itemName;
	}

	void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public SaleData getSaleData() {
		return (this);
	}

	public String toString() {
		return (this.id + " " + this.itemID + "  " + this.itemName + " " + this.customerName);
	}

}
