package application;

abstract class Item {
	protected String itemName;
	protected String quantity;
	
	public Item(String groceryName, String quantity) {
		this.itemName = groceryName;
		this.quantity = quantity;
	}

}
