package Server.Controller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.ResultSet;
import java.sql.*;
import java.sql.SQLException;
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
//            socket = serverSocket.accept();
//            socketOut = new PrintWriter((socket.getOutputStream()), true);
//            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
                    Statement statement = database.getConnection().createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * from Items");
                    String out = "";
                    while (rs.next()) {
                        out += (rs.getInt("itemId")+ "/" + rs.getString("itemName") +
                                "/" + rs.getInt("ItemQuantity") + "/" + rs.getDouble("itemPrice") + "\n");
                    }
                    socketOut.println(out);
                    socketOut.println("END");

                } else if(in.equals("SEARCHID")){

                } else if (in.equals("SEARCHNAME")){

                } else if (in.equals("DECREASE")){
                }
            } catch (SocketException e) {
                threadPool.shutdown();
            } catch (IOException e) {
                e.printStackTrace();
                threadPool.shutdown();
            }
        }
    }

    public void run(){
        try {
            socket = serverSocket.accept();
            socketOut = new PrintWriter((socket.getOutputStream()), true);
            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

  
}


