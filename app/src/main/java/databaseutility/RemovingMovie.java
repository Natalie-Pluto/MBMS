package databaseutility;
import MTBMS.Database;

public class RemovingMovie {
    public static boolean removeMovie(Database db, int movieName) {
        boolean updateStatus = db.sql_update("delete from moviebooking_db.Movie where movie_id = " + movieName + "';");
        return updateStatus;
    }
}
