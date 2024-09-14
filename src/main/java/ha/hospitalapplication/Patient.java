package ha.hospitalapplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.scene.image.Image;

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
     * @param joinDate
     * @param firstName
     * @param surname
     * @param conditions
     * @param descriptionOfEvent
     * @param age
     * @param mealChoice
     * @param medication
     */
    public Patient(String firstName, String surname, String gender, int age, String conditions,
            String descriptionOfEvent, LocalDateTime joinDate, int mealChoice, String medication) {
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

    public Image getPatientImage() {
        String malePatient = "/ha/hospitalapplication/male-patient-image.png";
        String femalePatient = "/ha/hospitalapplication/female-patient-image.png";
        if (getGender().equalsIgnoreCase("male")) {
            return new Image(getClass().getResource(malePatient).toExternalForm());
        } else {
            return new Image(getClass().getResource(femalePatient).toExternalForm());
        }
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
        formattedDate += " @ " + dateTimeObject.format(DateTimeFormatter.ofPattern("HH:mm"));
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

    private String getMealClass() {
        String mealClass = "";
        switch (this.mealChoice) {
            case 1:
                mealClass += "/ha/hospitalapplication/cake-slice.png" + "#";
                mealClass += "/ha/hospitalapplication/hamburger.png" + "#";
                mealClass += "/ha/hospitalapplication/hot-dog.png";
                return mealClass;
            case 2:
                mealClass += "/ha/hospitalapplication/hot-soup.png" + "#";
                mealClass += "/ha/hospitalapplication/ice-cream.png" + "#";
                mealClass += "/ha/hospitalapplication/lasagna.png";
                return mealClass;
            case 3:
                mealClass += "/ha/hospitalapplication/noodles.png" + "#";
                mealClass += "/ha/hospitalapplication/sandwich.png" + "#";
                mealClass += "/ha/hospitalapplication/spaghetti.png";
                return mealClass;
            default:
                return "";
        }
    }

    public Image getMainMeal() {
        String mainMeal = getMealClass().split("#")[0];
        return new Image(getClass().getResource(mainMeal).toExternalForm());
    }

    public Image getSecondaryMeal() {
        String secondaryMeal = getMealClass().split("#")[1];
        return new Image(getClass().getResource(secondaryMeal).toExternalForm());
    }

    public Image getTertiaryMeal() {
        String tertiaryMeal = getMealClass().split("#")[2];
        return new Image(getClass().getResource(tertiaryMeal).toExternalForm());
    }

}
