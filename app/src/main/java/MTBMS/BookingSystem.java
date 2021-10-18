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

    public static void main(String[] args) {
        // Greeting, then ask user to login or sign up or they can view the upcoming movies list
        getGreeting();
        defaultPage();
    }

    public static void defaultPage() {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            String service = input.nextLine();
            switch (service) {
                case "1":
                    System.out.println("Please enter your username:");
                    String accName = input.nextLine();
                    System.out.println("Please enter your password:");
                    String accPw = input.nextLine();
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
                    Guest guest = new Guest(" ", "G", " ");
                    guest.guestService("G");
                    break;
                default:
                    System.err.println("Please enter:");
                    System.err.println("1 - for log in");
                    System.err.println("2 - for sign up");
                    System.err.println("3 - for continue");
                    break;
            }
        }
    }

    // Login will interact with User table to check the user's info
    public static void login(String accNum, String accPw) {

    }

    // Signup will interact with User table to add user's info
    // Note: Need to check the username provided, it has to be unique
    public void signUp(String newAcc, String newPw) {

    }

    // Log out for the user, return to default page
    public static void logOut( ) {
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
        System.out.println("Welcome to Fancy Cinemas Official Website!");
        System.out.println("1. Log in       2. Sign up      3. I wish to continue");
    }
}
