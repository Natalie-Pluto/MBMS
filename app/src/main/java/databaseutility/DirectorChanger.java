package databaseutility;

import MTBMS.Database;

public class DirectorChanger {
    public static void changeDirectors(Database d, int movieID, String newDirectors){
        String query = String.format("UPDATE Movie SET directors = '%s' WHERE movieID = '%s';", newDirectors, movieID);
        d.sql_update(query);
    }
}
