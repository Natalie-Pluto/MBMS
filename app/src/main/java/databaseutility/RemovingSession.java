package databaseutility;
import MTBMS.Database;

public class RemovingSession {
    public static boolean removeSession(Database db, String cinemaName, String movieName, String screenType, String startTime) {
        boolean updateStatus = db.sql_update(String.format("delete from moviebooking_db.Cinema_Session where cinema = '%s' and screen_type = '%s' and movie = '%s' and start_time = '%s';", cinemaName.replace("'","''"), screenType, movieName.replace("'","''"), startTime));
        return updateStatus;
    }

    public static boolean removeSession(Database db, int sessionID) {
        boolean updateStatus = db.sql_update(String.format("delete from moviebooking_db.Cinema_Session where session_id = '%s';", sessionID));
        return updateStatus;
    }
}
