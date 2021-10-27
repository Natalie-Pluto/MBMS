package databaseutility;

import MTBMS.Database;

import java.util.Date;

public class GetMovieReleaseDate {
    public static String getMovieReleasedate(Database db, String movieName) {
        String query = "select * from moviebooking_db.Movie where name = '" + movieName.replace("'","''") + "';";
        String releaseDate = db.sql_getString(query,"release_date");
        return(releaseDate);
    }
}
