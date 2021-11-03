package databaseutility;

import MTBMS.Database;
import utils.StringUtils;

public class AddMovieSession {
    public static boolean addMovieSession(Database db, String cinemaName, String movieName, String screenType, String startTime,
                                          String kidTicketPrice, String adultTicketPrice, String seniorTicketPrice, String studentTicketPrice){
        String updateArgs = "'" + cinemaName.replace("'","''") + "', '" + movieName.replace("'","''") +
                "', to_timestamp('" + startTime + "','YYYY-MM-DD HH24:MI:SS'), '" + screenType + "','" + kidTicketPrice + "','" +
                adultTicketPrice + "','" + seniorTicketPrice + "','" + studentTicketPrice + "'";
        boolean success = db.sql_update("insert into moviebooking_db.cinema_session(cinema,movie,start_time,screen_type,ticket_price_kids,ticket_price_seniors," +
                "ticket_price_adults,ticket_price_students) values(" + updateArgs + ") on conflict (movie, cinema, screen_type, start_time) do nothing;");
        return success;
    }
}
