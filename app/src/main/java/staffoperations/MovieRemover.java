package staffoperations;

import MTBMS.Database;
import databaseutility.CheckIfMovieExists;
import databaseutility.RemovingMovie;
import java.util.Scanner;

public class MovieRemover {
    private Scanner s;
    private Database d;

    public MovieRemover(Scanner scanner, Database db){
        s = scanner;
        d = db;
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

    public boolean run(){
        String movieName = collectMovieName();
        boolean success = RemovingMovie.removeMovie(d, movieName);
        return success;
    }
}
