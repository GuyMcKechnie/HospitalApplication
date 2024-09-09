package ha.hospitalapplication;

import java.io.IOException;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javafx.scene.text.Text;

/**
 * This code is responsible for validating the data for the sign-in, sign-up,
 * and add patient processes. There are multiple checks that are done including:
 * presence, length, type, check digit, and range checks.
 * 
 * We assume that the user's credentials would have already been validated
 * to be added to the database in the sign-in process. Therefor, password
 * length/complexity checks are not done for the sign-in process. However,
 * they are done in the sign-up process.
 */
public class DataValidation {
    /**
     * Performs a presence and length check on the input string to ensure it is not
     * empty and has a minimum length of 2 characters.
     * 
     * This method checks if the input string is null or has a length of 1 or less.
     * If the input string is invalid, it sets the error text object to display an
     * error message.
     * 
     * @param stringToValidate the input string to be validated
     * @param errorTextObject  the Text object to display the error message if the
     *                         validation fails
     * @return true if the input string is valid, false otherwise
     */
    public boolean presenceAndLengthCheck(String stringToValidate, Text errorTextObject) {
        if (stringToValidate == null || stringToValidate.length() <= 1) {
            errorTextObject.setText("Enter at least one character!");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Performs a type check on the input string to ensure it only contains valid
     * characters.
     * 
     * This method checks if the input string contains any invalid characters. If an
     * invalid character is found, it sets the error text object to display an error
     * message.
     * 
     * @param stringToValidate        the input string to be validated
     * @param errorTextObject         the Text object to display the error message
     *                                if the validation fails
     * @param allowedCharactersString a string of valid characters that the input
     *                                string can contain
     * @return true if the input string is valid, false otherwise
     */
    public boolean typeCheck(String stringToValidate, Text errorTextObject, String allowedCharactersString) {
        for (int i = 0; i < stringToValidate.length(); i++) {
            if (!Character.isLetterOrDigit(stringToValidate.charAt(i))
                    && !(allowedCharactersString.indexOf(stringToValidate.charAt(i)) >= 0)) {
                errorTextObject.setText("Invalid characters!");
                return false;
            }
            i++;
        }
        return true;
    }

    /**
     * Validates the admin code.
     * 
     * This method checks if the admin code matches the expected value.
     * 
     * @param adminCodeToValidate the admin code to be validated
     * @param errorTextObject
     * @return true if the admin code is valid, false otherwise
     */
    public boolean adminPasswordValidation(String adminCodeToValidate, Text errorTextObject) {
        if (adminCodeToValidate.equals("adm")) {
            return true;
        } else {
            errorTextObject.setText("Incorrect admin code!");
            return false;
        }
    }

    /*
     * Validate User
     */
    private int id = -1;
    private String email = "";
    private String password = "";

    /**
     * Validates a user's credentials.
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
    public boolean validateUser(String inputtedEmail, String inputtedPassword, Text emailErrorTextObject,
            Text passwordErrorTextObject) {
        boolean validUser = false;
        if (checkEmail(inputtedEmail) == true) {
            if (checkPassword(inputtedPassword) == true) {
                validUser = true;
            } else {
                passwordErrorTextObject.setText("Invalid password!");
            }
        } else {
            emailErrorTextObject.setText("Invalid email!");
        }
        return validUser;
    }

    /**
     * Validates whether the user is in the database using their email.
     * 
     * This method queries the database to check if the inputted email exists.
     * 
     * @param inputtedEmail the email of the user
     * @return true if the user credentials are valid, false otherwise
     */
    private boolean checkEmail(String inputtedEmail) {
        try {
            String line = Controller.databaseManager.processResultSet(
                    Controller.databaseManager.query("SELECT * FROM tblStaff WHERE email = '" + inputtedEmail + "';"));
            if (!line.isEmpty()) {
                try (Scanner lineScanner = new Scanner(line).useDelimiter("#")) {
                    while (lineScanner.hasNext()) {
                        this.id = lineScanner.nextInt();
                        this.email = lineScanner.next();
                        this.password = lineScanner.next().trim();
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (NoSuchElementException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Validates the user's password.
     * 
     * This method checks if the inputted password matches the stored password.
     * 
     * @param inputtedPassword the password of the user
     * @return true if the user credentials are valid, false otherwise
     */
    private boolean checkPassword(String inputtedPassword) {
        if (this.password.equals(inputtedPassword)) {
            return true;
        } else {
            return false;
        }
    }
}
