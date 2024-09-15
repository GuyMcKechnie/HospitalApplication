package ha.hospitalapplication;

import java.awt.HeadlessException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class UserManager {
    public static DatabaseManager databaseManager = new DatabaseManager();

    public void addUser(String email, String password) {
        try {
            databaseManager
                    .update("INSERT INTO tblStaff (email, password) VALUES ('" + email + "','" + password + "');");
            JOptionPane.showMessageDialog(null,
                    "You (" + email + ") have been added to the database! Remember your password!");
        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
        }
    }
}
