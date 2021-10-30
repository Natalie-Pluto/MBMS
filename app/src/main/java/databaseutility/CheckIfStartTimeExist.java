package databaseutility;

import MTBMS.Database;

/* public class CheckIfStartTimeExist {
    public static boolean checkIfStartTimeExists(Database db, String movieName, String cinemaName, String startTime) {
        String movieNameFromDB = db.sql_getString("select mc.start_time from moviebooking_db.cinema_session mc where mc.name = '" + startTime + "' and mc.movie = '" + movieName + "' and mc.cinema = '" + cinemaName + "';",  "name");
        if (movieNameFromDB == null) return false;
        return movieNameFromDB.equals(startTime);
    }
} */
