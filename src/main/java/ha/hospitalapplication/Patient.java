package ha.hospitalapplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author mckec
 */
public class Patient {

    private int patientID;
    private String firstName;
    private String surname;
    private String name;
    private String gender;
    private int age;
    private String conditions;
    private String descriptionOfEvent;
    private LocalDateTime joinDate;
    private LocalDateTime estimatedDepartureDate;
    private int mealChoice;
    private String medication;
    private String patientInformation;

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
    public Patient(int patientID, String firstName, String surname, String gender, int age, String conditions,
            String descriptionOfEvent, LocalDateTime joinDate, int mealChoice, String medication) {
        this.patientID = patientID;
        this.firstName = firstName;
        this.surname = surname;
        this.gender = gender;
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
    public int getPatientID() {
        return patientID;
    }

    /**
     * This method creates the patient's full name by combining their first name
     * and surname with a space
     */
    public void setName() {
        this.name = firstName + " " + surname;
    }

    /**
     *
     * @return
     */
    public String getName() {
        setName();
        return name;
    }

    public String getGender() {
        String formattedString = this.gender.substring(0, 1).toUpperCase();
        formattedString += this.gender.substring(1);
        return formattedString;
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

    public String formatDate(LocalDateTime dateTimeObject) {
        String formattedDate = dateTimeObject.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        formattedDate += " @ " + dateTimeObject.format(DateTimeFormatter.ofPattern("hh:mm"));
        return formattedDate;
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
    public LocalDateTime getEstDepartureDate() {
        return getJoinDate().plusDays(15);
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

    public String getPatientInformation() {
        setPatientInformation();
        return patientInformation;
    }

    private void setPatientInformation() {
        this.patientInformation = "(" + getPatientID() + ")\t" + getName();
    }

}
