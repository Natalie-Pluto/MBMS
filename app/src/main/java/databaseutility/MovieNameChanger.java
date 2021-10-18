package databaseutility;

import MTBMS.Data;
import MTBMS.Database;
import java.sql.*;

public class MovieNameChanger {
    public static void changeMovieName(Data d, String previousName, String newName) {

        String query = String.format("UPDATE Movie SET name = '%s' WHERE name = '%s';", newName, previousName);
        d.databaseGetter().sql_update(query);
    }
}
