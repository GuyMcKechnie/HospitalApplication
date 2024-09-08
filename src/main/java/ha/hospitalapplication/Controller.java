package ha.hospitalapplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class Controller {

    public DataValidation dataValidator = new DataValidation();
    public static DatabaseManager databaseManager = new DatabaseManager();

    /*
     * Sign-in process.
     */
    @FXML
    TextField signIn_EmailInput;
    @FXML
    TextField signIn_PasswordInput;
    @FXML
    Text signIn_EmailError;
    @FXML
    Text signIn_PasswordError;

    /**
     * Handles the actions associated with the sign-in process.
     * 
     * This method is triggered when the sign-in button is clicked. It retrieves the
     * email and password input by the user, validates them using the DataValidation
     * class, and prints the validation results to the console.
     * 
     * @param event the ActionEvent that triggered this method invocation
     * @see DataValidation validation logic and error handling
     */
    @FXML
    private void handleSignIn(ActionEvent event) {
        clearErrors();
        // Sending the input for validation
        String email = signIn_EmailInput.getText();
        String password = signIn_PasswordInput.getText();
        boolean validEmail = false;
        boolean validPassword = false;
        if (dataValidator.presenceAndLengthCheck(email, signIn_EmailError) == true) {
            if (dataValidator.typeCheck(email, signIn_EmailError, ".") == true) {
                validEmail = true;
            }
        }
        if (dataValidator.presenceAndLengthCheck(password, signIn_PasswordError) == true) {
            if (dataValidator.typeCheck(password, signIn_PasswordError, ";#-=.") == true) {
                validPassword = true;
            }
        }
        if (validEmail && validPassword) {
            if (dataValidator.validateUser(email, password, signIn_EmailError, signIn_PasswordError)) {
                try {
                    App.setRoot("MainMenu");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    /*
     * Send to sign-up process.
     */
    @FXML
    Text signIn_SignUpButton;

    /**
     * Handles the actions associated with navigating to the sign-up menu.
     * 
     * This method is triggered when the sign-up button is clicked. It navigates to
     * the sign-up menu, allowing the user to create a new account.
     * 
     * @param event the MouseEvent that triggered this method invocation
     * @throws IOException if an I/O error occurs during the navigation process
     */
    @FXML
    private void handleSendToSignUp(MouseEvent event) throws IOException {
        App.setRoot("SignUpMenu");
    }

    /*
     * Sign-up process.
     */
    @FXML
    TextField signUp_EmailInput;
    @FXML
    TextField signUp_PasswordInput;
    @FXML
    TextField signUp_AdminCodeInput;
    @FXML
    Text signUp_EmailError;
    @FXML
    Text signUp_PasswordError;
    @FXML
    Text signUp_AdminCodeError;

    /**
     * Initiates the sign-up process when the sign-up button is clicked.
     * 
     * Retrieves user input for email, password, and admin code, and validates these
     * credentials using the DataValidation class. The validation results are then
     * printed to the console for further processing or logging.
     * 
     * @param event the ActionEvent that triggered this method invocation
     * 
     * @see DataValidation validation logic and error handling
     */
    @FXML
    private void handleSignUp(ActionEvent event) {
        clearErrors();
        // Sending the input for validation
        String email = signUp_EmailInput.getText();
        String password = signUp_PasswordInput.getText();
        String adminCode = signUp_AdminCodeInput.getText();
        boolean validEmail = false;
        boolean validPassword = false;
        boolean validAdminCode = false;
        if (dataValidator.presenceAndLengthCheck(email, signUp_EmailError) == true) {
            if (dataValidator.typeCheck(email, signUp_EmailError, ".") == true) {
                validEmail = true;
            }
        }
        if (dataValidator.presenceAndLengthCheck(password, signUp_PasswordError) == true) {
            if (dataValidator.typeCheck(password, signUp_PasswordError, ";#-=.") == true) {
                validPassword = true;
            }
        }
        validAdminCode = dataValidator.adminPasswordValidation(adminCode, signUp_AdminCodeError);
        System.out.println("Controller:\tSign-Up DV:\tEmail: " + validEmail + "\tPassword: " + validPassword
                + "\tAdmin Password: " + validAdminCode);

    }

    /**
     * Clears the error labels associated with the sign-in and sign-up processes.
     * 
     * This method is called at the beginning of the handleSignIn and handleSignUp
     * methods to reset the error labels. It iterates over a list of Text objects
     * representing the error labels and sets their text to an empty string.
     * 
     */
    public void clearErrors() {
        List<Text> errors = new ArrayList<>();
        errors.add(this.signIn_EmailError);
        errors.add(this.signIn_PasswordError);
        errors.add(this.signUp_EmailError);
        errors.add(this.signUp_PasswordError);
        errors.add(this.signUp_AdminCodeError);
        try {
            for (int i = 0; i < errors.size(); i++) {
                if (errors.get(i) != null) {
                    errors.get(i).setText("");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
