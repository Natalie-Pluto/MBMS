package databaseutility;


import MTBMS.Database;
import utils.StringUtils;

public class AddMovieSession {
    public static void addMovieSession(Database db, int sessionId, String cinemaName, String movieName){
        String updateArgs = "'" + sessionId + "', '" + cinemaName + "', '" + movieName + "'";
        db.sql_update("insert into moviebooking_db.cinema_session(session_id,cinema,movie) values(" + updateArgs + ") on conflict (session_id) do nothing;");
    }
}
