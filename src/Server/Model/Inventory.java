package Server.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
/**
 * Stores all the items, performs actions on the items
 * @author rashik Hassan
 *
 */
public class Inventory {
    /**
     * Order object for ordering
     */
    public Order order;
    /**
     * list of items present
     */
    private ArrayList<Item> tools;

    /**
     * Initializes tool
     */
    public Inventory() {
        tools = new ArrayList<Item>();
    }

    /**
     * searches the inventory with name
     * @param name of the tool
     * @return item that matches the name
     */
    public Item searchTool(String name) {
        Item tempItem = null;

        for(Item item: tools) {
            if(item.getName().equals(name)) {
                System.out.println("Item found on inventory");
                tempItem = item;
            }
        }

        return tempItem;

    }

    public ArrayList<Item> getTools() {
        return tools;
    }

    /**
     * searches the inventory with id
     * @param id of the tool
     * @return item that matches the id
     */
    public boolean searchTool(int id) {
        for(Item item: tools) {
            if(item.getId() == id) {
                System.out.println("Item found on inventory");
                return true;
            }
        }

        return false;

    }
    /**
     * prints all the tools
     */
    public void showTools(Socket s) {
        Item i = new Item();

        try
        {
            PrintWriter objectOutput = new PrintWriter(s.getOutputStream());
            objectOutput.println(i.toString(tools));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //Item i = new Item();
        //i.displayItems(tools);

    }

    /**
     * Adds tool to the list
     * @param tool item that needs to be added
     */
    public void addTool(Item tool) {
        tools.add(tool);
    }

    /**
     * Deletes a particular tool from the inventory
     * @param tool tool item that needs to be deleted
     */
    public void deleteTool(Item tool) {
        tools.remove(tool);
    }

    /**
     * decreses the quantity of a particular item
     * @param name name of the tool thats decresed
     * @param quantity number that will be deducted
     */
    public void decreaseItemQuantity(String name, int quantity) {
        Item item = searchTool(name);
        item.setQuantity(item.getQuantity() - quantity);
        item.checkQuantity();
    }

}

