package databaseutility;

import MTBMS.Database;

public class SynopsisChanger {
    public static void changeSynopsis(Database d, String movieName, String newSynopsis){
        String query = String.format("UPDATE Movie SET synopsis = '%s' WHERE name = '%s';", newSynopsis, movieName);
        d.sql_update(query);
    }
} 
