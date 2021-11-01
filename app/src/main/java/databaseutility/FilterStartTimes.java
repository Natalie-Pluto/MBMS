package databaseutility;


import MTBMS.Database;

import java.util.ArrayList;
import java.util.List;

public class FilterStartTimes {
    public static List<String> filterStartTimes(Database db, String cinemaName, String movieName, String screenType){
        if (cinemaName == null || cinemaName.length() == 0 || movieName == null || movieName.length() == 0){
            return new ArrayList<>();
        }else {
            return db.sql_getStrings("select start_time from moviebooking_db.cinema_session where cinema = '"+ cinemaName + "'and movie = '" + movieName + "' and screen_type = '" + screenType + "';", "start_time");
        }
    }
}
