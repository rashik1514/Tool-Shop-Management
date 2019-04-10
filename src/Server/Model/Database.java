package Server.Model;

import java.sql.*;


public class Database {
<<<<<<< HEAD
    Connection connection;
=======
    private Connection connection;
>>>>>>> 9c72b649e7eaa90693014b5f379428644aff196f

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.err.println("Unable to find and load driver");
        }
        try {

<<<<<<< HEAD
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ToolShop?serverTimezone=GMT",
                    "root", "Iig82cb3!");
=======
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ToolShop?serverTimezone=GMT", "root", "rootroot");
>>>>>>> 9c72b649e7eaa90693014b5f379428644aff196f
        } catch (SQLException e) {
           e.printStackTrace();
        }
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

}