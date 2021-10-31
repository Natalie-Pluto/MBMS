package databaseutility;

import MTBMS.Database;

import java.util.List;

public class GetCinemaName {
    public static List<String> getCinemaName(Database db) {
        String query = "select cinema_name from moviebooking_db.cinema;";
        return(db.sql_getStrings(query,"cinema_name"));
    }
}
