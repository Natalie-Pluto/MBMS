package movieManagement;

import MTBMS.Database;
import databaseutility.*;


import java.util.List;
//TODO
public class ListMovieByClassification {
    public static void listMovieByClassification(Database db, String classification) {
        List<String> name = FilterClassification.filterClassification(db, classification);
        for (String n : name) {
            String classification1 = GetMovieClassification.getMovieClassification(db, n.replace("'", "''"));
            String showDate = GetMovieShowDate.getMovieShowDate(db, n.replace("'", "''"));
            System.out.println(n + " " + PURPLE_BOLD + "[" + classification + "]" + ANSI_RESET + " " + YELLOW_BOLD + showDate + ANSI_RESET);
        }
    }

    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
}
