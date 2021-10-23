package databaseutility;

import MTBMS.Database;

public class GetMovieClassification {
    public static String getMovieClassification(Database db, String movieName) {
        String query = "select * from moviebooking_db.Movie where name = '" + movieName + "';";
        return(db.sql_getString(query,"classification"));
    }
}
