package MTBMS;
import movieManagement.*;
import databaseutility.*;
import java.util.regex.*;


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

    //Database dbInstance = new Database("jdbc:postgresql://ls-d4381878930280384f33af335289e24c73224a04.c0apyqxz8x8m.ap-southeast-2.rds.amazonaws.com:5432/postgres",
    //"dbmasteruser","A>XV>D*7r-V{y_wL}}I{+U=8zEtj1*T<");

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
    public static void guestService() throws InterruptedException {
        customerHomePage();
        Scanner input1 = new Scanner(System.in);
        String customerInput1 = input1.next();
        switch (customerInput1) {
            case "1":
                nowShowingCus();
                guestService();
                break;
            case "2":
                filterMovies("U" + CupcomingFilter());
                break;
            case "3":
                book();
                break;
            case "4":
                filterMovies("S" + CshowingFilter());
                break;
            case "5":
                book();
                break;
            case "6":
                book();
                break;
            case "return":
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

        }

        //Ask users to continue using or close the service
        continueService();
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
        System.out.println("======================================================");
        System.out.println(PURPLE_BOLD + "Enter 3 for \"Booking\"   Enter 4 for \"Filter\"" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter movie name for more details" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter \"return\" to return to home page"  + ANSI_RESET);
        System.out.println("======================================================\n");
        System.out.println(YELLOW_BOLD_BRIGHT + "<<Now Showing!>>"   + ANSI_RESET);
        ListNowShowing.listNowShowing(dbInstance);
        BookingSystem.seperator();
    }

    public static String CshowingFilter() throws InterruptedException {
        BookingSystem.seperator();
        System.out.println(PURPLE_BOLD + "Enter 5 for \"Filter through cinema\"" + "     " + "Enter 6 for \"Filter through screen size\"" + ANSI_RESET);
        BookingSystem.seperator();
        return Timer.timer("c");
    }

    public static String CupcomingFilter() throws InterruptedException {
        BookingSystem.seperator();
        System.out.println(PURPLE_BOLD + "Enter 5 for \"Filter through cinema\"" + "     " + "Enter 6 for \"Filter through screen size\"" + ANSI_RESET);
        BookingSystem.seperator();
        return Timer.timer("c");
    }

    public static void continueService() throws InterruptedException {
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
    public static void filterMovies(String type) {


    }

    // This method will call methods in movie class
    // It will allow user to book movie tickets.
    // Note: only customer can use this service.
    public static void book() throws InterruptedException {
        //Scanner input = new Scanner(System.in);
        String movieName = checkMovieName();
        String cinemaName = checkCinemaName(movieName);
        String movieStartTime = checkStartTime(movieName, cinemaName);
        String screenType = GetSingleScreenSize.getSingleScreenSize(dbInstance, movieName, cinemaName, movieStartTime);
        int audienceNum = getAudienceNum();
        String seatLocation = getSeatLocation();
        updateSeats(movieName, cinemaName, movieStartTime, screenType, audienceNum, seatLocation);

    }

    public static void updateSeats(String movieName, String cinemaName, String movieStartTime, String screenType, int audienceNum, String seatLocation) throws InterruptedException {
        if (checkPayment(getPaymentType())){
            UpdateSeats updateSeats = new UpdateSeats();
            updateSeats.updateSeats(dbInstance, cinemaName, movieName,movieStartTime, screenType, audienceNum, seatLocation);
        }else {
            String cardNum = getCardNum();
            String cardHolderName = getCardHolderName();
            System.out.println("You have successfully booked a movie!");
            saveCreditCard(cardHolderName, cardNum);
            //TODO: auto-generated id

        }
    }
    public static int getPaymentType() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
        System.out.println("Filter movies by");
        System.out.println("======================================================");
        System.out.println(PURPLE_BOLD + "Which payment do you want to make?" + ANSI_RESET);
        System.out.println(YELLOW_BOLD + "1.Credit Card       2.Gift Card"+ ANSI_RESET);
        System.out.println("======================================================\n");
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");

        int paymentType = input.nextInt();
        switch (paymentType){
            case 1:
                return 1;

            case 2:
                return 2;

            default:
                System.out.println("Please enter a correct number");
                getPaymentType();
                return 0;
        }

    }





    // This method should be called by book( )
    // It will check if the payment is successful.
    public static boolean checkPayment(int paymentType) throws InterruptedException {
        //call methods from databaseutility
        Scanner input = new Scanner(System.in);
        switch (paymentType){
            case 1://card
                if(cardNumberCheck()){
                    if (cardHolderNameCheck()){
                        //TODO: update balance
                    }
                }
                break;

            case 2://giftcard
                String pattern = "\\d{14}[GX]"; //regex 16-digit suffix GX
                System.out.println("Please enter your gift card number");
                String giftCardNum = input.nextLine();
                if (!Pattern.matches(pattern, giftCardNum)){
                    System.out.println("Wrong gift card number, it should be a 16-digit with suffix GX");
                    checkPayment(paymentType);
                    break;
                }

                if (RedeemedCheck.giftCardRedeemed(dbInstance, giftCardNum)){
                    RedeemingGiftCard.redeemGiftCard(dbInstance, giftCardNum);

                }else {
                    System.out.println("This card does not exist or has been redeemed");
                    checkPayment(paymentType);
                }
                break;

            default:
                System.err.println("Please enter the correct number");
                checkPayment(paymentType);
        }
        return false;
    }

    public static String checkMovieName(){
        Scanner input = new Scanner(System.in);
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
        System.out.println("======================================================");
        System.out.println("Please enter the movie name that you wish to book");
        System.out.println("======================================================\n");
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
        String movieName = input.nextLine();
        if (!CheckIfMovieExists.checkIfMovieExists(dbInstance, movieName)) {
            System.out.println("Movie " + movieName + " does not exist, please enter the correct movie name");
            checkMovieName();
        }

        return movieName;
    }

    public static String checkCinemaName(String movieName){
        Scanner input = new Scanner(System.in);
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
        System.out.println("======================================================");
        System.out.println("Please enter the cinema name that you wish to book");
        System.out.println("======================================================\n");
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
        String cinemaName = input.nextLine();
        if (!(CheckIfCinemaExists.checkIfCinemaExists(dbInstance, cinemaName))) {
            System.out.println("Cinema " + cinemaName + " does not exist, please enter the correct cinema name");
            checkCinemaName(movieName);
        } else if(!(CheckIfCinemaHasMovie.checkIfCinemaHasMovie(dbInstance, movieName, cinemaName))) {
            System.out.println(cinemaName + "currently does not have " + movieName + "being showed");
        }

        return cinemaName;
    }

    public static String checkStartTime(String movieName, String cinemaName){
        Scanner input = new Scanner(System.in);
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
        System.out.println("======================================================");
        System.out.println("Please enter the start time of the movie that you wish to book");
        System.out.println("======================================================\n");
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
        String startTime = input.nextLine();
        if (!(GetStartTime.getStartTime(dbInstance, cinemaName, movieName).contains(startTime))){
            System.out.println(movieName + " does not have start time at " + startTime + " in " + cinemaName);
            checkStartTime(movieName, cinemaName);
        }

        return startTime;
    }

    public static int getAudienceNum(){
        Scanner input = new Scanner(System.in);
        System.out.println("How many seats would you want to book");
        int audienceNum = input.nextInt();
        return audienceNum;
    }

    public static String getSeatLocation(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please select your seats location");
        System.out.println("1.front        2.mid       3.back");
        String seatLocation = input.nextLine();
        return seatLocation;
    }

    public static String getCardNum() throws InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your card number");
        //TODO hide card number
        String cardNum = Timer.timer("c");
        return cardNum;
    }
    public static boolean cardNumberCheck() throws InterruptedException {
        String cardNum = getCardNum();
        if (!(CheckIfCardExist.checkIfCardExist(dbInstance, cardNum))){
            System.out.println("Wrong card number");
            cardNumberCheck();
        }

        return true;
    }

    public static String getCardHolderName() throws InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your cardholder name");
        String cardHolderName = Timer.timer("c");
        return cardHolderName;
    }
    public static boolean cardHolderNameCheck() throws InterruptedException {
        String cardHolderName = getCardHolderName();
        if (!(CheckIfCardExist.checkIfCardExist(dbInstance, cardHolderName))){
            System.out.println("Wrong cardholder number");
            cardHolderNameCheck();
        }

        return true;
    }

    public void cancelTrans(){
        Scanner input = new Scanner(System.in);
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
        System.out.println("======================================================");
        System.out.println(PURPLE_BOLD + "Enter \"cancel\" to cancel transaction" + ANSI_RESET);
        System.out.println("======================================================\n");
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
        String cancel = input.nextLine();
        if (cancel.equals("cancel")){
            getPaymentType();
        }
    }

    public static void saveCreditCard(String cardHolderName, String cardNum){
        Scanner input = new Scanner(System.in);
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
        System.out.println("======================================================");
        System.out.println(PURPLE_BOLD + "Do you want to save your credit card information in your account?" + ANSI_RESET);
        System.out.println("1.Yes       2.No");
        System.out.println("======================================================\n");
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
        String saveInfo = input.nextLine();
        if (saveInfo.equals("1")){
            //TODO setSettings();
        }
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
                System.out.println("Please enter a correct number");
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