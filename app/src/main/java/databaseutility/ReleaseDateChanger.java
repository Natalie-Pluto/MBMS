package databaseutility;

import MTBMS.Data;
import MTBMS.Database;
import java.sql.*;

public class ReleaseDateChanger {
    public static void changeReleaseDate(Data d, String movieName, String newDate){
        String query = String.format("UPDATE Movie SET release_date = '%s' WHERE name = '%s';", newDate, movieName);
        d.databaseGetter().sql_update(query);
    }
}
