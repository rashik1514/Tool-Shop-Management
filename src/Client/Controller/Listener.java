package Client.Controller;

import java.io.IOException;

/**
 *
 */
public class Listener {
    private Client client;

    /**
     * Constructs the listener
     * @param client the client
     */
    public Listener(Client client) {
        this.client = client;
    }

    /**
     *
     * @param action
     * @return string received from the server
     */
    public String actionPerformed(String action) {
        if (action.equals("DISPLAY")) {
            try {
                return client.displayTools();
            } catch (IOException e) {
                return "Error getting tools";
            }
        }
        return "";
    }


}
