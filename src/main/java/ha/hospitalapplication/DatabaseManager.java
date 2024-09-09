package ha.hospitalapplication;

import java.sql.*;

/**
 *
 * @author mckec
 */
public class DatabaseManager {

    private static final String databaseURL = "jdbc:ucanaccess://src\\main\\resources\\ha\\hospitalapplication\\Hospital.accdb";

    private Connection conn;
    private PreparedStatement statement;
    private ResultSet resultSet;

    /**
     * The constructor for the database manager.
     */
    public DatabaseManager() {
        try {
            conn = DriverManager.getConnection(databaseURL);
        } catch (SQLException e) {
            e.printStackTrace();
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
