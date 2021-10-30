package databaseutility;

import MTBMS.Database;
import utils.StringUtils;

public class GetNumberOfSeatsBooked {
    public static int getNumberOfFrontSeatsBooked(Database db, String cinemaName, String movieName, String screenType, String startTime){
        Integer num = db.sql_getInt(String.format("select number_of_front_seats_booked from moviebooking_db.cinema_session WHERE cinema = '%s' and screen_type = '%s' and movie = '%s' and start_time = '%s';", cinemaName.replace("'","''"), screenType, movieName.replace("'","''"), startTime),"number_of_front_seats_booked");
        if (num == null) return -1;
        return num;
    }

    public static int getNumberOfMidSeatsBooked(Database db, String cinemaName, String movieName, String screenType, String startTime){
        Integer num = db.sql_getInt(String.format("select number_of_mid_seats_booked from moviebooking_db.cinema_session WHERE cinema = '%s' and screen_type = '%s' and movie = '%s' and start_time = '%s';", cinemaName.replace("'","''"), screenType, movieName.replace("'","''"), startTime),"number_of_mid_seats_booked");
        if (num == null) return -1;
        return num;
    }

    public static int getNumberOfBackSeatsBooked(Database db, String cinemaName, String movieName, String screenType, String startTime){
        Integer num = db.sql_getInt(String.format("select number_of_back_seats_booked from moviebooking_db.cinema_session WHERE cinema = '%s' and screen_type = '%s' and movie = '%s' and start_time = '%s';", cinemaName.replace("'","''"), screenType, movieName.replace("'","''"), startTime),"number_of_back_seats_booked");
        if (num == null) return -1;
        return num;
    }

}
