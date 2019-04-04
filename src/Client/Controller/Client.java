package Client.Controller;

import Client.View.UserGUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private PrintWriter socketOut;
    private Socket socket;
    private BufferedReader socketIn;
    private BufferedReader stdIn;

    boolean isActive = false;

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

    protected void communicate() {
        try {
            while (isActive) {
                String read;
                while (true) {
                    read = socketIn.readLine();
                    if (read.equals("QUIT")) {
                        disconnect();
                    }
                    System.out.println(read);
                }
            }
        } catch (IOException e) {
        } catch (NullPointerException e) {
        }
        disconnect();
    }

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

    public static void main(String[] args) throws IOException {
        Client aClient = new Client("localhost", 8902);
        UserGUI gui = new UserGUI();
        gui.setListener(new Listener(aClient));
        aClient.communicate();
        gui.updateView();
    }

    public String displayTools() throws IOException {
        socketOut.println("DISPLAY TOOLS");
        String response = socketIn.readLine();
        StringBuilder data = new StringBuilder();
        while (!response.equals("DISPLAY TOOLS")) {
            data.append(response);
            data.append("\n");
            response = socketIn.readLine();
        }
        return data.toString();
    }

}
