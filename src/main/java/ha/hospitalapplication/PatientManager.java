package ha.hospitalapplication;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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
                + descriptionOfEvent + "', '" + joinDate.toString() + "', " + mealChoice + ", '" + medication + "')";
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
    public Patient getPatient(int position) {
        return pArr[position];
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
        return date.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " " + time + ":00";
    }
}
