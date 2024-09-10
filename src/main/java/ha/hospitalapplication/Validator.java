package ha.hospitalapplication;

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
public class Validator {

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
    private boolean presenceAndLengthCheck(String stringToValidate, Text errorObject) {
        if (stringToValidate == null || stringToValidate.length() <= 1) {
            errorObject.setText("Enter at least one character!");
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
    private boolean typeCheck(String stringToValidate, Text errorTextObject, String allowedCharactersString) {
        for (int i = 0; i < stringToValidate.length(); i++) {
            char characterToCheck = stringToValidate.charAt(i);
            if (!Character.isLetterOrDigit(characterToCheck)) {
                if (allowedCharactersString.indexOf(characterToCheck) == -1) {
                    errorTextObject.setText("Invalid characters!");
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * Validate email
     * 
     * @todo add javadoc
     */
    public boolean validateEmail(String inputtedEmail, Text errorObject, String allowedCharactersString) {
        return presenceAndLengthCheck(inputtedEmail, errorObject)
                && typeCheck(inputtedEmail, errorObject, allowedCharactersString);
    }

    /*
     * Validate password
     * 
     * @todo add javadoc
     */
    public boolean validatePassword(String inputtedPassword, Text errorObject, String allowedCharactersString) {
        return presenceAndLengthCheck(inputtedPassword, errorObject)
                && typeCheck(inputtedPassword, errorObject, allowedCharactersString);
    }

}
