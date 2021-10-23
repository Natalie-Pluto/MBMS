package databaseutility;
import MTBMS.Database;
import utils.StringUtils;

import java.sql.Date;

// For listing upcoming movie in the default page
public class GetUpcomingMovies {
    public static void getUpcomingMovies(Database db) {
        String query = "select * from moviebooking_db.upcomingmovie";
        String name = db.sql_getString(query,"name");
        String classification = db.sql_getString(query,"classification");
        System.out.println(name + " " + PURPLE_BOLD +  "[" + classification + "]" + ANSI_RESET);
    }

    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String ANSI_RESET = "\u001B[0m";

}
