package ha.hospitalapplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class Controller implements Initializable {

    /**
     * Object to call the data validation methods in the validation class.
     *
     * @see Validator
     */
    public static Validator validator = new Validator();

    /**
     * Object to call the data authentication methods in the authentication
     * class.
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

    /**
     * Static object for fetching methods in the PatientManager class
     */
    public static PatientManager patientManager = new PatientManager();

    /**
     * Static object for fetching methods in the UserManager class
     */
    public static UserManager userManager = new UserManager();

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

    // Main GUI
    @FXML
    TableView<Patient> mm_PatientTable;
    @FXML
    TableColumn<Patient, String> mm_PatientTableColumn;
    @FXML
    Label mm_PatientID;
    @FXML
    Label mm_PatientName;
    @FXML
    Label mm_PatientGender;
    @FXML
    Label mm_PatientAge;
    @FXML
    Label mm_PatientJoinDate;
    @FXML
    Label mm_ConditionText;
    @FXML
    Label mm_DescriptionText;
    @FXML
    Label mm_MedicationText;
    @FXML
    Label mm_EstimatedDepartureText;

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
     * This method is triggered when the sign-in button is clicked. It retrieves
     * the email and password input by the user, validates them using the
     * DataValidation class, and prints the validation results to the console.
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
            App.alterScene("MainMenu", 1500, 750);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Handles the actions associated with navigating to the sign-up menu.
     *
     * This method is triggered when the sign-up button is clicked. It navigates
     * to the sign-up menu, allowing the user to create a new account.
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
     * Retrieves user input for email, password, and admin code, and validates
     * these credentials using the validator class and authenticator.
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
                userManager.addUser(email, password);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Clears the error labels associated with the sign-in and sign-up
     * processes.
     *
     * This method is called at the beginning of the handleSignIn and
     * handleSignUp methods to reset the error labels. It iterates over a list
     * of Text objects representing the error labels and sets their text to an
     * empty string.
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
     * This method is triggered when the F1 key is pressed. It displays a
     * message dialog providing contextual help for the currently focused
     * component.
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
            System.out.println(e);
        }
    }

    @FXML
    private void handleMainSendBack(ActionEvent event) {
        try {
            App.alterScene("MainMenu", 1500, 750);
        } catch (IOException e) {
        }
    }

    @FXML
    private void handleSelectedPatient() {
        Patient selectedPatient = mm_PatientTable.getSelectionModel().getSelectedItem();
        int num = mm_PatientTable.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }

        mm_PatientID.setText("(" + String.valueOf(selectedPatient.getPatientID()) + ")");
        mm_PatientName.setText(selectedPatient.getName());
        mm_PatientGender.setText(selectedPatient.getGender()); // add | in builder
        mm_PatientAge.setText(String.valueOf(selectedPatient.getAge()));
        mm_PatientJoinDate.setText(selectedPatient.formatDate(selectedPatient.getJoinDate()));
        mm_ConditionText.setText(selectedPatient.getConditions());
        mm_DescriptionText.setText(selectedPatient.getDescriptionOfEvent());
        mm_MedicationText.setText(selectedPatient.getMedication());
        mm_EstimatedDepartureText.setText(selectedPatient.formatDate(selectedPatient.getEstDepartureDate()));

    }

    @FXML
    TextField ap_NameInput;

    @FXML
    TextField ap_SurnameInput;

    @FXML
    TextField ap_AgeInput;

    @FXML
    TextField ap_ConditionInput;
    @FXML
    TextField ap_DescriptionOfEventInput;

    @FXML
    TextField ap_MedicationInput;

    @FXML
    Button ap_AddPatientButton;

    @FXML
    ChoiceBox<String> ap_GenderSelect;

    @FXML
    ChoiceBox<String> ap_MealChoiceSelect;

    @FXML
    DatePicker ap_AdmissionDateSelect;

    @FXML
    TextField ap_AdmissionTime;

    @FXML
    private void addPatient(ActionEvent event) {
        String name = ap_NameInput.getText();
        String surname = ap_SurnameInput.getText();
        int age = Integer.parseInt(ap_AgeInput.getText());
        String condition = ap_ConditionInput.getText();
        String descriptionOfEvent = ap_DescriptionOfEventInput.getText();
        String gender = ap_GenderSelect.getSelectionModel().getSelectedItem();
        int mealChoice = Integer.parseInt(ap_MealChoiceSelect.getSelectionModel().getSelectedItem());
        LocalDate admissionDate = ap_AdmissionDateSelect.getValue();
        String admissionTime = ap_AdmissionTime.getText();
        String admissionDateTime = patientManager.combineDateTime(admissionDate, admissionTime);
        String medication = ap_MedicationInput.getText();
        if (patientManager.addPatient(name, surname, gender, age, condition, descriptionOfEvent, admissionDateTime,
                mealChoice,
                medication)) {
            handleMainSendBack(event);
        }
    }

    @FXML
    private void handleAddPatientMenuOpen(ActionEvent event) {
        try {
            App.alterScene("AddPatient", 840, 700);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    private void handleRemovePatientMenuOpen(ActionEvent event) {
        try {
            App.alterScene("RemovePatient", 840, 700);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.mm_PatientTable.setItems(PatientManager.getPatientList());
            this.mm_PatientTableColumn.setCellValueFactory(new PropertyValueFactory<>("patientInformation"));
        } catch (Exception e) {
        }
        try {
            String[] mealChoices = { "1", "2", "3" };
            String[] genderChoices = { "Male", "Female" };
            ap_MealChoiceSelect.getItems().addAll(mealChoices);
            ap_GenderSelect.getItems().addAll(genderChoices);
        } catch (Exception e) {
        }
    }

}
