package Server.Controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Server.model.*;
/**
 *  Has a method to receive an input from the clients, makes changes to the inventories, and send the result back to the client
 *
 * @author Md Rashik Hassan
 * @version 1.0
 * @since March 31, 2019
 */

public class Server{

    /**
     * The socket that connects between server and client
     */
    private Socket aSocket;

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
            serverSocket= new ServerSocket(portNum);
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
        try {
            while (true) {
                String choice;
                aSocket = serverSocket.accept();
                Inventory i = new Inventory();
                Shop shop = new Shop(aSocket);
                shop.loadItems(i);
                //System.out.println("RUNNING!!");
                BufferedReader socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
                choice = socketIn.readLine();


                //StringBuilder test = new StringBuilder();
                //test.append("hi".repeat(5));


                while(!choice.equals("QUIT")) {
                    //choice = shop.menu();
                    choice = socketIn.readLine();
                    switch(choice) {
                        case "DISPLAY TOOLS":
                            i.showAllTools(aSocket);
                            break;
                        case "hyh":
                            System.out.println("Please enter the name:");
                            //i.searchTool(input.nextLine());
                            break;
                        case "kj":
                            System.out.println("Please enter the ID:");
                            //i.searchTool(input.nextInt());
                            break;
                        case "kjio":
                            // take the name and then check the quantity
                            System.out.println("Which tool do you want to check?");
                            //i.searchTool(input.nextLine()).checkQuantity();
                            break;
                        case "kji":
                            System.out.println("Which tool do you want to decrease?");
                            //String temp = input.nextLine();
                            System.out.println("And by how much?");
                            //int number = input.nextInt();
                            //i.decreaseItemQuantity(temp, number);

                            break;
                        case "":
                            System.out.println("Shutting off. Thank You.");
                            System.exit(0);
                        default:
                            System.out.println("Enter a valid option");
                            break;

                    }
                }

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            threadPool.shutdown();
        }
    }

    public static void main (String [] args) throws IOException
    {
        Server server = new Server(8902);
        server.communicate();
    }
}


