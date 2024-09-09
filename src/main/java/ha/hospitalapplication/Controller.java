package ha.hospitalapplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 *
 * @author mckec
 */
public class Controller {

    /**
     * Object to call the data validation methods in the data validation class.
     */
    public DataValidation dataValidator = new DataValidation();

    /**
     * Static object for fetching the information from the database.
     */
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
            if (dataValidator.typeCheck(password, signIn_PasswordError, "@;#-=.") == true) {
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
    }

    /**
     * Clears the error labels associated with the sign-in and sign-up processes.
     * 
     * This method is called at the beginning of the handleSignIn and handleSignUp
     * methods to reset the error labels. It iterates over a list of Text objects
     * representing the error labels and sets their text to an empty string.
     * 
     */
    private void clearErrors() {
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

    /*
     * Contextual help
     */

    @FXML
    Button signIn_SignInButton;
    @FXML
    Button signUp_SignUpButton;

    /**
     * Handles the contextual help feature.
     * 
     * This method is triggered when the F1 key is pressed. It displays a message
     * dialog providing contextual help for the currently focused component.
     * 
     * @param event the KeyEvent that triggered this method invocation
     */
    @FXML
    private void handleCT(KeyEvent event) {
        if (event.getCode() == KeyCode.F1) {
            if (event.getSource().equals(signIn_EmailInput) || event.getSource().equals(signUp_EmailInput)) {
                JOptionPane.showMessageDialog(null, "Enter your email address in the format 'example@example.com'");
            } else if (event.getSource().equals(signIn_PasswordInput)) {
                JOptionPane.showMessageDialog(null,
                        "Enter your password.");
            } else {
                try {
                    if (event.getSource().equals(signIn_SignInButton)) {
                        JOptionPane.showMessageDialog(null,
                                "Click to sign in to your account. Ensure you have entered a valid email and password.");
                    } else if (event.getSource().equals(signUp_SignUpButton)) {
                        JOptionPane.showMessageDialog(null,
                                "Click to create a new account. Ensure you have entered a valid email, password, and admin code.");
                    } else if (event.getSource().equals(signUp_AdminCodeInput)) {
                        JOptionPane.showMessageDialog(null, "Enter the admin code provided by the administrator.");
                    } else if (event.getSource().equals(signUp_PasswordInput)) {
                        JOptionPane.showMessageDialog(null,
                                "Enter your password. It should be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Please select a component to provide contextual help for.");
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    /*
     * Handle help
     */
    @FXML
    MenuButton signUp_HelpMenu;

    private void handleHelp(MouseEvent event) throws IOException {
        if (event.getSource().equals(signUp_HelpMenu)) {
            App.setRoot("HelpMenu");
        }
    }

    /*
     * Handle back button
     */

    /**
     * Navigates back to the sign-in menu.
     * 
     * This method is triggered when the back button is clicked. It sets the
     * application root to "SignInMenu", effectively returning the user to the
     * sign-in interface.
     * 
     * @param event the ActionEvent that triggered this method invocation
     * 
     */
    @FXML
    private void handleBack(ActionEvent event) {
        try {
            App.setRoot("SignInMenu");

        } catch (Exception e) {
            System.out.println("A");
        }
    }

}
