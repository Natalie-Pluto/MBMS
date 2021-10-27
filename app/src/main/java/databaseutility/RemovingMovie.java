package databaseutility;
import MTBMS.Database;

public class RemovingMovie {
    public static boolean removeMovie(Database db, String movieName) {
        boolean updateStatus = db.sql_update("delete from moviebooking_db.Movie where name_ = '" + movieName + "';");
        return updateStatus;
    }
}
