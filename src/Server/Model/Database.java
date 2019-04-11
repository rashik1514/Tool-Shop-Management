package Server.Model;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    private Connection connection;

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.err.println("Unable to find and load driver");
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ToolShop?serverTimezone=GMT",
                    "root", "rootroot");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet select(String queryStr) {
        ResultSet resultSet;
        try {
            PreparedStatement statement = connection.prepareStatement(queryStr);
            resultSet = statement.executeQuery(queryStr);
        } catch (SQLException e) {
            throw new IllegalStateException("Unable to execute query: " + queryStr, e);
        }
        return resultSet;
    }

    public void changeAlreadyOrdered(Item item) {
        String query = "UPDATE Items\n" +
                "SET alreadyOrdered = ?" +
                "WHERE itemId = ?";
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, 1);
            preparedStmt.setInt(2, item.getItemId());
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void changeItemQuantity(int amount, Item item) {
        String query = "UPDATE Items\n" +
                "SET ItemQuantity = ?" +
                "WHERE itemId = ?";
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, item.getItemQuantity() + amount);
            preparedStmt.setInt(2, item.getItemId());
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertItem(int id, String name, int quantity, double price, int supID) {
        try {
            String query = "INSERT INTO ITEMS (itemId, itemname, itemquantity, itemprice, supId) values(?, ?, ?, ?, ?)";
            PreparedStatement pStmt = connection.prepareStatement(query);
            pStmt.setInt(1, id);
            pStmt.setString(2, name);
            pStmt.setInt(3, quantity);
            pStmt.setDouble(4, price);
            pStmt.setInt(5, supID);

            int rowCount = pStmt.executeUpdate();
            System.out.println("row Count = " + rowCount);
            pStmt.close();
        } catch (SQLException e) {
            System.out.println("Error inserting in table");
            e.printStackTrace();
        }
    }

    public void insertSupplier(int id, String name, String address, String contactName) {
        try {
            String query = "INSERT INTO SUPPLIERS (supId, supname, supaddress, supcontactName) values(?, ?, ?, ?)";
            PreparedStatement pStmt = connection.prepareStatement(query);
            pStmt.setInt(1, id);
            pStmt.setString(2, name);
            pStmt.setString(3, address);
            pStmt.setString(4, contactName);

            int rowCount = pStmt.executeUpdate();
            System.out.println("row Count = " + rowCount);
            pStmt.close();
        } catch (SQLException sqle) {
            System.out.println("Error inserting in table");
            sqle.printStackTrace();
        }
    }

    /**
     * load all the supplier information from the database
     */
    public ArrayList<Supplier> loadSuppliers() {
        ResultSet rs = select("SELECT * FROM Suppliers");
        ArrayList<Supplier> suppliers = new ArrayList<>();
        try {
            while (rs.next()) {
                suppliers.add(new Supplier(rs.getInt("supId"), rs.getString("supName"), rs.getString("supAddress"),
                        rs.getString("supContactName")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    /**
     * load all the item information from the database
     *
     * @param s object where it stores the information
     */
    public ArrayList<Item> loadItems(ArrayList<Supplier> s) {
        ArrayList<Item> items = new ArrayList<>();
        ResultSet rs = select("SELECT * FROM Items");
        try {
            while (rs.next()) {
                Item myItem = new Item(rs.getInt("itemId"), rs.getString("itemName"), rs.getInt("itemQuantity"),
                        rs.getDouble("itemPrice"), findSupplier(rs.getInt("supId"), s));
                items.add(myItem);
                Supplier supplier = myItem.getTheSupplier();
                supplier.getItemList().add(myItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * finds the supplier by ID
     *
     * @param supplierId supplier's id
     * @param suppliers  list of suppliers
     * @return the supplier if found, null if otherwise
     */
    private Supplier findSupplier(int supplierId, ArrayList<Supplier> suppliers) {
        Supplier theSupplier = null;
        for (Supplier s : suppliers) {
            if (s.getSupId() == supplierId) {
                theSupplier = s;
                break;
            }
        }
        return theSupplier;
    }
    
    public Connection getConnection() {
        return connection;
    }
}