package databaseutility;

import MTBMS.Database;

public class GetMovieDirectors {
    public static String getDirectors(Database db, int movieName) {
        String query = "select * from moviebooking_db.Movie where movie_id = " + movieName + ";";
        String  directors = db.sql_getString(query,"directors");
        if (directors == null) return "";
        return(directors);
    }
}
