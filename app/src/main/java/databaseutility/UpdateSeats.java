package databaseutility;

import MTBMS.Database;
import utils.StringUtils;
public class UpdateSeats {
    public static void updateSeats(Database db, String cinemaName, String movieName, String startTime, String screenType, int numBooked, String seatLocation){
        if (StringUtils.isNotEmpty(cinemaName) && StringUtils.isNotEmpty(movieName)
                && StringUtils.isNotEmpty(startTime)){
                        String query = " from moviebooking_db.cinema_session where cinema = '"+ cinemaName.replace("'", "''") + "'and movie = '" + movieName.replace("'", "''") + "' and screen_type= '" + screenType + "' and start_time= '" + startTime + "';";
                        int frontBooked = db.sql_getInt("select number_of_front_seats_booked" + query, "number_of_front_seats_booked");
                        int midBooked = db.sql_getInt("select number_of_mid_seats_booked" + query, "number_of_mid_seats_booked");
                        int backBooked = db.sql_getInt("select number_of_back_seats_booked" + query, "number_of_back_seats_booked");
                        if (seatLocation.equals("front"))
                                db.sql_update(String.format("update moviebooking_db.cinema_session set number_of_front_seats_booked = %s where " +
                                        "cinema = '%s' and movie = '%s' and start_time = '%s' and screen_type = '%s';",
                                        numBooked + frontBooked,cinemaName.replace("'","''"),movieName.replace("'","''"),startTime,screenType));
                        else if (seatLocation.equals("mid"))
                                db.sql_update(String.format("update moviebooking_db.cinema_session set number_of_mid_seats_booked = %s where " +
                                                "cinema = '%s' and movie = '%s' and start_time = '%s' and screen_type = '%s';",
                                        numBooked + midBooked,cinemaName.replace("'","''"),movieName.replace("'","''"),startTime,screenType));
                        else if (seatLocation.equals("back")) 
                                db.sql_update(String.format("update moviebooking_db.cinema_session set number_of_back_seats_booked = %s where " +
                                                "cinema = '%s' and movie = '%s' and start_time = '%s' and screen_type = '%s';",
                                        numBooked + backBooked ,cinemaName.replace("'","''"),movieName.replace("'","''"),startTime,screenType));
        }
    }
}