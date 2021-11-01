package manageroperations;

import MTBMS.BookingSystem;
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

    public void run() throws InterruptedException {
        BookingSystem.seperator();
        System.out.println("\n============================================");
        System.out.println("Enter the number to operate: ");
        System.out.println("============================================\n");
        System.out.println(PURPLE_BOLD + "1.Add Movie" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "2.Remove Movie" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "3.Modify Movie" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "4.Enter Gift Card" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "5.Redeem Gift Card" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "6.Add Movie Session" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "7.Remove Movie Session" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "8.Add staff" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "9.Remove Staff" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "10.Generate Upcoming Movie Report" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "11.Generate Movie Session Report" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "12.Generate Cancellation Report" + ANSI_RESET);
        System.out.println(PURPLE_BOLD + "13.Log Out" + ANSI_RESET + "\n");
        BookingSystem.seperator();
        String userInput = scanner.nextLine();
        boolean success = true;
        switch (userInput) {
            case "1":
                success = movieInserter.run();
                if (success) {
                    System.out.println(PURPLE_BOLD_BRIGHT + "Success: Movie added. (｡･ω･｡)ﾉ" + ANSI_RESET);
                } else {
                    System.out.println("\n============================================");
                    System.out.println(RED_BOLD + "Error: Movie failed to be added. (｡´︿`｡)" + ANSI_RESET);
                    System.out.println("============================================\n");
                }
                run();
                break;
            case "2":
                success = movieRemover.run();
                if (success) {
                    System.out.println(PURPLE_BOLD_BRIGHT + "Success: Movie removed. (｡･ω･｡)ﾉ" + ANSI_RESET);
                } else {
                    System.out.println("\n============================================");
                    System.out.println(RED_BOLD + "Error: Movie failed to be removed. (｡´︿`｡)" + ANSI_RESET);
                    System.out.println("============================================\n");
                }
                run();
                break;
            case "3":
                success = movieModifier.run();
                if (success) {
                    System.out.println(PURPLE_BOLD_BRIGHT + "Success: Movie Modified. (｡･ω･｡)ﾉ" + ANSI_RESET);
                } else {
                    System.out.println("\n============================================");
                    System.out.println(RED_BOLD + "Error: Movie failed to be modified. (｡´︿`｡)" + ANSI_RESET);
                    System.out.println("============================================\n");
                }
                run();
                break;
            case "4":
                success = cardInserter.run();
                if (success) {
                    System.out.println(PURPLE_BOLD_BRIGHT + "Success: Gift Card added. (｡･ω･｡)ﾉ" + ANSI_RESET);
                } else {
                    System.out.println("\n============================================");
                    System.out.println(RED_BOLD + "Error: Gift Card failed to be added. (｡´︿`｡)" + ANSI_RESET);
                    System.out.println("============================================\n");
                }
                run();
                break;
            case "5":
                success = cardRedeemTool.run();
                if (success) {
                    System.out.println(PURPLE_BOLD_BRIGHT + "Success: Gift Card redeemed. (｡･ω･｡)ﾉ" + ANSI_RESET);
                } else {
                    System.out.println("\n============================================");
                    System.out.println(RED_BOLD + "Error: Gift Card failed to be redeemed. (｡´︿`｡)" + ANSI_RESET);
                    System.out.println("============================================\n");
                }
                run();
                break;
            case "6":
                success = sessionInserter.run();
                if (success) {
                    System.out.println(PURPLE_BOLD_BRIGHT + "Success: Movie Session added. (｡･ω･｡)ﾉ" + ANSI_RESET);
                } else {
                    System.out.println("\n============================================");
                    System.out.println(RED_BOLD + "Error: Movie Session failed to be added. (｡´︿`｡)" + ANSI_RESET);
                    System.out.println("============================================\n");
                }
                run();
                break;
            case "7":
                success = sessionRemover.run();
                if (success) {
                    System.out.println(PURPLE_BOLD_BRIGHT + "Success: Movie Session removed. (｡･ω･｡)ﾉ" + ANSI_RESET);
                } else {
                    System.out.println("\n============================================");
                    System.out.println(RED_BOLD + "Error: Movie failed to be removed. (｡´︿`｡)" + ANSI_RESET);
                    System.out.println("============================================\n");
                }
                run();
                break;
            case "8":
                success = staffInserter.run();
                if (success) {
                    System.out.println(PURPLE_BOLD_BRIGHT + "Success: Staff added. (｡･ω･｡)ﾉ" + ANSI_RESET);
                } else {
                    System.out.println("\n============================================");
                    System.out.println(RED_BOLD + "Error: Staff failed to be added. (｡´︿`｡)" + ANSI_RESET);
                    System.out.println("============================================\n");
                }
                run();
                break;
            case "9":
                success = staffRemover.run();
                if (success) {
                    System.out.println(PURPLE_BOLD_BRIGHT + "Success: Staff removed. (｡･ω･｡)ﾉ" + ANSI_RESET);
                } else {
                    System.out.println("\n============================================");
                    System.out.println(RED_BOLD + "Error: Staff failed to be removed. (｡´︿`｡)" + ANSI_RESET);
                    System.out.println("============================================\n");

                }
                run();
                break;
            case "10":
                staffReportGenerator.generateUpcomingMovieReport();
                run();
                break;
            case "11":
                staffReportGenerator.generateMovieSessionReport();
                run();
                break;
            case "12":
                managerReportGenerator.getCancellationReport();
                run();
                break;
            case "13":
                BookingSystem.logOut();
                break;
            default:
                System.out.println("\n============================================");
                System.out.println(RED_BOLD + "Error: Invalid number. (｡´︿`｡)" + ANSI_RESET);
                System.out.println("============================================\n");
                run();
        }
    }
}




