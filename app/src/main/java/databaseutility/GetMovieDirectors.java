package databaseutility;

import MTBMS.Database;

public class GetMovieDirectors {
    public static String getDirectors(Database db, String movieName) {
        String query = "select * from moviebooking_db.Movie where name_ = '" + movieName + "';";
        String  directors = db.sql_getString(query,"directors");
        if (directors == null) return "";
        return(directors);
    }
}
