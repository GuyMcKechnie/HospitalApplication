package ha.hospitalapplication;

import java.time.LocalDateTime;

/**
 *
 * @author mckec
 */
public class Patient {

    private String patientID;
    private String firstName;
    private String surname;
    private String name;
    private int age;
    private String conditions;
    private String descriptionOfEvent;
    private LocalDateTime joinDate;
    private LocalDateTime estimatedDepartureDate;
    private int mealChoice;
    private String medication;

    /**
     * Represents a patient in the hospital application.
     * 
     * @param patientID
     * @param joinDate
     * @param firstName
     * @param surname
     * @param conditions
     * @param descriptionOfEvent
     * @param age
     * @param mealChoice
     * @param medication
     */
    public Patient(String patientID, String firstName, String surname, int age, String conditions,
            String descriptionOfEvent, LocalDateTime joinDate, int mealChoice, String medication) {
        this.patientID = patientID;
        this.firstName = firstName;
        this.surname = surname;
        this.age = age;
        this.conditions = conditions;
        this.descriptionOfEvent = descriptionOfEvent;
        this.joinDate = joinDate;
        this.mealChoice = mealChoice;
        this.medication = medication;
    }

    /**
     *
     * @return
     */
    public String getPatientID() {
        return patientID;
    }

    /**
     * This method creates the patient's full name by combining their first name and
     * surname with a space
     */
    public void setName() {
        this.name = firstName + " " + surname;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public int getAge() {
        return age;
    }

    /**
     *
     * @return
     */
    public String getConditions() {
        return conditions;
    }

    /**
     *
     * @return
     */
    public String getDescriptionOfEvent() {
        return descriptionOfEvent;
    }

    /**
     *
     * @return
     */
    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    /**
     * This is a rudimentary way of getting the departure date.
     */
    public void setEstDepartureDate() {
        this.estimatedDepartureDate = getJoinDate().plusDays(15);
    }

    /**
     *
     * @return
     */
    public LocalDateTime getEstDepartureDate() {
        return estimatedDepartureDate;
    }

    /**
     *
     * @return
     */
    public int getMealChoice() {
        return mealChoice;
    }

    /**
     *
     * @return
     */
    public String getMedication() {
        return medication;
    }

}
