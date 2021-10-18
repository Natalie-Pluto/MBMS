package databaseutility;

import MTBMS.Data;
import MTBMS.Database;
import java.sql.*;

public class MovieClassificationChanger {
    public static void changeMovieClassification(Data d, String movieName, String txt){

        String query = String.format("UPDATE Movie SET classification = '%s' WHERE name = '%s';", txt, movieName);
        d.databaseGetter().sql_update(query);
    }
}
