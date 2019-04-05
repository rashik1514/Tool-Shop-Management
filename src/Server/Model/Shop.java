package Server.Model;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Implemets the whole retail system and also the front end of the system. Handles directly the inventory
 * and the database
 * @author Rashik Hassan
 * @version 1.0
 *
 */
public class Shop {
    /**
     * stores the information of the suppliers
     */
    private ArrayList<Supplier> suppliers;

    /**
     *	Transfering the data throughout the client and server.
     */
    Socket s;

    /**
     * Contructs the shop object
     */
    public Shop(Socket s) {
        suppliers = new ArrayList<Supplier>();
        this.s = s;
    }

    /**
     *
     */
    public Shop() {

    }

    /**
     * load all the supplier information from the database
     * @param shop object where it stores the information
     */
    public void loadSuppliers(Shop shop) {

        try (BufferedReader br = new BufferedReader(new FileReader("/Users/rashikhassan/Dropbox/workspace/RetailStoreSystem/src/system/suppliers.txt"))) {
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                String[] info = sCurrentLine.split(";");
                int id = Integer.parseInt(info[0]);


                shop.addSupplier(new Supplier(id, info[1], info[2], info[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * load all the item information from the database
     * @param i object where it stores the information
     */
    public void loadItems(Inventory i) {
        // C:/Users/rashi/Dropbox/workspace/RetailStoreSystem/src/system/
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/rashikhassan/Dropbox/workspace/RetailStoreSystem/items.txt"))){
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                String[] info = sCurrentLine.split(";");
                int id = Integer.parseInt(info[0]);
                int quantity = Integer.parseInt(info[2]);
                Double price = Double.parseDouble(info[3]);
                int supplierId = Integer.parseInt(info[4]);

                i.addTool(new Item(id, info[1], quantity, price, supplierId));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds supplier to the arraylist
     * @param supplier the supplier object that needs to be added
     */
    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }

    /**
     * Displays the interface options
     * @return returns the choice provided by the user
     */
    public int menu() {
        int choice;
        Scanner input = new Scanner(System.in);

        System.out.println("Chose from the following options");
        System.out.println("1.	List all tools");
        System.out.println("2.	Search for tool by toolName");
        System.out.println("3.	Search for tool by toolID");
        System.out.println("4.	Check item quantity");
        System.out.println("5.	Decrease Item quantity.");
        System.out.println("6.  Quit");

        choice = input.nextInt();
        return choice;
    }

}
