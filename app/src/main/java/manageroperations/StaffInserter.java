package manageroperations;

import MTBMS.Database;
import databaseutility.AddingUser;
import databaseutility.CheckIfUserExists;

import java.util.Scanner;

public class StaffInserter {
    private Scanner s;
    private Database d;

    public StaffInserter(Scanner scanner, Database db){
        s = scanner;
        d = db;
    }

    public String collectUsername(){
        System.out.println("Enter a username:");
        String username = s.nextLine();
        boolean staffExist = CheckIfUserExists.checkIfUserExists(d, username);
        if(staffExist){
            System.out.println("Error: Username already exists.");
            username = collectUsername();
        }
        return username;
    }

    public String collectPassword(){
        System.out.println("Enter a password");
        String password = s.nextLine();
        System.out.println("Enter the password again");
        String secondTimePassword = s.nextLine();
        if(!password.equals(secondTimePassword)){
            System.out.println("Error: Password does not match");
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
