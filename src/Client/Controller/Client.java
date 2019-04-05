package Client.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 */

public class Client {

    protected PrintWriter socketOut;
    protected Socket socket;
    protected BufferedReader socketIn;
    protected BufferedReader stdIn;

    boolean isActive = false;

    /**
     * Constructs the client
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
     * @return
     * @throws IOException
     */
    public String displayTools() throws IOException {
        socketOut.println("DISPLAY");
        String response = socketIn.readLine();
        StringBuilder data = new StringBuilder();
        while (! response.equals("END")) {
            data.append(response);
            data.append("\n");
            response = socketIn.readLine();
        }
        return data.toString();
    }

}
