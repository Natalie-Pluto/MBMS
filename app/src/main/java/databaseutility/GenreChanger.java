package databaseutility;

import MTBMS.Database;

public class GenreChanger {
    public static boolean changeGenre(Database d, String movieName, String newGenre){
        String query = String.format("UPDATE moviebooking_db.Movie SET genre = '%s' WHERE name_ = '%s';", newGenre.replace("'", "''"), movieName.replace("'", "''"));
        boolean success = d.sql_update(query);
        return success;
    }
}
