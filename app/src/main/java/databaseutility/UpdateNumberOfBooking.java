package databaseutility;

import MTBMS.Database;

public class UpdateNumberOfBooking {
    public static void updateNumberOfBooking(Database db, String cinemaName, String movieName, String startTime, String screenType){
        String query = " from moviebooking_db.cinema_session where cinema = '" + cinemaName.replace("'", "''") + "'and movie = '" + movieName.replace("'", "''") + "' and screen_type= '" + screenType + "' and start_time= '" + startTime + "';";
        int bookNum = db.sql_getInt("select number_of_booking" + query, "number_of_booking") + 1;
        db.sql_update("update moviebooking_db.cinema_session set number_of_booking = " + bookNum + " where cinema = '" + cinemaName.replace("'", "''") + "'and movie = '" + movieName.replace("'", "''") + "' and screen_type= '" + screenType + "' and start_time= '" + startTime + "';");
    }
}
