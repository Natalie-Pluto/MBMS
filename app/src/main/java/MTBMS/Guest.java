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
    private String settings;
    private BookingSystem BS = new BookingSystem();

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
    public void guestService(String identity) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        if (identity.equals("G")){
            // Guests can only view movies
            // They can choose to filter movies
            System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
            System.out.println("===============================");
            System.out.println(PURPLE_BOLD + "Enter 1 for \"Filter Movies\"" + ANSI_RESET);
            System.out.println("===============================\n");
            System.out.println(YELLOW_BOLD_BRIGHT + "<<Upcoming Movies!>>"   + ANSI_RESET);
            // TODO: List all the upcoming Movies & times
            System.out.println("\n=================================================");
            System.out.println("You can sign up to book your tickets! (｡･ω･｡)ﾉ ");
            System.out.println(PURPLE_BOLD + "Enter 2 for \"Sign up\""  + ANSI_RESET);
            System.out.println("=================================================");
            System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
            String  input1 = input.next();
            if (input1.equals("1")) {
                System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
                //TODO: Implement filter method
                //filterMovies();
                System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
            } else if (input1.equals("2")) {
                System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
                BookingSystem user = new BookingSystem();
                //user.signUp("C");
                System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
            } else {
                System.out.println("============================================");
                System.out.println(RED_BOLD + "Wrong Input! (｡´︿`｡)" + ANSI_RESET);
                System.out.println("============================================\n");
                Thread.sleep(2000);
                System.out.println(ANSI_PURPLE + "Returning...\n" + ANSI_RESET);
                Thread.sleep(2000);
                //guestService("G");
            }
            // For Customer
        }else if (identity.equals("C")){
            System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
            System.out.println("======================================================");
            System.out.println(PURPLE_BOLD + "Enter 1 for \"Filter Movies\"   2 for \"Book Tickets\""  + ANSI_RESET);
            System.out.println("======================================================\n");
            // TODO: List all the upcoming Movies & times
            System.out.println(YELLOW_BOLD_BRIGHT + "<<Upcoming Movies!>>"   + ANSI_RESET);
            System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
            String customerInput1 = input.next();
            switch (customerInput1){
                case "1":
                    //TODO: Implement filter method
                    System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
                    //filterMovies();
                    System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
                    break;
                case "2":
                    //TODO: Implement book method
                    break;

                default:
                    System.out.println("============================================");
                    System.out.println(RED_BOLD + "Wrong Input! (｡´︿`｡)" + ANSI_RESET);
                    System.out.println("============================================\n");
                    Thread.sleep(2000);
                    System.out.println(ANSI_PURPLE + "Returning...\n" + ANSI_RESET);
                    Thread.sleep(2000);
                    //guestService("C");
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

    // Regular
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";


    // Bold
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE

    // Background
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW

    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW

}
