package databaseutility;

import MTBMS.Database;

public class ReleaseDateChanger {

    public static boolean changeReleaseDate(Database d, String movieName, String newDate){
        String query = String.format("UPDATE moviebooking_db.Movie SET release_date = '%s' WHERE name_ = '%s';", newDate, movieName);
        boolean success = d.sql_update(query);
        return success;
    }
}
