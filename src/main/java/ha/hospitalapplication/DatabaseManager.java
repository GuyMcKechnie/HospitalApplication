package ha.hospitalapplication;

import java.sql.*;

public class DatabaseManager {

    private static final String databaseURL = "jdbc:ucanaccess://Hospital.accdb";

    private Connection conn;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public DatabaseManager() {
        try {
            conn = DriverManager.getConnection(databaseURL);
        } catch (SQLException ex) {
            System.out.println("Cannot connect");
        }
    }

    public void update(String update) throws SQLException {
        statement = conn.prepareStatement(update);
        statement.executeUpdate();
        statement.close();
    }

    public ResultSet query(String stmt) throws SQLException {
        statement = conn.prepareStatement(stmt);
        resultSet = statement.executeQuery();
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return temp;
    }
}
