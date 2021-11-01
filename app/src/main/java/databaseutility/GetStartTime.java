package databaseutility;

import MTBMS.Database;
import utils.StringUtils;

public class GetStartTime {
    public static String getStartTime(Database db, String cinemaName,String movieName){ // ali: this method needs to be removed. 
        if (cinemaName == null || cinemaName.length() == 0 || movieName ==null || movieName.length() == 0){
            return "";
        }else {
            return db.sql_getString(String.format("select start_time from moviebooking_db.cinema_session " +
                    "where cinema = '%s' and movie = '%s';",cinemaName.replace("'","''"),movieName.replace("'","''")), "start_time");
        }
    }
}
