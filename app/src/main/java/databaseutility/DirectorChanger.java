package databaseutility;

import MTBMS.Database;

public class DirectorChanger {
    public static void changeDirectors(Database d, String movieName, String newDirectors){
        String query = String.format("UPDATE Movie SET director = '%s' WHERE name = '%s';", newDirectors, movieName);
        d.sql_update(query);
    }
}
