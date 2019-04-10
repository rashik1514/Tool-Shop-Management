package Client.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Client that connects to server
 *
 * @author Christina Lu 30037885, Layla Arab 30017060, MD Rashik Hassan 30048022
 * @version 1.0
 * @since April 5 2019
 */
public class Client {

    /**
     * output to server
     */
    private PrintWriter socketOut;
    /**
     * connection to server
     */
    private Socket socket;
    /**
     * input from server
     */
    private BufferedReader socketIn;
    /**
     * output to console
     */
    private BufferedReader stdIn;
    /**
     * activity status of client
     */
    private boolean isActive = false;


    private PrintWriter socketOut;
    private Socket socket;
    private BufferedReader socketIn;
    private BufferedReader stdIn;

    boolean isActive = false;


    /**
     * Constructs the client
     *
     * @param serverName name of the server
     * @param portNumber port number
     */
    public Client(String serverName, int portNumber) {
        try {
            socket = new Socket(serverName, portNumber);
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketOut = new PrintWriter((socket.getOutputStream()), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * disconnects the client from the server
     */
    protected void disconnect() {
        isActive = false;
        try {
            socketIn.close();
            socketOut.close();
            stdIn.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Closing error: " + e.getMessage());
        }
    }

    /**
     * returns the string received from the server when the client sens DISPLAY
     *
     * @return
     * @throws IOException
     */
    protected String displayTools() throws IOException {
        socketOut.println("DISPLAY");
        String response = socketIn.readLine();
        StringBuilder data = new StringBuilder();
        while (!response.equals("END")) {
            data.append(response);
            data.append("\n");
            response = socketIn.readLine();
        }
        return data.toString();
    }

}
