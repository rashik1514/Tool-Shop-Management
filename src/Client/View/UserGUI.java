package Client.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UserGUI {

    private JPanel panel1;
    private JScrollBar scrollBar1;
    private JButton searchButton;
    private JTextField searchBarTextField;
    private JButton showToolsButton;
    private JButton exitButton;
    private JTextArea textArea1;

    public UserGUI() {
        //TODO: insert database stuff
    }

    public void addListeners(){
        showToolsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO: database.display() ??
            }
        });
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s  = searchBarTextField.getText();
                //TODO: database.search(s) ??
            }
        });

    }

}
