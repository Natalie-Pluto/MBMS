package databaseutility;

import java.util.Arrays;
import MTBMS.Database;

public class MovieClassificationChanger {

    public static boolean changeMovieClassification(Database d, String movieName, String txt){
        String query = String.format("UPDATE moviebooking_db.Movie SET classification = '%s' WHERE name_ = '%s';", txt, movieName);
        boolean success = d.sql_update(query);
        return success;
    }
}
