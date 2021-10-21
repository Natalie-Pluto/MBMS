package databaseutility;

import MTBMS.Database;
import utils.StringUtils;

public class GetEndTime {
    public static String getEndTime(Database db, String cinemaName,String movieName){
        if (StringUtils.isEmpty(cinemaName) || StringUtils.isEmpty(movieName)){
            return "";
        }else {
            return db.sql_getString(String.format("select end_time from cinema_session " +
                    "where cinema = '%s' and movie = '%s';",cinemaName,movieName), "end_time");
        }
    }
}
