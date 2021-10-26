package databaseutility;

import MTBMS.Database;

public class GetStartTime {
    public static String getStartTime(Database db, String cinemaName,String movieName){
        if (cinemaName == null || cinemaName.length() == 0 || movieName ==null || movieName.length() == 0){
            return "";
        }else {
            return db.sql_getString(String.format("select start_time from moviebooking_db.cinema_session " +
                    "where cinema = '%s' and movie = '%s';",cinemaName,movieName), "start_time");
        }
    }
}
