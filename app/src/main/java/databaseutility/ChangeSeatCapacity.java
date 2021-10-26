package databaseutility;

import MTBMS.Database;
public class ChangeSeatCapacity {
    public static void changeSeatCapacity(Database d, String cinemaName, String screenType, String movieName, String startTime, String newSeatCapacity, String seatLocation){
            d.sql_update(String.format("UPDATE moviebooking_db.Cinema_Session SET %s_seat_capacity = '%s' WHERE cinema = '%s' and screen_type = %s and movie = %s and start_time = %s;", seatLocation, newSeatCapacity, cinemaName, screenType, movieName, startTime));
    }
}
