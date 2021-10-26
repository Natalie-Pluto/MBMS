package databaseutility;

import MTBMS.*;

public class GetSingleScreenSize {
    public static String getSingleScreenSize(Database db, String movieName, String cinemaName, String startTime){
        String query = "select screen_type from moviebooking_db.cinema_session where start_time = '" + startTime + "' and movie = '" + movieName + "' and cinema = '" + cinemaName + "';";
        return db.sql_getString(query, "screen_type");
    }
}
