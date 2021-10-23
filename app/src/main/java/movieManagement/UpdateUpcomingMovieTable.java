package movieManagement;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class UpdateUpcomingMovieTable {

    public static void updateUpcomingMovieTable() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);
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
        if(delayInDays == 6 && hour < 23){
            delayInHours = 23 - hour;
        }else{
            delayInHours = delayInDays * 24 +((24-hour)+11);
        }
    }
}
