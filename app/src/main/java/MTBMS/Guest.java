package MTBMS;
import movieManagement.*;
import databaseutility.*;

import java.util.UUID;
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

    private static Database dbInstance = new Database("jdbc:postgresql://ls-d4381878930280384f33af335289e24c73224a04.c0apyqxz8x8m.ap-southeast-2.rds.amazonaws.com:5432/postgres",
                "dbmasteruser","A>XV>D*7r-V{y_wL}}I{+U=8zEtj1*T<");
    //private static Database dbInstance = new Database("jdbc:postgresql://localhost:5432/MTBMS", "postgres", "329099");
    //private static Database dbInstance = new Database("jdbc:postgresql://localhost:5432/postgres", "postgres", "0000");
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
        Scanner input = new Scanner(System.in);
        String service = input.nextLine();
        switch (service) {
            case "1":
                nowShowingCus();
                guestService();
                break;
            case "2":
                filterMovies("U" + CupcomingFilter());
                break;
            case "3":
                bookingHelper();
                break;
            case "4":
                filterMovies("S" + CshowingFilter());
                break;
            case "5":
                BookingSystem.logOut();
                break;
            case "return":
                customerHomePage();
                guestService();
                break;
            default:
                if (GetMovieSynopsis.getMovieSynopsis(dbInstance, service.replace("'", "''")) == null) {
                    wrongInput();
                    guestService();
                } else {
                    movieDetail(service);
                }
                break;

        }

        //Ask users to continue using or close the service
        continueService();
    }

    public static void wrongInput() throws InterruptedException {
        System.out.println("============================================");
        System.out.println(RED_BOLD + "Wrong Input! (｡´︿`｡)" + ANSI_RESET);
        System.out.println("Please enter:");
        System.out.println("1 - for now showing");
        System.out.println("2 - for filter upcoming movies");
        System.out.println("3 - for booking tickets");
        System.out.println("4 - for filter now showing movies");
        System.out.println("5 - for log out");
        System.out.println("Enter correct movie name_ for movie detail");
        System.out.println("============================================\n");
    }

    public static void movieDetail(String name_) throws InterruptedException {
        BookingSystem.seperator();
        MovieDetails.movieDetails(dbInstance, name_.replace("'", "''"));
        continueService();
    }

    public static void customerHomePage() {
        BookingSystem.seperator();
        System.out.println("======================================================");
        System.out.println(PURPLE_BOLD + "Enter 1 for \"Now Showing\"   Enter 2 for \"Filter\"" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter 3 for \"Booking\"" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter movie name_ for more details" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter 5 for \"Log out\""  + ANSI_RESET);
        System.out.println("======================================================\n");
        System.out.println(YELLOW_BOLD_BRIGHT + "<<Upcoming Movies!>>" + ANSI_RESET);
        GetUpcomingMovies.getUpcomingMovies(dbInstance);
        BookingSystem.seperator();
    }

    public static void nowShowingCus() {
        System.out.println("======================================================");
        System.out.println(PURPLE_BOLD + "Enter 3 for \"Booking\"   Enter 4 for \"Filter\"" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter movie name_ for more details" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter \"return\" to return to home page"  + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter 5 for \"Log out\""  + ANSI_RESET);
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

    public static void bookingHelper() throws InterruptedException {
        BookingSystem.seperator();
        BookingSystem.listCinema();
        String cinema = Timer.timer("c");
        BookingSystem.seperator();
        ListMovieByCinema.listMovieByCinema(dbInstance, cinema);
        book();
    }

    public static String getContinueService() throws InterruptedException{
        System.out.println("======================================================");
        System.out.println(PURPLE_BOLD + "Enter 1 for \"Return to the home page\"   2 for \"Log out\""  + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter 3 for Booking"  + ANSI_RESET);
        System.out.println("======================================================\n");
        return Timer.timer("c");
    }
    public static void continueService() throws InterruptedException {
        System.out.println("======================================================");
        System.out.println(PURPLE_BOLD + "Enter 1 for \"Return to the home page\"   2 for \"Log out\""  + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter 3 for Booking"  + ANSI_RESET);
        System.out.println("======================================================\n");
        String service = Timer.timer("c");
        switch (service) {
            case "1":
                customerHomePage();
                guestService();
                break;

            case "2":
                BookingSystem.logOut();
                break;

            case "3":
                bookingHelper();
                break;

            default:
                wrongInputMsg();
                continueService();
        }
    }

    public static void continueService1() throws InterruptedException {
        String service = getContinueService();
        switch (service) {
            case "1":
                customerHomePage();
                guestService();
                break;
            case "2":
                BookingSystem.logOut();
                break;
            case "3":
                book();
                break;
            default:
                wrongInputMsg();
                continueService();
        }
    }

    public static void wrongInputMsg(){
        System.out.println("=================================");
        System.out.println(RED_BOLD + "Please enter the correct number" + ANSI_RESET);
        System.out.println("=================================");
    }

    // This method will call method in movie class.
    // It will filter and display the movies up to user's choice.
    // Both guest and customer can use this service.
    public static void filterMovies(String type) throws InterruptedException {
        if(type.equals("U5")) {
            BookingSystem.listCinema();
            String cinema = Timer.timer("c");
            filterMsg("a", cinema);
        } else if (type.equals("U6")) {
            BookingSystem.listScreen();
            String screen = Timer.timer("c");
            filterMsg("b", screen);
        } else if (type.equals("S5")) {
            BookingSystem.listCinema();
            String cinemaName = Timer.timer("c");
            filterMsg("c", cinemaName);
        } else if (type.equals("S6")) {
            BookingSystem.listScreen();
            String size = Timer.timer("c");
            filterMsg("d", size);
        } else {
            BookingSystem.filterMsg(dbInstance,"e", " ");
            if (type.contains("U")) {
                guestService();
            } else if (type.contains("S")) {
                nowShowingCus();
            }
        }
        guestService();
    }

    public static void filterMsg(String type, String value) throws InterruptedException {
        if (type.equals("a")) {
            BookingSystem.seperator();
            ListUpcomingByCinema.listUpcomingByCinema(dbInstance, value);
            continueService1();
            BookingSystem.seperator();
        } else if (type.equals("b")) {
            BookingSystem.seperator();
            ListUpcomingByScreen.listUpcomingByScreen(dbInstance, value);
            continueService();
            BookingSystem.seperator();
        } else if (type.equals("c")) {
            BookingSystem.seperator();
            ListNowShowingCinema.listNowShowingCinema(dbInstance, value);
            continueService1();
            BookingSystem.seperator();
        } else if (type.equals("d")) {
            BookingSystem.seperator();
            ListNowShowingScreen.listNowshowingScreen(dbInstance, value);
            continueService();
            BookingSystem.seperator();
        } else if (type.equals("e")){
            BookingSystem.seperator();
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Wrong input (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
        }
    }

    // This method will call methods in movie class
    // It will allow user to book movie tickets.
    // Note: only customer can use this service.
    public static void book() throws InterruptedException {
        BookingSystem.seperator();
        String movieName = checkMovieName();
        String cinemaName = checkCinemaName(movieName);
        String movieStartTime = checkStartTime(movieName, cinemaName);
        String screenType = GetSingleScreenSize.getSingleScreenSize(dbInstance, movieName, cinemaName, movieStartTime);
        int audienceNum = getAudienceNum();
        String seatLocation = getSeatLocation();
        updateSeats(movieName, cinemaName, movieStartTime, screenType, audienceNum, seatLocation);
        BookingSystem.seperator();
    }

    /*
    public static String getBookChoice(){
        Scanner input = new Scanner(System.in);
        BookingSystem.seperator();
        System.out.println("======================================================");
        System.out.println(PURPLE_BOLD + "Enter 1 for \"Book now showing movies\"   2 for \"book upcoming movies\""  + ANSI_RESET);
        System.out.println("======================================================\n");
        String bookChoice = input.nextLine();
        switch (bookChoice){
            case "1":
                BookingSystem.seperator();
                return "1";

            case "2":
                BookingSystem.seperator();
                return "2";

            default:
                System.out.println("======================================================");
                System.out.println(RED_BOLD + "Please enter a correct number"  + ANSI_RESET);
                System.out.println("======================================================\n");
                BookingSystem.seperator();
                return getBookChoice();
        }
    }

    public void bookNowShowing(){
        BookingSystem.seperator();
        Scanner input = new Scanner(System.in);
        GetUpcomingMovies.getUpcomingMovies(dbInstance);
        BookingSystem.seperator();
    }
    */

    public static void updateSeats(String movieName, String cinemaName, String movieStartTime, String screenType, int audienceNum, String seatLocation) throws InterruptedException {
        UUID transId = UUID.randomUUID();
        String paymentType = getPaymentType();
        if (checkPayment(paymentType)) {
            UpdateSeats updateSeats = new UpdateSeats();
            updateSeats.updateSeats(dbInstance, cinemaName, movieName, movieStartTime, screenType, audienceNum, seatLocation);
            BookingSystem.seperator();
            System.out.println(GREEN_BOLD + "You have successfully booked a movie!" + ANSI_RESET);
            System.out.println("\n Transaction id: " + transId);

            /*if (paymentType == 1){
                String cardNum = getCardNum();
                cardNumberCheck();
                String cardHolderName = getCardHolderName();
                cardNumberCheck();
            }*/
            //TODO: auto-generated id
        }

    }

    public static String getPaymentType() {
        Scanner input = new Scanner(System.in);
        System.out.println("======================================================");
        System.out.println(PURPLE_BOLD + "Which payment do you want to make?" + ANSI_RESET);
        System.out.println(YELLOW_BOLD + "1.Credit Card       2.Gift Card"+ ANSI_RESET);
        System.out.println("======================================================\n");

        String paymentType = input.nextLine();
        switch (paymentType){
            case "1":
                return "1";

            case "2":
                return "2";

            default:
                getPaymentTypeWrongMsg();
                return getPaymentType();
        }
    }
    public static void getPaymentTypeWrongMsg(){
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
        System.out.println("======================================================");
        System.out.println(RED_BOLD + "Please enter a correct number" + ANSI_RESET);
        System.out.println("======================================================\n");
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
    }

    // This method should be called by book( )
    // It will check if the payment is successful.
    public static boolean checkPayment(String paymentType) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        switch (paymentType){
            /*case "1"://card
                if(cardNumberCheck()){
                    if (cardHolderNameCheck()){
                    //update balance
                        //String cardNum = getCardNum();
                        //String cardHolderName = getCardHolderName();

                    }
                }
                break;
            */
            case "2"://giftcard
                BookingSystem.seperator();
                System.out.println("======================================================");
                System.out.println(PURPLE_BOLD + "Please enter your gift card number" + ANSI_RESET);
                System.out.println("======================================================\n");
                String giftCardNum = input.nextLine();
                if (giftCardNum.length() != 16 || !(giftCardNum.endsWith("GC"))){
                    System.out.println("\n");
                    System.out.println(RED_BOLD + "Wrong gift card number, it should be 16-digit with suffix GC" + ANSI_RESET);
                    checkPayment(paymentType);
                    break;
                }

                if (!RedeemedCheck.giftCardRedeemed(dbInstance, giftCardNum)){
                    RedeemingGiftCard.redeemGiftCard(dbInstance, giftCardNum);
                    break;

                }else {
                    BookingSystem.seperator();
                    System.out.println(RED_BOLD + "This gift card does not exist or has been redeemed" + ANSI_RESET);
                    checkPayment(paymentType);
                    break;
                }

            default:
                System.out.println(RED_BOLD + "Please enter the correct number" + ANSI_RESET);
                checkPayment(paymentType);

        }
        return true;
    }
    public static String getMovieName() throws InterruptedException {
        System.out.println("======================================================");
        System.out.println(PURPLE_BOLD + "Please enter the movie name_ that you wish to book" + ANSI_RESET);
        System.out.println("======================================================\n");
        String movieName = Timer.timer("c");
        return movieName;
    }
    public static String checkMovieName() throws InterruptedException {
        String movieName = getMovieName();
        if (!CheckIfMovieExists.checkIfMovieExists(dbInstance, movieName)) {
            System.out.println("Movie " + movieName + " does not exist, please enter the correct movie name_\n");
            return checkMovieName();
        }

        return movieName;
    }

    public static String checkCinemaName(String movieName) throws InterruptedException {
        System.out.println("\n======================================================");
        System.out.println(PURPLE_BOLD + "Please enter the cinema name_ that you wish to book" + ANSI_RESET);
        System.out.println("======================================================\n");
        String cinemaName = Timer.timer("c");

        if (!(CheckIfCinemaExists.checkIfCinemaExists(dbInstance, cinemaName))) {
            System.out.println(RED_BOLD + "Cinema " + cinemaName + " does not exist, please enter the correct cinema name_\n" + ANSI_RESET);
            return checkCinemaName(movieName);

        } else if(!(CheckIfCinemaHasMovie.checkIfCinemaHasMovie(dbInstance, movieName, cinemaName))) {
            System.out.println(RED_BOLD+ cinemaName + " currently does not have " + movieName + " being showed\n" + ANSI_RESET);
            return checkCinemaName(movieName);
        }

        return cinemaName;
    }

    public static String checkStartTime(String movieName, String cinemaName) throws InterruptedException {
        System.out.println("\n======================================================");
        System.out.println(PURPLE_BOLD + "Please enter the start time of the movie that you wish to book" + ANSI_RESET);
        System.out.println("======================================================\n");
        String startTime = Timer.timer("c");

        if (!(GetStartTime.getStartTime(dbInstance, cinemaName, movieName).contains(startTime))){
            System.out.println(RED_BOLD + movieName + " does not have start time at " + startTime + " in " + cinemaName + "\n" + ANSI_RESET);
            return checkStartTime(movieName, cinemaName);
        }

        return startTime;
    }

    public static int getAudienceNum() throws InterruptedException {
        System.out.println("\n======================================================");
        System.out.println(PURPLE_BOLD + "How many seats would you want to book?" + ANSI_RESET);
        System.out.println("======================================================\n");
        String audienceNumStr = Timer.timer("c");
        int audienceNum = Integer.parseInt(audienceNumStr);
        return audienceNum;
    }


    public static String getSeatLocation() throws InterruptedException {
        System.out.println("======================================================");
        System.out.println(PURPLE_BOLD + "Please select your seats location" + ANSI_RESET);
        System.out.println(YELLOW_BOLD + "1.front        2.mid       3.back" + ANSI_RESET);
        System.out.println("======================================================\n");
        String seatLocation = Timer.timer("c");
        return seatLocation;
    }

    public static String getCardNum() throws InterruptedException {
        System.out.println("Please enter your card number");
        //TODO hide card number
        String cardNum = Timer.timer("c");
        return cardNum;
    }
    public static boolean cardNumberCheck() throws InterruptedException {
        String cardNum = getCardNum();
        if (!(CheckIfCreditCardExists.checkIfCreditCardExists(dbInstance, cardNum))){
            System.out.println(RED_BOLD + "Wrong card number" + ANSI_RESET);
            return cardNumberCheck();
        }

        return true;
    }

    public static String getCardHolderName() throws InterruptedException {
        System.out.println("Please enter your cardholder name_");
        String cardHolderName = Timer.timer("c");
        return cardHolderName;
    }
    public static boolean cardHolderNameCheck() throws InterruptedException {
        String cardHolderName = getCardHolderName();
        if (!(CheckIfHolderNameExist.checkIfHolderNameExist(dbInstance, cardHolderName))){
            System.out.println(RED_BOLD + "Wrong cardholder name_" + ANSI_RESET);
            return cardHolderNameCheck();
        }

        return true;
    }

    /*public void cancelTrans() throws InterruptedException {
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
        System.out.println("======================================================");
        System.out.println(PURPLE_BOLD + "Enter \"cancel\" to cancel transaction" + ANSI_RESET);
        System.out.println("======================================================\n");
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
        String cancel = Timer.timer("c");
        if (cancel.equals("cancel")){
            getPaymentType();
        }
    } */

    public static void saveCreditCard(String cardHolderName, String cardNum){
        Scanner input = new Scanner(System.in);
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
        System.out.println("======================================================");
        System.out.println(PURPLE_BOLD + "Do you want to save your credit card information in your account?" + ANSI_RESET);
        System.out.println(YELLOW_BOLD + "1.Yes       2.No" + ANSI_RESET);
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
    /*public void personalInfoUpdate(String opt) throws InterruptedException {//change opt from int to String
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
    */
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