package staffoperations;

import MTBMS.Database;

import java.util.Scanner;

public class StaffService {
    private Scanner scanner;
    private Database db;
    private CinemaSessionInserter sessionInserter;
    private CinemaSessionRemover sessionRemover;
    private GiftCardInserter cardInserter;
    private GiftCardRedeemTool cardRedeemTool;
    private MovieInserter movieInserter;
    private MovieModifier movieModifier;
    private MovieRemover movieRemover;
    private ReportGenerator reportGenerator;

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


    public StaffService(Scanner s, Database d) {
        scanner = s;
        db = d;
        sessionInserter = new CinemaSessionInserter(s, d);
        sessionRemover = new CinemaSessionRemover(s, d);
        cardInserter = new GiftCardInserter(s, d);
        cardRedeemTool = new GiftCardRedeemTool(s, d);
        movieInserter = new MovieInserter(s, d);
        movieModifier = new MovieModifier(s, d);
        movieRemover = new MovieRemover(s, d);
        reportGenerator = new ReportGenerator(s, d);
    }

    public void run() {
        System.out.println(YELLOW_BOLD_BRIGHT + "Note: To quit, Enter \"-Exit-\"" + ANSI_RESET + "\n");
        System.out.println(PURPLE_BOLD + "Enter the number to operate: " + ANSI_RESET);
        System.out.println("1.Add Movie 2.Remove Movie 3.Modify Movie 4.Enter Gift Card 5.Redeem Gift Card");
        System.out.println("6.Add Movie Session 7.Remove Movie Session");
        System.out.println("8.Generate Upcoming Movie Report 9.Generate Movie Session Report");
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
                reportGenerator.generateUpcomingMovieReport();
                run();
                break;
            case "9":
                reportGenerator.generateMovieSessionReport();
                run();
                break;
            case "-Exit":
                System.out.println(YELLOW_BOLD_BRIGHT + "Operation exits." + ANSI_RESET + "\n");
                break;
            default:
                System.out.println("\n============================================");
                System.out.println(RED_BOLD + "Error: Invalid number. (｡´︿`｡)" + ANSI_RESET);
                System.out.println("============================================\n");
                run();
        }
    }
}




