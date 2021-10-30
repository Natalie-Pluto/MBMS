package databaseutility;

import MTBMS.Database;

public class ReleaseDateChanger {
    public static void changeReleaseDate(Database d, String movieName, String newDate){
        String query = String.format("UPDATE moviebooking_db.Movie SET release_date = '%s' WHERE name_ = '%s';", newDate, movieName.replace("'", "''"));
        d.sql_update(query);
    }
}
