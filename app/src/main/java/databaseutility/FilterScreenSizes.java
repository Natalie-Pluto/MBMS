package databaseutility;


import MTBMS.Database;

import java.util.ArrayList;
import java.util.List;

public class FilterScreenSizes {
    public static List<String> filterScreenSizes(Database db, String cinemaName, String movieName){
        if (cinemaName == null || cinemaName.length() == 0 || movieName == null || movieName.length() == 0){
            return new ArrayList<>();
        }else {
            return db.sql_getStrings("select screen_type from moviebooking_db.cinema_session where cinema = '"+ cinemaName + "'and movie = '" + movieName + "';", "screen_type");
        }
    }
}
