package databaseutility;

import MTBMS.Database;

import java.util.ArrayList;
import java.util.List;

public class FilterScreenSize {
    public static List<String> filterScreenSize(Database db, String screenType){
        if (screenType == null || screenType.length() == 0){
            return new ArrayList<>();
        }else {
            return db.sql_getStrings("select distinct movie from moviebooking_db.cinema_session where screen_type = '"+screenType + "';", "movie");
        }
    }
}