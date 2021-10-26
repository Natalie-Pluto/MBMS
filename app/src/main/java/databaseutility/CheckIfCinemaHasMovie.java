package databaseutility;
import MTBMS.Database;

public class CheckIfCinemaHasMovie {
    public static boolean checkIfCinemaHasMovie(Database db, String movieName, String cinemaName) {
        String movieNameFromDB = db.sql_getString("select movie from moviebooking_db.cinema_session where movie = '" + movieName.replace("'", "''") + "' and cinema = '" + cinemaName + "';",  "movie");
        if (movieNameFromDB == null) return false;
        return movieNameFromDB.equals(movieName);
    }
}

