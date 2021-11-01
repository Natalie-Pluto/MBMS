package staffoperations;

import MTBMS.Database;
import databaseutility.AddMovieSession;
import databaseutility.CheckIfCinemaExists;
import databaseutility.CheckIfMovieExists;
import databaseutility.CheckIfSessionExists;

import java.util.Scanner;

public class CinemaSessionInserter {
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

    public CinemaSessionInserter(Scanner scanner, Database db){
        s = scanner;
        d = db;
    }

    public String collectCinemaName(){
        System.out.println(PURPLE_BOLD + "Enter the name of the cinema: " + ANSI_RESET);
        String cinema = s.nextLine();
        boolean cinemaExist = CheckIfCinemaExists.checkIfCinemaExists(d, cinema);
        if(!cinemaExist){
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Cinema does not exist. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            cinema = collectCinemaName();
        }
        return cinema;
    }

    public String collectMovieName(){
        System.out.println(PURPLE_BOLD + "Enter the name of the movie: " + ANSI_RESET);
        String movieName = s.nextLine();
        boolean movieExist = CheckIfMovieExists.checkIfMovieExists(d, movieName);
        if(!movieExist){
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Movie does not exists. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            movieName = collectMovieName();
        }
        return movieName;
    }

    public String collectScreenType(){
        System.out.println(PURPLE_BOLD + "Enter the screen type: " + ANSI_RESET);
        String screenType = s.nextLine();
        if(!screenType.equals("Gold") && !screenType.equals("Silver") && !screenType.equals("Bronze")){
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Screen type is invalid. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            screenType = collectScreenType();
        }
        return screenType;
    }

    public String collectKidTicketPrice(){
        System.out.println(PURPLE_BOLD + "Enter the ticket price for kid: " + ANSI_RESET);
        String kidPrice = s.nextLine();
        try{
            Double.parseDouble(kidPrice);
        }catch(NumberFormatException e){
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Ticket Price is invalid. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            kidPrice = collectKidTicketPrice();
        }
        return kidPrice;
    }

    public String collectAdultTicketPrice(){
        System.out.println(PURPLE_BOLD + "Enter the ticket price for adult: " + ANSI_RESET);
        String adultPrice = s.nextLine();
        try{
            Double.parseDouble(adultPrice);
        }catch(NumberFormatException e){
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Ticket Price is invalid. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            adultPrice = collectAdultTicketPrice();
        }
        return adultPrice;
    }

    public String collectSeniorTicketPrice(){
        System.out.println(PURPLE_BOLD + "Enter the ticket price for senior: " + ANSI_RESET);
        String seniorPrice = s.nextLine();
        try{
            Double.parseDouble(seniorPrice);
        }catch(NumberFormatException e){
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Ticket Price is invalid. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            seniorPrice = collectSeniorTicketPrice();
        }
        return seniorPrice;
    }

    public String collectStudentTicketPrice(){
        System.out.println(PURPLE_BOLD + "Enter the ticket price for student: " + ANSI_RESET);
        String studentPrice = s.nextLine();
        try{
            Double.parseDouble(studentPrice);
        }catch(NumberFormatException e){
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Ticket Price is invalid. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            studentPrice = collectStudentTicketPrice();
        }
        return studentPrice;
    }

    public String collectStartTime(){
        System.out.println(PURPLE_BOLD + "Enter the start time in YYYY-MM-DD HH:MI:SS format:" + ANSI_RESET);
        String startTime = s.nextLine();
        return startTime;
    }

    public boolean run(){
        String cinemaName = collectCinemaName();
        String movieName = collectMovieName();
        String screenType = collectScreenType();
        String startTime = collectStartTime();
        String kidPrice = collectKidTicketPrice();
        String adultPrice = collectAdultTicketPrice();
        String seniorPrice = collectSeniorTicketPrice();
        String studentPrice = collectStudentTicketPrice();
        boolean sessionExist = CheckIfSessionExists.checkIfSessionExists(d, cinemaName, movieName, screenType, startTime);
        if(sessionExist){
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Movie Session already exists. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            return false;
        }else{
            boolean success = AddMovieSession.addMovieSession(d, cinemaName, movieName, screenType, startTime, kidPrice, adultPrice, seniorPrice, studentPrice);
            return success;
        }

    }
}
