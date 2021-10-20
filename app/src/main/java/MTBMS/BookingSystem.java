package MTBMS;

import databaseutility.*;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;




// TODO/*
//  This class contains the main for the application.
//  This class will interact with User table to add, edit and delete data in the table.
//  It will make the default page for the system.
//  It will call methods in Guest and Staff class to provide further services for the user
//   */
public class BookingSystem {
    private static BookingSystem instance;
    private static Database dbInstance;

    public static void main(String[] args) throws InterruptedException {
        instance = new BookingSystem();
        dbInstance = new Database("jdbc:postgresql://ls-d4381878930280384f33af335289e24c73224a04.c0apyqxz8x8m.ap-southeast-2.rds.amazonaws.com:5432/postgres",
                "dbmasteruser","A>XV>D*7r-V{y_wL}}I{+U=8zEtj1*T<");
        // Greeting, then ask user to login or sign up or they can view the upcoming movies list
        instance.getGreeting();
    }

    public void defaultPage() throws InterruptedException {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            String service = input.nextLine();
            switch (service) {
                case "1":
                    System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
                    System.out.println("Please enter your username:");
                    String accName = timer();
                    System.out.println("Please enter your password:");
                    String accPw = readPwd();
                    System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
                    login(accName, accPw);
                    break;
                case "2":
                    System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
                    signUp("NA");
                    System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
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
            //getGreeting();
        }
    }

    // Login will interact with User table to check the user's info
    public static void login(String accName, String accPw) throws InterruptedException {
        if (!CheckIfUserExists.authenticate(dbInstance, accName, accPw)) {
            System.err.println(RED_BOLD + "Incorrect username or password (｡´︿`｡)" + ANSI_RESET);
            Thread.sleep(3000);
            // Returning to default page
            instance.getGreeting();
        } else {
            if (CheckStaff.isStaff(dbInstance, accName)) {
                System.out.println(ANSI_PURPLE + "Logging in as staff..." + ANSI_RESET);
                Thread.sleep(3000);
                //Staff staff = new Staff(accName, "S", " ");
                //staff.staffService("S");
            } else if (CheckStaff.isManager(dbInstance, accName)) {
                System.out.println(ANSI_PURPLE + "Logging in as manager..." + ANSI_RESET);
                Thread.sleep(3000);
                //Staff staff = new Staff(accName, "M", " ");
                //staff.staffService("M");
            } else {
                System.out.println(ANSI_PURPLE + "Logging in as customer..." + ANSI_RESET);
                System.out.println(ANSI_PURPLE + "Welcome " + accName + "!" + ANSI_RESET);
                Thread.sleep(3000);
                Guest customer = new Guest(accName, "C", " ");
                customer.guestService("C");
            }
        }
    }

    // Signup will interact with User table to add user's info
    // Note: Need to check the username provided, it has to be unique
    public void signUp(String id) throws InterruptedException {
        boolean success = false;
        System.out.println("Please create your username:");
        boolean isExisted = true;
        String newAcc = "";
        while(isExisted) {
            newAcc = timer();
            // check if the username existed already
            if (CheckIfUserExists.checkIfUserExists(dbInstance, newAcc)) {
                System.out.println("==========================================================");
                System.out.println(RED_BOLD + "User name already existed. Please enter again (｡´︿`｡)" + ANSI_RESET);
                System.out.println("==========================================================");
            } else {
                isExisted = false;
            }
        }
        System.out.println("Please create your password:");
        String newPw = readPwd();
        if (id.equals("NA")) {
            boolean isFinished = false;
            int counter2 = 0;
            while (!isFinished && counter2 < 3) {
                counter2++;
                System.out.println("Please choose your identity:");
                System.out.println("Enter 1 - for \"Customer\"");
                System.out.println("Enter 2 - for \"Staff\"");
                String input = timer();
                if (input.equals("1")) {
                    isFinished = true;
                    success = true;
                    System.out.println(PURPLE_BOLD_BRIGHT + "Congratulations! You have made your account (｡･ω･｡)ﾉ" + ANSI_RESET);
                    // Add this customer's detail to users table
                    AddingUser.addUser(dbInstance, newAcc,newPw,"c");
                } else if (input.equals("2")) {
                    isFinished = true;
                    boolean isDone = false;
                    int counter = 0;
                    while (!isDone && counter < 3) {
                        counter++;
                        System.out.println("Are you a Manager?");
                        System.out.println("Enter: Y/N");
                        String ans = timer();
                        if (ans.equals("Y")) {
                            isDone = true;
                            success = true;
                            System.out.println(PURPLE_BOLD_BRIGHT + "Congratulations! You have made your account (｡･ω･｡)ﾉ" + ANSI_RESET);
                            // Add this manager's detail to users table
                            AddingUser.addUser(dbInstance, newAcc,newPw,"m");
                        } else if (ans.equals("N")) {
                            isDone = true;
                            success = true;
                            System.out.println(PURPLE_BOLD_BRIGHT + "Congratulations! You have made your account (｡･ω･｡)ﾉ" + ANSI_RESET);
                            // Add this staff's detail to users table
                            AddingUser.addUser(dbInstance, newAcc,newPw,"s");
                        } else {
                            System.out.println("============================================");
                            System.out.println(RED_BOLD + "Wrong Input! (｡´︿`｡)" + ANSI_RESET);
                            System.out.println("============================================");
                        }
                    }
                } else {
                    System.out.println("============================================");
                    System.out.println(RED_BOLD + "Wrong Input! (｡´︿`｡)" + ANSI_RESET);
                    System.out.println("============================================");
                }
            }
        } else {
            success = true;
            System.out.println(PURPLE_BOLD_BRIGHT + "Congratulations! You have made your account (｡･ω･｡)ﾉ" + ANSI_RESET);
            // Add this customer's detail to users table
            AddingUser.addUser(dbInstance, newAcc,newPw,"c");
        }
        if (success) {
            login(newAcc, newPw);
        } else {
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Sign up failed (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            Thread.sleep(2000);
            System.out.println(ANSI_PURPLE + "Returning...\n" + ANSI_RESET);
            Thread.sleep(2000);
            getGreeting();
        }
    }

    // Log out for the user, return to default page
    public static void logOut( ) throws InterruptedException {
        instance.getGreeting();
        instance.defaultPage();
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


    public void getGreeting() throws InterruptedException {
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
        System.out.println(YELLOW_BOLD_BRIGHT + "    Welcome to Fancy Cinemas Official Website!!" + ANSI_RESET + "\n");
        System.out.println("    If you have an account, please sign in (｡･ω･｡)ﾉ ");
        System.out.println("    If you haven't joined us, you can sign up today! o(｀ω´ )o");
        System.out.println("    If you don't want to join us today, you can continue as a guest (´･ω･`)\n");
        System.out.println(PURPLE_BOLD + "    1. Log in       2. Sign up      3. I wish to continue" + ANSI_RESET);
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
        defaultPage();
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

    // Read password securely
    //https://stackoverflow.com/questions/10819469/hide-input-on-command-line
    public String readPwd() {
        String password;
        String message = "Enter password";
        //if( System.console() == null ) {
        //    final JPasswordField pw = new JPasswordField();
        //    password = JOptionPane.showConfirmDialog( null, pw, message, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE ) == JOptionPane.OK_OPTION ? new String( pw.getPassword() ) : "";
        //} else {
        //        password = new String( System.console().readPassword( "%s> ") );
        //}
        Scanner scanner = new Scanner(System.in);
        password = scanner.next();
        return password;
    }

    // Timer for user's input
    public String timer() throws InterruptedException {
        BlockingDeque<String> deque = new LinkedBlockingDeque<>();

        Thread thread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            String input;
            try {
                do {
                    if (System.in.available() > 0) {
                        input = scanner.nextLine();
                        deque.add(input);
                    } else
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            break;
                        }
                } while (true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        thread.start();
        int i = 0;
        String str;
        do {
            str = deque.poll(120, TimeUnit.SECONDS);
            i++;
        } while (i < 1);

        if(str != null) {
            if (str.equals("CLOSE")) {
                System.exit(0);
            }
        }

        thread.interrupt();
        return str;
    }
}
