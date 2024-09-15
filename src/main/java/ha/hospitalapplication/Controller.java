package ha.hospitalapplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
     * 
     * @see PatientManager
     */
    public static PatientManager patientManager = new PatientManager();

    /**
     * Static object for fetching methods in the UserManager class
     * 
     * @see UserManager
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
    ImageView mm_MealChoice_MainMeal;
    @FXML
    ImageView mm_MealChoice_SecondaryMeal;
    @FXML
    ImageView mm_MealChoice_TertiaryMeal;
    @FXML
    Label mm_EstimatedDepartureText;
    @FXML
    ImageView mm_PatientImage;

    // Add Patient
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
    ChoiceBox<String> ap_GenderSelect;
    @FXML
    ChoiceBox<String> ap_MealChoiceSelect;
    @FXML
    DatePicker ap_AdmissionDateSelect;
    @FXML
    TextField ap_AdmissionTime;

    // Help
    @FXML
    Label hme_Title;
    @FXML
    Label hme_Body;
    @FXML
    AnchorPane helpMenuContracted;
    @FXML
    AnchorPane helpMenuExpanded;

    // Remove patient
    @FXML
    TextField rp_PatientIDInput;
    @FXML
    TextField rp_AdminCodeInput;
    @FXML
    Text rp_AdminCodeErrorObject;

    /**
     * Initializes the application's UI components and sets up the initial state of
     * the application.
     * 
     * This method is called after the FXML file has been loaded and the UI
     * components have been created.
     * It is responsible for setting up the initial state of the application,
     * including populating the patient table and setting up the help menu.
     * 
     * @param location  the location of the FXML file
     * @param resources the resources used to localize the application
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.mm_PatientTable.setItems(PatientManager.getPatientList());
            this.mm_PatientTableColumn.setCellValueFactory(new PropertyValueFactory<>("patientInformation"));
        } catch (NullPointerException e) {
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            handleInitPatient();
        } catch (NullPointerException e) {
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            String[] mealChoices = { "1", "2", "3" };
            String[] genderChoices = { "Male", "Female" };
            ap_MealChoiceSelect.getItems().addAll(mealChoices);
            ap_GenderSelect.getItems().addAll(genderChoices);
        } catch (NullPointerException e) {
        } catch (Exception e) {
            System.out.println(e);
        }
    }

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
    @SuppressWarnings("unused")
    @FXML
    private void handleSignIn(ActionEvent event) {
        clearErrors();
        String emailInput = signIn_EmailInput.getText();
        String passwordInput = signIn_PasswordInput.getText();
        // Sending the input for validation
        if (!validator.validateEmail(emailInput, signIn_EmailErrorObject, ALLOWED_EMAIL_STRING)) {
            return;
        }
        if (!validator.validatePassword(passwordInput, signIn_PasswordErrorObject, ALLOWED_PASSWORD_STRING)) {
            return;
        }
        if (!authenticator.userAuthentication(emailInput, passwordInput, signIn_EmailErrorObject,
                signIn_PasswordErrorObject)) {
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
    @SuppressWarnings("unused")
    @FXML
    private void handleSendToSignUp(MouseEvent event) {
        try {
            App.alterScene("SignUpMenu", 840, 700);
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
    @SuppressWarnings("unused")
    @FXML
    private void handleSignUp(ActionEvent event) {
        clearErrors();
        String emailInput = signUp_EmailInput.getText();
        String passwordInput = signUp_PasswordInput.getText();
        String adminCodeInput = signUp_AdminCodeInput.getText();
        // Sending the input for validation
        if (validator.validateEmail(emailInput, signUp_EmailErrorObject, ALLOWED_EMAIL_STRING)
                && validator.validatePassword(passwordInput, signUp_PasswordErrorObject, ALLOWED_PASSWORD_STRING)
                && authenticator.adminCodeAuthentication(adminCodeInput, signUp_AdminCodeErrorObject)) {
            try {
                userManager.addUser(emailInput, passwordInput);
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
     * Handles the actions associated with the help menu.
     *
     * This method is triggered when the F1 key is pressed. It creates a help menu
     * and displays it to the user.
     *
     * @param event the KeyEvent that triggered this method invocation
     * @throws IOException if an I/O error occurs during the navigation process
     */
    @SuppressWarnings("unused")
    @FXML
    private void handleHelp(KeyEvent event) {
        if (event.getCode() == KeyCode.F1) {
            try {
                App.createHelp();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Handles the actions associated with the help menu.
     *
     * This method is triggered when the help button is clicked. It creates a help
     * menu and displays it to the user.
     *
     * @param event the ActionEvent that triggered this method invocation
     * @throws IOException if an I/O error occurs during the navigation process
     */
    @SuppressWarnings("unused")
    @FXML
    private void handleHelpButton(ActionEvent event) {
        try {
            App.createHelp();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Handles the actions associated with sending back the help menu.
     *
     * This method is triggered when the send back button is clicked in the help
     * menu.
     * It hides the expanded help menu and shows the contracted help menu.
     *
     * @param event the ActionEvent that triggered this method invocation
     */
    @SuppressWarnings("unused")
    @FXML
    private void handleHelpSendBack(ActionEvent event) {
        helpMenuExpanded.setVisible(false);
        helpMenuContracted.setVisible(true);
    }

    /**
     * Handles the actions associated with the help menu for adding a patient.
     *
     * This method is triggered when the "Add Patient" option is selected in the
     * help menu. It displays the help information for adding a patient, including
     * the title and body of the help text.
     *
     * @param event the ActionEvent that triggered this method invocation
     */
    @SuppressWarnings("unused")
    @FXML
    private void handleHelpMenu_AddPatient(ActionEvent event) {
        hme_Title.setText(help.getTitle("addPatient"));
        hme_Body.setText(help.getBody("addPatient"));
        try {
            helpMenuContracted.setVisible(false);
            helpMenuExpanded.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Handles the actions associated with the help menu for adding a user.
     * 
     * This method is triggered when the "Add User" option is selected in the help
     * menu. It displays the help information for adding a user, including the title
     * and body of the help text.
     * 
     * @param event the ActionEvent that triggered this method invocation
     */
    @SuppressWarnings("unused")
    @FXML
    private void handleHelpMenu_AddUser(ActionEvent event) {
        hme_Title.setText(help.getTitle("addUser"));
        hme_Body.setText(help.getBody("addUser"));
        try {
            helpMenuContracted.setVisible(false);
            helpMenuExpanded.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Handles the actions associated with the help menu for removing a patient.
     * 
     * This method is triggered when the "Remove Patient" option is selected in the
     * help menu. It displays the help information for removing a patient, including
     * the title and body of the help text.
     * 
     * @param event the ActionEvent that triggered this method invocation
     */
    @SuppressWarnings("unused")
    @FXML
    private void handleHelpMenu_RemovePatient(ActionEvent event) {
        hme_Title.setText(help.getTitle("removePatient"));
        hme_Body.setText(help.getBody("removePatient"));
        try {
            helpMenuContracted.setVisible(false);
            helpMenuExpanded.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Handles the actions associated with the help menu for viewing a patient.
     * 
     * This method is triggered when the "View Patient" option is selected in the
     * help menu. It displays the help information for viewing a patient, including
     * the title and body of the help text.
     * 
     * @param event the ActionEvent that triggered this method invocation
     */
    @SuppressWarnings("unused")
    @FXML
    private void handleHelpMenu_ViewPatient(ActionEvent event) {
        hme_Title.setText(help.getTitle("viewPatient"));
        hme_Body.setText(help.getBody("viewPatient"));
        try {
            helpMenuContracted.setVisible(false);
            helpMenuExpanded.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @SuppressWarnings("unused")
    @FXML
    private void handleBack(ActionEvent event) {
        App.back();
    }

    @SuppressWarnings("unused")
    @FXML
    private void handleHelpBack(ActionEvent event) {
        App.backHelp();
    }

    @SuppressWarnings("unused")
    @FXML
    private void handleMainSendBack(ActionEvent event) {
        try {
            App.alterScene("MainMenu", 1500, 750);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Handles the actions associated with selecting a patient from the patient
     * table.
     * 
     * This method is triggered when a patient is selected from the patient table.
     * It retrieves the selected patient's information and updates the corresponding
     * UI elements to display the patient's details.
     * 
     * @see Patient
     * @see PatientManager
     */
    @SuppressWarnings("unused")
    @FXML
    private void handleSelectedPatient() {
        Patient selectedPatient = mm_PatientTable.getSelectionModel().getSelectedItem();
        int num = mm_PatientTable.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        mm_PatientID.setText("(" + String.valueOf(selectedPatient.getPatientID()) + ")");
        mm_PatientName.setText(selectedPatient.getName());
        mm_PatientGender.setText(selectedPatient.getGender());
        mm_PatientAge.setText(String.valueOf(selectedPatient.getAge()));
        mm_PatientJoinDate.setText(selectedPatient.formatDate(selectedPatient.getJoinDate()));
        mm_ConditionText.setText(selectedPatient.getConditions());
        mm_DescriptionText.setText(selectedPatient.getDescriptionOfEvent());
        mm_MedicationText.setText(selectedPatient.getMedication());
        mm_MealChoice_MainMeal.setImage(selectedPatient.getMainMeal());
        mm_MealChoice_SecondaryMeal.setImage(selectedPatient.getSecondaryMeal());
        mm_MealChoice_TertiaryMeal.setImage(selectedPatient.getTertiaryMeal());
        mm_EstimatedDepartureText.setText(selectedPatient.formatDate(selectedPatient.getEstDepartureDate()));
        mm_PatientImage.setImage(selectedPatient.getPatientImage());
    }

    /**
     * Initializes the patient information displayed in the main menu.
     * 
     * This method is called during the initialization of the application and is
     * responsible for retrieving the information of the last patient added to the
     * system and displaying it in the main menu.
     * 
     * @see Patient
     * @see PatientManager
     */
    private void handleInitPatient() {
        Patient selectedPatient = patientManager.getPatient(patientManager.getSize());
        mm_PatientID.setText("(" + String.valueOf(selectedPatient.getPatientID()) + ")");
        mm_PatientName.setText(selectedPatient.getName());
        mm_PatientGender.setText(selectedPatient.getGender());
        mm_PatientAge.setText(String.valueOf(selectedPatient.getAge()));
        mm_PatientJoinDate.setText(selectedPatient.formatDate(selectedPatient.getJoinDate()));
        mm_ConditionText.setText(selectedPatient.getConditions());
        mm_DescriptionText.setText(selectedPatient.getDescriptionOfEvent());
        mm_MedicationText.setText(selectedPatient.getMedication());
        mm_MealChoice_MainMeal.setImage(selectedPatient.getMainMeal());
        mm_MealChoice_SecondaryMeal.setImage(selectedPatient.getSecondaryMeal());
        mm_MealChoice_TertiaryMeal.setImage(selectedPatient.getTertiaryMeal());
        mm_EstimatedDepartureText
                .setText(selectedPatient.formatDate(selectedPatient.getEstDepartureDate()));
        mm_PatientImage.setImage(selectedPatient.getPatientImage());
    }

    private boolean validatePatient() {
        return validator.validateAddPatient(ap_NameInput.getText(), ap_SurnameInput.getText(),
                ap_ConditionInput.getText(), ap_DescriptionOfEventInput.getText(), ap_MedicationInput.getText(),
                ap_AdmissionTime.getText(), ap_AgeInput.getText());
    }

    /**
     * Adds a new patient to the system.
     * 
     * This method is triggered when the "Add Patient" button is clicked. It
     * retrieves the patient's information from the input fields, validates the
     * data, and then adds the patient to the system if the data is valid.
     * 
     * @param event the ActionEvent that triggered this method invocation
     * @see Patient
     * @see PatientManager
     */
    @SuppressWarnings("unused")
    @FXML
    private void addPatient(ActionEvent event) {
        if (!validatePatient()) {
            return;
        }
        if (ap_GenderSelect.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Gender cannot be empty!");
            return;
        }
        if (ap_MealChoiceSelect.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Meal choice cannot be empty!");
            return;
        }
        if (ap_AdmissionDateSelect.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Admission date cannot be empty!");
            return;
        }
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

    @SuppressWarnings("unused")
    @FXML
    private void handleAddPatientMenuOpen(ActionEvent event) {
        try {
            App.alterScene("AddPatient", 840, 700);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Handles the actions associated with removing a patient from the system.
     * 
     * This method is triggered when the "Remove Patient" button is clicked. It
     * retrieves the patient's ID and admin code from the input fields, validates
     * the admin code, and then removes the patient with the specified ID from the
     * system if the admin code is valid.
     * 
     * @param event the ActionEvent that triggered this method invocation
     * @see PatientManager
     */
    @SuppressWarnings("unused")
    @FXML
    private void handleRemovePatient(ActionEvent event) {
        int patientIDInput = Integer.parseInt(rp_PatientIDInput.getText());
        String adminCodeInput = rp_AdminCodeInput.getText();
        if (!authenticator.adminCodeAuthentication(adminCodeInput, rp_AdminCodeErrorObject)) {
            return;
        }
        try {
            patientManager.removePatient(patientIDInput);
            handleMainSendBack(event);
        } catch (Exception e) {
            System.out.println(e);
        }
        /*
         * This code can be used to delete the patient that is currently selected in the
         * list of patients. However it removes the whole RemovePatient flow.
         * 
         * Patient selectedPatient =
         * mm_PatientTable.getSelectionModel().getSelectedItem();
         * int num = mm_PatientTable.getSelectionModel().getSelectedIndex();
         * if ((num - 1) < -1) {
         * return;
         * }
         * try {
         * patientManager.removePatient(selectedPatient.getPatientID());
         * } catch (Exception e) {
         * System.out.println(e);
         * }
         */
    }

    @SuppressWarnings("unused")
    @FXML
    private void handleRemovePatientMenuOpen(ActionEvent event) {
        try {
            App.alterScene("RemovePatient", 840, 700);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // HEHE SPOOKY WEIRD METHOD
    @SuppressWarnings("unused")
    @FXML
    private void massAddPatients(ActionEvent event) {
        patientManager.massAddPatients();
    }
}
