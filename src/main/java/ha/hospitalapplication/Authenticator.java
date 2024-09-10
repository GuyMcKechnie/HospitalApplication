package ha.hospitalapplication;

import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javafx.scene.text.Text;

/**
 * @todo add javadoc
 */
public class Authenticator {

    /*
     * Variables used in user auth
     */
    // private int id = -1;
    // private String email = "";
    private String password = "";

    /**
     * Authenticates the admin code.
     * 
     * This method checks if the admin code matches the expected value.
     * 
     * @param adminCodeToValidate the admin code to be authenticated
     * @param errorTextObject
     * @return true if the admin code is valid, false otherwise
     */
    private boolean adminCodeCheck(String adminCodeToAuth, Text errorTextObject) {
        if (!adminCodeToAuth.equals("adm")) {
            errorTextObject.setText("Incorrect admin code!");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Authenticates whether the user is in the database using their email.
     * 
     * This method queries the database to check if the inputted email exists.
     * 
     * @param inputtedEmail the email of the user
     * @return true if the user credentials are valid, false otherwise
     */
    private boolean emailCheck(String inputtedEmail) {
        String line;
        try {
            line = Controller.databaseManager.processResultSet(
                    Controller.databaseManager.query("SELECT * FROM tblStaff WHERE email = '" + inputtedEmail + "';"));
        } catch (NoSuchElementException | SQLException e) {
            System.out.println(e);
            return false;
        }
        if (line.isEmpty()) {
            return false;
        } else {
            try (Scanner lineScanner = new Scanner(line).useDelimiter("#")) {
                while (lineScanner.hasNext()) {
                    // this.id = lineScanner.nextInt();
                    // this.email = lineScanner.next();
                    lineScanner.nextInt();
                    lineScanner.next();
                    this.password = lineScanner.next().trim();
                }
            }
            return true;
        }
    }

    /**
     * Authenticates the user's password.
     * 
     * This method checks if the inputted password matches the stored password.
     * 
     * @param inputtedPassword the password of the user
     * @return true if the user credentials are valid, false otherwise
     */
    private boolean passwordCheck(String inputtedPassword) {
        return this.password.equals(inputtedPassword);
    }

    /*
     * Authenticates adminCode
     * 
     * @todo add javadoc
     */
    public boolean adminCodeAuth(String adminCodeToAuth, Text errorTextObject) {
        return (adminCodeCheck(adminCodeToAuth, errorTextObject));
    }

    /**
     * Authenticates a user's credentials.
     * 
     * This method checks if the inputted email and password match the stored values
     * in the database.
     * 
     * @param inputtedEmail           the email of the user
     * @param inputtedPassword        the password of the user
     * @param emailErrorTextObject    the Text object to display the error message
     *                                if
     *                                the email is invalid
     * @param passwordErrorTextObject the Text object to display the error message
     *                                if the password is invalid
     * @return true if the user credentials are valid, false otherwise
     */
    public boolean userAuth(String inputtedEmail, String inputtedPassword, Text emailErrorTextObject,
            Text passwordErrorTextObject) {
        if (!emailCheck(inputtedEmail)) {
            emailErrorTextObject.setText("Invalid email!");
            return false;
        }
        if (!passwordCheck(inputtedPassword)) {
            passwordErrorTextObject.setText("Invalid password!");
            return false;
        }
        return true;
    }
}
