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
     * This code handles the actions that are associated with the sign-in process.
     */
    @FXML
    TextField signIn_EmailInput;
    @FXML
    TextField signIn_PasswordInput;
    @FXML
    Text signIn_EmailError;
    @FXML
    Text signIn_PasswordError;

    @FXML
    private void handleSignIn(ActionEvent event) {
        clearErrors();
        // Sending the input for validation
        boolean validEmail = false;
        boolean validPassword = false;
        validEmail = dataValidator.presenceAndLengthCheck(signIn_EmailInput, signIn_EmailError);
        if (validEmail == true) {
            validPassword = dataValidator.typeCheck(fields.get(i), errors.get(i), ".");
        }
        System.out.println("Controller:\tSign-In DV:\tEmail: " + validEmail + "\tPassword: " + validPassword); // Will
                                                                                                               // be
                                                                                                               // used
                                                                                                               // later
    }

    /*
     * This code handles the actions that are associated with the sign-up process.
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

    @FXML
    private void handleSignUp(ActionEvent event) {
        clearErrors();
        /*
         * This code is add all the elements into an array to loop through easier than
         * manually adding each component to the validation. Future proofing for the add
         * patient GUI.
         */
        List<TextField> fields = new ArrayList<>();
        fields.add(signUp_EmailInput);
        fields.add(signUp_PasswordInput);
        fields.add(signUp_AdminPasswordInput);
        List<Text> errors = new ArrayList<>();
        errors.add(signUp_EmailError);
        errors.add(signUp_PasswordError);
        errors.add(signUp_AdminPasswordError);

        // Sending the input for validation
        boolean signUpValidation = true;
        for (int i = 0; i < fields.size(); i++) {
            signUpValidation = dataValidator.presenceAndLengthCheck(fields.get(i), errors.get(i))
                    && dataValidator.typeCheck(fields.get(i), errors.get(i), " :-',;");
        }
        System.out.println("Controller:\tSign-Up DV:\t" + signUpValidation); // Will be used later
    }

    /*
     * This code is to clear the error labels of the associated processes every
     * time their buttons are clicked.
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
