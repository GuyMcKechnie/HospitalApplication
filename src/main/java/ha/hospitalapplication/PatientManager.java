package ha.hospitalapplication;

import java.time.LocalDateTime;

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
    public void addPatient(String patientID, String firstName, String surname, int age, String conditions,
            String descriptionOfEvent, LocalDateTime joinDate, int mealChoice, String medication) {
        pArr[size] = new Patient(patientID, firstName, surname, age, conditions, descriptionOfEvent, joinDate,
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

}
