package ha.hospitalapplication;

public class PatientManager {

    public static DatabaseManager databaseManager = new DatabaseManager();
    private Patient[] pArr = new Patient[100];
    private int size = 0;

    public PatientManager() {
    }

    public int getSize() {
        return size;
    }

    public Patient getPatient(int position) {
        return pArr[position];
    }

}
