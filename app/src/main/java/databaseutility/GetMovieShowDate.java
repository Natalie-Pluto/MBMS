package databaseutility;

import MTBMS.Database;

import java.util.Date;

public class GetMovieShowDate {
    public static Date getMovieShowDate(Database db, String movieName) {
        String query = "select * from moviebooking_db.Movie where name = '" + movieName + "';";
        Date showDate = db.sql_getDate(query, "showing_date");
        return showDate;
    }
}
