package databaseutility;

import MTBMS.Database;

public class DirectorChanger {
    public static void changeDirectors(Database d, String movieName, String newDirector){
        String query = String.format("UPDATE Movie SET director = '%s' WHERE name = '%s';", newDirector, movieName);
        d.sql_update(query);
    }
}
