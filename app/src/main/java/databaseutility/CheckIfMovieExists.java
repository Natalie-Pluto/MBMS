package databaseutility;

import MTBMS.Database;

public class CheckIfMovieExists {
    public static boolean checkIfMovieExists(Database db, String movieName) {
        String movieNameFromDB = db.sql_getString("select name from moviebooking_db.Movie where name = '" + movieName.replace("'", "''") + "';", "name");
        if (movieNameFromDB == null) return false;
        return movieNameFromDB.equals(movieName);
    }
}
