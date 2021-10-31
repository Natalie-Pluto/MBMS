package staffoperations;

import MTBMS.Database;
import databaseutility.CheckIfMovieExists;
import databaseutility.RemovingMovie;
import java.util.Scanner;

public class MovieRemover {
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

    public MovieRemover(Scanner scanner, Database db){
        s = scanner;
        d = db;
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

    public boolean run(){
        String movieName = collectMovieName();
        boolean success = RemovingMovie.removeMovie(d, movieName);
        return success;
    }
}
