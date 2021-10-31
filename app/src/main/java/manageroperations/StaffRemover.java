package manageroperations;

import MTBMS.Database;
import databaseutility.AddingUser;
import databaseutility.CheckIfUserExists;
import databaseutility.RemovingUser;

import java.util.Scanner;

public class StaffRemover {
    private Scanner s;
    private Database d;

    public StaffRemover(Scanner scanner, Database db){
        s = scanner;
        d = db;
    }

    public String collectUsername(){
        System.out.println("Enter a username:");
        String username = s.nextLine();
        boolean staffExist = CheckIfUserExists.checkIfUserExists(d, username);
        if(!staffExist){
            System.out.println("Error: Username does not exist.");
            username = collectUsername();
        }
        return username;
    }

    public boolean run(){
        String username = collectUsername();
        boolean success = RemovingUser.removeUser(d, username);
        return success;
    }
}
