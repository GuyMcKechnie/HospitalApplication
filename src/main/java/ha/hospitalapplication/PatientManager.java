package ha.hospitalapplication;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    public void addPatient(int patientID, String firstName, String surname, String gender, int age, String conditions,
            String descriptionOfEvent, LocalDateTime joinDate, int mealChoice, String medication) {
        pArr[size] = new Patient(patientID, firstName, surname, gender, age, conditions, descriptionOfEvent, joinDate,
                mealChoice, medication);
        size++;
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

}
