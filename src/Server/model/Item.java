package Server.Model;

/**
 * Class for items
 * @author Christina Lu, Layla Arab, MD Rashik Hassan
 * @version 1.0
 * @since April 5 2019
 */
public class Item {
    /**
     * ID of the tool
     */
    private int itemId;
    /**
     * name of the tool
     */
    private String itemName;
    /**
     * quantity of the tool
     */
    private int itemQuantity;
    /**
     * price of the tool
     */
    private double itemPrice;
    /**
     * true if item is already ordered, false otherwise
     */
    private boolean alreadyOrdered;
    /**
     * supplier of the tool
     */
    private Supplier theSupplier;
    /**
     * quantity for which item will be ordered automatically
     */
    private static final int ORDERQUANTITY = 40;
    /**
     * minimum number of the item in stock
     */
    private static final int MINIMUMUMBER = 20;

    /**
     * constructs the item
     * @param id
     * @param name
     * @param quanitiy
     * @param price
     * @param sup
     */
    public Item (int id, String name, int quanitiy, double price, Supplier sup) {

        itemId = id;
        itemName = name;
        itemQuantity = quanitiy;
        itemPrice = price;
        theSupplier = sup;
        setAlreadyOrdered(false);
    }

    /**
     * @return true if item has been decreased
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
     * places an order for the item
     * @return
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
     *
     * @return the itemID
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * @param itemId the value for ID to set
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /**
     * @return returns the item name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     *
     * @param itemName the name for the item to be set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     *
     * @return the item quantity
     */
    public int getItemQuantity() {
        return itemQuantity;
    }

    /**
     *
     * @param itemQuantity the quantity of the item to set
     */
    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    /**
     *
     * @return the item price
     */
    public double getItemPrice() {
        return itemPrice;
    }

    /**
     *
     * @param itemPrice price of the item to be set
     */
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    /**
     *
     * @param sup supplier to set
     */
    public void setTheSupplier (Supplier sup) {
        theSupplier = sup;
    }

    /**
     *
     * @return supplier member variable
     */
    public Supplier getTheSupplier () {
        return theSupplier;
    }

    /**
     *
     * @return makes the member variables into a string
     */
    public String toString () {
        return "Item ID: " + itemId + ",\tItem Name: " + itemName + ",\tItem Quantity: " +
                itemQuantity + "\n";
    }

    /**
     *
     * @return true if the item has been ordered
     */
    public boolean isAlreadyOrdered() {
        return alreadyOrdered;
    }

    /**
     *
     * @param alreadyOrdered sets the order status of item
     */
    public void setAlreadyOrdered(boolean alreadyOrdered) {
        this.alreadyOrdered = alreadyOrdered;
    }

}
