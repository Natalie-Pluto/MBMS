package databaseutility;

import MTBMS.Database;

public class GetMovieCast {
    public static String getMovieCast(Database db, String movieName) {
        String query = "select * from moviebooking_db.Movie where lower(name_) = '" + movieName.replace("'","''") + "';";
        return(db.sql_getString(query,"cast_"));
    }
}
