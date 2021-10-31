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

    public CinemaSessionInserter(Scanner scanner, Database db){
        s = scanner;
        d = db;
    }

    public String collectCinemaName(){
        System.out.println("Enter the name of the cinema: ");
        String cinema = s.nextLine();
        boolean cinemaExist = CheckIfCinemaExists.checkIfCinemaExists(d, cinema);
        if(!cinemaExist){
            System.out.println("Error: Cinema does not exist.");
            cinema = collectCinemaName();
        }
        return cinema;
    }

    public String collectMovieName(){
        System.out.println("Enter the name of the movie: ");
        String movieName = s.nextLine();
        boolean movieExist = CheckIfMovieExists.checkIfMovieExists(d, movieName);
        if(!movieExist){
            System.out.println("Error: Movie does not exists.");
            movieName = collectMovieName();
        }
        return movieName;
    }

    public String collectScreenType(){
        System.out.println("Enter the screen type");
        String screenType = s.nextLine();
        if(screenType.length() > 6){
            System.out.println("Error: Screen type is invalid.");
            screenType = collectScreenType();
        }
        return screenType;
    }

    public String collectStartTime(){
        System.out.println("Enter the start time in YYYY-MM-DD HH:MI:SS format:");
        String startTime = s.nextLine();
        return startTime;
    }

    public boolean run(){
        String cinemaName = collectCinemaName();
        String movieName = collectMovieName();
        String screenType = collectScreenType();
        String startTime = collectStartTime();
        boolean sessionExist = CheckIfSessionExists.checkIfSessionExists(d, cinemaName, movieName, screenType, startTime);
        if(sessionExist){
            System.out.println("Error: Movie Session already exists.");
            return false;
        }else{
            boolean success = AddMovieSession.addMovieSession(d, cinemaName, movieName, screenType, startTime);
            return success;
        }

    }
}
