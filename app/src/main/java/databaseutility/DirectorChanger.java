package databaseutility;

import MTBMS.Database;

public class DirectorChanger {
    public static boolean changeDirectors(Database d, String movieName, String newDirectors){
        String query = String.format("UPDATE moviebooking_db.Movie SET directors = '%s' WHERE name_ = '%s';", newDirectors, movieName);
        boolean success = d.sql_update(query);
        return success;
    }
}
