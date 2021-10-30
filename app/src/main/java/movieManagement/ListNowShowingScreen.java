package movieManagement;

import MTBMS.Database;
import databaseutility.*;


import java.util.List;

import static MTBMS.BookingSystem.RED_BOLD;

public class ListNowShowingScreen {
    public static void listNowshowingScreen(Database db, String screenType) {
        List<String> name_ = FilterNowshowingScreen.filterNowshowingScreenSize(db, screenType);
        if(name_.isEmpty()) {
            if(!CheckScreenTypeExist.checkScreenExist(db,screenType)) {
                System.out.println("\n=============================================================");
                System.out.println(RED_BOLD + "Wrong input, please enter the right screen type num (｡´︿`｡)" + ANSI_RESET);
                System.out.println("=============================================================\n");
            } else {
                System.out.println("\n==============================================================================");
                System.out.println(RED_BOLD + "Sorry no movie is scheduled to be played in this screen type (｡´︿`｡)" + ANSI_RESET);
                System.out.println("==============================================================================\n");
            }
        } else {
            for (String n : name_) {
                String classification = GetMovieClassification.getMovieClassification(db, n.replace("'", "''"));
                String showDate = GetMovieShowDate.getMovieShowDate(db, n.replace("'", "''"));
                System.out.println(n + " " + PURPLE_BOLD + "[" + classification + "]" + ANSI_RESET + " " + YELLOW_BOLD + showDate + ANSI_RESET);
            }
        }
    }

    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
}
