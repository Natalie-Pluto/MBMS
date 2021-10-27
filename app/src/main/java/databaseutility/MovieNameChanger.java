package databaseutility;

import MTBMS.Database;

public class  MovieNameChanger {

    public static void changeMovieName(Database d, String previousName, String newName) {

        String query = String.format("UPDATE moviebooking_db.movie SET name_ = '%s' WHERE name_ = '%s';", newName, previousName);
        d.sql_update(query);
    }
}
