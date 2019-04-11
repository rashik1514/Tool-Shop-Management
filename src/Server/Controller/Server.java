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
 * Connection to client
 */
public class Server implements Runnable {

    /**
     * Input to server
     */
    private BufferedReader socketIn;

    /**
     * Output to client
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
     * @param portNum port number
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

    /**
     * Initializes the shop and connects the clients
     */
    public void communicate() {
        ArrayList<Supplier> suppliers = database.loadSuppliers();
        ArrayList<Item> items = database.loadItems(suppliers);
        Shop shop = new Shop(new Inventory());
        while (true) {
            try {
                String in = socketIn.readLine();
                if (in.equals("DISPLAY")) {
                    String out = "";
                    for (Item item : items)
                        out += item.toString();
                    socketOut.println(out);
                    socketOut.println("END");
                } else if (in.equals("QUIT")) {
                    close();
                    threadPool.shutdown();
                }
            } catch (SocketException e) {
                threadPool.shutdown();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        communicate();
    }

    /**
     * closes all the sockets
     */
    public void close() {
        try {
            socketIn.close();
            socketOut.close();
            socket.close();
            try {
                database.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Closing error: " + e.getMessage());
        }
    }

    /*
      When an object implementing interface <code>Runnable</code> is used
      to create a thread, starting the thread causes the object's
      <code>run</code> method to be called in that separately executing
      thread.
      <p>
      The general contract of the method <code>run</code> is that it may
      take any action whatsoever.

      @see Thread#run()
     */
}


