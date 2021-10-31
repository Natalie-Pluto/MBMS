package manageroperations;

import MTBMS.Database;
import staffoperations.*;
import java.util.Scanner;

public class ManagerService {
    private Scanner scanner;
    private Database db;
    private CinemaSessionInserter sessionInserter;
    private CinemaSessionRemover sessionRemover;
    private GiftCardInserter cardInserter;
    private GiftCardRedeemTool cardRedeemTool;
    private MovieInserter movieInserter;
    private MovieModifier movieModifier;
    private MovieRemover movieRemover;
    private staffoperations.ReportGenerator staffReportGenerator;
    private manageroperations.ReportGenerator managerReportGenerator;
    private StaffInserter staffInserter;
    private StaffRemover staffRemover;


    public ManagerService(Scanner s, Database d) {
        scanner = s;
        db = d;
        sessionInserter = new CinemaSessionInserter(s, d);
        sessionRemover = new CinemaSessionRemover(s, d);
        cardInserter = new GiftCardInserter(s, d);
        cardRedeemTool = new GiftCardRedeemTool(s, d);
        movieInserter = new MovieInserter(s, d);
        movieModifier = new MovieModifier(s, d);
        movieRemover = new MovieRemover(s, d);
        staffReportGenerator = new staffoperations.ReportGenerator(s, d);
        managerReportGenerator = new manageroperations.ReportGenerator(s, d);
        staffInserter = new StaffInserter(s, d);
        staffRemover = new StaffRemover(s, d);
    }

    public void run() {
        System.out.println("Note: To quit, Enter \"-Exit-\"");
        System.out.println("Enter the number to operate: ");
        System.out.println("1.Add Movie 2.Remove Movie 3.Modify Movie");
        System.out.println("4.Enter Gift Card 5.Redeem Gift Card");
        System.out.println("6.Add Movie Session 7.Remove Movie Session");
        System.out.println("8.Add staff 9.Remove Staff");
        System.out.println("10.Generate Upcoming Movie Report 11.Generate Movie Session Report 12.Generate Cancellation Report");
        String userInput = scanner.nextLine();
        boolean success = true;
        switch (userInput) {
            case "1":
                success = movieInserter.run();
                if (success) {
                    System.out.println("Success: Movie added.");
                } else {
                    System.out.println("Error: Movie failed to be added.");
                }
                run();
            case "2":
                success = movieRemover.run();
                if (success) {
                    System.out.println("Success: Movie removed.");
                } else {
                    System.out.println("Error: Movie failed to be removed.");
                }
                run();
            case "3":
                success = movieModifier.run();
                if (success) {
                    System.out.println("Success: Movie Modified.");
                } else {
                    System.out.println("Error: Movie failed to be modified.");
                }
                run();
            case "4":
                success = cardInserter.run();
                if (success) {
                    System.out.println("Success: Gift Card added.");
                } else {
                    System.out.println("Error: Gift Card failed to be added.");
                }
                run();
            case "5":
                success = cardRedeemTool.run();
                if (success) {
                    System.out.println("Success: Gift Card redeemed.");
                } else {
                    System.out.println("Error: Gift Card failed to be redeemed.");
                }
            case "6":
                success = sessionInserter.run();
                if (success) {
                    System.out.println("Success: Movie Session added.");
                } else {
                    System.out.println("Error: Movie Session failed to be added.");
                }
                run();
            case "7":
                success = sessionRemover.run();
                if (success) {
                    System.out.println("Success: Movie Session removed.");
                } else {
                    System.out.println("Error: Movie failed to be removed.");
                }
                run();
            case "8":
                success = staffInserter.run();
                if (success) {
                    System.out.println("Success: Staff added.");
                } else {
                    System.out.println("Error: Staff failed to be added.");
                }
                run();
            case "9":
                success = staffRemover.run();
                if (success) {
                    System.out.println("Success: Staff removed.");
                } else {
                    System.out.println("Error: Staff failed to be removed.");
                }
                run();
            case "10":
                staffReportGenerator.generateUpcomingMovieReport();
                run();
            case "11":
                staffReportGenerator.generateMovieSessionReport();
                run();
            case "12":
                managerReportGenerator.getCancellationReport();
            case "-Exit":
                System.out.println("Operation exits.");
            default:
                System.out.println("Error: Invalid number.");
                run();
        }
    }
}




