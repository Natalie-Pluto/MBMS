package databaseutility;

import MTBMS.Database;
import java.util.List;

public class GetMovieShowingTime {
    public static List<String> getShowingTime(Database db, String name_, String cinema) {
        String query = "select start_time from moviebooking_db.cinema_session where cinema = '" + cinema.replace("'", "''") + "' and movie = '" +  name_.replace("'","''") + "'";
        return db.sql_getStrings(query, "start_time");
    }
}
