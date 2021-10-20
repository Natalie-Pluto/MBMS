package databaseutility;

import MTBMS.Database;

public class GetMovieDirectors {
    public static String getDirectors(Database db, int movie_id) {
        String query = "select * from moviebooking_db.Movie where movie_id = " + movie_id + ";";
        String  directors = db.sql_getString(query,"directors");
        if (directors == null) return "";
        return(directors);
    }
}
