package Server.model;
import java.util.ArrayList;
/**
 * Stores all the information of the tools thats required or added to the inventory
 * @author MD Rashik Hassan
 *
 */
public class Item {

    /**
     * Unique identification of the item
     */
    private int id;

    /**
     * name of the item
     */
    private String name;

    /**
     * Quantity remained in the inventory
     */
    private int quantity;
    /**
     * Price of the item
     */
    private Double price;
    /**
     * Supplier whose providing the item
     */
    private int supplierId;

    /**
     * Empty constructor
     * @param id
     * @param name
     * @param quantity
     * @param price
     * @param theSupplier
     */
    public Item(int id, String name, int quantity, double price, Supplier theSupplier) {

    }
    /**
     * Contructs the item putting various information
     * @param id Unique identification of the item
     * @param name name of the item
     * @param quantity Quantity remained in the inventory
     * @param price Price of the item
     * @param supplierId Supplier whose providing the item
     */
    public Item(int id, String name, int quantity, Double price, int supplierId) {
        this.name = name;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.supplierId = supplierId;
    }

    public Item() {

    }

    /**
     * checks the quantity and orders if it falls below the limit 40.
     * @return the number of items present
     */
    public int checkQuantity() {
        Inventory i = new Inventory();

        if(quantity < 40) {
            i.order.orders.add(new OrderLine(id, name, 50-quantity, supplierId));
        }
        System.out.println("Number of "+name+"s is "+ quantity);
        return quantity;

    }

    /**
     * Converts the information to string
     * @param items list of items in the 9inventory
     * @return the string formed with all the information
     */
    public String toString(ArrayList<Item> items) {
        String temp = "";
        for(Item item: items) {
            temp += "ID: "+ item.id + " Name: "+item.name + " Price: "+
                    item.price + " Quantity: "+item.quantity+"\n";
        }
        return temp;
    }

    /**
     * displays all the items in the list
     * @param items list of items
     */
    public void displayItems(ArrayList<Item> items) {
        System.out.println(toString(items));
    }

    /**
     * returns id
     * @return id
     */
    public int getId() {
        return id;
    }
    /**
     * Overwrites id
     * @param id Unique identification
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * returns name
     * @return name of the item
     */
    public String getName() {
        return name;
    }
    /**
     * Overrides the name
     * @param name name of the item
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * return quantity of the particular item present
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * Overrides with a new quantity
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * returns prices
     * @return price of the item
     */
    public Double getPrice() {
        return price;
    }
    /**
     * Overrides the price of the item
     * @param price of the item
     */
    public void setPrice(Double price) {
        this.price = price;
    }
    /**
     * returns supplier ID
     * @return
     */
    public int getSupplierId() {
        return supplierId;
    }
    /**
     * Overrides supplier ID
     * @param supplierId
     */
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }


}

