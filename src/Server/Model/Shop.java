package Server.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * Manages the inventory
 */
public class Shop extends Database implements Runnable {
    /**
     * Inventory of items
     */
    private Inventory theInventory;
    /**
     * Input to client
     */
    private BufferedReader socketIn;
    /**
     * Output from client
     */
    private PrintWriter socketOut;
    /**
     * Socket to connect to client
     */
    private Socket socket;

    /**
     * Constructs the shop
     * @param inventory inventory to assign
     * @param s socket to the client
     */
    public Shop(Inventory inventory, Socket s) {
        theInventory = inventory;
        socket = s;
        try {
            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketOut = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * lists all the suppliers
     */
    public void listAllSuppliers() {
        ArrayList<Supplier> supplierList = loadSuppliers();
        for (Supplier s : supplierList) {
            System.out.println(s);
        }
    }

    /**
     * Outputs item information
     * @param theItem the item to access
     * @return item's information
     */
    private String outputItem(Item theItem) {
        return "The item information is as follows: \n" + theItem;
    }

    /**
     * returns the item quantity stringed
     * @param name name of the item
     * @return the String of item the quantity attribute
     */
    public String getItemQuantity(String name) {
        int quantity = theInventory.getItemQuantity(name);
        if (quantity < 0)
            return "Item " + name + " could not be found!";
        else
            return "The quantity of Item " + name + " is: " + quantity + "\n";
    }

    /**
     * Returns the order printed into a String
     * @return the order atrributes stringed
     */
    public String printOrder() {
        return theInventory.printOrder();
    }

    /**
     * Method to be called and executed by threads
     */
    public void run() {
        String in = "";
        while (true) {
            theInventory.placeOrders();

            try {
                in = socketIn.readLine();

                if (in.equals("DISPLAY")) {
                    String out = loadItemsTable();
                    socketOut.println(out);
                    socketOut.println("END");

                } else if (in.equals("SEARCHID")) {
                    int id = Integer.parseInt(socketIn.readLine());
                    String out = searchByItemId(id);
                    socketOut.println(out);


                } else if (in.equals("SEARCHNAME")) {
                    String name = socketIn.readLine();
                    String out = searchByItemName(name);
                    socketOut.println(out);


                } else if (in.equals("DECREASEID")) {
                    int id = Integer.parseInt(socketIn.readLine());
                    int amount = Integer.parseInt(socketIn.readLine());
                    String out = changeItemQuantity(amount, id);
                    socketOut.println(out);


                } else if (in.equals("DECREASENAME")) {
                    String name = socketIn.readLine();
                    int amount = Integer.parseInt(socketIn.readLine());
                    String out = changeItemQuantity(amount, name);
                    socketOut.println(out);


                } else if (in.equals("QUIT")) {
                    break;
                }
            } catch (SocketException e) {
            } catch (IOException e) {
            }

        }
    }

}

