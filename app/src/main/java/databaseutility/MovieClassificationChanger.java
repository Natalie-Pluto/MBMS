package databaseutility;

import java.util.Arrays;
import MTBMS.Database;

public class MovieClassificationChanger {
    private static final String[] validClassifications = {"r18+","g","pg","ma15+","m"};

    public static void changeMovieClassification(Database d, String movieName, String txt){
        if (!Arrays.asList(validClassifications).contains(txt)) return;
        String query = String.format("UPDATE moviebooking_db.Movie SET classification = '%s' WHERE name_ = '%s';", txt, movieName);
        d.sql_update(query);
    }
}
