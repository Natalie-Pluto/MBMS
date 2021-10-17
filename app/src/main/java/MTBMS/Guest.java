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
    //     You should use this method to accept further the user's input, create suitable CLI to interact with the user.
    //     It's kinda like a main method for guest class
    //     */
    public void guestService(String identity) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Fancy Cinemas!");
        System.out.println("Please choose service");
        if (identity.equals("G")){
            //Guests may only want to filter movies, so they may skip the logging process
            System.out.println("1. Log in        2. Sign up      3. Skip");
            String  input1 = input.next();
            switch (input1){
                case "1":
                    System.out.println("Please enter your account number");
                    //TODO: create a users instance from users database to check users info
                    String accountNumber = input.next();
                    System.out.println("Please enter your password");
                    String accountPassword = input.next();
                    break;

                case "2":
                    System.out.println("Please create your account number");
                    String newAccountNumber = input.next();
                    System.out.println("Please create your account password");
                    String newAccountPassword = input.next();
                    break;

                case "3":
                    System.out.println("Please choose service");
                    System.out.println("1.Filter movies        2.Book a movie");
                    String input2 = input.next();
                    switch (input2){
                        case "1":
                            //TODO: list all the movie names and ask the user to choose
                            //Movies movies = new Movies()；
                            System.out.println("Please enter a number to filter a movie");
                            int filterMovieNumber = input.nextInt();
                            filterMovies(filterMovieNumber);
                            //TODO: list a specific movie with its info
                            System.out.println("Please choose a time");
                            break;

                        case "2":
                            System.out.println("Please enter the movie name or movie number");
                            String movieName = input.next();
                            System.out.println("Please enter the cinema name");
                            String cinemaName = input.next();
                            book(movieName, cinemaName);
                            break;

                        default:
                            System.err.println("Please enter a correct number");
                    }

                default:
                    System.err.println("Please enter a valid number");
            }


        }else if (identity.equals("C")){
            System.out.println("Please choose the service");
            System.out.println("1.Filter movies     2.Book a movie");
            String customerInput1 = input.next();
            switch (customerInput1){
                case "1":
                    //TODO: list
                    int filterMovieNumber = input.nextInt();
                    filterMovies(filterMovieNumber);
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
    public void filterMovies(int filterType) {
        System.out.println("");
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
    User's setting is an optional feature (not specifically addressed in the assignment spec.
    It seems like a feature that we can be designed by us.
    I will check with tutor next week.
    Just ignore it for sprint 1.
    */

    public void setSettings(String settings) {
        this.settings = settings;
        // TODO
    }
}
