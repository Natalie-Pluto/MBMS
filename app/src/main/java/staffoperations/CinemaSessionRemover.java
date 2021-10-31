package staffoperations;

import MTBMS.Database;
import databaseutility.*;

import java.util.Scanner;

public class CinemaSessionRemover {
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

    public CinemaSessionRemover(Scanner scanner, Database db){
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
        if(screenType.length() > 6){
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Screen type is invalid. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            screenType = collectScreenType();
        }
        return screenType;
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
        boolean sessionExist = CheckIfSessionExists.checkIfSessionExists(d, cinemaName, movieName, screenType, startTime);
        if(!sessionExist){
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Movie Session does not exists. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            return false;
        }else{
            boolean success = RemovingSession.removeSession(d, cinemaName, movieName, screenType, startTime);
            return success;
        }

    }
}
