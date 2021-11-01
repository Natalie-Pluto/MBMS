package manageroperations;

import MTBMS.Database;
import databaseutility.AddingUser;
import databaseutility.CheckIfUserExists;

import java.util.Scanner;

public class StaffInserter {
    private Scanner s;
    private Database d;

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

    public StaffInserter(Scanner scanner, Database db){
        s = scanner;
        d = db;
    }

    public String collectUsername(){
        System.out.println(PURPLE_BOLD + "Enter a username:" + ANSI_RESET);
        String username = s.nextLine();
        boolean staffExist = CheckIfUserExists.checkIfUserExists(d, username);
        if(staffExist){
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Username already exists. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            username = collectUsername();
        }
        return username;
    }

    public String collectPassword(){
        System.out.println(PURPLE_BOLD + "Enter a password" + ANSI_RESET);
        String password = s.nextLine();
        System.out.println(PURPLE_BOLD + "Enter the password again" + ANSI_RESET);
        String secondTimePassword = s.nextLine();
        if(!password.equals(secondTimePassword)){
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Password does not match. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            password = collectPassword();
        }
        return password;
    }

    public boolean run(){
        String username = collectUsername();
        String password = collectPassword();
        String identity = "s";
        boolean success = AddingUser.addUser(d, username, password, identity);
        return success;
    }
}
