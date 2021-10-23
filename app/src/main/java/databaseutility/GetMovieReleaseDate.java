package databaseutility;

import MTBMS.Database;

import java.util.Date;

public class GetMovieReleaseDate {
    public static Date getMovieReleasedate(Database db, String movieName) {
        String query = "select * from moviebooking_db.Movie where name = '" + movieName + "';";
        return(db.sql_getDate(query,"release_date"));
    }
}
