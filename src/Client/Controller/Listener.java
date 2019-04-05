package Client.Controller;

import java.io.IOException;

/**
 * This implements the Listener class
 * @author Christina Lu, Layla Arab, MD Rashik Hassan
 * @version 1.0
 * @since April 5 2019
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
