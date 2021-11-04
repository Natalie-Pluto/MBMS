package MTBMS;
import movieManagement.*;
import databaseutility.*;
import org.checkerframework.checker.units.qual.A;

import java.util.*;
import java.util.regex.*;


import javax.print.attribute.AttributeSetUtilities;
import javax.xml.crypto.Data;
import java.rmi.ServerError;
import java.sql.SQLOutput;

//TODO/*
//  This class will interact with user (guest & customer)
//  It will continue the CLI for guests and provide services for them.
//  */
public class Guest {
    private String username;
    private String identity;
    private String settings;


    //private static Database dbInstance = new Database("jdbc:postgresql://ls-d4381878930280384f33af335289e24c73224a04.c0apyqxz8x8m.ap-southeast-2.rds.amazonaws.com:5432/postgres", "dbmasteruser","A>XV>D*7r-V{y_wL}}I{+U=8zEtj1*T<");
    //private static Database dbInstance = new Database("jdbc:postgresql://localhost:5432/MTBMS", "postgres", "329099");
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
        Scanner input = new Scanner(System.in);
        String service = input.nextLine();
        switch (service) {
            case "1":
                nowShowingCus(dbInstance);
                guestService();
                break;
            case "2":
                filterMovies(dbInstance, "U" + CupcomingFilter());
                continueService();
                break;
            case "3":
                book(dbInstance);
                break;
            case "4":
                filterMovies(dbInstance, "S" + CshowingFilter());
                continueService();
                break;
            case "5":
                BookingSystem.logOut();
                break;
            case "6":
                continuePersonalSettings(personalSettingsMsgs());
                break;
            case "return":
                customerHomePage();
                guestService();
                break;
            default:
                if (GetMovieSynopsis.getMovieSynopsis(dbInstance, service.replace("'", "''").toLowerCase(Locale.ROOT)) == null) {
                    wrongInput();
                    guestService();
                } else {
                    movieDetail(dbInstance, service.toLowerCase(Locale.ROOT));
                    continueService();
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
        System.out.println("6 - for personal settings");
        System.out.println("Enter correct movie name for movie detail");
        System.out.println("============================================\n");
    }

    public static void movieDetail(Database dbInstance, String name_) throws InterruptedException {
        BookingSystem.seperator();
        MovieDetails.movieDetails(dbInstance, name_.replace("'", "''").toLowerCase(Locale.ROOT));
        BookingSystem.seperator();
    }

    public void customerHomePage() throws InterruptedException {
        BookingSystem.seperator();
        System.out.println("======================================================");
        System.out.println(PURPLE_BOLD + "Enter 1 for \"Now Showing\"" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter 2 for \"Filter\"" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter 3 for \"Booking\"" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter 5 for \"Log out\""  + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter 6 for \"Personal Settings\""  + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter movie name for more details" + ANSI_RESET);
        System.out.println("======================================================\n");
        System.out.println(YELLOW_BOLD_BRIGHT + "<<Upcoming Movies!>>" + ANSI_RESET);
        GetUpcomingMovies.getUpcomingMovies(dbInstance);
        String cinemaName = GetuserSetting.getUsersetting(dbInstance, username);
        if(cinemaName != null) {
            System.out.println("\n======================================================\n");
            ListMovieByCinema.listMovieByCinema(dbInstance, cinemaName, username);
        }
        BookingSystem.seperator();
    }

    public static void nowShowingCus(Database dbInstance) {
        System.out.println("======================================================");
        System.out.println(PURPLE_BOLD + "Enter 3 for \"Booking\"" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter 4 for \"Filter\"" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter 5 for \"Log out\""  + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter 6 for \"Personal Settings\""  + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter movie name_ for more details" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter \"return\" to return to home page"  + ANSI_RESET);
        System.out.println("======================================================\n");
        System.out.println(YELLOW_BOLD_BRIGHT + "<<Now Showing!>>"   + ANSI_RESET);
        ListNowShowing.listNowShowing(dbInstance);
        BookingSystem.seperator();
    }

    public String personalSettingsMsgs() throws InterruptedException {
        BookingSystem.seperator();
        System.out.println("======================================================");
        System.out.println(PURPLE_BOLD + "Enter 1 for \"Update Password\"" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter 2 for \"Cinema Preference\"" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter \"return\" to return to home page"  + ANSI_RESET);
        System.out.println("======================================================");
        BookingSystem.seperator();
        return Timer.timer(username);
    }

    public String wrongpersonalSettingsMsgs() throws InterruptedException {
        BookingSystem.seperator();
        System.out.println("======================================================");
        System.out.println(RED_BOLD + "Wrong input! (｡´︿`｡)" + ANSI_RESET);
        System.out.println("Please:");
        System.out.println(PURPLE_BOLD + "Enter 1 for \"Update Password\"" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter 2 for \"Cinema Preference\"" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter \"return\" to return to home page"  + ANSI_RESET);
        System.out.println("======================================================");
        BookingSystem.seperator();
        return Timer.timer(username);
    }

    public void continuePersonalSettings(String type) throws InterruptedException {
        switch(type) {
            case "1":
                passwordUpdate();
                break;
            case "2":
                setCinemaPreference(dbInstance);
                break;
            case "return":
                customerHomePage();
                guestService();
                break;
            default:
                continuePersonalSettings(wrongpersonalSettingsMsgs());
                break;
        }

    }

    public void setCinemaPreference(Database dbInstance) throws InterruptedException {
        List<String> cinemas = cinemaPreferenceMsg(dbInstance);
        String num = Timer.timer(username);
        BookingSystem.seperator();
        boolean success = setSuccessful(num, cinemas);
        if(!success) {
            continuePersonalSettings(personalSettingsMsgs());
        }
    }

    public List<String> cinemaPreferenceMsg(Database dbInstance) {
        BookingSystem.seperator();
        System.out.println(YELLOW_BACKGROUND + "By choosing the cinema preference, the list of scheduled sessions of " + ANSI_RESET);
        System.out.println(YELLOW_BACKGROUND + "your preference cinema will be displayed in your home page." + ANSI_RESET + "\n");
        System.out.println("Please choose a cinema:");
        return BookingSystem.listCinema(dbInstance);
    }

    public boolean setSuccessful(String num, List<String> cinemas) {
        try {
            if (Integer.parseInt(num) > cinemas.size()) {
                System.out.println(RED_BOLD + "Please enter the right cinema number" + ANSI_RESET);
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println(RED_BOLD + "Please enter the right cinema number" + ANSI_RESET);
            return false;
        }
        SetUserSetting.setUserSetting(dbInstance, username, cinemas.get(Integer.parseInt(num) - 1));
        System.out.println("Your cinema preference is set (｡･ω･｡)ﾉ");
        return true;
    }

    // This method will allow customers to update their password and specific settings.
    // opt 1 for changing password
    // opt 2 for changing settings
    public void passwordUpdate() throws InterruptedException {
        BookingSystem.seperator();
        String presentPwd = GetUserPassword.getUserPassword(dbInstance, username);
        //check new password twice to ensure customers are typing their expected password correctly
        String newPwd_1 = "";
        int i = 0;
        while (i < 3) {
            System.out.println("Please enter your new password");
            newPwd_1 = BookingSystem.readPwd(username);
            if (newPwd_1.length() < 5) {
                i++;
                if(i == 3) {
                    System.out.println(RED_BOLD + "Updates on password failed!" + ANSI_RESET);
                    continuePersonalSettings(personalSettingsMsgs());
                }
                System.out.println(RED_BOLD + "Password has to be longer than 4 characters! (｡´︿`｡) Please try again" + ANSI_RESET);
            }
        }
        System.out.println("Please enter your new password again");
        String newPwd_2 = BookingSystem.readPwd(username);
        boolean success = checkPwd(newPwd_1, newPwd_2, presentPwd);
        if(!success) {
            System.out.println(RED_BOLD + "Updates on password failed!" + ANSI_RESET);
            continuePersonalSettings(personalSettingsMsgs());
        } else {
            //update new password to database
            ChangingUserPassword.changeUserPassword(dbInstance, username, newPwd_1);
        }
    }

    public boolean checkPwd(String p1, String p2, String p3) {
        if(p1.equals(p3) || p2.equals(p3)) {
            System.out.println(RED_BOLD + "New password can not be the same as your present one" + ANSI_RESET);
            return false;
        } else if (!p1.equals(p2)) {
            System.out.println(RED_BOLD + "Password not matching, please try again" + ANSI_RESET);
            return false;
        }
        System.out.println("Your new password is set (｡･ω･｡)ﾉ");
        return true;
    }

    public String CshowingFilter() throws InterruptedException {
        BookingSystem.seperator();
        System.out.println(PURPLE_BOLD + "Enter 5 for \"Filter through cinema\"" + "     " + "Enter 6 for \"Filter through screen size\"" + ANSI_RESET);
        BookingSystem.seperator();
        return Timer.timer(username);
    }

    public String CupcomingFilter() throws InterruptedException {
        BookingSystem.seperator();
        System.out.println(PURPLE_BOLD + "Enter 5 for \"Filter through cinema\"" + "     " + "Enter 6 for \"Filter through screen size\"" + ANSI_RESET);
        BookingSystem.seperator();
        return Timer.timer(username);
    }

    public String bookingHelper(Database dbInstance) throws InterruptedException {
        List<String> cinemas = BookingSystem.listCinema(dbInstance);
        String cinema = Timer.timer(username);
        BookingSystem.seperator();
        try {
            if (Integer.parseInt(cinema) > cinemas.size()) {
                ListMovieByCinema.listMovieByCinema(dbInstance, "hfiuiuaa", username);
            } else {
                ListMovieByCinema.listMovieByCinema(dbInstance, cinemas.get(Integer.parseInt(cinema) - 1), username);
                return cinemas.get(Integer.parseInt(cinema) - 1);
            }
        } catch (NumberFormatException e) {
            ListMovieByCinema.listMovieByCinema(dbInstance, "hfiuiuaa", username);
        }
        return " ";
    }

    public String getContinueService() throws InterruptedException{
        System.out.println("======================================================");
        System.out.println(PURPLE_BOLD + "Enter 1 for \"Return to the home page\""  + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter 2 for \"Log out\""  + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter 3 for Booking"  + ANSI_RESET);
        System.out.println("======================================================");
        BookingSystem.seperator();
        return Timer.timer(username);
    }

    public void continueService() throws InterruptedException {
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
                book(dbInstance);
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
    public void filterMovies(Database dbInstance, String type) throws InterruptedException {
        if(type.equals("U5")) {
            List<String> cinemaName1 = BookingSystem.listCinema(dbInstance);
            String cinema = Timer.timer(username);
            try {
                assert cinema != null;
                if (Integer.parseInt(cinema) > cinemaName1.size()) {
                    filterMsg(dbInstance, "a", "hiufbjkv");
                } else {
                    filterMsg(dbInstance, "a", cinemaName1.get(Integer.parseInt(cinema) - 1));
                }
            } catch (NumberFormatException e) {
                filterMsg(dbInstance, "a", "hiufbjkv");
            }
        } else if (type.equals("U6")) {
            BookingSystem.listScreen();
            String screen = Timer.timer(username);
            assert screen != null;
            if(screen.equals("1")) {
                filterMsg(dbInstance, "b", "Gold");
            } else if (screen.equals("2")) {
                filterMsg(dbInstance, "b", "Sliver");
            } else if (screen.equals("3")) {
                filterMsg(dbInstance, "b", "Bronze");
            } else {
                filterMsg(dbInstance, "b", "Hi");
            }
        } else if (type.equals("S5")) {
            List<String> cinemaName2 = BookingSystem.listCinema(dbInstance);
            String cinemaName = Timer.timer(username);
            try {
                assert cinemaName != null;
                if (Integer.parseInt(cinemaName) > cinemaName2.size()) {
                    filterMsg(dbInstance, "c", "hiufbjkv");
                } else {
                    filterMsg(dbInstance, "c", cinemaName2.get(Integer.parseInt(cinemaName) - 1));
                }
            } catch (NumberFormatException e) {
                filterMsg(dbInstance, "c", "hiufbjkv");
            }
        } else if (type.equals("S6")) {
            BookingSystem.listScreen();
            String size = Timer.timer(username);
            assert size != null;
            if(size.equals("1")) {
                filterMsg(dbInstance, "d", "Gold");
            } else if (size.equals("2")) {
                filterMsg(dbInstance, "d", "Sliver");
            } else if (size.equals("3")) {
                filterMsg(dbInstance, "d", "Bronze");
            } else {
                filterMsg(dbInstance, "d", "Hi");
            }
        } else {
            BookingSystem.filterMsg(dbInstance,"e", " ");
            if (type.contains("U")) {
                guestService();
            } else if (type.contains("S")) {
                nowShowingCus(dbInstance);
            }
        }
    }

    public static void filterMsg(Database dbInstance, String type, String value) throws InterruptedException {
        if (type.equals("a")) {
            BookingSystem.seperator();
            ListUpcomingByCinema.listUpcomingByCinema(dbInstance, value);
            BookingSystem.seperator();
        } else if (type.equals("b")) {
            BookingSystem.seperator();
            ListUpcomingByScreen.listUpcomingByScreen(dbInstance, value);
            BookingSystem.seperator();
        } else if (type.equals("c")) {
            BookingSystem.seperator();
            ListNowShowingCinema.listNowShowingCinema(dbInstance, value);
            BookingSystem.seperator();
        } else if (type.equals("d")) {
            BookingSystem.seperator();
            ListNowShowingScreen.listNowshowingScreen(dbInstance, value);
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
    public void book(Database dbInstance) throws InterruptedException {
        String cinemaName = bookingHelper(dbInstance);
        String movieName = getMovieName(dbInstance, cinemaName);
        String screenType = getScreenType(dbInstance, cinemaName, movieName);
        String startTime = getStartTime(dbInstance, cinemaName, movieName, screenType);
        String seatLocation = getSeatLocation(dbInstance, cinemaName, movieName, screenType, startTime);
        List<Integer> audienceNum = getSeatNum(dbInstance, cinemaName, movieName, screenType, startTime, seatLocation);
        int audiences = getAudiences(audienceNum);
        if(updateSeats(dbInstance,movieName, cinemaName, startTime, screenType, audiences, seatLocation, audienceNum)){
            bookSuccess();
            UpdateNumberOfBooking.updateNumberOfBooking(dbInstance, cinemaName, movieName, startTime, screenType);
        } else {
            customerHomePage();
            guestService();
        }
        BookingSystem.seperator();
        continueService();
    }


    public int getAudiences(List<Integer> audienceNum){
        int audiences = 0;
        for(int i =0; i < audienceNum.size();i++){
            audiences += audienceNum.get(i);
        }

        return audiences;
    }
    public double getTotalPrice(Database db, String movieName, String cinemaName, String startTime, String screenType, List<Integer> audienceNum) throws InterruptedException {
        List<Double> ticketPrices = getTicketPrices(db, cinemaName, movieName, startTime, screenType);
        double totalPrice = 0.0;
        for(int i = 0; i < audienceNum.size(); i++){
            totalPrice += (audienceNum.get(i) * ticketPrices.get(i));
        }
        /*for(Integer audience: audienceNum){
            System.out.println(audience);
            for(Double price: ticketPrices){
                System.out.println(price);
                double prices = audience * price;
                totalPrice += prices;
            }
        }*/
        return totalPrice;
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
    public void bookSuccess(){
        UUID transId = UUID.randomUUID();
        BookingSystem.seperator();
        System.out.println(GREEN_BOLD + "You have successfully booked a movie!\n" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Transaction id: " + transId + ANSI_RESET);
    }
    public boolean updateSeats(Database db, String movieName, String cinemaName, String startTime, String screenType, int audienceNum, String seatLocation, List<Integer> audienceList) throws InterruptedException {
        if (checkPayment(db, movieName, cinemaName, startTime, screenType, audienceList, username)) {
            UpdateSeats.updateSeats(db, cinemaName, movieName, startTime, screenType, audienceNum, seatLocation);
            BookingSystem.seperator();
            return true;
        }

        return false;
    }

    public String getPaymentType() throws InterruptedException {
        System.out.println("\n======================================================");
        System.out.println(PURPLE_BOLD + "Which payment do you want to make?" + ANSI_RESET);
        System.out.println(YELLOW_BOLD + "1.Credit Card       2.Gift Card"+ ANSI_RESET);
        System.out.println("======================================================\n");

        String paymentType = Timer.timer(username);
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
        System.out.println(RED_BOLD + "Please enter a correct number" + ANSI_RESET + "\n");
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
    }

    // This method should be called by book( )
    // It will check if the payment is successful.
    public boolean checkPayment(Database db, String movieName, String cinemaName, String startTime, String screenType, List<Integer> audienceNum, String username) throws InterruptedException {
        String paymentType = getPaymentType();
        switch (paymentType){
            case "1"://PayByCreditCard()
                String cardNum = "";
                String cardHolderName = "";
                if(GetCardNumByUserName.getCardNumByUserName(db, username) != null) {
                    cardNum = GetCardNumByUserName.getCardNumByUserName(db, username);
                    double cardBalancee = GetCreditCardBalance.getCreditCardBalance(dbInstance, cardNum);
                    double ticketPricee = getTotalPrice(db, movieName, cinemaName, startTime, screenType, audienceNum);
                    ChangingCreditCardBalance.changeCreditCardBalance(dbInstance, cardNum, cardBalancee - ticketPricee);
                    return true;
                } else {
                    cardNum = getCardNum();
                    if (cardNum == null) {
                        break;
                    }
                    cardHolderName = getCardHolderName();
                    if (cardHolderName == null) {
                        break;
                    }
                }
                if(CheckCreditCard.checkCreditCard(db, cardNum, cardHolderName)){
                    double cardBalance = GetCreditCardBalance.getCreditCardBalance(dbInstance, cardNum);
                    double ticketPrice = getTotalPrice(db, movieName, cinemaName, startTime, screenType, audienceNum);
                    if (cardBalance < ticketPrice){
                        System.out.println(RED_BOLD + "Insufficient balance\n" + ANSI_RESET);
                        checkPayment(db, movieName, cinemaName, startTime, screenType, audienceNum, username);
                    }else {
                        ChangingCreditCardBalance.changeCreditCardBalance(dbInstance, cardNum, cardBalance - ticketPrice);
                        saveCreditCard(db, cardNum, username);
                        return true;
                    }
                }else{
                    System.out.println(RED_BOLD + "Wrong card number or name\n");
                    checkPayment(db, movieName, cinemaName, startTime, screenType, audienceNum, username);
                }
                break;

            case "2"://giftcard
                int counter = 0;
                for (Integer audience: audienceNum){
                    counter += audience;
                }
                String result = "";
                String giftCardNum = "";
                boolean checkGiftCards = false;
                while (counter > 0){
                    Msg1();
                    giftCardNum = Timer.timer("p");
                    result = checkGiftCard(giftCardNum);
                    if(result.equals("1") || result.equals("2")) {
                        checkGiftCards = checkPaymentGiftCard(db, movieName, cinemaName, startTime, screenType, audienceNum, username, counter);
                        counter = 0;
                    } else if (result.equals("3") || result.equals("5")) {
                        checkGiftCards = checkPaymentGiftCard(db, movieName, cinemaName, startTime, screenType, audienceNum, username, counter);
                        counter = 0;

                    } else if (result.equals("4")) {
                        checkGiftCards = true;
                        counter -= 1;
                    }
                }
                return checkGiftCards;

            default:
                wrongInputMsg();
                checkPayment(db, movieName, cinemaName, startTime, screenType, audienceNum, username);
        }

        return false;
    }
    public boolean checkPaymentGiftCard(Database db, String movieName, String cinemaName, String startTime, String screenType, List<Integer> audienceNum, String username, int counters) throws InterruptedException {
        int counter = counters;
        String result = "";
        String giftCardNum = "";
        boolean checkGiftCards = false;
        while (counter > 0){
            Msg1();
            giftCardNum = Timer.timer("p");
            result = checkGiftCard(giftCardNum);
            if(result.equals("1") || result.equals("2")) {
                checkGiftCards = checkPaymentGiftCard(db, movieName, cinemaName, startTime, screenType, audienceNum, username, counter);
                counter = 0;
            } else if (result.equals("3") || result.equals("5")) {
                checkGiftCards = checkPaymentGiftCard(db, movieName, cinemaName, startTime, screenType, audienceNum, username, counter);
                counter = 0;
            } else if (result.equals("4")) {
                checkGiftCards = true;
                counter -= 1;
            }
        }
        return checkGiftCards;
    }

    public void Msg1() {
        BookingSystem.seperator();
        System.out.println("======================================================");
        System.out.println(PURPLE_BOLD + "Please enter your gift card number" + ANSI_RESET);
        cancelTransMsg();
        System.out.println("======================================================\n");
    }

    public String checkGiftCard(String giftCardNum) throws InterruptedException {
        if(giftCardNum == null) {
            System.out.println(RED_BOLD + "Time out! Transaction cancelled." + ANSI_RESET);
            cancelTrans(username, "Time out");
            return "1";
        } else if(giftCardNum.toLowerCase(Locale.ROOT).equals("cancel")) {
            System.out.println(RED_BOLD + "Transaction cancelled." + ANSI_RESET);
            cancelTrans(username, "Cancelled Transaction");
            return "2";
        } else {
            if (giftCardNum.length() != 18 || !(giftCardNum.endsWith("GC"))) {
                System.out.println("\n");
                System.out.println(RED_BOLD + "Wrong gift card number, it should be 16-digit with suffix GC" + ANSI_RESET);
                return "3";
            }

            if (!CheckIfGiftCardExists.checkIfGiftCardExists(dbInstance, giftCardNum) || (RedeemedCheck.giftCardRedeemed(dbInstance, giftCardNum))){
                BookingSystem.seperator();
                System.out.println(RED_BOLD + "This gift card does not exist or has been redeemed" + ANSI_RESET);
                return "5";
            }

            if (!RedeemedCheck.giftCardRedeemed(dbInstance, giftCardNum)) {
                RedeemingGiftCard.redeemGiftCard(dbInstance, giftCardNum);
                return "4";

            }

        }
        return null;
    }


    public String getMovieName(Database dbInstance, String cinemaName) throws InterruptedException {
        BookingSystem.seperator();
        int counter = 1;
        System.out.println("======================================================");
        System.out.println(PURPLE_BOLD + "Please select a movie:" + ANSI_RESET);
        System.out.println("======================================================\n");
        List<String> movieNames = FilterCinema.filterCinema(dbInstance, cinemaName.replace("'", "''"));
        for (String movieName: movieNames){
            System.out.println(counter + ". " + movieName);
            counter++;
        }
        BookingSystem.seperator();
        String movie = Timer.timer(username);
        try {
            if (Integer.parseInt(movie) > counter) {
                System.out.println(RED_BOLD + "please enter a correct number" + ANSI_RESET);
                return getMovieName(dbInstance, cinemaName);
            } else {
                return movieNames.get(Integer.parseInt(movie) - 1);
            }
        } catch (NumberFormatException e) {
            System.out.println(RED_BOLD + "please enter a correct number" + ANSI_RESET);
            return getMovieName(dbInstance, cinemaName);
        }
        //System.out.println((counter + 1) + ". go back");
    }

   /* public String checkMovieName(String cinemaName) throws InterruptedException {
        String movieName = getMovieName(cinemaName);
        if (CheckIfMovieExists.checkIfMovieExists(dbInstance, movieName)) {
            System.out.println("Movie \"" + movieName + "\" does not exist, please enter the correct movie name\n");
            return checkMovieName(cinemaName);
        }

        return movieName;
    }*/


    /*public String checkCinemaName(String movieName) throws InterruptedException {
        System.out.println("\n======================================================");
        System.out.println(PURPLE_BOLD + "Please enter the c that you wish to book" + ANSI_RESET);
        System.out.println("======================================================\n");
        String cinemaName = Timer.timer(username);

        if (!(CheckIfCinemaExists.checkIfCinemaExists(dbInstance, cinemaName))) {
            System.out.println(RED_BOLD + "Cinema " + cinemaName + " does not exist, please enter the correct cinema name\n" + ANSI_RESET);
            return checkCinemaName(movieName);

        } else if(!(CheckIfCinemaHasMovie.checkIfCinemaHasMovie(dbInstance, movieName, cinemaName))) {
            System.out.println(RED_BOLD+ cinemaName + " currently does not have " + movieName + " being showed\n" + ANSI_RESET);
            return checkCinemaName(movieName);
        }

        return cinemaName;
    }*/

    public String getScreenType(Database dbInstance, String cinemaName, String movieName) throws InterruptedException {
        BookingSystem.seperator();
        int counter = 1;
        System.out.println("======================================================");
        System.out.println(PURPLE_BOLD + "Please select a screenType:" + ANSI_RESET);
        System.out.println("======================================================\n");
        List<String> screenTypes = FilterScreenSizes.filterScreenSizes(dbInstance, cinemaName.replace("'", "''"), movieName.replace("'", "''"));
        for (String screenType: screenTypes){
            System.out.println(counter + ". " + screenType);
            counter++;
        }
        String screentype = Timer.timer(username);
        try {
            if (Integer.parseInt(screentype) > screenTypes.size()) {
                System.out.println(RED_BOLD + "please enter a correct number" + ANSI_RESET);
                return getScreenType(dbInstance, cinemaName, movieName);
            } else {
                return screenTypes.get(Integer.parseInt(screentype) - 1);
            }
        } catch (NumberFormatException e) {
            System.out.println(RED_BOLD + "please enter a correct number" + ANSI_RESET);
            return getScreenType(dbInstance, cinemaName, movieName);
        }
        //System.out.println((counter + 1) + ". go back");
    }


    public String getStartTime(Database dbInstance, String cinemaName, String movieName, String screenType) throws InterruptedException {
        BookingSystem.seperator();
        int counter = 1;
        System.out.println("======================================================");
        System.out.println(PURPLE_BOLD + "Please select a start time:" + ANSI_RESET);
        System.out.println("======================================================\n");
        List<String> startTimes = FilterStartTimes.filterStartTimes(dbInstance, cinemaName.replace("'", "''"), movieName.replace("'", "''"), screenType.replace("'","''"));
        for (String startTime: startTimes){
            System.out.println(counter + ". " + startTime);
            counter++;
        }
        String starttime = Timer.timer(username);
        try {
            if (Integer.parseInt(starttime) > startTimes.size()) {
                System.out.println(RED_BOLD + "please enter a correct number" + ANSI_RESET);
                return getStartTime(dbInstance, cinemaName, movieName, screenType);
            } else {
                return startTimes.get(Integer.parseInt(starttime) - 1);
            }
        } catch (NumberFormatException e) {
            System.out.println(RED_BOLD + "please enter a correct number" + ANSI_RESET);
            return getStartTime(dbInstance, cinemaName, movieName, screenType);
        }
        //System.out.println((counter + 1) + ". go back");
    }


    public List<Integer> getBookNum(Database db, String cinemaName, String movieName, String screenType, String StartTime, String seatLocation) throws InterruptedException {
        String audienceNumStrAdults = bookNumHelper2("3", db, cinemaName, movieName, screenType, StartTime, seatLocation);
        String audienceNumStrKids = bookNumHelper2("1", db, cinemaName, movieName, screenType, StartTime, seatLocation);
        String audienceNumStrStudents = bookNumHelper2("4", db, cinemaName, movieName, screenType, StartTime, seatLocation);
        String audienceNumStrSeniors = bookNumHelper2("2", db, cinemaName, movieName, screenType, StartTime, seatLocation);
        return bookNumHelper2(audienceNumStrKids, audienceNumStrSeniors, audienceNumStrAdults, audienceNumStrStudents);
    }

    public String bookNumHelper2(String type, Database db, String cinemaName, String movieName, String screenType, String StartTime, String seatLocation) throws InterruptedException {
        String number = bookNumHelper(type);
        try {
            if (Integer.parseInt(number) > ListSeats.getSeatNum(db, cinemaName, movieName, screenType, StartTime, seatLocation)) {
                System.out.println(RED_BOLD + seatLocation + " row does not have this much seats!" + ANSI_RESET);
                return bookNumHelper2(type, db, cinemaName, movieName, screenType, StartTime, seatLocation);
            }
        }catch (NumberFormatException e){
            System.out.println(RED_BOLD + "Please enter a correct number" + ANSI_RESET);
            return bookNumHelper2(type, db, cinemaName, movieName, screenType, StartTime, seatLocation);
        }
        return number;
    }

    public List<Integer> bookNumHelper2 (String audienceNumStrKids, String audienceNumStrSeniors, String audienceNumStrAdults, String audienceNumStrStudents) {
        List<Integer> seatsBooked = new ArrayList<>();
        seatsBooked.add(Integer.parseInt(audienceNumStrKids));
        seatsBooked.add(Integer.parseInt(audienceNumStrSeniors));
        seatsBooked.add(Integer.parseInt(audienceNumStrAdults));
        seatsBooked.add(Integer.parseInt(audienceNumStrStudents));
        return seatsBooked;
    }

    public String bookNumHelper(String type) throws InterruptedException {
        if(type.equals("1")) {
            System.out.println("\n======================================================");
            System.out.println(PURPLE_BOLD + "How many seats do you wish to book for kids?" + ANSI_RESET);
            System.out.println("======================================================\n");
            return Timer.timer(username);
        } else if (type.equals("2")) {
            System.out.println("\n======================================================");
            System.out.println(PURPLE_BOLD + "How many seats do you wish to book for seniors?" + ANSI_RESET);
            System.out.println("======================================================\n");
            return Timer.timer(username);
        } else if (type.equals("3")) {
            System.out.println("\n======================================================");
            System.out.println(PURPLE_BOLD + "How many seats do you wish to book for adults?" + ANSI_RESET);
            System.out.println("======================================================\n");
            return Timer.timer(username);
        } else {
            System.out.println("\n======================================================");
            System.out.println(PURPLE_BOLD + "How many seats do you wish to book for students?" + ANSI_RESET);
            System.out.println("======================================================\n");
            return Timer.timer(username);
        }
    }


    public List<Integer> getSeatNum(Database db, String cinemaName, String movieName, String screenType, String StartTime, String seatLocation) throws InterruptedException {
         List<Integer> numberBook = getBookNum(db, cinemaName, movieName, screenType, StartTime, seatLocation);
         int number = 0;
         for (Integer n: numberBook){
             number += n;
         }
         if (number > ListSeats.getSeatNum(db, cinemaName, movieName, screenType, StartTime, seatLocation)){
             System.out.println(RED_BOLD + "The seat number you are booking is more than the capacity of " + seatLocation + "row" + ANSI_RESET);
             return getSeatNum(db, cinemaName, movieName, screenType, StartTime, seatLocation);
         }

         return numberBook;
    }

    public String getSeatLocation(Database db, String cinemaName, String movieName, String screenType, String StartTime) throws InterruptedException {
        ListSeats.listSeats(db, cinemaName, movieName, screenType, StartTime);
        String seatLocation = Timer.timer(username);
        switch (seatLocation) {
            case "1":
                return "front";

            case "2":
                return "mid";

            case "3":
                return "back";

            default:
                System.out.println(RED_BOLD + "\nPlease enter a correct number" + ANSI_RESET);
                return getSeatLocation(db, cinemaName, movieName, screenType, StartTime);
        }
    }

    public List<Double> getTicketPrices(Database db, String cinemaName, String movieName, String startTime, String screenType) throws InterruptedException {
        List<Double> ticketPrices = new ArrayList<>();
        ticketPrices.add(GetTicketPrice.getTicketPriceKids(db, cinemaName, movieName, startTime, screenType));
        ticketPrices.add(GetTicketPrice.getTicketPriceSeniors(db, cinemaName, movieName, startTime, screenType));
        ticketPrices.add(GetTicketPrice.getTicketPriceAdults(db, cinemaName, movieName, startTime, screenType));
        ticketPrices.add(GetTicketPrice.getTicketPriceStudents(db, cinemaName, movieName, startTime, screenType));
        return ticketPrices;
    }
    public String getCardNum() throws InterruptedException {
        System.out.println("\n======================================================");
        System.out.println(PURPLE_BOLD + "Please enter your card number" + ANSI_RESET);
        cancelTransMsg();
        System.out.println("======================================================");
        String cardNum = BookingSystem.readPwd("2");
        return getCardHelper(cardNum);
    }

    public String getCardHolderName() throws InterruptedException {
        System.out.println("\n======================================================");
        System.out.println(PURPLE_BOLD + "Please enter your cardholder name" + ANSI_RESET);
        cancelTransMsg();
        System.out.println("======================================================");
        String cardHolderName = Timer.timer("p");
        return getCardHelper(cardHolderName);
    }

    public String getCardHelper(String input) throws InterruptedException {
        if (input == null) {
            System.out.println(RED_BOLD + "Time out! Transaction cancelled." + ANSI_RESET);
            cancelTrans(username, "Timeout");
            return null;
        } else if(input.toLowerCase(Locale.ROOT).equals("cancel")) {
            System.out.println(RED_BOLD + "Transaction cancelled." + ANSI_RESET);
            cancelTrans(username, "Cancelled Transaction");
            return null;
        }
        return input;
    }

    public static void cancelTransMsg(){
        System.out.println(PURPLE_BOLD + "Enter \"cancel\" to cancel transaction" + ANSI_RESET);
    }

    public void cancelTrans(String username, String reason) throws InterruptedException {
        UpdateCancelTrans.updateCancelTrans(dbInstance, username, reason);
    }

    public void saveCreditCard(Database dbInstance, String cardNum, String username) throws InterruptedException {
        if(GetCardNumByUserName.getCardNumByUserName(dbInstance, username) == null) {
            System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
            System.out.println("======================================================");
            System.out.println(PURPLE_BOLD + "Do you want to save your credit card information in your account?" + ANSI_RESET);
            System.out.println(YELLOW_BOLD + "1.Yes       2.No" + ANSI_RESET);
            System.out.println("======================================================\n");
            System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
            String saveInfo = Timer.timer(username);
            saveCardHelper(dbInstance, saveInfo, cardNum, username);
        }
    }

    public void saveCardHelper(Database dbInstance, String saveInfo, String cardNum, String username) throws InterruptedException {
        if (saveInfo == null) {
            System.out.println(RED_BOLD + "Time Out!" + ANSI_RESET);
            System.out.println(PURPLE_BOLD + "Credit card not saved" + ANSI_RESET);
        }else if(saveInfo.equals("1")) {
            SaveCreditCard.saveCreditCard(dbInstance, cardNum, username);
            System.out.println(GREEN_BOLD + "Your credit card has been stored in your account, you can use it next time!" + ANSI_RESET);
        } else if (saveInfo.equals("2")) {
            System.out.println(PURPLE_BOLD + "Credit card not saved" + ANSI_RESET);
        } else {
            System.out.println(RED_BOLD + "Please enter a correct number" + ANSI_RESET);
            saveCreditCard(dbInstance, cardNum, username);
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