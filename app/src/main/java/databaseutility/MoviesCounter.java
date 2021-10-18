package databaseutility;

import MTBMS.Data;
import MTBMS.Database;
import java.sql.*;

public class MoviesCounter {
    public static int countMovies(Data d) {
        String query = "SELECT COUNT(*) FROM Movie;";
        Integer i = d.databaseGetter().sql_getInt(query, "count");
        int count = i.intValue();
        return count;
    }
}
