package databaseutility;

import MTBMS.Database;
import utils.StringUtils;
public class UpdateSeats {
    public static void updateSeats(Database db, String cinemaName, String movieName, String startTime, String screenType, int numBooked, String seatLocation){
        if (StringUtils.isNotEmpty(cinemaName) && StringUtils.isNotEmpty(movieName)
                && StringUtils.isNotEmpty(startTime)){
                        if (seatLocation.equals("front"))
                                db.sql_update(String.format("update moviebooking_db.cinema_session set number_of_front_seats_booked = %s where " +
                                        "cinema = '%s' and movie = '%s' and start_time = '%s' and screen_type = '%s';",
                                        numBooked,cinemaName.replace("'","''"),movieName.replace("'","''"),startTime,screenType));
                        else if (seatLocation.equals("mid"))
                                db.sql_update(String.format("update moviebooking_db.cinema_session set number_of_mid_seats_booked = %s where " +
                                                "cinema = '%s' and movie = '%s' and start_time = '%s' and screen_type = '%s';",
                                        numBooked,cinemaName.replace("'","''"),movieName.replace("'","''"),startTime,screenType));
                        else if (seatLocation.equals("back")) 
                                db.sql_update(String.format("update moviebooking_db.cinema_session set number_of_back_seats_booked = %s where " +
                                                "cinema = '%s' and movie = '%s' and start_time = '%s' and screen_type = '%s';",
                                        numBooked,cinemaName.replace("'","''"),movieName.replace("'","''"),startTime,screenType));
        }
    }
}