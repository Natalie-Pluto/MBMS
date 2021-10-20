package databaseutility;

import MTBMS.Database;

import java.util.ArrayList;
import java.util.List;

public class FilterScreenSize {
    public static List<String> filterScreenSize(Database db, String screenType){
        if (screenType == null || screenType.length() == 0){
            return new ArrayList<>();
        }else {
            return db.sql_getStrings("select cs.movie from cinema_session as cs inner join movie as mv on cs.movie" +
                    "= mv.name and cs.screen_type = '"+screenType+"';", "movie");
        }
    }
}