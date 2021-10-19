package databaseutility;

import MTBMS.Database;

public class MovieClassificationChanger {
    public static void changeMovieClassification(Database d, String movieName, String txt){

        String query = String.format("UPDATE Movie SET classification = '%s' WHERE name = '%s';", txt, movieName);
        d.sql_update(query);
    }
}
