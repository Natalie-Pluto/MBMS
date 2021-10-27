package databaseutility;

import MTBMS.Database;

import java.util.ArrayList;
import java.util.List;

public class FilterUpcomingMCinema {
    public static List<String> filterUpcomingCinema(Database db, String cinema){
        if (cinema == null || cinema.length() == 0){
            return new ArrayList<>();
        }else {
            return db.sql_getStrings("select distinct name_ from moviebooking_db.upcomingmovie join moviebooking_db.cinema_session on (name_ = movie) where cinema = '" + cinema + "'", "name_");
        }
    }
}
