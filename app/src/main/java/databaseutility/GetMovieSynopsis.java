package databaseutility;

import MTBMS.Database;

public class GetMovieSynopsis {
    public static String getMovieSynopsis(Database db, String movieName) {
        String query = "select * from moviebooking_db.Movie where name_ = '" + movieName.replace("'","''") + "';";
        return(db.sql_getString(query,"synopsis"));
    }
}
