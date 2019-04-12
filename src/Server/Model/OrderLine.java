package Server.Model;

/**
 * Order for a single item
 */
public class OrderLine {
    /**
     * the item to order
     */
    private Item theItem;
    /**
     * quantity to order
     */
    private int orderQuantity;

    /**
     * Constructs an orderline
     * @param item item to order
     * @param quantity quantity to order
     */
    protected OrderLine(Item item, int quantity) {
        theItem = item;
        setOrderQuantity(quantity);
    }

    public Item getTheItem() {
        return theItem;
    }

    protected void setTheItem(Item theItem) {
        this.theItem = theItem;
    }

    protected int getOrderQuantity() {
        return orderQuantity;
    }

    protected void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String toString() {
        return "Item Name: " + theItem.getItemName() +
                ", Item ID: " + theItem.getItemId() + "\n" +
                "Order Quantity: " + orderQuantity + "\n";
    }

}
