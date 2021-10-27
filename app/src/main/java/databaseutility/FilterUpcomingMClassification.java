package databaseutility;

import MTBMS.Database;

import java.util.ArrayList;
import java.util.List;

public class FilterUpcomingMClassification {
    public static List<String> filterUpcomingMClassification(Database db, String classification) {
        if (classification == null || classification.length() == 0) {
            return new ArrayList<>();
        } else {
            return db.sql_getStrings("select name_ from moviebooking_db.upcomingmovie where classification = " + classification + ";", "name_");
        }
    }
}
