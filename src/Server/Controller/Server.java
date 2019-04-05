package Server.Controller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.*;
import Server.model.*;

/**
 *  Has a method to receive an input from the clients, makes changes to the inventories, and send the result back to the client
 *
 * @author Md Rashik Hassan
 * @version 1.0
 * @since March 31, 2019
 */

public class Server{
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
     * Constructs an object of type server that initializes the socket and the thread pools
     */
    public Server (int portNum)
    {
        try {
            serverSocket = new ServerSocket(portNum);
            socket = serverSocket.accept();
            socketOut = new PrintWriter((socket.getOutputStream()), true);
            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            threadPool = Executors.newCachedThreadPool();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server is now runnning...");
    }

    /**
     * Initializes the shop and connects the clients
     */
    public void communicate () throws IOException{

        while (true) {
            ArrayList<Supplier> suppliers = new ArrayList<>();
            loadSuppliers(suppliers);
            Inventory inventory = new Inventory();
            loadItems(inventory);
            Shop shop = new Shop(suppliers, inventory);
            try {
                String in = socketIn.readLine();
                if (in.equals("DISPLAY")){
                    Inventory temp = shop.getInventory();
                    String out = "";
                    for(int i = 0; i < temp.getTools().size(); i++) {
                        out += temp.getTools().get(i).toString();
                    }
                    socketOut.println(out);
                    socketOut.println("END");
                }
            }catch (Exception e) {
                e.printStackTrace();
                threadPool.shutdown();
            }
        }

    }

    public static void main (String [] args) throws IOException
    {
        Server server = new Server(8902);
        server.communicate();
        server.close();
    }

    private void close(){
        try {
            socketIn.close();
            socketOut.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Closing error: " + e.getMessage());
        }
    }

    /**
     * load all the supplier information from the database
     * @param suppliers object where it stores the information
     */
    public void loadSuppliers(ArrayList<Supplier> suppliers) {

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Christina\\Documents\\GitHub\\ENSF409Project\\suppliers.txt"))) {
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                String[] info = sCurrentLine.split(";");
                int id = Integer.parseInt(info[0]);

                suppliers.add(new Supplier(id, info[1], info[2], info[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * load all the item information from the database
     * @param i object where it stores the information
     */
//    public void loadItems(Inventory i, ArrayList<Supplier> s) {
//        try {
//            FileReader fr = new FileReader("items.txt");
//            BufferedReader br = new BufferedReader(fr);
//
//            String line = "";
//            while ((line = br.readLine()) != null) {
//                String[] temp = line.split(";");
//                int supplierId = Integer.parseInt(temp[4]);
//
//                Supplier supplier = new Supplier();
//                searchSupplier(supplierId, s);
//                if (theSupplier != null) {
//                    Item myItem = new Item(Integer.parseInt(temp[0]), temp[1], Integer.parseInt(temp[2]),
//                            Double.parseDouble(temp[3]), theSupplier);
//                    items.add(myItem);
//                    theSupplier.getItemList().add(myItem);
//                }
//            }
//            br.close();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return items;
//    }
//        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Christina\\Documents\\GitHub\\ENSF409Project\\items.txt"))){
//            String sCurrentLine;
//
//            while ((sCurrentLine = br.readLine()) != null) {
//                String[] info = sCurrentLine.split(";");
//                int id = Integer.parseInt(info[0]);
//                int quantity = Integer.parseInt(info[2]);
//                Double price = Double.parseDouble(info[3]);
//                int supplierId = Integer.parseInt(info[4]);
//
//                i.addTool(new Item(id, info[1], quantity, price, supplierId));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}


