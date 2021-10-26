package databaseutility;

import MTBMS.Database;

public class SetSessionTime {
    public static boolean setStartTime(Database db, String movieName, String startTime, String cinemaName, String screenType, String newStartTime) {
        boolean updateStatus = db.sql_update(String.format("UPDATE moviebooking_db.Cinema_Session SET start_time = '%s' where cinema = '%s' and screen_type = '%s' and movie = '%s' and start_time = '%s';", newStartTime, cinemaName, screenType, movieName, startTime));
        return updateStatus;
    }

    public static boolean setEndTime(Database db, String movieName, String startTime, String cinemaName, String screenType, String newEndTime) {
        boolean updateStatus = db.sql_update(String.format("UPDATE moviebooking_db.Cinema_Session SET end_time = '%s' where cinema = '%s' and screen_type = '%s' and movie = '%s' and start_time = '%s';", newEndTime, cinemaName, screenType, movieName, startTime));
        return updateStatus;
    }
}