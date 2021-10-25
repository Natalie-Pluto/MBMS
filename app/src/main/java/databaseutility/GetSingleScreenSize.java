package databaseutility;

import MTBMS.*;

public class GetSingleScreenSize {
    public static String getSingleScreenSize(Database db, String movieName, String cinemaName, String startTime){
        String query = "select mc.screen_type from moviebooking_db.cinema_session mc where mc.name = '" + startTime + "' and mc.movie = '" + movieName + "' and mc.cinema = '" + cinemaName + "';";
        return db.sql_getString(query, "screen_type");
    }
}
