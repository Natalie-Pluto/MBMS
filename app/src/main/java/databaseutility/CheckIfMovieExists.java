package databaseutility;

import MTBMS.Database;

public class CheckIfMovieExists {
    public static boolean checkIfMovieExists(Database db, int movieName) {
        Integer movieNameFromDB = db.sql_getInt("select username from moviebooking_db.Movie where movie_id = " + movieName + ";", "movie_id");
        if (movieNameFromDB == null) return false;
        return movieNameFromDB.equals(movieName);
    }
}
