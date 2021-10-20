package databaseutility;

import MTBMS.Database;

public class CheckIfMovieExists {
    public static boolean checkIfMovieExists(Database db, int movieID) {
        Integer movieIDFromDB = db.sql_getInt("select username from moviebooking_db.Movie where movie_id = " + movieID + ";", "movie_id");
        if (movieIDFromDB == null) return false;
        return movieIDFromDB == movieID;
    }
}
