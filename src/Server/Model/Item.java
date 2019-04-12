package Server.Model;

public class Item extends Database {

    /**
     * id of item
     */
    private int itemId;
    /**
     * name of item
     */
    private String itemName;
    /**
     * quantity of item
     */
    private int itemQuantity;
    /**
     * price of item
     */
    private double itemPrice;
    /**
     * supplier of item
     */
    private Supplier theSupplier;
    /**
     * minimum order number
     */
    private static final int MINIMUMUMBER = 40;

    /**
     * Constructs the item
     *
     * @param id
     * @param name
     * @param quantity
     * @param price
     * @param sup
     */
    public Item(int id, String name, int quantity, double price, Supplier sup) {
        itemId = id;
        itemName = name;
        itemQuantity = quantity;
        itemPrice = price;
        theSupplier = sup;
    }

    /**
     * decreases the quantity of the item in the database
     *
     * @return true if item has changed quantity
     */
    protected boolean decreaseItemQuantity() {
        if (itemQuantity > 0) {
            itemQuantity--;
            changeItemQuantity(-1, this);
            return true;
        } else
            return false;

    }

    /**
     * places an order for the item if its quantity in stock is less than the minimum quantity
     *
     * @return the orderline placed
     */
    protected OrderLine placeOrder() {
        OrderLine ol;
        if (getItemQuantity() < MINIMUMUMBER) {
            int amount = MINIMUMUMBER - itemQuantity;
            ol = new OrderLine(this, amount);
            changeItemQuantity(amount, this);
            return ol;
        }
        return null;
    }

    protected int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    protected String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    protected int getItemQuantity() {
        return itemQuantity;
    }

    protected void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    protected double getItemPrice() {
        return itemPrice;
    }

    protected void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    protected void setTheSupplier(Supplier sup) {
        theSupplier = sup;
    }

    protected Supplier getTheSupplier() {
        return theSupplier;
    }

    public String toString() {
        return "Item ID: " + itemId + ";Item Name: " + itemName + ";Item Quantity: " +
                itemQuantity + ";Supplier: " + theSupplier.getSupName();
    }
}


