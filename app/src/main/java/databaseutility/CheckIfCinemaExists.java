package databaseutility;

import MTBMS.Database;

public class CheckIfCinemaExists {
    public static boolean checkIfCinemaExists(Database db, String cinemaName) {
        String cinemaNameFromDB = db.sql_getString("select cinema_name from moviebooking_db.cinema where cinema_name = '" + cinemaName + "';", "cinema_name");
        if (cinemaNameFromDB == null) return false;
        return cinemaNameFromDB.equals(cinemaName);
    }
}
