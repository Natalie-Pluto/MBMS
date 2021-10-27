package databaseutility;

import MTBMS.Database;

import java.util.ArrayList;
import java.util.List;

public class FilterUpcomingScreen {
    public static List<String> filterUpcomingScreen(Database db, String screen){
        if (screen == null || screen.length() == 0){
            return new ArrayList<>();
        }else {
            return db.sql_getStrings("select distinct name_ from moviebooking_db.upcomingmovie join moviebooking_db.cinema_session on (name_ = movie) where screen_type = '" + screen + "'", "name_");
        }
    }
}
