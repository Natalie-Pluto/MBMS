package movieManagement;

import MTBMS.Database;

public class ListSeats {
    public static void listSeats(Database db, String cinemaName, String movieName, String screenType, String startTime){
        String query = " from moviebooking_db.cinema_session where cinema = '"+ cinemaName + "'and movie = '" + movieName + "' and screen_type= '" + screenType + "' and start_time= '" + startTime + "';";
        int frontNum = db.sql_getInt("select front_seat_capacity"+query, "front_seat_capacity");
        int midNum = db.sql_getInt("select mid_seat_capacity"+query, "mid_seat_capacity");
        int backNum = db.sql_getInt("select back_seat_capacity"+query, "back_seat_capacity");
        int frontBooked = db.sql_getInt("select number_of_front_seats_booked" + query, "number_of_front_seats_booked");
        int midBooked = db.sql_getInt("select number_of_mid_seats_booked" + query, "number_of_mid_seats_booked");
        int backBooked = db.sql_getInt("select number_of_back_seats_booked" + query, "number_of_back_seats_booked");

        System.out.println("\n======================================================");
        System.out.println(PURPLE_BOLD + "Please select your seatLocation:" + ANSI_RESET);
        System.out.println("======================================================\n");
        System.out.println("1.Front: " + PURPLE_BOLD + (frontNum - frontBooked) + ANSI_RESET + " seats left");
        System.out.println("2.Mid: " + PURPLE_BOLD + (midNum - midBooked) + ANSI_RESET + " seats left");
        System.out.println("3.Back: " + PURPLE_BOLD + (backNum - backBooked) + ANSI_RESET + " seats left");
    }

    public static int getSeatNum(Database db, String cinemaName, String movieName, String screenType, String startTime, String seatLocation){
        String query = " from moviebooking_db.cinema_session where cinema = '"+ cinemaName + "'and movie = '" + movieName + "' and screen_type= '" + screenType + "' and start_time= '" + startTime + "';";
        int frontNum = db.sql_getInt("select front_seat_capacity"+query, "front_seat_capacity");
        int midNum = db.sql_getInt("select mid_seat_capacity"+query, "mid_seat_capacity");
        int backNum = db.sql_getInt("select back_seat_capacity"+query, "back_seat_capacity");
        int frontBooked = db.sql_getInt("select number_of_front_seats_booked" + query, "number_of_front_seats_booked");
        int midBooked = db.sql_getInt("select number_of_mid_seats_booked" + query, "number_of_mid_seats_booked");
        int backBooked = db.sql_getInt("select number_of_back_seats_booked" + query, "number_of_back_seats_booked");

        switch (seatLocation){
            case "1":
                return frontNum - frontBooked;

            case "2":
                return midNum - midBooked;

            default://3
                return backNum - backBooked;
        }
    }

    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
}
