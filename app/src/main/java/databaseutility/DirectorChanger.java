package databaseutility;

import MTBMS.Database;

public class DirectorChanger {
    public static void changeDirectors(Database d, String movieName, String newDirectors){
        System.out.println(newDirectors + " PLS");
        String query = String.format("UPDATE moviebooking_db.Movie SET directors = '%s' WHERE name_ = '%s';", newDirectors, movieName);
        System.out.println(d.sql_update(query) + " PGUIhhh");
    }
}
