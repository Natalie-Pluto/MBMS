package databaseutility;

import MTBMS.Database;

import java.util.List;

public class GetNewUpcomingMovie {
    public static List<String> getNewUpcomingMovie(Database db) {
        String query = "select name_ from moviebooking_db.movie where showing_date > current_date + 7 and showing_date < current_date + 14";
        return db.sql_getStrings(query, "name_");
    }
}
