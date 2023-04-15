package application;

import java.sql.Date;

public class Groceries extends Item {

	private int id;
	private String groceryName;
	private String quantity;
	private Date useBy;
	
	public Groceries(int id, String groceryName, String quantity, Date useBy) {
		super(groceryName, quantity);
		this.id = id;
		this.groceryName = groceryName;
		this.quantity = quantity;
		this.useBy = useBy;
	}

	public String getGroceryName() {
		return groceryName;
	}

	public void setGroceryName(String groceryName) {
		this.groceryName = groceryName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Date getUseBy() {
		return useBy;
	}

	public void setUseBy(Date useBy) {
		this.useBy = useBy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
