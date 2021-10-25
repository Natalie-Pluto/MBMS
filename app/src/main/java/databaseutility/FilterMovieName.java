package databaseutility;

import MTBMS.Database;

import java.util.ArrayList;
import java.util.List;

public class FilterMovieName {
    public static List<String> filterMovieName(Database db, String movieName){
        if (movieName == null || movieName.length() == 0){
            return new ArrayList<>();
        }else {
            return db.sql_getStrings("select name from moviebooking_db where name = " + movieName + ";", "name");
        }
    }
}
