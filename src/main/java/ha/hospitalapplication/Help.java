package ha.hospitalapplication;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Help {
    public String getTitle(String topicID) {
        ResultSet resultSet;
        try {
            resultSet = Controller.databaseManager
                    .query("SELECT topic FROM tblHelp WHERE topicID = '" + topicID + "';");
        } catch (SQLException e) {
            System.out.println(e);
            return "";
        }
        return Controller.databaseManager.processResultSet(resultSet).substring(1);
    }

    public String getBody(String topicID) {
        ResultSet resultSet;
        try {
            resultSet = Controller.databaseManager
                    .query("SELECT descriptionText FROM tblHelp WHERE topicID = '" + topicID + "';");
        } catch (SQLException e) {
            System.out.println(e);
            return "";
        }
        return Controller.databaseManager.processResultSet(resultSet).substring(1);
    }
}
