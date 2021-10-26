package databaseutility;

import MTBMS.Database;

import java.util.ArrayList;
import java.util.List;

public class FilterNowshowingScreen {
    public static List<String> filterNowshowingScreenSize(Database db, String screenType){
        if (screenType == null || screenType.length() == 0){
            return new ArrayList<>();
        }else {
            return db.sql_getStrings("select distinct movie from moviebooking_db.cinema_session where screen_type = '"+screenType + "'and start_time <= current_date + 2;", "movie");
        }
    }
}

