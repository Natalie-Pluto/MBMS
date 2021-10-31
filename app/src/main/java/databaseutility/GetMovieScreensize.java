package databaseutility;

import MTBMS.Database;

import java.util.ArrayList;
import java.util.List;

public class GetMovieScreensize {
    public static List<String> getMovieScreensize(Database db, String movieName) {
        List<String> screen = new ArrayList<>();
        String query = "select * from moviebooking_db.cinema_session where lower(movie) = '" + movieName + "';";
        List<String> sc = db.sql_getStrings(query,"screen_type");
        if (sc != null) {
            for (String size : sc) {
                if (!screen.contains(size)) {
                    screen.add(size);
                }
            }
        }
        return screen;
    }
}
