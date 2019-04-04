package model;
import java.util.ArrayList;

/**
 * Implements the supplier
 * @author MD Rashik Hassan
 *
 */
public class Supplier {
    /**
     * Supplier's ID
     */
    private int id;
    /**
     * Name of the supplier
     */
    private String name;
    /**
     * address of the Supplier
     */
    private String address;
    /**
     * Person to contact for information regarding the supply
     */
    private String salesContact;

    /**
     * Forms the supplier object putting all the required information
     * @param id Unique number to address a supplier
     * @param name Supplier's name
     * @param address Supplier's address
     * @param salesContact Supplier's cointact
     */
    public Supplier(int id, String name, String address, String salesContact) {
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
        this.setSalesContact(salesContact);
    }

    /**
     * Searches the supplier with the id
     * @param id id of the supplier
     * @param suppliers list of suppliers
     * @return the supplier object that matches
     */
    public Supplier searchSupplier(int id, ArrayList<Supplier> suppliers) {
        for(Supplier s: suppliers) {
            if(s.getId() == id)
                return s;
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalesContact() {
        return salesContact;
    }

    public void setSalesContact(String salesContact) {
        this.salesContact = salesContact;
    }

}

