package Server.Model;

public class Item extends Database {

    private int itemId;
    private String itemName;
    private int itemQuantity;
    private double itemPrice;
    private boolean alreadyOrdered;
    private Supplier theSupplier;
    private static final int ORDERQUANTITY = 40;
    private static final int MINIMUMUMBER = 20;

    public Item(int id, String name, int quantity, double price, Supplier sup) {
        itemId = id;
        itemName = name;
        itemQuantity = quantity;
        itemPrice = price;
        theSupplier = sup;
        setAlreadyOrdered(false);
    }

    public boolean decreaseItemQuantity() {
        if (itemQuantity > 0) {
            itemQuantity--;
            changeItemQuantity(-1, this);
            return true;
        } else
            return false;

    }

    public OrderLine placeOrder() {
        OrderLine ol;
        if (getItemQuantity() < MINIMUMUMBER && alreadyOrdered == false) {
            ol = new OrderLine(this, ORDERQUANTITY);
            alreadyOrdered = true;
            changeAlreadyOrdered(this);
            return ol;
        }
        return null;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setTheSupplier(Supplier sup) {
        theSupplier = sup;
    }

    public Supplier getTheSupplier() {
        return theSupplier;
    }

    public String toString() {
        return "Item ID: " + itemId + "\n Item Name: " + itemName + "\n Item Quantity: " +
                itemQuantity + "\n" + "Supplier: " + theSupplier.getSupName() + "\n";
    }

    public boolean isAlreadyOrdered() {
        return alreadyOrdered;
    }

    public void setAlreadyOrdered(boolean alreadyOrdered) {
        this.alreadyOrdered = alreadyOrdered;
    }

}
