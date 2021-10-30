package movieManagement;

import MTBMS.Database;
import databaseutility.*;


import java.util.List;
import java.util.Locale;

public class MovieDetails {
    public static boolean movieDetails (Database db, String movieName) {
        String synopsis = GetMovieSynopsis.getMovieSynopsis(db, movieName.replace("'", "''"));
        String classicfication = GetMovieClassification.getMovieClassification(db, movieName.replace("'", "''"));
        String releaseDate = GetMovieReleaseDate.getMovieReleasedate(db, movieName.replace("'", "''"));
        String director = GetMovieDirectors.getDirectors(db, movieName.replace("'", "''"));
        String cast = GetMovieCast.getMovieCast(db, movieName.replace("'", "''"));
        List<String> screenSize = GetMovieScreensize.getMovieScreensize(db, movieName.replace("'", "''"));

        System.out.println(PURPLE_BOLD_BRIGHT + movieName.toUpperCase(Locale.ROOT) + ANSI_RESET + "\n");
        System.out.println(YELLOW + synopsis + ANSI_RESET + "\n");
        System.out.println("Classification: " + PURPLE_BOLD + "[" + classicfication + "]" + ANSI_RESET);
        System.out.println("Release String: " + YELLOW_BOLD + releaseDate + ANSI_RESET);
        System.out.println("Director: " + director);
        System.out.println("Cast: " + cast);
        System.out.println("Screen Size: " + screenSize);
        return true;
    }
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
}
