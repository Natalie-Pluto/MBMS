package databaseutility;

import MTBMS.Database;

public class GetMovieDirectors {
    public String getDirectors(Database db, String movie_id) {
        String query = "select * from moviebooking_db.Movie where movie_id = '" + movie_id + "';";
        return(db.sql_getString(query,"directors"));
    }
}
