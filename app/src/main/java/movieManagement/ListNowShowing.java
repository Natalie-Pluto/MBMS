package movieManagement;

import MTBMS.Database;
import databaseutility.GetMovieClassification;
import databaseutility.GetMovieNames;
import databaseutility.GetMovieShowDate;
import databaseutility.GetNowShowing;


import java.util.List;
import java.util.Locale;

public class ListNowShowing {
    public static void listNowShowing(Database db) {
        List<String> name_ = GetNowShowing.getNowShowing(db);
        if(name_ != null) {
            for (String n : name_) {
                String classification = GetMovieClassification.getMovieClassification(db, n.replace("'", "''").toLowerCase(Locale.ROOT));
                String showDate = GetMovieShowDate.getMovieShowDate(db, n.replace("'", "''"));
                System.out.println(n + " " + PURPLE_BOLD + "[" + classification + "]" + ANSI_RESET + " " + YELLOW_BOLD + showDate + ANSI_RESET);
            }
        }
    }
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW



}
