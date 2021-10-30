package movieManagement;
import MTBMS.Database;
import databaseutility.AddingUpcomingMovie;
import databaseutility.DeleteAllUpcoming;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Referenced:
// https://stackoverflow.com/questions/22118116/how-to-run-a-particular-task-every-friday-in-a-week-at-any-time-using-schedulede
public class UpdateUpcomingMovieTable {

    public UpdateUpcomingMovieTable() {
        updateUpcomingMovieTable();
    }
    // The upcoming movie table will be updated every Monday at 6am
    public static void updateUpcomingMovieTable() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Calendar with = Calendar.getInstance();
        Map<Integer, Integer> dayToDelay = new HashMap<Integer, Integer>();
        dayToDelay.put(Calendar.FRIDAY, 2);
        dayToDelay.put(Calendar.SATURDAY, 1);
        dayToDelay.put(Calendar.SUNDAY, 0);
        dayToDelay.put(Calendar.MONDAY, 6);
        dayToDelay.put(Calendar.TUESDAY, 5);
        dayToDelay.put(Calendar.WEDNESDAY, 4);
        dayToDelay.put(Calendar.THURSDAY, 3);
        int dayOfWeek = with.get(Calendar.DAY_OF_WEEK);
        int hour = with.get(Calendar.HOUR_OF_DAY);
        int delayInDays = dayToDelay.get(dayOfWeek);
        int delayInHours = 0;
        if(delayInDays == 6 && hour < 6){
            delayInHours = 6 - hour;
        }else{
            delayInHours = delayInDays * 24 + ((24-hour) + 6);
        }
        scheduler.scheduleAtFixedRate(new MondayUpdate(), delayInHours,
                174, TimeUnit.HOURS);
    }

    public static class MondayUpdate implements Runnable{
        //Database dbInstance = new Database("jdbc:postgresql://ls-d4381878930280384f33af335289e24c73224a04.c0apyqxz8x8m.ap-southeast-2.rds.amazonaws.com:5432/postgres",
                         //                "dbmasteruser", "A>XV>D*7r-V{y_wL}}I{+U=8zEtj1*T<");
        Database dbInstance =  new Database("jdbc:postgresql://localhost:5432/postgres", "postgres", "0000");
        @Override
        public void run() {
            // Delete all rows in the table
            DeleteAllUpcoming.deleteUpcoming(dbInstance);
            // Add upcoming movies
            // The movie which it’s showing date is 7 days after this Monday and within future 14 days
            // will be added into upcoming movie table
            try {
                AddingUpcomingMovie.addUpcomingMovie(dbInstance);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
