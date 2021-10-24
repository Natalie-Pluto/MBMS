package databaseutility;

import MTBMS.Database;

import java.util.List;

public class GetNowShowing {
    public static List<String> getNowShowing(Database db) {
        String query = "select name from moviebooking_db.movie where showing_date <= current_date";
        return db.sql_getStrings(query, "name");
    }
}
