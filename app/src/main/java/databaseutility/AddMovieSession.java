package databaseutility;

import MTBMS.Database;
import utils.StringUtils;

public class AddMovieSession {
    public static boolean addMovieSession(Database db, String cinemaName, String movieName, String screenType, String startTime){
        String updateArgs = "'" + cinemaName.replace("'","''") + "', '" + movieName.replace("'","''") + "', to_timestamp('" + startTime + "','YYYY-MM-DD HH:MI:SS'), '" + screenType + "'";
        boolean success = db.sql_update("insert into moviebooking_db.cinema_session(cinema,movie,start_time,screen_type) values(" + updateArgs + ") on conflict (movie, cinema, screen_type, start_time) do nothing;");
        return success;
    }
}
