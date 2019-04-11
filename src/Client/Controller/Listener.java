package Client.Controller;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
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
        } else if (action.equals("SEARCH")) {
            SearchForm dialog = new SearchForm();
            dialog.setTitle("Search");
            dialog.pack();
            dialog.setVisible(true);
            int selectedIndex = dialog.searchType.getSelectedIndex();
            if (selectedIndex == 0) {
                try {
                    int id = Integer.parseInt(dialog.search.getText());
                    client.search(id);
                } catch (NumberFormatException e){
                    UIManager UI=new UIManager();
                    UI.put("OptionPane.background",new ColorUIResource(222,187,247));
                    UI.put("Panel.background",new ColorUIResource(222,187,247));
                    JOptionPane.showMessageDialog(null,"Please input valid ID","Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (selectedIndex == 1) {
                String name = dialog.search.getText();
                client.search(name);
            }
        } else if (action.equals("DECREASE")) {
            DecreaseForm dialog = new DecreaseForm();
            dialog.setTitle("Decrease Quantity");
            dialog.pack();
            dialog.setVisible(true);
            int selectedIndex = dialog.searchType.getSelectedIndex();
            int amount = Integer.parseInt(dialog.Quantity.getText());
            if (selectedIndex == 0) {
                int id = Integer.parseInt(dialog.item.getText());
                client.decrease(id, amount);
            } else if (selectedIndex == 1) {
                String name = dialog.item.getText();
                client.decrease(name, amount);
            }
        }
        return "";
    }

}
