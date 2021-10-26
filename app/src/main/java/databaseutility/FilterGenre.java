package databaseutility;

import MTBMS.Database;

import java.util.ArrayList;
import java.util.List;

public class FilterGenre {
    public static List<String> filterGenre(Database db, String genre){
        if (genre == null || genre.length() == 0){
            return new ArrayList<>();
        }else {
            return db.sql_getStrings("select name from moviebooking_db.movie where genre = " + genre + ";", "name");
        }
    }
}