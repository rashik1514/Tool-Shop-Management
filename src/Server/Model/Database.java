package Server.Model;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;


public class Database {
    CachedRowSet rowSet; //Contains data
    Connection connection;
    int numcols, numrows; //Number of rows and columns


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
//            System.err.println("Cannot connect to the database");
            e.printStackTrace();

        }
    }

    public ResultSet select(String queryStr) {
        ResultSet resultSet;
        try {
            PreparedStatement statement = connection.prepareStatement(queryStr);
            resultSet = statement.executeQuery(queryStr);
            return resultSet;
        } catch (SQLException e) {
//            throw new IllegalStateException("Unable to execute query: " + queryStr, e);
            e.printStackTrace();
        }
        return null;
    }

}