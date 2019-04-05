package Server.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
/**
 * Class for orders
 * @author Christina Lu, Layla Arab, MD Rashik Hassan
 * @version 1.0
 * @since April 5 2019
 */
public class Order {

    /**
     * date of placement of order
     */
    private Date today;
    /**
     * id of order
     */
    private int orderId;
    /**
     * list of orderlines
     */
    private ArrayList <OrderLine> orderLines;

    /**
     * default constructor of order
     */
    public Order () {
        today = Calendar.getInstance().getTime();
        orderLines = new ArrayList <OrderLine> ();
    }

    /**
     * adds orderline to the order
     * @param ol orderline to add
     */
    public void addOrderLine (OrderLine ol) {
        orderLines.add(ol);
    }

    /**
     * @return the orderlines
     */
    public ArrayList <OrderLine> getOrderLines (){
        return orderLines;
    }

    /**
     *
     * @param lines set the orderline list
     */
    public void setOrderLines (ArrayList <OrderLine> lines){
        orderLines = lines;
    }

    /**
     *
     * @return the orderID
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     *
     * @param orderId sets the orderID
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     *
     * @return the order attributes made to a string
     */
    public String toString (){
        String order = "Order Date: " + today.toString() + "\n\n";
        String str = "";
        for (OrderLine ol: orderLines) {
            str += ol;
            str += "------------------------\n";
        }
        if (str == "")
            str = "here are corrently no orderlines";

        order += str;
        order += "\n";
        return order;
    }

}
