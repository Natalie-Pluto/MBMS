package databaseutility;

import MTBMS.Database;

import java.util.ArrayList;
import java.util.List;

public class FilterClassification {
    public static List<String> filterClassification(Database db, String classification){
        if (classification == null || classification.length() == 0){
            return new ArrayList<>();
        }else {
            return db.sql_getStrings("select name from moviebooking_db.movie where classification = " + classification + ";", "name");
        }
    }
}
