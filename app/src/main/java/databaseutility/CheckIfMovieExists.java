package databaseutility;

import MTBMS.Database;

public class CheckIfMovieExists {
    public static boolean checkIfMovieExists(Database db, String movieName) {
        String movieNameFromDB = db.sql_getString("select name_ from moviebooking_db.Movie where name_ = '" + movieName.replace("'", "''") + "';", "name_");
        if (movieNameFromDB == null) return false;
        return movieNameFromDB.equals(movieName);
    }
}
