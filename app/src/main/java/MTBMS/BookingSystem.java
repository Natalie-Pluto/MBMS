package MTBMS;

import databaseutility.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
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

    public static void main(String[] args) throws InterruptedException {
        instance = new BookingSystem();
        dbInstance = new Database("jdbc:postgresql://ls-d4381878930280384f33af335289e24c73224a04.c0apyqxz8x8m.ap-southeast-2.rds.amazonaws.com:5432/postgres",
                "dbmasteruser", "A>XV>D*7r-V{y_wL}}I{+U=8zEtj1*T<");
        // Greeting, then ask user to login or sign up or they can view the upcoming movies list
        getGreeting();
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
                // TODO filter movies
                break;
            case "4":
                System.out.println("Test");
            default:
                wrongInput();
                options();
                break;
        }

    }

    // Login will interact with User table to check the user's info
    public static void login(String accName, String accPw) throws InterruptedException {
        if (!instance.tryLogin(accName, accPw)) {
            instance.loginMsg();
            if (Timer.timer("g").equals("back")) {
                getGreeting();
                options();
            } else {
                login(username(), password());
            }
        } else {
            if (CheckStaff.isStaff(dbInstance, accName)) {
                instance.loginGreeting("s", accName);
                //Staff staff = new Staff(accName, "S", " ");
                //staff.staffService("S");
            } else if (CheckStaff.isManager(dbInstance, accName)) {
                instance.loginGreeting("m", accName);
                //Staff staff = new Staff(accName, "M", " ");
                //staff.staffService("M");
            } else {
                instance.loginGreeting("c", accName);
                Guest customer = new Guest(accName, "C", " ");
                customer.guestService("C");
            }
        }
    }

    // Signup will interact with User table to add user's info
    // Note: Need to check the username provided, it has to be unique
    public static void signUp(String id) throws InterruptedException {
        boolean success = false;
        System.out.println("Please create your username:");
        boolean isExisted = true;
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
        createPwd();
        String newPw = readPwd();
        createPwd2();
        String newPw2 = readPwd();
        if (checkPwd(newPw, newPw2)) {
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
                System.out.println(PURPLE_BOLD_BRIGHT + "Congratulations! You have made your account (｡･ω･｡)ﾉ" + ANSI_RESET);
                // Add this customer's detail to users table
                AddingUser.addUser(dbInstance, newAcc, newPw, "c");
            }
            if (success) {
                login(newAcc, newPw);
            } else {
                instance.signinFailed();
                getGreeting();
            }
        }
    }

    // Log out for the user, return to default page
    public static void logOut() throws InterruptedException {
        getGreeting();
        options();
    }

    // Get username
    public static String username() throws InterruptedException {
        System.out.println("Please enter your username:");
        return Timer.timer("g");
    }

    // Get password
    public static String password() throws InterruptedException {
        System.out.println("Please enter your password:");
        return Timer.timer("g");
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

    // Retry log in
    public boolean tryLogin(String username, String pwd) throws InterruptedException {
        if (!authenticate(dbInstance, username, pwd)) {
            System.err.println(RED_BOLD + "Incorrect username or password (｡´︿`｡)" + ANSI_RESET);
            System.err.println("Please try again: ");
            Thread.sleep(3000);
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
            if (Timer.timer("g").equals("zootopia")) {
                success = "1";
                System.out.println(PURPLE_BOLD_BRIGHT + "Congratulations! You have made your account (｡･ω･｡)ﾉ" + ANSI_RESET);
                // Add this manager's detail to users table
                AddingUser.addUser(dbInstance, newAcc, newPw, "m");
            } else {
                success = "3";
            }
        } else if (input.equals("2")) {
            System.out.println("Please enter the staff code:");
            if (Timer.timer("g").equals("shawshank")) {
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


    // Below are the print methods:
    public static void defaultPage() {
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
        System.out.println("===============================");
        System.out.println(PURPLE_BOLD + "Enter 3 for \"Filter Movies\"" + ANSI_RESET);
        System.out.println("===============================\n");
        System.out.println(YELLOW_BOLD_BRIGHT + "<<Upcoming Movies!>>"   + ANSI_RESET);
        // TODO: List all the upcoming Movies & times
        System.out.println("\n=================================================");
        System.out.println("You can sign up to book your tickets! (｡･ω･｡)ﾉ ");
        System.out.println(PURPLE_BOLD + "Enter 2 for \"Sign up\""  + ANSI_RESET);
        System.out.println("=================================================");
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

    }

    public static void wrongInput() throws InterruptedException {
        System.out.println("============================================");
        System.out.println(RED_BOLD + "Wrong Input! (｡´︿`｡)" + ANSI_RESET);
        System.out.println("Please enter:");
        System.out.println("1 - for log in");
        System.out.println("2 - for sign up");
        System.out.println("3 - for filter movies");
        System.out.println("============================================\n");
        Thread.sleep(2000);
        System.out.println(ANSI_PURPLE + "Returning...\n" + ANSI_RESET);
        Thread.sleep(2000);
        getGreeting();
    }

    public static void getGreeting() {
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
        System.out.println(YELLOW_BOLD_BRIGHT + "    Welcome to Fancy Cinemas Official Website!!" + ANSI_RESET + "\n");
        System.out.println("    If you have an account, please sign in (｡･ω･｡)ﾉ ");
        System.out.println("    If you haven't joined us, you can sign up today! o(｀ω´ )o");
        System.out.println(PURPLE_BOLD + "    1. Log in       2. Sign up" + ANSI_RESET);
        System.out.println("\n" + YELLOW_BACKGROUND + "                                                                                " + ANSI_RESET + "\n");
        defaultPage();
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