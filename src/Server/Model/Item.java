package Server.Model;

/**
 *
 */
public class Item {

	private int itemId;
	private String itemName;
	private int itemQuantity;
	private double itemPrice;
	private boolean alreadyOrdered;
	private Supplier theSupplier;
	private static final int ORDERQUANTITY = 40;
	private static final int MINIMUMUMBER = 20;


	/**
	 * Constructor for the item
	 * @param id item ID
	 * @param name name of the item
	 * @param quantity amount of the item in stock
	 * @param price price of the item
	 * @param sup item supplier
	 */
	public Item (int id, String name, int quantity, double price, Supplier sup) {
		itemId = id;
		itemName = name;
		itemQuantity = quantity;
		itemPrice = price;
		theSupplier = sup;
		setAlreadyOrdered(false);
	}

	/**
	 * decreases item quantity by 1
	 * @return returns true if decrease was successful, false if otherwise
	 */
	public boolean decreaseItemQuantity () {
		if (itemQuantity > 0) {
			itemQuantity--;
		    return true;
		}
		else
			return false;
			
	}

	/**
	 * places an order if stock falls under minimum quantity and sets alreadyOrdered as true
	 * @return the order line
	 */
	public OrderLine placeOrder (){
		OrderLine ol;
		if (getItemQuantity() < MINIMUMUMBER && alreadyOrdered == false){
			ol = new OrderLine (this, ORDERQUANTITY);
			alreadyOrdered = true;
			return ol;
		}
	    return null;
	}

	/**
	 * gets the item id
	 * @return returns the item id
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * sets the item id to the received id
	 * @param itemId item id
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	/**
	 * gets the item name
	 * @return the item name
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * sets the item name to the received name
	 * @param itemName the item name
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * gets the item quantity
	 * @return the quantity if the item in stock
	 */
	public int getItemQuantity() {
		return itemQuantity;
	}

	/**
	 * sets the item quantity to the received item quantity
	 * @param itemQuantity item quantity
	 */
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	/**
	 * gets the price of the item
	 * @return price of the item
	 */
	public double getItemPrice() {
		return itemPrice;
	}

	/**
	 * sets the item's price
	 * @param itemPrice price of the item
	 */
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	/**
	 * sets the item supplier
	 * @param sup item supplier
	 */
	public void setTheSupplier (Supplier sup) {
		theSupplier = sup;
	}

	/**
	 * gets the supplier
	 * @return the item's supplier
	 */
	public Supplier getTheSupplier () {
		return theSupplier;
	}

	/**
	 * returns the item information as a string
	 * @return the string
	 */
	public String toString () {
		return "Item ID: " + itemId + ", Item Name: " + itemName + ", Item Quantity: " + 
	           itemQuantity + "\n";
	}

	/**
	 * returns alreadyOrdered
	 * @return
	 */
	public boolean isAlreadyOrdered() {
		return alreadyOrdered;
	}

	/**
	 * sets alreadyOrdered
	 * @param alreadyOrdered true if already ordered, false if otherwise
	 */
	public void setAlreadyOrdered(boolean alreadyOrdered) {
		this.alreadyOrdered = alreadyOrdered;
	}

	/**
	 * sets item id
	 * @param ID item id
	 */
	public void setItemID(int ID) {
		this.itemId = ID;
	}

	/**
	 * sets item id
	 * @return item id
	 */
	public int getItemID(){ return itemId; }

}
