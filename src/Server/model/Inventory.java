package Server.Model;

import java.util.ArrayList;

/**
 * This implements the store's inventory
 */
public class Inventory extends Database {

    /**
     * an order
     */
    private Order myOrder;

    /**
     * constructs the inventory
     */
    public Inventory() {
        myOrder = new Order();
    }

    /**
     * this places multiple orders
     */
    protected void placeOrders() {
        ArrayList<Item> items = loadItems(loadSuppliers());
        for (Item i : items)
            if (i != null)
                placeOrder(i);
    }

    /**
     * this places an order for the received item
     *
     * @param theItem the item to be ordered
     */
    protected void placeOrder(Item theItem) {
        OrderLine ol = theItem.placeOrder();
        if (ol != null) {
            myOrder.addOrderLine(ol);
        }
    }

    /**
     * this decreases the quantity of the received item in stock
     *
     * @param name the name of the item
     * @return the item, null if the item is not found
     */
    private Item decreaseItem(String name) {
        Item theItem = searchForItem(name);

        if (theItem == null)
            return null;

        if (theItem.decreaseItemQuantity()) {
            return theItem;
        }
        return null;

    }

    /**
     * this decreases the quantity of the received item in stock
     *
     * @param name the name of the item
     * @return the item, null if the item is not found
     */
    protected int getItemQuantity(String name) {
        Item theItem = searchForItem(name);
        if (theItem == null)
            return -1;
        else
            return theItem.getItemQuantity();
    }

    /**
     * searches for the item by name
     *
     * @param name name of the item
     * @return the item if found, null if otherwise
     */
    protected Item searchForItem(String name) {
        ArrayList<Item> itemList = loadItems(loadSuppliers());
        for (Item i : itemList) {
            if (i.getItemName().equals(name))
                return i;
        }
        return null;
    }

    /**
     * retruns the inventory list as a string
     *
     * @return inventory as a string
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        ArrayList<Item> itemList = loadItems(loadSuppliers());
        for (Item i : itemList) {
            str.append(i);
        }
        return str.toString();
    }

    /**
     * searchs for the item by id
     *
     * @param id item id
     * @return the item if found, null if other wise
     */
    public Item searchForItem(int id) {
        ArrayList<Item> itemList = loadItems(loadSuppliers());
        for (Item i : itemList) {
            if (i.getItemId() == id)
                return i;
        }
        return null;
    }

    /**
     * returns the order as a string
     *
     * @return order as a string
     */
    protected String printOrder() {
        return myOrder.toString();
    }

}


