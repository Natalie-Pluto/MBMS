package databaseutility;

import MTBMS.Data;
import MTBMS.Database;
import java.sql.*;

public class DirectorChanger {
    public static void changeDirectors(Data d, String movieName, String newDirector){
        String query = String.format("UPDATE Movie SET director = '%s' WHERE name = '%s';", newDirector, movieName);
        d.databaseGetter().sql_update(query);
    }
}
