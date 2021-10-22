package MTBMS;
import databaseutility.*;


import javax.print.attribute.AttributeSetUtilities;
import java.rmi.ServerError;
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
    Database dbInstance = new Database("jdbc:postgresql://ls-d4381878930280384f33af335289e24c73224a04.c0apyqxz8x8m.ap-southeast-2.rds.amazonaws.com:5432/postgres",
                                      "dbmasteruser","A>XV>D*7r-V{y_wL}}I{+U=8zEtj1*T<");

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
        if (identity.equals("G")){
            // Guests can only view movies
            // They can choose to filter movies


            System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
            Scanner input = new Scanner(System.in);
            String  input1 = input.next();
            if (input1.equals("1")) {
                System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
                //TODO: Implement filter method
                filterMovies();
                System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
            } else if (input1.equals("2")) {
                System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
                BookingSystem user = new BookingSystem();
                user.signUp("C");
                System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
            } else {
                System.out.println("============================================");
                System.out.println(RED_BOLD + "Wrong Input! (｡´︿`｡)" + ANSI_RESET);
                System.out.println("============================================\n");
                Thread.sleep(2000);
                System.out.println(ANSI_PURPLE + "Returning...\n" + ANSI_RESET);
                Thread.sleep(2000);
                guestService("G");
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
            Scanner input1 = new Scanner(System.in);
            String customerInput1 = input1.next();
            switch (customerInput1){
                case "1":
                    //TODO: Implement filter method
                    System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
                    filterMovies();
                    System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
                    break;
                case "2":
                    //TODO: Implement book method
                    System.out.println("Please enter the movie name that you wish to book");
                    Scanner input2 = new Scanner(System.in);
                    String movieName = input2.next();
                    System.out.println("Please enter the cinema name that you wish to go to");
                    Scanner input3 = new Scanner(System.in);
                    String cinemaName = input3.next();
                    book(movieName, cinemaName);
                    break;

                default:
                    System.out.println("============================================");
                    System.out.println(RED_BOLD + "Wrong Input! (｡´︿`｡)" + ANSI_RESET);
                    System.out.println("============================================\n");
                    Thread.sleep(2000);
                    System.out.println(ANSI_PURPLE + "Returning...\n" + ANSI_RESET);
                    Thread.sleep(2000);
                    guestService("C");
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
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to filter movies by");
        System.out.println("1.Names        2.Times        3.Cinema Name        4.Classification        5.Genre");
        String filterType = input.next();
        switch (filterType){
            case "1":
                System.out.println("Please enter the name of the movie");
               // String movieName = input.next();
                break;

            case "2":
                System.out.println("");
                //
                break;

            case "3":
                System.out.println("Please enter the name of the cinema");
                //String cinemaName = input.next();
                //
                break;
            case "4":
                System.out.println("Which Classification do you want to filter?");
                //String classification = input.next();
                //
                break;

            case "5":
                System.out.println("What type of movie do you want to filter?");
                //String genre = input.next();
                //
                break;

            default:
                System.out.println("Please enter the correct number");
                filterMovies();
        }

    }

    // This method will call methods in movie class
    // It will allow user to book movie tickets.
    // Note: only customer can use this service.
    public void book (String movieName, String cinemaName) {
        System.out.println("");
        Scanner scan = new Scanner(System.in);
        int paymentType = scan.nextInt();
        if(checkPayment(paymentType)){
        //TODO
        }else System.out.println("");
    }

    // This method should be called by book( )
    // It will check if the payment is successful.
    public boolean checkPayment(int paymentType) {
        //TODO: call methods from databaseutility
        switch (paymentType){
            case 1:
                break;

            case 2:
                break;

            default:
        }
        return true;
    }

    // This method will allow customers to update their password and specific settings.
    // opt 1 for changing password
    // opt 2 for changing settings
    public void personalInfoUpdate(String opt) throws InterruptedException {//change opt from int to String
        Scanner input = new Scanner(System.in);
        switch(opt){
            case "1":
                System.out.println("Please enter your present password");
                String presentPwd = input.next();
                //TODO: check pwd


                //check new password twice to ensure customers are typing their expected password correctly
                // Hi John, I have made your pwd input hidden XD!!! You can delete this msg.
                System.out.println("Please enter your new password");
                String newPwd_1 = BookingSystem.readPwd();
                System.out.println("Please enter your new password again");
                String newPwd_2 = BookingSystem.readPwd();
                if (newPwd_1.equals(newPwd_2)){
                    //TODO: update new password to database
                    System.out.println("Password changed, please login again");
                }else {
                    System.out.println("Two Passwords do not match, please try again");
                    personalInfoUpdate(opt);
                }

                break;

            case "2":
                System.out.println("What setting do you wanna change?");
                System.out.println("1.Preferred Genre       2.Preferred Cinema      3.Preferred Classification        4.Skip");
                //TODO
                String newSetting = input.next();
                if (newSetting.equals("4")){
                    break;

                }else setSettings(newSetting);
                break;

            default:
                System.err.println("Please enter a correct number");
                personalInfoUpdate(opt);
        }
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
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";


    // Bold
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE

    // Background
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW

    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW

}