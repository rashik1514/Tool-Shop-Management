package Server.Controller;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;

/**
 * Controls the database
 *
 * @author Christina Lu 30037885, Layla Arab 30017060, MD Rashik Hassan 30048022
 * @version 1.0
 * @since April 5 2019
 */
public abstract class DatabaseController {
    CachedRowSet rowSet; // Contains data
    ResultSetMetaData metaData; // Additional info about the data
    Connection connection;
    Statement statement;
    int numcols, numrows; // Number of rows and columns


    protected DatabaseController() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.err.println("Unable to find and load driver");
            System.exit(1);
        }
        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost/test1",
                    "root", "password");
        } catch (SQLException sqlerr) {
            System.out.println(sqlerr.getMessage());
            System.out.println(sqlerr.getSQLState());
            System.out.println(sqlerr.getErrorCode());
        }

        System.out.println("Connected Successfully");

        try {
            connection.setAutoCommit(false);
//            rowSet = new CachedRowSetImpl();
            rowSet.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
            rowSet.setConcurrency(ResultSet.CONCUR_UPDATABLE);
            rowSet.setCommand("SELECT * FROM toolshop");
            rowSet.execute(connection);

            metaData = rowSet.getMetaData();
            numcols = metaData.getColumnCount();
            numrows = rowSet.size();
            rowSet.first();
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }

}
