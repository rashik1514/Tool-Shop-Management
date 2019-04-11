package Server.Model;

import java.util.ArrayList;

public class Shop extends Database {

    private Inventory theInventory;

    public Shop(Inventory inventory) {
        theInventory = inventory;
    }

    public String decreaseItem(String name) {
        if (theInventory.manageItem(name) == null)
            return "Couldn't not decrease item quantity!\n";
        else
            return "Item quantity was decreased!\n";
    }

    public void listAllSuppliers() {
        ArrayList<Supplier> supplierList = loadSuppliers();
        for (Supplier s : supplierList) {
            System.out.println(s);
        }
    }

    private String outputItem(Item theItem) {
        return "The item information is as follows: \n" + theItem;
    }

    public String getItemQuantity(String name) {
        int quantity = theInventory.getItemQuantity(name);
        if (quantity < 0)
            return "Item " + name + " could not be found!";
        else
            return "The quantity of Item " + name + " is: " + quantity + "\n";
    }

    public String printOrder() {
        return theInventory.printOrder();
    }


}

