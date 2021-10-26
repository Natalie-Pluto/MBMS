package staffoperations;

import MTBMS.Database;

import java.util.Scanner;

public class StaffService {
    public static void staffService(Database d){

        System.out.println("Note: To quit, Enter \"-Exit-\"");
        System.out.println("1.Modify Movie 2.Add/Remove Gift Card 3.Generate Upcoming Movie Report");
        System.out.println("Enter the number to operate: ");
        Scanner s = new Scanner("System.in");
        String input = "";

        //keep running unless user quits
        Outer: while(s.hasNext()){

            // retrieve information
            input = s.nextLine();
            if (input.equals("-Exit-")) {
                break Outer;
            }

            switch(input){
                case "1":
                    System.out.println("1.Insert New Movie 2.Delete Movie 3.Modify movie");
                    input = s.nextLine();
                    if (input.equals("-Exit-")) {
                        break Outer;
                    }
                    switch(input){
                        case "1":
                            MovieDataManipulator.insertMovieData(d);
                        case "2":
                            MovieDataManipulator.deleteMovieData(d);
                        case "3":
                            MovieDataManipulator.modifyMovieData(d);
                    }
                case "2":
                    System.out.println("1.Add Gift Card 2.Remove Gift Card");
                    input = s.nextLine();
                    if (input.equals("-Exit-")) {
                        break Outer;
                    }
                    switch(input){
                        case "1":
                            GiftCardManipulator.addGiftCard(d);
                        case "2":
                            GiftCardManipulator.removeGiftCard(d);
                    }
                case "3":
                    staffoperations.ReportGenerator.generateReport(d);

            }
        }
        System.out.println("Staff operations exits.");
    }

}
