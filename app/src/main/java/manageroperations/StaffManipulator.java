package manageroperations;

import java.util.Scanner;
import databaseutility.*;
import MTBMS.Database;

public class StaffManipulator {
    public static void addStaff(Database d){

        System.out.println("Note: To quit, Enter \"-Exit-\"");
        Scanner s = new Scanner(System.in);
        String input = "";
        String username = "";
        String password = "";

        //keep running unless user quits
        while(!input.equals("-Exit-")) {

            // retrieve information
            System.out.println("Enter the username of the staff to add: ");
            input = s.nextLine();

            //quit if user wants to quit
            if(input.equals("-Exit-")){
                break;
            }
            username = input;

            //check if username already exists
            boolean exist = CheckIfUserExists.checkIfUserExists(d, username);
            if(exist == true){
                System.out.println("Error: username already exists.");
                continue;
            }

            System.out.println("Enter the password to add: ");
            input = s.nextLine();
            if(input.equals("-Exit-")){
                break;
            }
            password = input;

            AddingUser.addUser(d, username, password, "s");
            System.out.println("Success: Staff added.");
        }
    }

    public static void removeStaff(Database d){

        System.out.println("Note: To quit, Enter \"-Exit-\"");
        Scanner s = new Scanner(System.in);
        String input = "";
        String username = "";

        //keep running unless user quits
        while(!input.equals("-Exit-")) {

            // retrieve information
            System.out.println("Enter the username of the staff to remove: ");
            input = s.nextLine();

            //quit if user wants to quit
            if(input.equals("-Exit-")){
                break;
            }
            username = input;

            //check if username exists
            boolean exist = CheckIfUserExists.checkIfUserExists(d, username);
            if(exist == false){
                System.out.println("Error: username does not exist.");
                continue;
            }

            RemovingUser.removeUser(d, username);
            System.out.println("Success: Staff removed.");
        }

    }
}
