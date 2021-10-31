package databaseutility;

import MTBMS.Database;

public class GetMovieGenre {
    public static String getMovieGenre(Database db, String movieName) {
        String query = "select genre from moviebooking_db.Movie where name_ = '" + movieName + "';";
        String genre = db.sql_getString(query,"genre");
        return genre;
    }
}
