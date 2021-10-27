package movieManagement;

import MTBMS.Database;
import databaseutility.GetMovieClassification;
import databaseutility.GetMovieNames;
import databaseutility.GetMovieShowDate;
import databaseutility.GetNowShowing;


import java.util.List;

public class ListNowShowing {
    public static void listNowShowing(Database db) {
        List<String> name = GetNowShowing.getNowShowing(db);
        for(String n : name) {
            String classification = GetMovieClassification.getMovieClassification(db, n.replace("'", "''"));
            String showDate = GetMovieShowDate.getMovieShowDate(db, n.replace("'", "''"));
            System.out.println(n + " " + PURPLE_BOLD + "[" + classification + "]" + ANSI_RESET + " " + YELLOW_BOLD + showDate + ANSI_RESET);
        }
    }
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW



}
