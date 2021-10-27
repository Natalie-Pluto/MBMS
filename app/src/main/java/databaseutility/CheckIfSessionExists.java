package databaseutility;

import MTBMS.Database;

public class CheckIfSessionExists {
    public static boolean checkIfSessionExists(Database db, String cinemaName, String movieName, String screenType, String startTime) {
        String movieNameFromDB = db.sql_getString(String.format("select movie from moviebooking_db.Cinema_Session where cinema = '%s' and movie = '%s' and start_time = '%s' and screen_type = '%s'" + ";",cinemaName.replace("'", "''"),movieName.replace("'", "''"),startTime,screenType),"movie");
        if (movieNameFromDB == null) return false;
        return movieNameFromDB.equals(movieName);
    }
}