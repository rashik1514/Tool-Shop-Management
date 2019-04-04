package Server.Model;

import javax.Items.Item;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileIO {
    public void readSupplierFile() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("/users/layla/documents/uni/ensf409/lab3/ex1/suppliers.txt"));
        while (scan.hasNext()) {
            Supplier supplier = new Supplier();
            String line = scan.nextLine();
            String[] lines = line.split(";");
            supplier.setSupId(Integer.parseInt(lines[0]));
            supplier.setSupName(lines[1]);
            supplier.setSupAddress(lines[2]);
            supplier.setSupContactName(lines[3]);
            suppliers.add(supplier);
        }

    }

    /**
     * Reads the Items.txt file and inserts the contents into the ArrayList in
     * inventory
     *
     * @throws FileNotFoundException
     */
    public void readItemFile() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("/users/layla/documents/uni/ensf409/lab3/ex1/items.txt"));
        while (scan.hasNext()) {
            Item Item = new Item();
            String line = scan.nextLine();
            String[] lines = line.split(";");
            Item.setItemID(Integer.parseInt(lines[0]));
            Item.setItemName(lines[1]);
            Item.setQuantity(Integer.parseInt(lines[2]));
            Item.setPrice(Double.parseDouble(lines[3]));
            int i = searchByID(Integer.parseInt(lines[4]));
            Item.setSupplier(suppliers.get(i));
            getInventory().getItems().add(Item);
        }

    }
}
