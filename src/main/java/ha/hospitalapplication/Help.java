package ha.hospitalapplication;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Help {
    /**
     * Retrieves the title of a help topic based on the provided topic ID.
     * 
     * @param topicID the ID of the help topic
     * @return the title of the help topic, or an empty string if an error occurs
     */
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

    /**
     * Retrieves the body of a help topic based on the provided topic ID.
     * 
     * @param topicID the ID of the help topic
     * @return the body of the help topic, or an empty string if an error occurs
     */
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
