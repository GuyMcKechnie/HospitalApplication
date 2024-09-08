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

    /*
     * Presence and Length check to ensure data is inputted.
     */
    public boolean presenceAndLengthCheck(TextField textField, Text text) {
        if (textField == null || textField.getText().length() <= 1) {
            text.setText("Enter at least one character!");
            return false;
        }
        return true;
    }

    /*
     * Type check to ensure data is valid by checking it against a string of valid
     * characters.
     */
    public boolean typeCheck(TextField textField, Text text, String validString) {
        for (int i = 0; i < textField.getText().length(); i++) {
            if (!Character.isLetterOrDigit(textField.getText().charAt(i))
                    && !(validString.indexOf(textField.getText().charAt(i)) >= 0)) {
                text.setText("Invalid characters!");
                return false;
            }
            i++;
        }
        return true;
    }
}
