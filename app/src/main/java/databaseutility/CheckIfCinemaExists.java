package databaseutility;

import MTBMS.Database;

public class CheckIfCinemaExists {
    public static Boolean checkIfCinemaExists(Database db, String cinemaName) {
        String cinemaNameFromDB = db.sql_getString("select cinema_name from moviebooking_db.Cinema where cinema_name = '" + cinemaName.replace("'", "''") + "';", "cinema_name");
        if (cinemaNameFromDB == null) return false;
        //System.out.println(cinemaNameFromDB.equals(cinemaName));
        return cinemaNameFromDB.equals(cinemaName);
    }
}
