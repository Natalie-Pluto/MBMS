package movieManagement;

import MTBMS.Database;
import databaseutility.*;

import java.util.Date;
import java.util.List;

public class MovieDetails {
    public static boolean movieDetails (Database db, String movieName) {
        String synopsis = GetMovieSynopsis.getMovieSynopsis(db, movieName.replace("'", "''"));
        String classicfication = GetMovieClassification.getMovieClassification(db, movieName.replace("'", "''"));
        Date releaseDate = GetMovieReleaseDate.getMovieReleasedate(db, movieName.replace("'", "''"));
        String director = GetMovieDirectors.getDirectors(db, movieName.replace("'", "''"));
        String cast = GetMovieCast.getMovieCast(db, movieName.replace("'", "''"));
        Date showingDate = GetMovieShowDate.getMovieShowDate(db, movieName.replace("'", "''"));
        List<String> screenSize = GetMovieScreensize.getMovieScreensize(db, movieName.replace("'", "''"));

        System.out.println("<<" + movieName + ">>");
        System.out.println(YELLOW + synopsis + ANSI_RESET);
        System.out.println("Classification: " + PURPLE_BOLD + "[" + classicfication + "]" + ANSI_RESET);
        System.out.println("Release Date: " + YELLOW_BOLD + releaseDate + ANSI_RESET);
        System.out.println("Director: " + director);
        System.out.println("Cast: " + cast);
        System.out.println("Screen Size: " + screenSize);
        return true;
    }
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String YELLOW = "\033[0;33m";  // YELLOW
}
