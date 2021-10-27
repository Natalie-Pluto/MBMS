package databaseutility;

import MTBMS.Database;
public class ChangeSeatCapacity {
    public static void changeFrontSeatCapacity(Database d, String cinemaName, String movieName, String screenType, String startTime, int newSeatCapacity){
            d.sql_update(String.format("UPDATE moviebooking_db.Cinema_Session SET front_seat_capacity = '%s' WHERE cinema = '%s' and screen_type = %s and movie = %s and start_time = %s;", newSeatCapacity, cinemaName, screenType, movieName, startTime));
    }

    public static void changeMidSeatCapacity(Database d, String cinemaName, String movieName, String screenType, String startTime, int newSeatCapacity){
        d.sql_update(String.format("UPDATE moviebooking_db.Cinema_Session SET mid_seat_capacity = '%s' WHERE cinema = '%s' and screen_type = %s and movie = %s and start_time = %s;", newSeatCapacity, cinemaName, screenType, movieName, startTime));
    }

    public static void changeBackSeatCapacity(Database d, String cinemaName, String movieName, String screenType, String startTime, int newSeatCapacity){
        d.sql_update(String.format("UPDATE moviebooking_db.Cinema_Session SET back_seat_capacity = '%s' WHERE cinema = '%s' and screen_type = %s and movie = %s and start_time = %s;", newSeatCapacity, cinemaName, screenType, movieName, startTime));
    }

}
