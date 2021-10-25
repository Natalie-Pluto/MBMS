package movieManagement;

import MTBMS.Database;
import databaseutility.*;

import java.util.Date;
import java.util.List;

import static MTBMS.BookingSystem.RED_BOLD;

//TODO
public class ListMovieByScreen {
    public static void listMovieByScreen(Database db, String screenType) {
        List<String> name = FilterScreenSize.filterScreenSize(db, screenType);
        if(name == null) {
            System.out.println("\n=======================================================");
            System.out.println(RED_BOLD + "Wrong input, please check the screen size entered (｡´︿`｡)" + ANSI_RESET);
            System.out.println("=======================================================\n");
        }
        for (String n : name) {
            String classification = GetMovieClassification.getMovieClassification(db, n.replace("'", "''"));
            Date showDate = GetMovieShowDate.getMovieShowDate(db, n.replace("'", "''"));
            System.out.println(n + " " + PURPLE_BOLD + "[" + classification + "]" + ANSI_RESET + " " + YELLOW_BOLD + showDate + ANSI_RESET);
        }
    }

    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
}