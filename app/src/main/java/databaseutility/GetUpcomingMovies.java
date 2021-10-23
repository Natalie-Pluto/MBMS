package databaseutility;
import MTBMS.Database;
import utils.StringUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

// For listing upcoming movie in the default page
public class GetUpcomingMovies {
    public static void getUpcomingMovies(Database db) {
        String query = "select * from moviebooking_db.upcomingmovie";
        List<String> name = db.sql_getStrings(query,"name");
        List<String> classification = db.sql_getStrings(query,"classification");
        for (int i = 0; i < name.size(); i++) {
            System.out.println(name.get(i) + " " + PURPLE_BOLD + "[" + classification.get(i) + "]" + ANSI_RESET);
        }
    }

    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String ANSI_RESET = "\u001B[0m";

}
