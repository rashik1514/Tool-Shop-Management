package Server.Model;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Contains list of orders
 */
public class Order {

    /**
     * date order was placed
     */
    private Date today;
    /**
     * id of order
     */
    private int orderId;
    /**
     * Arraylist of orderlines in order
     */
    private ArrayList<OrderLine> orderLines;

    /**
     * default constructor
     */
    protected Order() {
        today = Calendar.getInstance().getTime();
        orderLines = new ArrayList<OrderLine>();
    }

    protected void addOrderLine(OrderLine ol) {
        orderLines.add(ol);
    }

    public ArrayList<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(ArrayList<OrderLine> lines) {
        orderLines = lines;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String toString() {
        String order = "Order Date: " + today.toString() + "\n\n";
        StringBuilder str = new StringBuilder();
        for (OrderLine ol : orderLines) {
            str.append(ol);
            str.append("------------------------\n");
        }
        if (str.toString().equals(""))
            str = new StringBuilder("here are currently no orderlines");
        order += str;
        order += "\n";
        return order;
    }

}
