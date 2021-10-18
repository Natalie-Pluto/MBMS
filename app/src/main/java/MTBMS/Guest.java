package MTBMS;


import java.sql.SQLOutput;
import java.util.Scanner;

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
        Scanner input = new Scanner(System.in);
        if (identity.equals("G")){
            // Guests can only view movies
            // They can choose to filter movies
            System.out.println("===============================");
            System.out.println("Enter 1 for \"Filter Movies\"");
            System.out.println("===============================\n");
            System.out.println("<<Upcoming Movies!>>");
            // TODO: List all the upcoming Movies & times then ask user to filter movies of their choice
            System.out.println("\n======================================");
            System.out.println("You can sign up to book your tickets!");
            System.out.println("Enter 2 for \"Sign up\"");
            System.out.println("======================================");
            String  input1 = input.next();
            if (input1.equals("1")) {
                filterMovies();
            } else if (input1.equals("2")) {
                System.out.println("Please create your username:");
                String newAcc = input.nextLine();
                // TODO: check if the username existed already
                System.out.println("Please create your password:");
                // TODO: the password input is hidden by showing asterisk symbol (*) or
                //  by hiding the password text completely (for text or console UI)
                String newPw = input.nextLine();
                BookingSystem user = new BookingSystem(newAcc, newPw, "C");
                user.signUp(newAcc, newPw);
            }

        }else if (identity.equals("C")){
            System.out.println("Please choose the service");
            System.out.println("1.Filter movies     2.Book a movie");
            String customerInput1 = input.next();
            switch (customerInput1){
                case "1":
                    //TODO: list
                    filterMovies();
                    break;

                case "2":
                    break;

                default:
                    System.err.println("Please enter a correct number");
            }
        }

        //Ask users to continue using or close the service
        System.out.println("Do you want to go back to the filter page?");
        //TODO
    }

    // This method will call method in movie class.
    // It will filter and display the movies up to user's choice.
    // Both guest and customer can use this service.
    public void filterMovies() {
        // TODO: Ask user how they wanna filter the movies and filter the movies of their choice
    }

    // This method will call methods in movie class
    // It will allow user to book movie tickets.
    // Note: only customer can use this service.
    public void book (String movieName, String cinemaName) {
        System.out.println("");
        Scanner scan = new Scanner(System.in);
        int paymentType = scan.nextInt();
        if(checkPayment(paymentType)){

        }else System.out.println("");
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
