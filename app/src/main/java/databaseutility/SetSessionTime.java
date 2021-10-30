package databaseutility;

import MTBMS.Database;

public class SetSessionTime {
/*     public static boolean setStartTime(Database db, String movieName, String startTime, String cinemaName, String screenType, String newStartTime) {
        boolean updateStatus = db.sql_update(String.format("UPDATE moviebooking_db.Cinema_Session SET start_time = '%s' where cinema = '%s' and screen_type = '%s' and movie = '%s' and start_time = '%s' on conflict (cinema, screen_type, movie, start_time) do nothing;", newStartTime, cinemaName.replace("'", "''"), screenType, movieName.replace("'", "''"), startTime));
        return updateStatus;
    } */

    public static boolean setEndTime(Database db, String cinemaName, String movieName, String screenType, String startTime, String newEndTime) {
        boolean updateStatus = db.sql_update(String.format("UPDATE moviebooking_db.Cinema_Session SET end_time = '%s' where cinema = '%s' and screen_type = '%s' and movie = '%s' and start_time = '%s';", newEndTime, cinemaName.replace("'", "''"), screenType, movieName.replace("'", "''"), startTime));
        return updateStatus;
    }
}