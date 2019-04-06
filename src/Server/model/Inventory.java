package Server.Model;

import java.util.ArrayList;

/**
 * Class that holds the items
 *
 * @author Christina Lu 30037885, Layla Arab 30017060, MD Rashik Hassan 30048022
 * @version 1.0
 * @since April 5 2019
 */
public class Inventory {
    /**
     * ArrayList that holds the items
     */
    private ArrayList<Item> itemList;
    /**
     * Order of items for the inventory
     */
    private Order myOrder;

    /**
     * Constructs the inventory
     *
     * @param itemList itemList to set
     */
    public Inventory(ArrayList<Item> itemList) {
        this.itemList = itemList;
        myOrder = new Order();
    }

    /**
     * @return the item list
     */
    public ArrayList<Item> getItemList() {
        return itemList;
    }

    /**
     * @param itemList list to set
     */
    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }

    /**
     * Manages the item's quantity and orders it
     *
     * @param name name of the item
     * @return the item
     */
    protected Item manageItem(String name) {
        Item theItem = decreaseItem(name);

        if (theItem != null) {
            placeOrder(theItem);
        }
        return theItem;
    }

    /**
     * places an order
     *
     * @param theItem item to order
     */
    protected void placeOrder(Item theItem) {
        OrderLine ol = theItem.placeOrder();
        if (ol != null) {
            myOrder.addOrderLine(ol);
        }
    }

    /**
     * searches for item and decreases quantity
     *
     * @param name name of item to search for
     * @return the item
     */
    private Item decreaseItem(String name) {

        Item theItem = searchForItem(name);

        if (theItem == null)
            return null;

        if (theItem.decreaseItemQuantity() == true) {

            return theItem;
        }
        return null;

    }

    /**
     * @param name name of item to find
     * @return quantity of item
     */
    protected int getItemQuantity(String name) {
        Item theItem = searchForItem(name);
        if (theItem == null)
            return -1;
        else
            return theItem.getItemQuantity();
    }

    /**
     * @param name name of item to find
     * @return index of item to find
     */
    protected Item searchForItem(String name) {
        for (Item i : itemList) {
            if (i.getItemName().equals(name))
                return i;
        }
        return null;
    }

    /**
     * @return the String of the inventory
     */
    public String toString() {
        String str = "";
        for (Item i : itemList) {
            str += i;
        }
        return str;
    }

    /**
     * @param id id of item to search for
     * @return the item with the passed id
     */
    protected Item searchForItem(int id) {
        // TODO Auto-generated method stub
        for (Item i : itemList) {
            if (i.getItemId() == id)
                return i;
        }
        return null;
    }

    /**
     * @return the String of the order
     */
    protected String printOrder() {
        // TODO Auto-generated method stub
        return myOrder.toString();
    }

}


