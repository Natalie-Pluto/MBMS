package MTBMS;

import java.util.ArrayList;
import java.util.Scanner;

// TODO/*
//  This class contains the main for the application.
//  This class will interact with User table to add, edit and delete data in the table.
//  It will make the default page for the system.
//  It will call methods in Guest and Staff class to provide further services for the user
//   */
public class BookingSystem {
    private String username;
    private String password;
    private String identity;

    BookingSystem(String username, String password, String identity) {
        this.username = username;
        this.password = password;
        this.identity = identity;
    }

    public static void main(String[] args) throws InterruptedException {
        // Greeting, then ask user to login or sign up or they can view the upcoming movies list
        getGreeting();
        defaultPage();
    }

    public static void defaultPage() throws InterruptedException {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            String service = input.nextLine();
            switch (service) {
                case "1":
                    System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
                    System.out.println("Please enter your username:");
                    String accName = input.nextLine();
                    System.out.println("Please enter your password:");
                    String accPw = input.nextLine();
                    System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
                    login(accName, accPw);
                    break;
                case "2":
                    System.out.println("Please create your username:");
                    String newAcc = input.nextLine();
                    // TODO: check if the username existed already
                    System.out.println("Please create your password:");
                    String newPw = input.nextLine();
                    System.out.println("Please choose your identity:");
                    System.out.println("Enter 1 - for \"Customer\"");
                    System.out.println("Enter 2 - for \"Staff\"");
                    String id = input.nextLine();
                    if (id.equals("1")) {
                        BookingSystem newUswer = new BookingSystem(newAcc, newAcc, "C");
                        newUswer.signUp(newAcc, newPw);
                    } else if (id.equals("2")) {
                        boolean isDone = false;
                        while(!isDone) {
                            System.out.println("Are you a Manager?");
                            System.out.println("Enter: Y/N");
                            String ans = input.nextLine();
                            if (ans.equals("Y")) {
                                BookingSystem newUswer = new BookingSystem(newAcc, newAcc, "M");
                                newUswer.signUp(newAcc, newPw);
                                isDone = true;
                            } else if (ans.equals("N")) {
                                BookingSystem newUswer = new BookingSystem(newAcc, newAcc, "S");
                                newUswer.signUp(newAcc, newPw);
                                isDone = true;
                            } else {
                                System.out.println("============================================");
                                System.err.println("Please enter \"Y\" for Yes and \"N\" for No");
                                System.out.println("============================================");
                            }
                        }
                    }
                    break;
                case "3":
                    System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
                    System.out.println(ANSI_PURPLE + "Logging in as guest..." + ANSI_RESET);
                    Thread.sleep(3000);
                    Guest guest = new Guest(" ", "G", " ");
                    guest.guestService("G");
                    break;
                default:
                    System.out.println("============================================");
                    System.out.println(RED_BOLD + "Wrong Input! (｡´︿`｡)" + ANSI_RESET);
                    System.out.println("Please enter:");
                    System.out.println("1 - for log in");
                    System.out.println("2 - for sign up");
                    System.out.println("3 - for continue");
                    System.out.println("============================================\n");
                    Thread.sleep(2000);
                    System.out.println(ANSI_PURPLE + "Returning...\n" + ANSI_RESET);
                    Thread.sleep(2000);
                    getGreeting();
                    break;
            }
        }
    }

    // Login will interact with User table to check the user's info
    public static void login(String accName, String accPw) throws InterruptedException {
        Data db = new Data("jdbc:postgresql://ls-d4381878930280384f33af335289e24c73224a04.c0apyqxz8x8m.ap-southeast-2.rds.amazonaws.com:5432/postgres",
                "dbmasteruser","A>XV>D*7r-V{y_wL}}I{+U=8zEtj1*T<");
        if (!db.authenticate(accName, accPw)) {
            System.err.println(RED_BOLD + "Incorrect username or password (｡´︿`｡)" + ANSI_RESET);
        } else {
            if (db.isStaff(accName)) {
                System.out.println(ANSI_PURPLE + "Logging in as staff..." + ANSI_RESET);
                Thread.sleep(3000);
                Staff staff = new Staff(accName, "S", " ");
                staff.staffService("S");
            } else if (db.isManager(accName)) {
                System.out.println(ANSI_PURPLE + "Logging in as manager..." + ANSI_RESET);
                Thread.sleep(3000);
                Staff staff = new Staff(accName, "M", " ");
                staff.staffService("M");
            } else {
                System.out.println(ANSI_PURPLE + "Logging in as customer..." + ANSI_RESET);
                Thread.sleep(3000);
                Guest customer = new Guest(accName, "C", " ");
                customer.guestService("C");
            }
        }
    }

    // Signup will interact with User table to add user's info
    // Note: Need to check the username provided, it has to be unique
    public void signUp(String newAcc, String newPw) throws InterruptedException {
        login(newAcc, newPw);
    }

    // Log out for the user, return to default page
    public static void logOut( ) throws InterruptedException {
        getGreeting();
        defaultPage();
    }

    /*
    User's setting is an optional feature (not specifically addressed in the assignment spec.
    It seems like a feature that we can be designed by us.
    I will check with tutor next week.
    Just ignore it for sprint 1.
    */

    // Get the user's specific settings from user table (discuss it in sprint 2)
    public void getSettings( ) {
        // TODO
    }

    // Update the user's specific settings from user table (discuss it in sprint 2)
    public void updateSettings (ArrayList<String> settings){
        // TODO
    }

    // Delete an user from User table.
    // This can only be done by manager.
    // This method should be called by Staff class only.
    public void deleteUser (String username) {
        // TODO
    }

    // Add an user to User table.
    // It should be called by signup( ) method
    public void addUser (String userInfo) {
        // TODO
    }

    public String getIdentity() {
        return identity;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void getGreeting() {
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
        System.out.println(YELLOW_BOLD_BRIGHT + "    Welcome to Fancy Cinemas Official Website!!" + ANSI_RESET + "\n");
        System.out.println("    If you have an account, please sign in (｡･ω･｡)ﾉ ");
        System.out.println("    If you haven't joined us, you can sign up today! o(｀ω´ )o");
        System.out.println("    If you don't want to join us today, you can continue as a guest (´･ω･`)\n");
        System.out.println(ANSI_PURPLE + "    1. Log in       2. Sign up      3. I wish to continue" + ANSI_RESET);
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
    }

    // Regular
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

    // Background
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

    // Underline
    public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE
}
