package databaseutility;

import MTBMS.Database;
import utils.StringUtils;

public class UpdateScreenSize {
    public void updateSeats(Database db, String cinemaName, String movieName, String startTime, String screenType){
            db.sql_update(String.format("update cinema_session set screen_type = %s where " +
                    "cinema_name = '%s' and movie = '%s' and start_time = to_timestamp(%s);",
                    screenType,cinemaName,movieName,startTime));
        }
}
