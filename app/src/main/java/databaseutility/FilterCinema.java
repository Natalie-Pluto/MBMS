package databaseutility;

import MTBMS.Database;

import java.util.ArrayList;
import java.util.List;

public class FilterCinema {
    public static List<String> filterCinema(Database db, String cinemaName){
        if (cinemaName == null || cinemaName.length() == 0){
            return new ArrayList<>();
        }else {
            return db.sql_getStrings("select cs.movie as movie from cinema as ci inner join cinema_session as " +
                    "cs on ci.cinema_name = cs.cinema and ci.cinema_name ='"+ cinemaName+"';", "movie");
        }
    }
}
