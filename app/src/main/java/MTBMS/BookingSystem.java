package MTBMS;

import databaseutility.*;
import movieManagement.*;
import staffoperations.*;
import manageroperations.*;


import java.text.ParseException;
import java.util.Scanner;
import static databaseutility.UserAuthenticate.authenticate;




// TODO/*
//  This class contains the main for the application.
//  This class will interact with User table to add, edit and delete data in the table.
//  It will make the default page for the system.
//  It will call methods in Guest and Staff class to provide further services for the user
//   */
public class BookingSystem {
    private static BookingSystem instance;
    private static Database dbInstance;
    Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException, ParseException {
        instance = new BookingSystem();
        //dbInstance = new Database("jdbc:postgresql://ls-d4381878930280384f33af335289e24c73224a04.c0apyqxz8x8m.ap-southeast-2.rds.amazonaws.com:5432/postgres",
          //                      "dbmasteruser", "A>XV>D*7r-V{y_wL}}I{+U=8zEtj1*T<");

        //dbInstance =  new Database("jdbc:postgresql://localhost:5432/postgres", "postgres", "0000");
        dbInstance =  new Database("jdbc:postgresql://localhost:5432/MTBMS", "postgres", "329099");
        // Update upcoming movie table every Monday at 6am
        new UpdateUpcomingMovieTable();
        DeleteAllUpcoming.deleteUpcoming(dbInstance);
        AddingUpcomingMovie.addUpcomingMovie(dbInstance);
        // Greeting, then ask user to login or sign up or they can view the upcoming movies list
        getGreeting(dbInstance);
        options();
    }

    public static void options() throws InterruptedException {
        Scanner input = new Scanner(System.in);
        String service = input.nextLine();
        switch (service) {
            case "1":
                seperator();
                String name = username();
                String password = password();
                seperator();
                login(name, password);
                break;
            case "2":
                seperator();
                signUp("NA");
                seperator();
                break;
            case "3":
                nowShowing();
                options();
                break;
            case "4":
                filterMovie("U" + upcomingFilter());
                break;
            case "5":
                filterMovie("S" + showingFilter());
                break;
            case "Caribbean":
                System.out.println("Test");
                break;
            case "return":
                defaultPage(dbInstance);
                options();
                break;
            default:
                if (GetMovieSynopsis.getMovieSynopsis(dbInstance, service.replace("'", "''")) == null) {
                    wrongInput();
                    options();
                } else {
                    movieDetail(service);
                    options();
                }
                break;
        }

    }

    // Login will interact with User table to check the user's info
    public static void login(String accName, String accPw) throws InterruptedException {
        if (!instance.tryLogin(accName, accPw)) {
            instance.loginMsg();
            String name = instance.backCheck1();
            String pw = instance.backCheck2();
            if(!name.equals("back") || !pw.equals("back")) {
                login(name, pw);
            }
        } else {
            if (CheckStaff.isStaff(dbInstance, accName)) {
                instance.loginGreeting("s", accName);
                StaffService.staffService(dbInstance);
            } else if (CheckStaff.isManager(dbInstance, accName)) {
                instance.loginGreeting("m", accName);
                ManagerService.managerService(dbInstance);
            } else {
                instance.loginGreeting("c", accName);
                Guest customer = new Guest(accName, "C", " ");
                Guest.customerHomePage();
                Guest.guestService();
            }
        }
    }

    // Signup will interact with User table to add user's info
    // Note: Need to check the username provided, it has to be unique
    public static void signUp(String id) throws InterruptedException {
        boolean success = false;
        boolean isExisted = true;
        boolean isValid = false;
        boolean isMatched = false;
        System.out.println("Please create your username:");
        String newAcc = "";
        while (isExisted) {
            newAcc = Timer.timer("g");
            // check if the username existed already
            if (CheckIfUserExists.checkIfUserExists(dbInstance, newAcc)) {
                instance.signinMsg3();
            } else {
                isExisted = false;
            }
        }
        while (!isMatched) {
            createPwd();
            String newPw = "";
            while (!isValid) {
                newPw = readPwd();
                if (newPw.length() < 5) {
                    System.out.println(RED_BOLD + "Password has to be longer than 4 characters! (｡´︿`｡) Please try again" + ANSI_RESET);
                } else {
                    isValid = true;
                }
            }
            createPwd2();
            String newPw2 = readPwd();
            if (checkPwd(newPw, newPw2)) {
                isMatched = true;
                if (id.equals("NA")) {
                    boolean isFinished = false;
                    int counter2 = 0;
                    while (!isFinished && counter2 < 3) {
                        counter2++;
                        instance.signinMsg4();
                        String input = Timer.timer("g");
                        String num = instance.signUpHelper(input, newAcc, newPw);
                        if (num.equals("1")) {
                            isFinished = true;
                            success = true;
                        } else if (!num.equals("0")) {
                            instance.signUpHelper(num, newAcc, newPw);
                        }
                    }
                } else {
                    success = true;
                    instance.cSignup(newAcc, newPw);
                }
                if (success) {
                    login(newAcc, newPw);
                } else {
                    instance.signinFailed();
                    getGreeting(dbInstance);
                    options();
                }
            } else {
                System.out.println(RED_BOLD + "Password not matching! (｡´︿`｡)" + ANSI_RESET);
                isValid = false;
            }
        }
    }

    // Log out for the user, return to default page
    public static void logOut() throws InterruptedException {
        msg3();
        Thread.sleep(2000);
        getGreeting(dbInstance);
        options();
    }

    public static void msg3() {
        System.out.println("=================");
        System.out.println(PURPLE_BOLD + "Logging out..." + ANSI_RESET);
        System.out.println("=================");
    }


    // Get username
    public static String username() throws InterruptedException {
        System.out.println("Please enter your username:");
        return Timer.timer("g");
    }

    // Get password
    public static String password() throws InterruptedException {
        System.out.println("Please enter your password:");
        return readPwd();
    }

    // "U6" -> filter upcoming movies via cinema
    // "U7" -> filter upcoming movies via screen size
    // "S6" -> filter now showing movies via cinema
    // "S7" -> filter now showing movies via screen size
    public static void filterMovie(String type) throws InterruptedException {
        if(type.equals("U6")) {
            listCinema();
            String cinema = Timer.timer("g");
            filterMsg("a", cinema);
        } else if (type.equals("U7")) {
            listScreen();
            String screen = Timer.timer("g");
            filterMsg("b", screen);
        } else if (type.equals("S6")) {
            listCinema();
            String cinemaName = Timer.timer("g");
            filterMsg("c", cinemaName);
        } else if (type.equals("S7")) {
            listScreen();
            String size = Timer.timer("g");
            filterMsg("d", size);
        } else {
            filterMsg("e", " ");
            if (type.contains("U")) {
                defaultPage(dbInstance);
            } else if (type.contains("S")) {
                nowShowing();
            }
        }
        options();
    }

    // https://www.generacodice.com/en/articolo/4311769/hide-input-on-command-line
    static class EraserThread implements Runnable {
        private boolean stop;
        public EraserThread(String prompt) {
            System.out.print(prompt);
        }

        public void run () {
            stop = true;
            while (stop) {
                System.out.print("\010 ");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        public void stopMasking() {
            this.stop = false;
        }
    }


    // Read password securely
    public static String readPwd() throws InterruptedException {
        EraserThread er = new EraserThread(" ");
        Thread mask = new Thread(er);
        mask.start();
        String password = Timer.timer("g");
        er.stopMasking();
        return password;
    }

    // Check if the password entered are equal
    public static boolean checkPwd(String pwd1, String pwd2) {
        return pwd1.equals(pwd2);
    }

    public String backCheck1() throws InterruptedException {
        String name = username();
        if (name.equals("back")) {
            getGreeting(dbInstance);
            options();
        }
        return name;
    }

    public String backCheck2() throws InterruptedException {
        String pw = password();
        if (pw.equals("back")) {
            getGreeting(dbInstance);
            options();
        }
        return pw;
    }

    // Retry log in
    public boolean tryLogin(String username, String pwd) throws InterruptedException {
        if (!authenticate(dbInstance, username, pwd)) {
            System.out.println(RED_BOLD + "Incorrect username or password (｡´︿`｡)" + ANSI_RESET);
            System.out.println(RED_BOLD + "Please try again" + ANSI_RESET);
            return false;
        }
        return true;
    }

    public String signUpHelper(String input, String newAcc, String newPw) throws InterruptedException {
        String success = "0";
        if (input.equals("1")) {
            success = "1";
            System.out.println(PURPLE_BOLD_BRIGHT + "Congratulations! You have made your account (｡･ω･｡)ﾉ" + ANSI_RESET);
            // Add this customer's detail to users table
            AddingUser.addUser(dbInstance, newAcc, newPw, "c");
        } else if (input.equals("3")) {
            System.out.println("Please enter the staff code:");
            if (readPwd().equals("zootopia")) {
                success = "1";
                System.out.println(PURPLE_BOLD_BRIGHT + "Congratulations! You have made your account (｡･ω･｡)ﾉ" + ANSI_RESET);
                // Add this manager's detail to users table
                AddingUser.addUser(dbInstance, newAcc, newPw, "m");
            } else {
                success = "3";
            }
        } else if (input.equals("2")) {
            System.out.println("Please enter the staff code:");
            if (readPwd().equals("shawshank")) {
                success = "1";
                System.out.println(PURPLE_BOLD_BRIGHT + "Congratulations! You have made your account (｡･ω･｡)ﾉ" + ANSI_RESET);
                // Add this staff's detail to users table
                AddingUser.addUser(dbInstance, newAcc, newPw, "s");
            } else {
                success = "2";
            }
        } else {
            instance.signinMsg1();
        }
        return success;
    }

    public void cSignup(String newAcc, String newPw) {
        System.out.println(PURPLE_BOLD_BRIGHT + "Congratulations! You have made your account (｡･ω･｡)ﾉ" + ANSI_RESET);
        // Add this customer's detail to users table
        AddingUser.addUser(dbInstance, newAcc, newPw, "c");
    }

    public static String showingFilter() throws InterruptedException {
        seperator();
        System.out.println(PURPLE_BOLD + "Enter 6 for \"Filter through cinema\"" + "     " + "Enter 7 for \"Filter through screen size\"" + ANSI_RESET);
        seperator();
        return Timer.timer("g");
    }

    public static String upcomingFilter() throws InterruptedException {
        seperator();
        System.out.println(PURPLE_BOLD + "Enter 6 for \"Filter through cinema\"" + "     " + "Enter 7 for \"Filter through screen size\"" + ANSI_RESET);
        seperator();
        return Timer.timer("g");
    }

    public static void movieDetail(String name) {
        seperator();
        MovieDetails.movieDetails(dbInstance, name.replace("'", "''"));
        System.out.println("\n===================================================================");
        System.out.println("You have to log in / sign up to book movie tickets! (｡･ω･｡)ﾉ ");
        System.out.println(PURPLE_BOLD + "Enter 1 for \"Log in\""  + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter 2 for \"Sign up\""  + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter 3 for \"Now Showing\""  + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter \"return\" to return to home page"  + ANSI_RESET);
        System.out.println("=====================================================================");
        seperator();
    }

    public static void listCinema() {
        seperator();
        System.out.println("=======================");
        System.out.println(PURPLE_BOLD + "Please select a cinema:" + ANSI_RESET);
        System.out.println("=======================");
        System.out.println("Warringah Mall");
        System.out.println("Town Hall");
        System.out.println("Eastgarden");
        System.out.println("Blacktown");
        seperator();
    }

    public static void listScreen() {
        seperator();
        System.out.println("============================");
        System.out.println(PURPLE_BOLD + "Please select a screen size:" + ANSI_RESET);
        System.out.println("============================");
        System.out.println("Gold");
        System.out.println("Sliver");
        System.out.println("Bronze");
        seperator();
    }

    public static void filterMsg(String type, String value) throws InterruptedException {
        if (type.equals("a")) {
            seperator();
            ListUpcomingByCinema.listUpcomingByCinema(dbInstance, value);
            msg2();
            seperator();
        } else if (type.equals("b")) {
            seperator();
            ListUpcomingByScreen.listUpcomingByScreen(dbInstance, value);
            msg2();
            seperator();
        } else if (type.equals("c")) {
            seperator();
            ListNowShowingCinema.listNowShowingCinema(dbInstance, value);
            msg2();
            seperator();
        } else if (type.equals("d")) {
            seperator();
            ListNowShowingScreen.listNowshowingScreen(dbInstance, value);
            msg2();
            seperator();
        } else if (type.equals("e")){
            seperator();
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Wrong input (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
        }
    }


    // Below are the print methods:
    public static void defaultPage(Database dbInstance) {
        seperator();
        System.out.println("=====================================================================");
        System.out.println(PURPLE_BOLD + "    Enter 3 for \"Now Showing\"" + "       " + "Enter 4 for \"Filter\"" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "    Enter movie name for more details" + ANSI_RESET);
        System.out.println("=====================================================================\n");
        System.out.println(YELLOW_BOLD_BRIGHT + "<<Upcoming Movies!>>"   + ANSI_RESET);
        GetUpcomingMovies.getUpcomingMovies(dbInstance);
        msg();
        seperator();
    }

    public static void msg() {
        System.out.println("\n=====================================================================");
        System.out.println("You have to log in / sign up to book movie tickets! (｡･ω･｡)ﾉ ");
        System.out.println(PURPLE_BOLD + "Enter 1 for \"Log in\""  + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter 2 for \"Sign up\""  + ANSI_RESET);
        System.out.println("=====================================================================");
    }

    public static void msg2() {
        System.out.println("\n=====================================================================");
        System.out.println("You have to log in / sign up to book movie tickets! (｡･ω･｡)ﾉ ");
        System.out.println(PURPLE_BOLD + "Enter 1 for \"Log in\""  + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter 2 for \"Sign up\""  + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter \"return\" to return to home page"  + ANSI_RESET);
        System.out.println("=====================================================================");
    }

    // List all now showing
    public static void nowShowing() {
        seperator();
        System.out.println("=====================================================================");
        System.out.println(PURPLE_BOLD + "   Enter 5 for \"Filter\"" + "      " + "Enter movie name for more details" + ANSI_RESET);
        System.out.println("=====================================================================\n");
        System.out.println(YELLOW_BOLD_BRIGHT + "<<Now Showing!>>"   + ANSI_RESET);
        ListNowShowing.listNowShowing(dbInstance);
        System.out.println("\n===================================================================");
        System.out.println("You have to log in / sign up to book movie tickets! (｡･ω･｡)ﾉ ");
        System.out.println(PURPLE_BOLD + "Enter 1 for \"Log in\""  + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter 2 for \"Sign up\""  + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "Enter \"return\" to return to home page"  + ANSI_RESET);
        System.out.println("=====================================================================");
        seperator();
    }

    public static void createPwd() {
        System.out.println("Please create your password:");
    }

    public static void createPwd2() {
        System.out.println("Please enter your password again:");
    }

    public static void seperator() {
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
    }

    public void loginGreeting(String id, String accName) throws InterruptedException {
        switch (id) {
            case "s":
                System.out.println(ANSI_PURPLE + "Logging in as staff..." + ANSI_RESET);
                System.out.println(ANSI_PURPLE + "Welcome " + accName + "!" + ANSI_RESET);
                Thread.sleep(3000);
                break;
            case "m":
                System.out.println(ANSI_PURPLE + "Logging in as manager..." + ANSI_RESET);
                System.out.println(ANSI_PURPLE + "Welcome " + accName + "!" + ANSI_RESET);
                Thread.sleep(3000);
                break;
            case "c":
                System.out.println(ANSI_PURPLE + "Logging in as customer..." + ANSI_RESET);
                System.out.println(ANSI_PURPLE + "Welcome " + accName + "!" + ANSI_RESET);
                Thread.sleep(3000);
                break;
        }
    }

    public void loginMsg() {
        System.out.println(ANSI_PURPLE + "If you wish to return to the default page, please enter \"back\"" + ANSI_RESET);
        seperator();
    }

    public static void wrongInput() throws InterruptedException {
        System.out.println("============================================");
        System.out.println(RED_BOLD + "Wrong Input! (｡´︿`｡)" + ANSI_RESET);
        System.out.println("Please enter:");
        System.out.println("1 - for log in");
        System.out.println("2 - for sign up");
        System.out.println("3 - for now showing");
        System.out.println("4 - for filter upcoming movies");
        System.out.println("5 - for filter now showing movies");
        System.out.println("Enter correct movie name for movie detail");
        System.out.println("============================================\n");
        Thread.sleep(2000);
    }

    public static void getGreeting(Database dbInstance) {
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
        System.out.println(YELLOW_BOLD_BRIGHT + "    Welcome to Fancy Cinemas Official Website!!" + ANSI_RESET + "\n");
        System.out.println("    If you have an account, please sign in (｡･ω･｡)ﾉ ");
        System.out.println("    If you haven't joined us, you can sign up today! o(｀ω´ )o\n");
        System.out.println(PURPLE_BOLD + "    Enter 1 for \"Log in\"" + "            Enter 2 for \"Sign up\"" + ANSI_RESET);
        defaultPage(dbInstance);
    }

    public void signinFailed() throws InterruptedException {
        System.out.println("\n============================================");
        System.out.println(RED_BOLD + "Sign up failed (｡´︿`｡)" + ANSI_RESET);
        System.out.println("============================================\n");
        Thread.sleep(2000);
        System.out.println(ANSI_PURPLE + "Returning...\n" + ANSI_RESET);
        Thread.sleep(2000);
    }

    public void signinMsg1() {
        System.out.println("============================================");
        System.out.println(RED_BOLD + "Wrong Input! (｡´︿`｡)" + ANSI_RESET);
        System.out.println("============================================");
    }

    public void signinMsg3() {
        System.out.println("==========================================================");
        System.out.println(RED_BOLD + "User name already existed. Please enter again (｡´︿`｡)" + ANSI_RESET);
        System.out.println("==========================================================");
    }

    public void signinMsg4() {
        System.out.println("Please choose your identity:");
        System.out.println("Enter 1 - for \"Customer\"");
        System.out.println("Enter 2 - for \"Staff\"");
        System.out.println("Enter 3 - for \"Manager\"");
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