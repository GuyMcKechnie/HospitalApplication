package ha.hospitalapplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.scene.image.Image;

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
     * Represents a patient in the hospital application. It has the patientID input
     * for if there are requirements that need to suffice entire database entries.
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

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public void setDescriptionOfEvent(String descriptionOfEvent) {
        this.descriptionOfEvent = descriptionOfEvent;
    }

    public void setJoinDate(LocalDateTime joinDate) {
        this.joinDate = joinDate;
    }

    public void setMealChoice(int mealChoice) {
        this.mealChoice = mealChoice;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public void setPatientInformation(String patientInformation) {
        this.patientInformation = patientInformation;
    }

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

    public String getName() {
        setName();
        return name;
    }

    /**
     * Returns the patient's gender with the first letter capitalized.
     * 
     * @return the patient's gender
     */
    public String getGender() {
        String formattedString = this.gender.substring(0, 1).toUpperCase();
        formattedString += this.gender.substring(1);
        return formattedString;
    }

    /**
     * Returns the patient's image based on their gender.
     * 
     * @return the patient's image
     */
    public Image getPatientImage() {
        String malePatient = "/ha/hospitalapplication/male-patient-image.png";
        String femalePatient = "/ha/hospitalapplication/female-patient-image.png";
        if (getGender().equalsIgnoreCase("male")) {
            return new Image(getClass().getResource(malePatient).toExternalForm());
        } else {
            return new Image(getClass().getResource(femalePatient).toExternalForm());
        }
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

    /**
     * Formats a LocalDateTime object into a string in the format "dd/MM/yyyy @
     * HH:mm".
     * 
     * @param dateTimeObject the LocalDateTime object to be formatted
     * @return the formatted date and time string
     */
    public String formatDate(LocalDateTime dateTimeObject) {
        String formattedDate = dateTimeObject.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        formattedDate += " @ " + dateTimeObject.format(DateTimeFormatter.ofPattern("HH:mm"));
        return formattedDate;
    }

    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    /*
     * This is a rudimentary way of getting the departure date. It can be customised
     * to whatever is needed.
     */
    public LocalDateTime getEstDepartureDate() {
        return getJoinDate().plusDays(15);
    }

    public int getMealChoice() {
        return mealChoice;
    }

    public String getMedication() {
        return medication;
    }

    public String getPatientInformation() {
        setPatientInformation();
        return patientInformation;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private void setPatientInformation() {
        this.patientInformation = "(" + getPatientID() + ")\t" + getName();
    }

    /**
     * Returns a string representing the meal class based on the patient's meal
     * choice. The string contains three meal options separated by '#', which can be
     * used to retrieve the corresponding meal images.
     * 
     * @return a string representing the meal class
     */
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
}
