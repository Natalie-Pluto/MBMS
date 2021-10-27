package databaseutility;

import MTBMS.Database;

import java.text.ParseException;
;
import java.util.List;

public class AddingUpcomingMovie {
    // The movie which it’s showing date is 7 days after this Monday and within future 14 days
    // will be added into upcoming movie table
    public static void addUpcomingMovie(Database db) throws ParseException {
        boolean updateStatus = false;
        List<String> names = GetNewUpcomingMovie.getNewUpcomingMovie(db);
        for (String nn : names) {
            String classification = GetMovieClassification.getMovieClassification(db, nn.replace("'", "''"));
            String Sdate = GetMovieShowDate.getMovieShowDate(db, nn.replace("'", "''"));
            String updateArgs = "'" + nn.replace("'", "''") +"', '" + classification + "', '" + Sdate + "'";
            updateStatus = db.sql_update("insert into moviebooking_db.upcomingmovie values(" + updateArgs + ") on conflict (name) do nothing;;");
            if(!updateStatus) {
                System.out.println("Error happened when adding upcoming movies");
                break;
            }
        }
    }
}
