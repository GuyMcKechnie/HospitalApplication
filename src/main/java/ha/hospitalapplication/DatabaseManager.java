package ha.hospitalapplication;

import java.io.File;
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
 * Code taken and adapted from the provided code from the Funworks team.
 * 
 * @author Funworks
 */
public class DatabaseManager {
    // Adapted code to be able to be stored in a JAR file for distribution
    private static final String DATABASE_URL = "/ha/hospitalapplication/Hospital.accdb";
    private static final String TEMP_DATABASE_FILE_NAME = "temp_Hospital.accdb";
    // End of adaptation
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public DatabaseManager() {
        // Adapted to try-with-resources
        try {
            File tempFile;
            try ( // Extract the database file from the jar to a temporary location
                    InputStream dbInputStream = getClass().getResourceAsStream(DATABASE_URL)) {
                tempFile = new File(TEMP_DATABASE_FILE_NAME);
                try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = dbInputStream.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                    }
                }
            }
            // Connect to the temporary database file
            connection = DriverManager.getConnection("jdbc:ucanaccess://" + tempFile.getAbsolutePath());
        } catch (SQLException | IOException e) {
            System.out.println(e);
        }
    }

    public void update(String update) throws SQLException {
        preparedStatement = connection.prepareStatement(update);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public ResultSet query(String stmt) throws SQLException {
        preparedStatement = connection.prepareStatement(stmt);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

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
        } catch (SQLException e) {
            System.out.println(e);
        }
        return temp;
    }
}
