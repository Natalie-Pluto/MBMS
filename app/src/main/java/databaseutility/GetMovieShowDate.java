package databaseutility;

import MTBMS.Database;

;

public class GetMovieShowDate {
    public static String getMovieShowDate(Database db, String movieName) {
        String query = "select * from moviebooking_db.Movie where name = '" + movieName + "';";
        String showDate = db.sql_getDate(query, "showing_date");
        return showDate;
    }
}
