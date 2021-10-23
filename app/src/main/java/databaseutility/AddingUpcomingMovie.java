package databaseutility;

import MTBMS.Database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddingUpcomingMovie {

    public static boolean addUpcomingMovie(Database db) throws ParseException {
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



        return true;
    }
}
