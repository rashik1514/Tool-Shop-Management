package Client.Controller;

import java.io.IOException;

public class Listener {
    private Client client;

    public Listener(Client client) {
        this.client = client;
    }

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
