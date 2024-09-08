package ha.hospitalapplication;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class Controller {

    private DataValidation dataValidator = new DataValidation();

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
     * email and password input by the user,
     * validates them using the DataValidation class, and prints the validation
     * results to the console.
     * 
     * @param event the ActionEvent that triggered this method
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
        System.out.println("Controller:\tSign-In DV:\tEmail: " + validEmail + "\tPassword: " + validPassword);
    }

    /*
     * Sign-up process.
     */
    @FXML
    TextField signUp_EmailInput;
    @FXML
    TextField signUp_PasswordInput;
    @FXML
    TextField signUp_AdminPasswordInput;
    @FXML
    Text signUp_EmailError;
    @FXML
    Text signUp_PasswordError;
    @FXML
    Text signUp_AdminPasswordError;

    /**
     * Handles the actions associated with the sign-up process.
     * 
     * This method is triggered when the sign-up button is clicked. It retrieves the
     * email, password, and admin password input by the user,
     * validates them using the DataValidation class, and prints the validation
     * results to the console.
     * 
     * @param event the ActionEvent that triggered this method
     */
    @FXML
    private void handleSignUp(ActionEvent event) {
        clearErrors();
        // Sending the input for validation
        String email = signUp_EmailInput.getText();
        String password = signUp_PasswordInput.getText();
        String adminPassword = signUp_AdminPasswordInput.getText();
        boolean validEmail = false;
        boolean validPassword = false;
        boolean validAdminPassword = false;
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
        if (dataValidator.presenceAndLengthCheck(adminPassword, signIn_PasswordError) == true) {
            if (dataValidator.typeCheck(adminPassword, signIn_PasswordError, ";#-=.") == true) {
                validAdminPassword = true;
            }
        }
        System.out.println("Controller:\tSign-In DV:\tEmail: " + validEmail + "\tPassword: " + validPassword
                + "\tAdmin Password: " + validAdminPassword);
    }

    /**
     * Clears the error labels associated with the sign-in and sign-up processes.
     * 
     * This method is called at the beginning of the handleSignIn and handleSignUp
     * methods to reset the error labels.
     * It iterates over a list of Text objects representing the error labels and
     * sets their text to an empty string.
     */
    public void clearErrors() {
        List<Text> errors = new ArrayList<>();
        errors.add(this.signIn_EmailError);
        errors.add(this.signIn_PasswordError);
        errors.add(this.signUp_EmailError);
        errors.add(this.signUp_PasswordError);
        errors.add(this.signUp_AdminPasswordError);
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
