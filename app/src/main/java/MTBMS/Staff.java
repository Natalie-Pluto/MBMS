package MTBMS;

import java.util.ArrayList;

// TODO/*
//  This class will interact with user (staff & manager)
//  It will continue the CLI for staffs and managers services for them.
//   */
public class Staff {
    String username;
    String identity;
    String settings;

    public Staff(String username, String identity, String settings) {
        this.username = username;
        this.identity = identity;
        this.settings = settings;
    }

    // TODO/* bookingSystem class will call this method with the user's identity type.
    //      It should be either 'S' for staff or 'M' for manager.
    //      Note: different service for staff and manager, pls refer to the asm spec.
    //      You should use this method to accept further the user's input, create suitable CLI to interact with the user.
    //      It's kinda like a main method for staff class
    //  */

    public void staffService(String identity) {
        // TODO
    }

    // This method will allow staff and manager to update their password and specific settings.
    // opt 1 for changing password
    // opt 2 for changing setting
    public void personalInfoUpdate(int opt) {
        // TODO
    }

    // This method will call method in movie class.
    // It will filter and display the movies up to user's choice.
    public void filterMovies(int filterType) {
        // TODO
    }

    // This method will call method in bookingSystem class.
    // It will manage the staff info in the User table.
    // Note: This service can only be used by manager
    public void manageStaff(String username){
        // TODO
    }

    // This method will call method in Movies class.
    // It will manage the movie info in the Movie table.
    // Note: This service can be used both by staffs and manager
    public void manageMovie(String movieName, String cinemaName) {
        // TODO
    }

    // Pls refer to the assignment spec for the reports.
    // There are several types of reports, you can make separate methods for them.
    public void obtainReports(int opt ) {
        // TODO
    }


    public String getUsername() {
        return username;
    }

    public String getSettings() {
        return settings;
    }

    public String getIdentity() {
        return identity;
    }

    /*
   User's setting is an optional feature (not specifically addressed in the assignment spec.
   It seems like a feature that we can be designed by us.
   I will check with tutor next week.
   Just ignore it for sprint 1.
   */
    public void setSettings(String settings) {
        // TODO
        this.settings = settings;
    }
}
