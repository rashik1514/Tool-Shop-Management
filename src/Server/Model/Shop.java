package Server.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Shop {

    private Inventory theInventory;
    private ArrayList<Supplier> supplierList;

    public Shop(Inventory inventory, ArrayList<Supplier> suppliers) {

        theInventory = inventory;
        supplierList = suppliers;

    }

    public Inventory getTheInventory() {
        return theInventory;
    }

    public void setTheInventory(Inventory inventory) {
        theInventory = inventory;
    }

    public ArrayList<Supplier> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(ArrayList<Supplier> suppliers) {
        supplierList = suppliers;
    }


    public void listAllItems() {
        System.out.println(theInventory);

    }

    public String decreaseItem(String name) {
        if (theInventory.manageItem(name) == null)
            return "Couldn't not decrease item quantity!\n";
        else
            return "Item quantity was decreased!\n";
    }

    public void listAllSuppliers() {
        // TODO Auto-generated method stub
        for (Supplier s : supplierList) {
            System.out.println(s);
        }

    }

    public String getItem(String name) {
        // TODO Auto-generated method stub
        Item theItem = theInventory.searchForItem(name);
        if (theItem == null)
            return "Item " + name + " could not be found!";
        else
            return outputItem(theItem);

    }

    public String getItem(int id) {
        // TODO Auto-generated method stub
        Item theItem = theInventory.searchForItem(id);
        if (theItem == null)
            return "Item number " + id + " could not be found!";
        else
            return outputItem(theItem);
    }

    public int searchBySupID(int ID) {
        for (int i = 0; i < supplierList.size(); i++) {
            if (supplierList.get(i).getSupId() == ID)
                return i;
        }
        return -1; // could not find
    }

    public int searchByItemID(int ID) {
        for (int i = 0; i < theInventory.getItemList().size(); i++) {
            if (theInventory.getItemList().get(i).getItemId() == ID)
                return i;
        }
        return -1; // could not find
    }


    private String outputItem(Item theItem) {
        return "The item information is as follows: \n" + theItem;
    }

    public String getItemQuantity(String name) {
        // TODO Auto-generated method stub
        int quantity = theInventory.getItemQuantity(name);
        if (quantity < 0)
            return "Item " + name + " could not be found!";
        else
            return "The quantity of Item " + name + " is: " + quantity + "\n";
    }

    public String printOrder() {
        // TODO Auto-generated method stub

        return theInventory.printOrder();
    }

//    public void readItemFile() throws FileNotFoundException {
//        Scanner scan = new Scanner(new File("/users/layla/documents/uni/ensf409/lab3/ex1/items.txt"));
//        while (scan.hasNext()) {
//            Item Item = new Item();
//            String line = scan.nextLine();
//            String[] lines = line.split(";");
//            Item.setItemID(Integer.parseInt(lines[0]));
//            Item.setItemName(lines[1]);
//            Item.setItemQuantity(Integer.parseInt(lines[2]));
//            Item.setItemPrice(Double.parseDouble(lines[3]));
//            int i = searchBySupID(Integer.parseInt(lines[4]));
//            Item.setTheSupplier(supplierList.get(i));
//            getTheInventory().getItemList().add(Item);
//        }
//
//    }
//
//    public void readSupplierFile() throws FileNotFoundException {
//        Scanner scan = new Scanner(new File("/users/layla/documents/uni/ensf409/lab3/ex1/suppliers.txt"));
//        while (scan.hasNext()) {
//            Supplier supplier = new Supplier();
//            String line = scan.nextLine();
//            String[] lines = line.split(";");
//            supplier.setSupId(Integer.parseInt(lines[0]));
//            supplier.setSupName(lines[1]);
//            supplier.setSupAddress(lines[2]);
//            supplier.setSupContactName(lines[3]);
//            supplierList.add(supplier);
//        }
//
//    }


}
