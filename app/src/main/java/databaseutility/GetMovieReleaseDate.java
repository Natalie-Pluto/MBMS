package databaseutility;

import MTBMS.Database;

;

public class GetMovieReleaseDate {
    public static String getMovieReleasedate(Database db, String movieName) {
        String query = "select * from moviebooking_db.Movie where lower(name_) = '" + movieName.replace("'","''") + "';";
        String releaseDate = db.sql_getString(query,"release_date");
        return(releaseDate);
    }
}
