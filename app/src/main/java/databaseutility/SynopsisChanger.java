package databaseutility;

import MTBMS.Database;

public class SynopsisChanger {
    public static void changeSynopsis(Database d, String movieName, String newSynopsis){
        String query = String.format("UPDATE moviebooking_db.Movie SET synopsis = '%s' WHERE name_ = '%s';", newSynopsis, movieName);
        d.sql_update(query);
    }
} 
