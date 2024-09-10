package ha.hospitalapplication;

import javax.swing.JOptionPane;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class Help {
    /**
     * add javadoc
     */
    public void displayContextualHelpMessage(KeyCode keyPressed, Object componentSource,
            TextField signIn_EmailInput,
            TextField signIn_PasswordInput,
            TextField signUp_EmailInput,
            TextField signUp_PasswordInput,
            TextField signUp_AdminCodeInput,
            Button signIn_SignInButton,
            Button signUp_SignUpButton) {
        if (keyPressed == KeyCode.F1) {
            if (componentSource.equals(signIn_EmailInput) || componentSource.equals(signUp_EmailInput)) {
                displayEmailHelpMessage();
            } else if (componentSource.equals(signIn_PasswordInput)) {
                displaySignInPasswordHelpMessage();
            } else if (componentSource.equals(signUp_PasswordInput)) {
                displaySignUpPasswordHelpMessage();
            } else if (componentSource.equals(signUp_AdminCodeInput)) {
                displayAdminCodeHelpMessage();
            } else if (componentSource.equals(signIn_SignInButton)) {
                displaySignInButtonHelpMessage();
            } else if (componentSource.equals(signUp_SignUpButton)) {
                displaySignUpButtonHelpMessage();
            } else {
                displayDefaultHelpMessage();
            }
        }
    }

    private void displayEmailHelpMessage() {
        JOptionPane.showMessageDialog(null, "Enter your email address in the format 'example@example.com'");
    }

    private void displaySignInPasswordHelpMessage() {
        JOptionPane.showMessageDialog(null, "Enter your password.");
    }

    private void displaySignUpPasswordHelpMessage() {
        JOptionPane.showMessageDialog(null,
                "Enter a strong password with at least 8 characters, including uppercase and lowercase letters, numbers, and special characters.");
    }

    private void displaySignInButtonHelpMessage() {
        JOptionPane.showMessageDialog(null,
                "Click to sign in to your account. Ensure you have entered a valid email and password.");
    }

    private void displaySignUpButtonHelpMessage() {
        JOptionPane.showMessageDialog(null,
                "Click to create a new account. Ensure you have entered a valid email, password, and admin code.");
    }

    private void displayAdminCodeHelpMessage() {
        JOptionPane.showMessageDialog(null, "Enter the admin code provided by the administrator.");
    }

    private void displayDefaultHelpMessage() {
        JOptionPane.showMessageDialog(null, "Please select a component to provide contextual help for.");
    }
}
