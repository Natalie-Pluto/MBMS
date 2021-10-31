package movieManagement;

import MTBMS.BookingSystem;
import MTBMS.Database;
import databaseutility.*;


import java.util.List;

import static MTBMS.BookingSystem.RED_BOLD;

public class ListUpcomingByCinema {
    public static void listUpcomingByCinema(Database db, String cinema) throws InterruptedException {
        List<String> name_ = FilterUpcomingMCinema.filterUpcomingCinema(db, cinema);
        if(name_ != null) {
            if (name_.isEmpty()) {
                if (!CheckIfCinemaExists.checkIfCinemaExists(db, cinema)) {
                    System.out.println("\n=============================================================");
                    System.out.println(RED_BOLD + "Wrong input! Please enter the right cinema number (｡´︿`｡)" + ANSI_RESET);
                    System.out.println("=============================================================\n");
                } else {
                    System.out.println("\n======================================================================================");
                    System.out.println(RED_BOLD + "Sorry, no upcoming movie is scheduled to be played in this cinema at the moment (｡´︿`｡)" + ANSI_RESET);
                    System.out.println("======================================================================================\n");
                }
            } else {
                System.out.println(YELLOW_BOLD_BRIGHT + cinema + ANSI_RESET + "\n");
                for (String n : name_) {
                    String classification = GetMovieClassification.getMovieClassification(db, n.replace("'", "''"));
                    String showDate = GetMovieShowDate.getMovieShowDate(db, n.replace("'", "''"));
                    List<String> showingTime = GetMovieShowingTime.getShowingTime(db, n.replace("'", "''"), cinema);
                    System.out.println("===============================================");
                    System.out.println(n + " " + PURPLE_BOLD + "[" + classification + "]" + ANSI_RESET + " " + YELLOW_BOLD + showDate + ANSI_RESET);
                    System.out.println("===============================================\n");
                    System.out.println(PURPLE_BOLD_BRIGHT + "Showing Time" + ANSI_RESET);
                    for (String time : showingTime) {
                        String size = GetSingleScreenSize.getSingleScreenSize(db, n.replace("'", "''"), cinema, time);
                        System.out.println(time + " " + PURPLE_BOLD + "[" + size + "]" + ANSI_RESET);
                    }
                    System.out.println("");
                }
            }
        }
    }

    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
}
