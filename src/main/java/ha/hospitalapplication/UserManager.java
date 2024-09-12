package ha.hospitalapplication;

import javax.swing.JOptionPane;

/**
 *
 * @author mckec
 */
public class UserManager {

    /**
     *
     */
    public static DatabaseManager databaseManager = new DatabaseManager();

    public UserManager() {

    }

    public void addUser(String email, String password) {
        try {
            databaseManager
                    .update("INSERT INTO tblStaff (email, password) VALUES ('" + email + "','" + password + "');");
            JOptionPane.showMessageDialog(null,
                    "You (" + email + ") have been added to the database! Remember your password!");
        } catch (Exception e) {
        }
    }

}
