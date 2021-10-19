package databaseutility;

import MTBMS.Database;

public class ReleaseDateChanger {
    public static void changeReleaseDate(Database d, String movieName, String newDate){
        String query = String.format("UPDATE Movie SET release_date = '%s' WHERE name = '%s';", newDate, movieName);
        d.sql_update(query);
    }
}
