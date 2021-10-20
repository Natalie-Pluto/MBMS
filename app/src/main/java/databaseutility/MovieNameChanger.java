package databaseutility;

import MTBMS.Database;

public class  MovieNameChanger {
    public static void changeMovieName(Database d, String previousName, String newName) {
        String query = String.format("UPDATE Movie SET name = '%s' WHERE name = '%s';", newName, previousName);
        d.sql_update(query);
    }
}
