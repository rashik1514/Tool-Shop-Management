package Server.Model;

import java.util.ArrayList;

/**
 * This implements the shop
 *
 * @author Christina Lu 30037885, Layla Arab 30017060, MD Rashik Hassan 30048022
 * @version 1.0
 * @since April 5 2019
 */
public class Shop {
    /**
     * the shops inventory
     */
    private Inventory theInventory;
    /**
     * list of suppliers
     */
    private ArrayList<Supplier> supplierList;

    /**
     * constructs the shop
     *
     * @param inventory
     * @param suppliers
     */
    public Shop(Inventory inventory, ArrayList<Supplier> suppliers) {
        theInventory = inventory;
        supplierList = suppliers;
    }

    /**
     * @return the inventory
     */
    public Inventory getTheInventory() {
        return theInventory;
    }

    /**
     * @param inventory the inventory to set
     */
    public void setTheInventory(Inventory inventory) {
        theInventory = inventory;
    }

    /**
     * @return supplier list
     */
    public ArrayList<Supplier> getSupplierList() {
        return supplierList;
    }

    /**
     * @param suppliers the list of suppliers to set
     */
    public void setSupplierList(ArrayList<Supplier> suppliers) {
        supplierList = suppliers;
    }

    /**
     * lists all items in the inventory
     */
    public void listAllItems() {
        System.out.println(theInventory);
    }

    /**
     * @param name name of the item to decrease
     * @return the item that has been decreased
     */
    public String decreaseItem(String name) {
        if (theInventory.manageItem(name) == null)
            return "Couldn't not decrease item quantity!\n";
        else
            return "Item quantity was decreased!\n";
    }

    /**
     * lists all the suppliers
     */
    public void listAllSuppliers() {
        for (Supplier s : supplierList) {
            System.out.println(s);
        }
    }

    /**
     * @param name name of the item to retrieve
     * @return item with the name passed
     */
    public String getItem(String name) {
        Item theItem = theInventory.searchForItem(name);
        if (theItem == null)
            return "Item " + name + " could not be found!";
        else
            return outputItem(theItem);
    }

    /**
     * @param id if of the item to retrieve
     * @return item with the id passed
     */
    public String getItem(int id) {
        Item theItem = theInventory.searchForItem(id);
        if (theItem == null)
            return "Item number " + id + " could not be found!";
        else
            return outputItem(theItem);
    }

    /**
     * outputs the item details to a string
     *
     * @param theItem item to process
     * @return the item attributes stringed
     */
    private String outputItem(Item theItem) {
        return "The item information is as follows: \n" + theItem;
    }

    /**
     * @param name name of the item to access
     * @return the quantity of the item
     */
    public String getItemQuantity(String name) {
        // TODO Auto-generated method stub
        int quantity = theInventory.getItemQuantity(name);
        if (quantity < 0)
            return "Item " + name + " could not be found!";
        else
            return "The quantity of Item " + name + " is: " + quantity + "\n";
    }

    /**
     * prints the order
     *
     * @return prints the inventory attributes
     */
    public String printOrder() {
        // TODO Auto-generated method stub

        return theInventory.printOrder();
    }


}

