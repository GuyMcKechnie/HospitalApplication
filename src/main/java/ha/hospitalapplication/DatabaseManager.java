package ha.hospitalapplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author mckec
 */
public class DatabaseManager {

    private static final String databaseURL = "/ha/hospitalapplication/Hospital.accdb";
    private static final String tempDatabaseFile = "temp_Hospital.accdb";

    private Connection conn;
    private PreparedStatement statement;
    private ResultSet resultSet;

    /**
     * The constructor for the database manager.
     * 
     * @throws IOException
     */
    public DatabaseManager() {
        try {
            // Extract the database file from the jar to a temporary location
            InputStream dbInputStream = getClass().getResourceAsStream(databaseURL);
            File tempFile = new File(tempDatabaseFile);
            FileOutputStream fos = new FileOutputStream(tempFile);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = dbInputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            fos.close();
            dbInputStream.close();

            // Connect to the temporary database file
            conn = DriverManager.getConnection("jdbc:ucanaccess://" + tempFile.getAbsolutePath());
        } catch (SQLException e) {
            System.out.println(e);
        } catch (FileNotFoundException ex) {
        } catch (IOException e) {

        }

    }

    /**
     *
     * @param update
     * @throws SQLException
     */
    public void update(String update) throws SQLException {
        statement = conn.prepareStatement(update);
        statement.executeUpdate();
        statement.close();
    }

    /**
     *
     * @param stmt
     * @return
     * @throws SQLException
     */
    public ResultSet query(String stmt) throws SQLException {
        statement = conn.prepareStatement(stmt);
        resultSet = statement.executeQuery();
        return resultSet;
    }

    /**
     *
     * @param rs
     * @return
     */
    public String processResultSet(ResultSet rs) {
        String temp = "";
        try {
            ResultSetMetaData meta = rs.getMetaData();
            int size = meta.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= size; i++) {
                    Object value = rs.getObject(i);
                    temp += "#" + value;
                }
                temp += "\n";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return temp;
    }
}
