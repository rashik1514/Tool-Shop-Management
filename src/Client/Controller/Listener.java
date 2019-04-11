package Client.Controller;

import javax.swing.*;
import java.io.IOException;

/**
 *
 */
public class Listener {
    private Client client;

    /**
     * Constructs the listener
     *
     * @param client the client
     */
    public Listener(Client client) {
        this.client = client;
    }

    /**
     * @param action action that occurred
     * @return string received from the server
     */
    public String actionPerformed(String action) {
        if (action.equals("DISPLAY")) {
            try {
                return client.displayTools();
            } catch (IOException e) {
                return "Error getting tools";
            }
        } else if (action.equals("SEARCH")) {
            SearchForm dialog = new SearchForm();
            dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dialog.setTitle("Search");
            dialog.pack();
            dialog.setVisible(true);
            if (dialog.search.equals("null")){
                return "invalid";
            }
            int selectedIndex = dialog.searchType.getSelectedIndex();
            if (selectedIndex == 0) {
                try {
                    int id = Integer.parseInt(dialog.search.getText());
                    return client.search(id);
                } catch (NumberFormatException e){
                    return "invalid";
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (selectedIndex == 1) {
                String name = dialog.search.getText();
                try {
                    return client.search(name);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (action.equals("DECREASE")) {
            DecreaseForm dialog = new DecreaseForm();
            dialog.setDefaultCloseOperation(dialog.DISPOSE_ON_CLOSE);
            dialog.setTitle("Decrease Quantity");
            dialog.pack();
            dialog.setVisible(true);
            int selectedIndex = dialog.searchType.getSelectedIndex();
            int amount = 0 - Integer.parseInt(dialog.Quantity.getText());
            if (selectedIndex == 0) {
                int id = Integer.parseInt(dialog.item.getText());
                try {
                    return client.decrease(id, amount);
                } catch (IOException e) {
                    return null;
                }
            } else if (selectedIndex == 1) {
                String name = dialog.item.getText();
                try {
                    return client.decrease(name, amount);
                } catch (IOException e) {
                    return null;
                } 
            }

        }
        return "";
    }

}
