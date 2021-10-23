package databaseutility;

import MTBMS.Database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddingUpcomingMovie {

    public static void addUpcomingMovie(Database db) throws ParseException {
        boolean updateStatus = false;
        List<String> names = GetMovieNames.getMovieNames(db);
        List<String> targetName = new ArrayList<>();
        SimpleDateFormat obj = new SimpleDateFormat("yyyy-MM-dd");
        for (String n : names) {
            try {
                Date date = obj.parse(GetMovieShowDate.getMovieShowDate(db, n).toString());
                // Current date
                Date currDate = obj.parse(new Date().toString());
                long difference = date.getTime() - currDate.getTime();
                if (difference > 7 && difference < 14) {
                    targetName.add(n);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        for (String nn : targetName) {
            String classification = GetMovieClassification.getMovieClassification(db, nn);
            Date Sdate = GetMovieShowDate.getMovieShowDate(db, nn);
            String updateArgs = "'" + nn +"', '" + classification + "', '" + Sdate + "'";
            updateStatus = db.sql_update("insert into moviebooking_db.upcomingmovie values(" + updateArgs + ") on conflict (name) do nothing;;");
            if(!updateStatus) {
                System.out.println("Error happened when adding upcoming movies");
                break;
            }
        }
    }
}
