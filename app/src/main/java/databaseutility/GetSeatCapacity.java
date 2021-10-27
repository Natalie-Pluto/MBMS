package databaseutility;

import MTBMS.Database;

public class GetSeatCapacity {
    public static int getFrontSeatCapacity(Database db, String cinemaName, String movieName, String screenType, String startTime){
        Integer capacity = db.sql_getInt(String.format("select front_seat_capacity from moviebooking_db.cinema_session WHERE cinema = '%s' and screen_type = '%s' and movie = '%s' and start_time = '%s';", cinemaName.replace("'","''"), screenType, movieName.replace("'","''"), startTime),"front_seat_capacity");
        if (capacity == null) return -1;
        return capacity;
    }

    public static int getMidSeatCapacity(Database db, String cinemaName, String movieName, String screenType, String startTime) {
        Integer capacity = db.sql_getInt(String.format("select mid_seat_capacity from moviebooking_db.cinema_session WHERE cinema = '%s' and screen_type = '%s' and movie = '%s' and start_time = '%s';", cinemaName.replace("'","''"), screenType, movieName.replace("'","''"), startTime),"mid_seat_capacity");
        if (capacity == null) return -1;
        return capacity;
    }

    public static int getBackSeatCapacity(Database db, String cinemaName, String movieName, String screenType, String startTime) {
        Integer capacity = db.sql_getInt(String.format("select back_seat_capacity from moviebooking_db.cinema_session WHERE cinema = '%s' and screen_type = '%s' and movie = '%s' and start_time = '%s';", cinemaName.replace("'","''"), screenType, movieName.replace("'","''"), startTime),"back_seat_capacity");
        if (capacity == null) return -1;
        return capacity;
    }
}
