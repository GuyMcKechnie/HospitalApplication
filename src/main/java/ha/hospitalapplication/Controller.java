package ha.hospitalapplication;

import java.io.IOException;
import java.sql.SQLException;
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

public class Controller {

    /**
     * Object to call the data validation methods in the validation class.
     * 
     * @see Validator
     */
    public static Validator validator = new Validator();

    /**
     * Object to call the data authentication methods in the authentication class.
     * 
     * @see Authenticator
     */
    public static Authenticator authenticator = new Authenticator();

    /**
     * Object to call the help methods in the help class.
     * 
     * @see Help
     */
    public static Help help = new Help();

    /**
     * Static object for fetching the information from the database.
     * 
     * @see DatabaseManager
     */
    public static DatabaseManager databaseManager = new DatabaseManager();

    /*
     * Variables used in sign-in/sign-up
     */
    private static final String ALLOWED_EMAIL_STRING = ".@";
    private static final String ALLOWED_PASSWORD_STRING = "@;#-=.";

    /*
     * FXML Objects
     */
    // Sign In
    @FXML
    TextField signIn_EmailInput;
    @FXML
    TextField signIn_PasswordInput;
    @FXML
    Text signIn_EmailErrorObject;
    @FXML
    Text signIn_PasswordErrorObject;

    // Sign Up
    @FXML
    TextField signUp_EmailInput;
    @FXML
    TextField signUp_PasswordInput;
    @FXML
    TextField signUp_AdminCodeInput;
    @FXML
    Text signUp_EmailErrorObject;
    @FXML
    Text signUp_PasswordErrorObject;
    @FXML
    Text signUp_AdminCodeErrorObject;

    // Help
    @FXML
    Button signIn_SignInButton;
    @FXML
    Button signUp_SignUpButton;
    @FXML
    MenuButton signUp_HelpMenu;

    /**
     * Handles the actions associated with the sign-in process.
     * 
     * This method is triggered when the sign-in button is clicked. It retrieves the
     * email and password input by the user, validates them using the DataValidation
     * class, and prints the validation results to the console.
     * 
     * @param event the ActionEvent that triggered this method invocation
     * @see Validator validation logic and error handling
     */
    @FXML
    private void handleSignIn(ActionEvent event) {
        clearErrors();
        // Sending the input for validation
        String email = signIn_EmailInput.getText();
        String password = signIn_PasswordInput.getText();

        if (!validator.validateEmail(email, signIn_EmailErrorObject, ALLOWED_EMAIL_STRING)) {
            return;
        }
        if (!validator.validatePassword(password, signIn_PasswordErrorObject, ALLOWED_PASSWORD_STRING)) {
            return;
        }
        if (!authenticator.userAuth(email, password, signIn_EmailErrorObject, signIn_PasswordErrorObject)) {
            return;
        }
        try {
            App.alterMain();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

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
    private void handleSendToSignUp(MouseEvent event) {
        try {
            App.setRoot("SignUpMenu");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /*
     * Sign-up process.
     */

    /**
     * Initiates the sign-up process when the sign-up button is clicked.
     * 
     * Retrieves user input for email, password, and admin code, and validates these
     * credentials using the validator class and authenticator.
     * 
     * @param event the ActionEvent that triggered this method invocation
     * 
     * @see Validator validation logic and error handling
     * @see Authenticator authenticator logic and error handling
     */
    @FXML
    private void handleSignUp(ActionEvent event) {
        clearErrors();
        // Sending the input for validation
        String email = signUp_EmailInput.getText();
        String password = signUp_PasswordInput.getText();
        String adminCode = signUp_AdminCodeInput.getText();
        if (validator.validateEmail(email, signUp_EmailErrorObject, ALLOWED_EMAIL_STRING)
                && validator.validatePassword(password, signUp_PasswordErrorObject, ALLOWED_PASSWORD_STRING)
                && authenticator.adminCodeAuth(adminCode, signUp_AdminCodeErrorObject)) {
            try {
                databaseManager
                        .update("INSERT INTO tblStaff (email, password) VALUES ('" + email + "','" + password + "');");

                JOptionPane.showMessageDialog(null,
                        "You (" + email + ") have been added to the database! Remember your password!");
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
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
        errors.add(this.signIn_EmailErrorObject);
        errors.add(this.signIn_PasswordErrorObject);
        errors.add(this.signUp_EmailErrorObject);
        errors.add(this.signUp_PasswordErrorObject);
        errors.add(this.signUp_AdminCodeErrorObject);
        try {
            for (int i = 0; i < errors.size(); i++) {
                if (errors.get(i) != null) {
                    errors.get(i).setText("");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Handles the contextual help feature.
     * 
     * This method is triggered when the F1 key is pressed. It displays a message
     * dialog providing contextual help for the currently focused component.
     * 
     * @param event the KeyEvent that triggered this method invocation
     */
    @FXML
    private void handleContextualHelp(KeyEvent event) {
        KeyCode keyPressed = event.getCode();
        Object componentSource = event.getSource();
        help.displayContextualHelpMessage(keyPressed, componentSource,
                signIn_EmailInput,
                signIn_PasswordInput,
                signUp_EmailInput,
                signUp_PasswordInput,
                signUp_AdminCodeInput,
                signIn_SignInButton,
                signUp_SignUpButton);
    }

    /**
     * add javadoc
     * 
     * @param event
     * @throws IOException
     */
    private void handleHelp(MouseEvent event) throws IOException {
        if (event.getSource().equals(signUp_HelpMenu)) {
            App.setRoot("HelpMenu");
        }
    }

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
        } catch (IOException e) {
            System.out.println("A");
        }
    }
}
