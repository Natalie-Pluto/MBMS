package databaseutility;

import MTBMS.Database;

public class GetMovieCast {
    public static String getMovieCast(Database db, String movieName) {
        String query = "select * from moviebooking_db.Movie where name = '" + movieName + "';";
        return(db.sql_getString(query,"cast"));
    }
}
