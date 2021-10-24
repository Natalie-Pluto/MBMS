package databaseutility;

import MTBMS.Database;
import java.util.List;

public class GetMovieShowingTime {
    public static List<String> getShowingTime(Database db, String name, String cinema) {
        String query = "select start_time from moviebooking_db.cinema_session where cinema = '" + cinema + "' and movie = '" +  name + "'";
        return db.sql_getStrings(query, "start_time");
    }
}
