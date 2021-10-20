package databaseutility;
import MTBMS.Database;

public class GetMovieID {
    public static int getMovieID(Database db, String name, String classification, String releaseDate) {
        String query = "select * from moviebooking_db.Movie where name = '" + name + "' and classification = '" + classification + "' and release_date = '" + releaseDate + "';";
        int id = db.sql_getInt(query,"movie_id");
        return id;
    }
}
