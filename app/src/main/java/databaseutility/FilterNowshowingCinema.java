package databaseutility;

import MTBMS.Database;

import java.util.ArrayList;
import java.util.List;

public class FilterNowshowingCinema {
    public static List<String> filternowshowingCinema(Database db, String cinemaName){
        if (cinemaName == null || cinemaName.length() == 0){
            return new ArrayList<>();
        }else {
            return db.sql_getStrings("select distinct movie from moviebooking_db.cinema_session where cinema = '"+ cinemaName+"' and start_time <= current_date + 2;", "movie");
        }
    }
}
