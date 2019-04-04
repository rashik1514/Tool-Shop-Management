package Server.model;


/**
 * Creates a line of order for a particular item.
 * @author Md Rashik Hassan
 *
 */
public class OrderLine {
    /**
     * id of the item
     */
    private int id;
    /**
     * name of the item
     */
    private String name;
    /**
     * quantity that needs to be ordered
     */
    private int quantity;
    /**
     * supplierId of the item
     */
    private int supplierId;

    /**
     * Constructs a order request of a single item
     * @param id unique identity of the item
     * @param name name of the item
     * @param quantity quantity that needs to be ordered
     * @param supplierId Supplier who provides the item
     */
    public OrderLine(int id, String name, int quantity, int supplierId) {
        this.setId(id);
        this.setName(name);
        this.setQuantity(quantity);
        this.setSupplierId(supplierId);

        System.out.println("Item: "+ name + "added to the order");
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}

