package Client.Controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * Forms connection to server
 */
public class Client {

    private PrintWriter socketOut;
    private Socket socket;
    private BufferedReader socketIn;
    private BufferedReader stdIn;

    private boolean isActive = false;

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
    protected void close() {
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
     * @return tools in a string
     * @throws IOException io exception
     */
    public String displayTools() throws IOException {
        socketOut.println("DISPLAY");
        String response = socketIn.readLine();
        StringBuilder data = new StringBuilder();
        while (!response.equals("END")) {
            data.append(response);
            data.append(";");
            response = socketIn.readLine();
        }
        return data.toString();
    }

    public String search (int id) throws IOException{
        socketOut.println("SEARCHID");
        socketOut.println(id);
        return socketIn.readLine();
    }

    public String search (String name)throws IOException{
        socketOut.println("SEARCHNAME");
        socketOut.println(name);
        return socketIn.readLine();
    }

    public String decrease (int id, int amount){
        socketOut.println("DECREASEID");
        //TODO

        return "";
    }

    public String decrease (String name, int amount){
        socketOut.println("DECREASENAME");
        //TODO

        return "";
    }



}
