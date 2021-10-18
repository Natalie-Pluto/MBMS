package MTBMS;


//TODO/*
//  This class will interact with user (guest & customer)
//  It will continue the CLI for guests and provide services for them.
//  */
public class Guest {
    private String username;
    private String identity;
    public String settings;

    public Guest(String username, String identity, String settings) {
        this.username = username;
        this.identity = identity;
        this.settings = settings;
    }

    // TODO/* bookingSystem class will call this method with the user's identity type.
    //     It should be either 'G' for guest or 'C' for customer.
    //     Note: different service for guest and customer, pls refer to the asm spec.
    //     You should use this method to accept further user's input, create suitable CLI to interact with the user.
    //     It's kinda like a main method for guest class
    //     */
    public void guestService(String identity) {

    }

    // This method will call method in movie class.
    // It will filter and display the movies up to user's choice.
    // Both guest and customer can use this service.
    public void filterMovies(int filterType) {
        // TODO
    }

    // This method will call methods in movie class
    // It will allow user to book movie tickets.
    // Note: only customer can use this service.
    public void book (String movieName, String cinemaName) {
        // TODO
    }

    // This method should be called by book( )
    // It will check if the payment is successful.
    public boolean checkPayment(int paymentType) {
        // TODO
        return true;
    }

    // This method will allow customers to update their password and specific settings.
    // opt 1 for changing password
    // opt 2 for changing settings
    public void personalInfoUpdate(int opt) {
        // TODO
    }

    public String getUsername() {
        return username;
    }

    public String getIdentity() {
        return identity;
    }

    public String getSettings() {
        return settings;
    }

    /*
    User's setting is an optional feature (not specifically addressed in the assignment spec.)
    It seems like a feature that can be designed by us.
    I will check with tutor next week.
    Just ignore it for sprint 1.
    */

    public void setSettings(String settings) {
        this.settings = settings;
        // TODO
    }
}
