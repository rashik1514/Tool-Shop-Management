package Server.Controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Server.Model.*;

/**
 * Has a method to receive an input from the clients, makes changes to the inventories, and send the result back to the client
 *
 * @author Md Rashik Hassan
 * @version 1.0
 * @since March 31, 2019
 */

public class Server {
    /**
     * Used for executing the method within class ShopController
     */
    private ExecutorService threadPool;
    /**
     * The socket that connects between server and client
     */
    private ServerSocket serverSocket;
    /**
     * Printing back to client
     */
    private PrintWriter socketOut;
    /**
     * Allows aSocket to be connected between server and client
     */
    private Socket aSocket;
    /**
     * Reading input from client
     */
    private BufferedReader socketIn;


    /**
     * Constructor for Server
     *
     * @param portNumber port used for Socket
     */
    public Server(int portNumber) {
        try {
            serverSocket = new ServerSocket(portNumber);
            aSocket = serverSocket.accept();
            System.out.println("Server is now running...");
            socketOut = new PrintWriter(aSocket.getOutputStream(), true);
            socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println("error in constructor");

        }
    }

    /**
     * Initializes the shop and connects the clients
     */
    public void communicate() throws IOException {
        try {
            while (true) {
                String choice;
                Shop shop = new Shop(aSocket);
                Inventory inventory = new Inventory();
                shop.loadItems(inventory);
                BufferedReader socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
                choice = socketIn.readLine();


                while (!choice.equals("QUIT")) {
                    //choice = shop.menu();
                    choice = socketIn.readLine();
                    switch (choice) {
                        case "DISPLAY TOOLS":
                            inventory.showAllTools(aSocket);
                            break;
//                        case "hyh":
//                            System.out.println("Please enter the name:");
//                            //i.searchTool(input.nextLine());
//                            break;
//                        case "kj":
//                            System.out.println("Please enter the ID:");
//                            //i.searchTool(input.nextInt());
//                            break;
//                        case "kjio":
//                            // take the name and then check the quantity
//                            System.out.println("Which tool do you want to check?");
//                            //i.searchTool(input.nextLine()).checkQuantity();
//                            break;
//                        case "kji":
//                            System.out.println("Which tool do you want to decrease?");
//                            //String temp = input.nextLine();
//                            System.out.println("And by how much?");
//                            //int number = input.nextInt();
//                            //i.decreaseItemQuantity(temp, number);
//                            break;
//                        case "QUIT":
//                            System.out.println("Shutting off. Thank You.");
//                            System.exit(0);
//                        default:
//                            System.out.println("Enter a valid option");
//                            break;

                    }
                }

            }
        } catch (NullPointerException e) {
        } catch (Exception e) {
            e.printStackTrace();
            threadPool.shutdown();
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(8902);
        server.communicate();
    }
}


