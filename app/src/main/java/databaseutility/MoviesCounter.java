package databaseutility;

import MTBMS.Database;

public class MoviesCounter {
    public static int countMovies(Database d) {
        String query = "SELECT COUNT(*) FROM moviebooking_db.Movie;";
        Integer i = d.sql_getInt(query, "count");
        int count = i.intValue();
        return count;
    }
}
