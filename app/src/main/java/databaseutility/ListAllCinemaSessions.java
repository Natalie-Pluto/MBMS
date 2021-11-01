package databaseutility;

import MTBMS.Database;
import java.util.List;
import java.util.ArrayList;

public class ListAllCinemaSessions {
    public static ArrayList<String[]> listAllCinemaSessions(Database d){
        String query = "SELECT * FROM moviebooking_db.cinema_session ORDER BY cinema ASC, movie ASC, screen_type ASC, start_time ASC;";
        List<String> cinemaName = d.sql_getStrings(query, "cinema");
        List<String> movieName = d.sql_getStrings(query, "movie");
        List<String> screenType = d.sql_getStrings(query, "screen_type");
        List<String> startTime = d.sql_getStrings(query, "start_time");
        List<String> numberOfBooking = d.sql_getStrings(query, "number_of_booking");
        List<String> frontSeatBooking = d.sql_getStrings(query, "number_of_front_seats_booked");
        List<String> midSeatBooking = d.sql_getStrings(query, "number_of_mid_seats_booked");
        List<String> backSeatBooking = d.sql_getStrings(query, "number_of_back_seats_booked");
        List<String> frontSeatCapacity = d.sql_getStrings(query, "front_seat_capacity");
        List<String> midSeatCapacity = d.sql_getStrings(query, "mid_seat_capacity");
        List<String> backSeatCapacity = d.sql_getStrings(query, "back_seat_capacity");
        ArrayList<String[]> cinemaSessions = new ArrayList<String[]>();
        for(int i = 0; i < cinemaName.size(); i++){
            String[] cinemaSession = new String[11];
            cinemaSession[0] = cinemaName.get(i);
            cinemaSession[1] = movieName.get(i);
            cinemaSession[2] = screenType.get(i);
            cinemaSession[3] = startTime.get(i);
            cinemaSession[4] = numberOfBooking.get(i);
            cinemaSession[5] = frontSeatBooking.get(i);
            cinemaSession[6] = midSeatBooking.get(i);
            cinemaSession[7] = backSeatBooking.get(i);
            cinemaSession[8] = frontSeatCapacity.get(i);
            cinemaSession[9] = midSeatCapacity.get(i);
            cinemaSession[10] = backSeatCapacity.get(i);
            cinemaSessions.add(cinemaSession);
        }
        return cinemaSessions;


    }
}
