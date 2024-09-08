package ha.hospitalapplication;

import java.time.LocalDateTime;

public class PatientManager {

    private Patient[] pArr = new Patient[100];
    private int size = 0;

    public PatientManager() {

    }

    public void addPatient(String patientID, String firstName, String surname, int age, String conditions,
            String descriptionOfEvent, LocalDateTime joinDate, int mealChoice, String medication) {
        pArr[size] = new Patient(patientID, firstName, surname, age, conditions, descriptionOfEvent, joinDate,
                mealChoice, medication);
        size++;
    }

    public int getSize() {
        return size;
    }

    public Patient getPatient(int position) {
        return pArr[position];
    }

}
