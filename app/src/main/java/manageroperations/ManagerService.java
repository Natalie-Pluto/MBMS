package manageroperations;

import MTBMS.Database;

import java.util.Scanner;

public class ManagerService {
    public static void managerService(Database d){

        System.out.println("Note: To quit, Enter \"-Exit-\"");
        System.out.println("1.Add/Remove Staff 2.Generate Cancellation Report");
        System.out.println("Enter the number to operate: ");
        Scanner s = new Scanner("System.in");
        String input = "";

        //keep running unless user quits
        Outer: while(!input.equals("-Exit-")){

            // retrieve information
            input = s.nextLine();
            if (input.equals("-Exit-")) {
                break Outer;
            }

            switch(input){
                case "1":
                    System.out.println("1.Add Staff 2.Remove Staff");
                    input = s.nextLine();
                    if (input.equals("-Exit-")) {
                        break Outer;
                    }
                    switch(input){
                        case "1":
                            StaffManipulator.addStaff(d);
                        case "2":
                            StaffManipulator.removeStaff(d);
                    }
                case "2":
                    manageroperations.ReportGenerator.getCancellationReport(d);
            }
        }
    }
}
