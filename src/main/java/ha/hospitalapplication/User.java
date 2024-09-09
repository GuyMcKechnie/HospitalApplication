package ha.hospitalapplication;

/**
 *
 * @author mckec
 */
public class User {

    private String userID;
    private String email;
    private String password;

    /**
     *
     * @param userID
     * @param email
     * @param password
     */
    public User(String userID, String email, String password) {
        this.userID = userID;
        this.email = email;
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getUserID() {
        return this.userID;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return this.email;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return this.password;
    }
}
