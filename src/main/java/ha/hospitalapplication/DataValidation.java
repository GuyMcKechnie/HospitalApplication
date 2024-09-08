package ha.hospitalapplication;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/*
    This code is responsible for validating the data for the sign-in, sign-up, 
    and add patient processes. There are multiple checks that are done including: 
    presence, length, type, check digit, and range checks.

    We assume that the user's credentials would have already been validated
    to be added to the database in the sign-in process. Therefor, password 
    length/complexity checks are not done for the sign-in process. However, 
    they are done in the sign-up process.
 */
public class DataValidation {
    /**
     * Performs a presence and length check on the input string to ensure it is not
     * empty and has a minimum length of 2 characters.
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
}
