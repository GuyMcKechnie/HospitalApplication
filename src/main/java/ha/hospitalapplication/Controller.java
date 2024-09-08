package ha.hospitalapplication;

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
        boolean signInValidation = true;
        signInValidation = dataValidator.presenceAndLengthCheck(signIn_EmailInput, signIn_EmailError)
                && dataValidator.presenceAndLengthCheck(signIn_PasswordInput, signIn_PasswordError)
                && dataValidator.typeCheck(signIn_EmailInput, signIn_EmailError, " :-',;")
                && dataValidator.typeCheck(signIn_PasswordInput, signIn_PasswordError, " -':,;");
    }

    /*
     * This code is to clear the error labels of the associated processes every
     * time their buttons are clicked.
     */
    public void clearErrors() {
        try {
            signIn_EmailError.setText("");
            signIn_PasswordError.setText("");
        } catch (Exception e) {
        }
    }

}
