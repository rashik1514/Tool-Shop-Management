package Server.Controller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.*;

import Server.Model.*;

/**
 *
 */
public class Server implements Runnable {
    private BufferedReader socketIn;

    private PrintWriter socketOut;

    /**
     * The socket that connects between server and client
     */
    private Socket socket;

    /**
     * Used for executing the method within class ShopController
     */
    private ExecutorService threadPool;

    /**
     * Allows aSocket to be connected between server and client
     */
    private ServerSocket serverSocket;
    /**
     * The database in which the records are stored
     */
    Database database;

    /**
     * @param portNum
     */
    public Server(int portNum) {
        try {
            serverSocket = new ServerSocket(portNum);
            socket = serverSocket.accept();
            socketOut = new PrintWriter((socket.getOutputStream()), true);
            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            threadPool = Executors.newCachedThreadPool();
            database = new Database();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server is now runnning...");
    }

    @Override
    public void run() {
        try {
            communicate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the shop and connects the clients
     */
    public void communicate() throws IOException {
        while (true) {
            ArrayList<Supplier> suppliers = new ArrayList<>();
            loadSuppliers(suppliers);
            ArrayList<Item> items = loadItems(suppliers);
            Shop shop = new Shop(new Inventory(items), suppliers);

            try {
                String in = socketIn.readLine();
                if (in.equals("DISPLAY")) {
                    Inventory temp = shop.getTheInventory();
                    String out = "";
                    for (int i = 0; i < temp.getItemList().size(); i++) {
                        out += temp.getItemList().get(i).toString();
                    }
                    socketOut.println(out);
                    socketOut.println("END");
                }
            } catch (Exception e) {
                e.printStackTrace();
                threadPool.shutdown();
            }
        }

    }

    /**
     * closes all the sockets
     */
    public void close() {
        try {
            socketIn.close();
            socketOut.close();
            socket.close();
//            database.connection.close();
        } catch (IOException e) {
            System.out.println("Closing error: " + e.getMessage());
        }
    }

    /**
     * load all the supplier information from the database
     *
     * @param suppliers object where it stores the information
     */
    public void loadSuppliers(ArrayList<Supplier> suppliers) {
        ResultSet rs = database.select("SELECT * FROM Suppliers");
        try {
            while (rs.next()) {
                suppliers.add(new Supplier(rs.getInt("supId"), rs.getString("supName"), rs.getString("supAddress"),
                        rs.getString("supContactName")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * load all the item information from the database
     *
     * @param s object where it stores the information
     */
    public ArrayList<Item> loadItems(ArrayList<Supplier> s) {
        ArrayList<Item> items = new ArrayList<>();
        ResultSet rs = database.select("SELECT * FROM Items");
        try {
            while (rs.next()) {
                Item myItem = new Item(rs.getInt("itemId"), rs.getString("itemName"), rs.getInt("itemQuantity"),
                        rs.getDouble("itemPrice"), findSupplier(rs.getInt("supId"), s));
                items.add(myItem);
                Supplier supplier = myItem.getTheSupplier();
                supplier.getItemList().add(myItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;

    }

    /**
     * finds the supplier by ID
     *
     * @param supplierId supplier's id
     * @param suppliers  list of suppliers
     * @return the supplier if found, null if otherwise
     */
    private Supplier findSupplier(int supplierId, ArrayList<Supplier> suppliers) {
        Supplier theSupplier = null;
        for (Supplier s : suppliers) {
            if (s.getSupId() == supplierId) {
                theSupplier = s;
                break;
            }
        }
        return theSupplier;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
}


