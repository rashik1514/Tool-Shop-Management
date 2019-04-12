package Server.Model;

import java.util.ArrayList;

/**
 * Supplier of the items
 */
public class Supplier {

    /**
     * Supplier Id
     */
    private int supId;
    /**
     * Supplier name
     */
    private String supName;
    /**
     * Supplier address
     */
    private String supAddress;
    /**
     * Contact name of supplier
     */
    private String supContactName;
    /**
     *
     */
    private ArrayList<Item> itemList;

    /**
     * Constructs the supplier
     * @param id id of supplier
     * @param name name of supplier
     * @param address address of supplier
     * @param contactName contact name of supplier
     */
    protected Supplier(int id, String name, String address, String contactName) {
        supId = id;
        supName = name;
        supAddress = address;
        supContactName = contactName;
        itemList = new ArrayList<Item>();
    }

    /**
     * getter for the supplier id
     *
     * @return supId
     */
    protected int getSupId() {
        return supId;
    }

    /**
     * @param supId ID of supplier to set
     */
    protected void setSupId(int supId) {
        this.supId = supId;
    }

    /**
     * getter for the supplier name
     *
     * @return supName
     */
    protected String getSupName() {
        return supName;
    }

    /**
     * @param supName name of sup to set
     */
    protected void setSupName(String supName) {
        this.supName = supName;
    }

    /**
     * getter for the supplier address
     *
     * @return supplier address
     */
    protected String getSupAddress() {
        return supAddress;
    }

    /**
     * @param supAddress sup address to set
     */
    protected void setSupAddress(String supAddress) {
        this.supAddress = supAddress;
    }

    /**
     * getter for the supplier contact name
     *
     * @return supplier's contact name
     */
    protected String getSupContactName() {
        return supContactName;
    }

    /**
     * @param supContactName contact name of sup to set
     */
    protected void setSupContactName(String supContactName) {
        this.supContactName = supContactName;
    }

    /**
     * @return the attributes of supplier stringed
     */
    public String toString() {
        return supName + " Supplier ID: " + supId + "\n";
    }

    /**
     * getter for the item list
     *
     * @return gets item list
     */
    protected ArrayList<Item> getItemList() {
        return itemList;
    }

    /**
     * @param itemList item list to set
     */
    protected void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }
}

