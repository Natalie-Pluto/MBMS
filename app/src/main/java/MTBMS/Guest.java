package MTBMS;
import databaseutility.*;
import movieManagement.ListNowShowing;

import java.util.regex.*;


import java.util.Scanner;

//TODO/*
//  This class will interact with user (guest & customer)
//  It will continue the CLI for guests and provide services for them.
//  */
public class Guest {
    private String username;
    private String identity;
    private String settings;
    private static Database dbInstance = new Database("jdbc:postgresql://localhost:5432/postgres", "postgres", "0000");

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
    public void guestService() throws InterruptedException {
        customerHomePage();
        Scanner input1 = new Scanner(System.in);
        String customerInput1 = input1.next();
        switch (customerInput1) {
            case "1":

                break;
            case "2":
                book();
                break;

            default:
                System.out.println("============================================");
                System.out.println(RED_BOLD + "Wrong Input! (｡´︿`｡)" + ANSI_RESET);
                System.out.println("============================================\n");
                Thread.sleep(2000);
                System.out.println(ANSI_PURPLE + "Returning...\n" + ANSI_RESET);
                Thread.sleep(2000);
                guestService();

                //Ask users to continue using or close the service
                continueService();

        }
    }

    public static void customerHomePage() {
        BookingSystem.seperator();
        System.out.println("======================================================");
        System.out.println(PURPLE_BOLD + "Enter 1 for \"Now Showing\"   Enter 2 for \"Filter\"" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter 3 for \"Booking\"" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter movie name for more details" + ANSI_RESET);
        System.out.println("======================================================\n");
        System.out.println(YELLOW_BOLD_BRIGHT + "<<Upcoming Movies!>>" + ANSI_RESET);
        GetUpcomingMovies.getUpcomingMovies(dbInstance);
        BookingSystem.seperator();
    }

    public static void nowShowingCus() {
        System.out.println("=====================================================================");
        System.out.println(PURPLE_BOLD + "   Enter 5 for \"Filter\"" + "      " + "Enter movie name for more details" + ANSI_RESET);
        System.out.println("=====================================================================\n");
        System.out.println(YELLOW_BOLD_BRIGHT + "<<Now Showing!>>"   + ANSI_RESET);
        ListNowShowing.listNowShowing(dbInstance);
    }

    public void continueService() throws InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
        System.out.println("======================================================");
        System.out.println(PURPLE_BOLD + "Enter 1 for \"Return to the main page\"   2 for \"Log out\""  + ANSI_RESET);
        System.out.println("======================================================\n");
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
        String service = input.nextLine();
        switch (service) {
            case "1":
                guestService();
                break;

            case "2":
                BookingSystem.logOut();
                break;

            default:
                System.out.println("Please enter the correct number");
                continueService();
            }
    }
    // This method will call method in movie class.
    // It will filter and display the movies up to user's choice.
    // Both guest and customer can use this service.
    public void filterMovies() {
        // TODO: Ask user how they wanna filter the movies and filter the movies of their choice
        Scanner input = new Scanner(System.in);
        System.out.println("Filter movies by");
        System.out.println("1.Name        2.Screen Size        3.Cinema Name        4.Classification        5.Genre");
        String filterType = input.nextLine();
        switch (filterType){
            case "1":
                System.out.println("Please enter the name of the movie");
                String movieName = input.nextLine();
                if (!CheckIfMovieExists.checkIfMovieExists(dbInstance,movieName)){
                    System.err.println("This movie does not exist");
                    filterMovies();
                }else {
                    System.out.println(movieName + " list is shown below");
                    FilterMovieName.filterMovieName(dbInstance, movieName);
                }
                break;

            case "2":
                System.out.println("Please enter the size of the screen you want to filter");
                String screenType = input.nextLine();
                FilterScreenSize.filterScreenSize(dbInstance, screenType);
                break;

            /*case "3":
                System.out.println("Please enter the name of the cinema");
                String cinemaName = input.nextLine();
                FilterCinema.filterCinema(dbInstance, cinemaName);
                break;
            case "4":
                System.out.println("Which Classification do you want to filter?");
                String classification = input.nextLine();
                FilterClassification.filterClassification(dbInstance, classification);
                break;

            case "5":
                System.out.println("What type of movie do you want to filter?");
                String genre = input.nextLine();
                FilterGenre.filterGenre(dbInstance, genre);
                break;
            */
            default:
                System.out.println("Please enter the correct number");
                filterMovies();
        }

    }

    // This method will call methods in movie class
    // It will allow user to book movie tickets.
    // Note: only customer can use this service.

    public String checkMovieName(){
        Scanner input = new Scanner(System.in);
        boolean not_exist = true;
        while(not_exist){
            System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
            System.out.println("======================================================");
            System.out.println("Please enter the movie name that you wish to book");
            System.out.println("======================================================\n");
            System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
            String movieName = input.nextLine();
            if (!CheckIfMovieExists.checkIfMovieExists(dbInstance, movieName)) {
                System.err.println("Movie " + movieName + " does not exist, please enter the correct movie name");
            }else {
                return movieName;
            }
        }
        return "N/A";
    }

    public String checkCinemaName(){
        Scanner input = new Scanner(System.in);
        boolean not_exist = true;
        while(not_exist){
            System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
            System.out.println("======================================================");
            System.out.println("Please enter the cinema name that you wish to book");
            System.out.println("======================================================\n");
            System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
            String cinemaName = input.nextLine();
            if (!CheckIfCinemaExists.checkIfCinemaExists(dbInstance, cinemaName)) {
                System.err.println("Cinema " + cinemaName + " does not exist, please enter the correct cinema name");
            }else {
                return cinemaName;
            }
        }
        return "N/A";
    }
    public void book() {
        Scanner input = new Scanner(System.in);
        String movieName = checkMovieName();
        String cinemaName = checkCinemaName();

        System.out.println("Which payment do you want to make?");
        System.out.println("1.Credit Card       2.Gift Card");
        int paymentType = input.nextInt();
        if (checkPayment(paymentType)){
            //TODO
            UpdateSeats updateSeats = new UpdateSeats();
            //updateSeats.updateSeats(dbInstance, cinemaName, movieName,  );
        }else {
            System.out.println("You have successfully booked a movie!");

        }
    }

    public int getPaymentType() {
        Scanner input = new Scanner(System.in);
        System.out.println("Which payment do you want to make?");
        System.out.println("1.Credit Card       2.Gift Card");
        int paymentType = input.nextInt();
        switch (paymentType){
            case 1:
                return 1;

            case 2:
                return 2;

            default:
                System.out.println("Please enter a correct number");
                getPaymentType();
        }
        return 0;
    }





    // This method should be called by book( )
    // It will check if the payment is successful.
    public boolean checkPayment(int paymentType) {
        //call methods from databaseutility
        Scanner input = new Scanner(System.in);
        switch (paymentType){
            case 1://card
                while(cardNumberCheck()){
                    System.out.println("Wrong card number, Please enter again");
                    //
                }
                break;

            case 2://giftcard
                String pattern = "\\d{14}[GX]";
                System.out.println("Please enter your gift card number");
                String giftCardNum = input.nextLine();
                if (!Pattern.matches(pattern, giftCardNum)){
                    System.out.println("Wrong gift card number, it should be a 16-digit with suffix GX");
                    checkPayment(paymentType);
                    break;
                }

                if (RedeemedCheck.giftCardRedeemed(dbInstance, giftCardNum)){
                    RedeemingGiftCard.redeemGiftCard(dbInstance, giftCardNum);
                    break;

                }else {
                    System.out.println("This card does not exist or has been redeemed");
                    checkPayment(paymentType);
                    break;
                }

            default:
                System.err.println("Please enter the correct number");
                checkPayment(paymentType);
        }
        return false;
    }

    public boolean cardNumberCheck(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your card number");
        String cardNum = input.nextLine();
        if (!CheckIfCardExist.checkIfCardExist(dbInstance, cardNum)){
            System.err.println("Wrong card number");
            cardNumberCheck();
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
                System.out.println("Please enter your new password");
                String newPwd_1 = BookingSystem.readPwd();
                System.out.println("Please enter your new password again");
                String newPwd_2 = BookingSystem.readPwd();
                if (newPwd_2.equals(presentPwd) || newPwd_1.equals(presentPwd)) {
                    System.out.println("New password can not be the same as your present one");
                    personalInfoUpdate(opt);
                }else if (newPwd_1.equals(newPwd_2)){
                    //update new password to database
                    ChangingUserPassword.changeUserPassword(dbInstance, username, newPwd_1);
                    System.out.println("Password changed, please login again");
                    guestService();
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
        public static final String ANSI_PURPLE = "\u001B[35m";


        // Bold
        public static final String RED_BOLD = "\033[1;31m";    // RED
        public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE

        // Background
        public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW


        // Bold High Intensity
        public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
        public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE

}