package Server.model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;


/**
 * Consists of the variuous order requests of several items.
 * @author MD Rashik Hassan
 *
 */
public class Order {
    /**
     * list of order of each item with their information
     */
    public ArrayList<OrderLine> orders;
    /**
     * Unique id of the order
     */
    private int id;

    /**
     * Initializes the object order
     */
    public Order() {

        orders = new ArrayList<OrderLine>();
        Random rand = new Random();

        id = rand.nextInt(10000) + 1000;

    }

    /**
     * print the order details
     */
    public void printOrder(ArrayList<Supplier> suppliers) {
        System.out.println("*****************************************"
                + "*****************************");
        System.out.println("Order ID:                     "+id);
        System.out.println("Datre Ordered:                     "+ getCurrentTimeUsingCalendar() +"\n");

        for(OrderLine order: orders) {
            System.out.println("Item description:"+"          "+ order.getName());
            System.out.println("Amount ordered:              "+ order.getQuantity());
            System.out.println("Suppler's name:              "+  suppliers.get(0).searchSupplier(id, suppliers).getName() );
        }
    }

    /**
     * generates current time when the order was generated
     */
    public  String getCurrentTimeUsingCalendar() {
        Calendar cal = Calendar.getInstance();
        Date date = (Date) cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate=dateFormat.format(date);
        return formattedDate;
    }
}

