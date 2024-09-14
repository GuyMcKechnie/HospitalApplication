package ha.hospitalapplication;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mckec
 */
public class PatientManager {

    private Patient[] pArr = new Patient[100];
    private int size = 0;

    /**
     * This is the constructor for the patient manager.
     */
    public PatientManager() {

        try {
            ResultSet resultSet = Controller.databaseManager.query("SELECT * FROM tblPatients");
            Patient patientObject;
            while (resultSet.next()) {
                patientObject = new Patient(resultSet.getInt("PatientID"), resultSet.getString("firstName"),
                        resultSet.getString("surname"), resultSet.getString("gender"), resultSet.getInt("age"),
                        resultSet.getString("condition"),
                        resultSet.getString("descriptionOfEvent"), resultSet.getTimestamp("joinDate").toLocalDateTime(),
                        resultSet.getInt("mealChoice"), resultSet.getString("medication"));
                pArr[size] = patientObject;
                size++;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void removePatient(int patientID) {
        try {
            Controller.databaseManager.update("DELETE * FROM tblPatients WHERE PatientID = '" + patientID + "';");
            JOptionPane.showMessageDialog(null, "Patient (ID = " + patientID + ") has been removed!");
        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Patient could not be removed!");
        }
    }

    /**
     *
     * @param patientID
     * @param firstName
     * @param surname
     * @param age
     * @param conditions
     * @param descriptionOfEvent
     * @param joinDate
     * @param mealChoice
     * @param medication
     */
    public boolean addPatient(String firstName, String surname, String gender, int age, String conditions,
            String descriptionOfEvent, String joinDate, int mealChoice, String medication) {
        String query = "INSERT INTO tblPatients (firstName, surname, gender, age, condition, descriptionOfEvent, joinDate, mealChoice, medication) VALUES ('"
                + firstName + "', '" + surname + "', '" + gender + "', " + age + ", '" + conditions + "', '"
                + descriptionOfEvent + "', '" + joinDate + "', " + mealChoice + ", '" + medication + "')";
        try {
            Controller.databaseManager.update(query);
            JOptionPane.showMessageDialog(null, "Patient has been added to the database!");
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Patient could not be added to the database!");
            return false;
        }
    }

    /*
     * For development purposes
     */
    public void massAddPatients() {
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(
                    new File("src\\main\\resources\\ha\\hospitalapplication\\Patients.txt"));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(line);
                try (Scanner lineScanner = new Scanner(line).useDelimiter("#")) {
                    String firstName = lineScanner.next();
                    String surname = lineScanner.next();
                    String gender = lineScanner.next();
                    int age = lineScanner.nextInt();
                    String conditions = lineScanner.next();
                    String descriptionOfEvent = lineScanner.next();
                    String joinDate = lineScanner.next();
                    int mealChoice = lineScanner.nextInt();
                    String medication = lineScanner.next();
                    addPatient(firstName, surname, gender, age, conditions, descriptionOfEvent, joinDate, mealChoice,
                            medication);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    /**
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * @param position
     * @return
     */
    public Patient getPatient(int patientID) {
        try {
            Controller.databaseManager.query("SELECT * FROM tblPatients WHERE patientID = '" + patientID + "';");
            return pArr[searchPatient(patientID)];
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    private int searchPatient(int patientID) {
        for (int i = 0; i < size; i++) {
            if (pArr[i].getPatientID() == patientID) {
                return i;
            }
        }
        return -1;
    }

    public static ObservableList<Patient> getPatientList() {
        ObservableList<Patient> patientList = FXCollections.observableArrayList();
        Patient patientObject;
        try {
            ResultSet resultSet = Controller.databaseManager.query("SELECT * from tblPatients");
            while (resultSet.next()) {
                patientObject = new Patient(resultSet.getInt("PatientID"), resultSet.getString("firstName"),
                        resultSet.getString("surname"), resultSet.getString("gender"), resultSet.getInt("age"),
                        resultSet.getString("condition"),
                        resultSet.getString("descriptionOfEvent"), resultSet.getTimestamp("joinDate").toLocalDateTime(),
                        resultSet.getInt("mealChoice"), resultSet.getString("medication"));
                patientList.add(patientObject);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return patientList;
    }

    public String combineDateTime(LocalDate date, String time) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " " + time + ":00";
    }
}
