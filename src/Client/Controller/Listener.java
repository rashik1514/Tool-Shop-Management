package Client.Controller;

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
        } else if (action.equals("SEARCH")){
            SearchForm dialog = new SearchForm();
            dialog.pack();
            dialog.setVisible(true);
            int selectedIndex = dialog.searchType.getSelectedIndex();

            if (selectedIndex == 1){
                int id = Integer.parseInt(dialog.search.getText());
                client.search(id);
            } else if (selectedIndex == 2){
                String name = dialog.search.getText();
                client.search(name);
            }
        }
        return "";

//        if (action.equals("QUIT")){
//            try {
//
//            }catch (NullPointerException e){
//                return
//            }
//        }
    }

}
