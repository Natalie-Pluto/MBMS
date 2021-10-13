package MTBMS;

import java.util.ArrayList;

public class BookingSystem {
    private String username;
    private String password;
    private String identity;

    BookingSystem(String username, String password, String identity) {
        this.username = username;
        this.password = password;
        this.identity = identity;
    }

    public static void main(String[] args) {
        // Greeting, then ask user to login or sign up or they can view the upcoming movies list
        login();
        signUp();
    }

    // Login will interact with User table to check the user's info
    public static void login() {
        // TODO
    }

    // Signup will interact with User table to add user's info
    // Note: Need to check the username provided, it has to be unique
    public static void signUp( ) {
        // TODO
    }

    /*
    User's setting is an optional feature (not specifically addressed in the assignment spec.
    It seems like a feature that we can be designed by us.
    I will check with tutor next week.
    Just ignore it for sprint 1.
    */

    // Get the user's specific settings from user table (discuss it in sprint 2)
    public void getSettings( ) {
        // TODO
    }

    // Update the user's specific settings from user table (discuss it in sprint 2)
    public void updateSettings (ArrayList<String> settings){
        // TODO
    }

    // Delete an user from User table.
    // This can only be done by manager.
    public void deleteUser (String username) {
        // TODO
    }

    // Add an user to User tabole.
    // It should be called by signup( ) method
    public void addUser (String userInfo) {
        // TODO
    }

    public String getIdentity() {
        return identity;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
