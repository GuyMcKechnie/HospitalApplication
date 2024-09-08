package ha.hospitalapplication;

import java.time.LocalDateTime;

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

    public String getPatientID() {
        return patientID;
    }

    /*
     * This method creates the patient's full name by combining their first name and
     * surname with a space
     */
    public void setName() {
        this.name = firstName + " " + surname;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getConditions() {
        return conditions;
    }

    public String getDescriptionOfEvent() {
        return descriptionOfEvent;
    }

    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    /*
     * This is a rudementary way of getting the departure date.
     */
    public void setEstDepartureDate() {
        this.estimatedDepartureDate = getJoinDate().plusDays(15);
    }

    public LocalDateTime getEstDepartureDate() {
        return estimatedDepartureDate;
    }

    public int getMealChoice() {
        return mealChoice;
    }

    public String getMedication() {
        return medication;
    }

}
