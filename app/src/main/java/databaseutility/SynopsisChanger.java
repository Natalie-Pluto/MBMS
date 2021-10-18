package databaseutility;

import MTBMS.Data;
import MTBMS.Database;
import java.sql.*;

public class SynopsisChanger {
    public static void changeSynopsis(Data d, String movieName, String newSynopsis){
        String query = String.format("UPDATE Movie SET synopsis = '%s' WHERE name = '%s';", newSynopsis, movieName);
        d.databaseGetter().sql_update(query);
    }
}
