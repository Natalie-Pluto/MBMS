package databaseutility;
import MTBMS.Database;

public class CheckIfCinemaHasMovie {
    public static boolean checkIfCinemaHasMovie(Database db, String movieName, String cinemaName) {
        String movieNameFromDB = db.sql_getString("select mc.cinema from moviebooking_db.cinema_session mc where mc.movie = '" + movieName + "' and mc.cinema = '" + cinemaName + "';",  "name");
        if (movieNameFromDB == null) return false;
        return movieNameFromDB.equals(cinemaName);
    }
}

