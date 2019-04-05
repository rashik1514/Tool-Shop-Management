package Server.model;
import java.util.ArrayList;
/**
 * Class for suppliers
 * @author Christina Lu, Layla Arab, MD Rashik Hassan
 * @version 1.0
 * @since April 5 2019
 */
public class Supplier {
    /**
     * id of supplier
     */
    private int supId;
    /**
     * name of supplier
     */
    private String supName;
    /**
     * address of supplier
     */
    private String supAddress;
    /**
     * name of supplier
     */
    private String supContactName;
    /**
     * list of items
     */
    private ArrayList <Item> itemList;

    /**
     * constructs the supplier
     * @param id
     * @param name
     * @param address
     * @param contactName
     */
    public Supplier (int id, String name, String address, String contactName) {
        supId = id;
        supName = name;
        supAddress = address;
        supContactName = contactName;
        itemList = new ArrayList <Item>();
    }

    /**
     * getter for the supplier id
     * @return supId
     */
    public int getSupId() { return supId; }

    /**
     *
     * @param supId
     */
    public void setSupId(int supId) { this.supId = supId; }

    /**
     * getter for the supplier name
     * @return supName
     */
    public String getSupName() { return supName; }

    /**
     *
     * @param supName
     */
    public void setSupName(String supName) { this.supName = supName; }

    /**
     * getter for the supplier address
     * @return supplier address
     */
    public String getSupAddress() { return supAddress; }

    /**
     *
     * @param supAddress
     */
    public void setSupAddress(String supAddress) { this.supAddress = supAddress; }

    /**
     * getter for the supplier contact name
     * @return supplier's contact name
     */
    public String getSupContactName() { return supContactName; }

    /**
     *
     * @param supContactName
     */
    public void setSupContactName(String supContactName) { this.supContactName = supContactName; }

    /**
     *
     * @return
     */
    public String toString () { return supName + " Supplier ID: " + supId+ "\n"; }

    /**
     * getter for the item list
     * @return
     */
    public ArrayList <Item> getItemList() { return itemList; }

    /**
     *
     * @param itemList
     */
    public void setItemList(ArrayList <Item> itemList) { this.itemList = itemList; }
}

