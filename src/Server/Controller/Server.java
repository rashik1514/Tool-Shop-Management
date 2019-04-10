package Server.Controller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.*;

import Server.Model.*;

/**
 * This implements the server
 *
 * @author Christina Lu 30037885, Layla Arab 30017060, MD Rashik Hassan 30048022
 * @version 1.0
 * @since April 5 2019
 */
public class Server {
    /**
     * reads from the client
     */
    private BufferedReader socketIn;
    /**
     * prints to the client
     */
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

//            threadPool = Executors.newCachedThreadPool();
            threadPool = Executors.newCachedThreadPool();
            database = new Database();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server is now running...");
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
                    StringBuilder out = new StringBuilder();
                    for (int i = 0; i < temp.getItemList().size(); i++) {
                        out.append(temp.getItemList().get(i).toString());
                    String out = "";
                    for (int i = 0; i < temp.getItemList().size(); i++) {
                        out += temp.getItemList().get(i).toString();
                    }
                    socketOut.println(out);
                    socketOut.println("END");
                }
            } catch (SocketException e) {
                threadPool.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
                threadPool.shutdown();
            }
        }

    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(5050);
        server.communicate();
        server.close();
    }

    /**
     * closes all the sockets
     */
    private void close() {
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
    protected void loadSuppliers(ArrayList<Supplier> suppliers) {

        try (BufferedReader br = new BufferedReader(new FileReader("suppliers.txt"))) {
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                String[] info = sCurrentLine.split(";");
                int id = Integer.parseInt(info[0]);

                suppliers.add(new Supplier(id, info[1], info[2], info[3]));
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
            FileReader fr = new FileReader("items.txt");
            BufferedReader br = new BufferedReader(fr);

            String line = "";
            while ((line = br.readLine()) != null) {
                String[] temp = line.split(";");
                int supplierId = Integer.parseInt(temp[4]);

                Supplier supplier = findSupplier(supplierId, s);
                if (supplier != null) {
                    Item myItem = new Item(Integer.parseInt(temp[0]), temp[1], Integer.parseInt(temp[2]),
                            Double.parseDouble(temp[3]), supplier);
                    items.add(myItem);
                    supplier.getItemList().add(myItem);
                }
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
}


