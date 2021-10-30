package databaseutility;

import MTBMS.Database;
import utils.StringUtils;

public class GetEndTime {
    public static String getEndTime(Database db, String cinemaName, String movieName, String screenType, String startTime){
        if (StringUtils.isEmpty(cinemaName) || StringUtils.isEmpty(movieName)){
            return "";
        }else {
            return db.sql_getString(String.format("select end_time from moviebooking_db.cinema_session " +
                    "where cinema = '%s' and movie = '%s' and screen_type = '%s' and start_time = '%s';",cinemaName.replace("'", "''"),movieName.replace("'", "''"),screenType,startTime), "end_time");
        }
    }
}
