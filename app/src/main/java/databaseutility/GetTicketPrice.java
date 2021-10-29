package databaseutility;

import MTBMS.Database;
import utils.StringUtils;

public class GetTicketPrice {
    public static Double getTicketPriceKids(Database db, String cinemaName, String movieName,String startTime, String screenType){
        if (StringUtils.isEmpty(cinemaName)|| StringUtils.isEmpty(movieName)
                ||StringUtils.isEmpty(startTime)){
            return null;
        }else {
            Double kidPrice = db.sql_getDouble(String.format("select ticket_price_kids from moviebooking_db.cinema_session " +
                    "where cinema = '%s' and movie = '%s' and start_time = '%s' " +
                    "and screen_type = '%s';",cinemaName.replace("'","''"),movieName.replace("'","''"),startTime,screenType), "ticket_price_kids");
            return kidPrice;
        }
    }

    public static Double getTicketPriceAdults(Database db, String cinemaName, String movieName,String startTime, String screenType){
        if (StringUtils.isEmpty(cinemaName)|| StringUtils.isEmpty(movieName)
                ||StringUtils.isEmpty(startTime)||StringUtils.isEmpty(screenType)){
            return null;
        }else {
            Double adultPrice = db.sql_getDouble(String.format("select ticket_price_adults from moviebooking_db.cinema_session " +
                    "where cinema = '%s' and movie = '%s' and start_time = '%s' " +
                    "and screen_type = '%s';",cinemaName.replace("'","''"),movieName.replace("'","''"),startTime,screenType), "ticket_price_adults");
            return adultPrice;
        }
    }

    public static Double getTicketPriceStudents(Database db, String cinemaName, String movieName,String startTime, String screenType){
        if (StringUtils.isEmpty(cinemaName)|| StringUtils.isEmpty(movieName)
                ||StringUtils.isEmpty(startTime)||StringUtils.isEmpty(screenType)){
            return null;
        }else {
            Double studentPrice = db.sql_getDouble(String.format("select ticket_price_students from moviebooking_db.cinema_session " +
                    "where cinema = '%s' and movie = '%s' and start_time = '%s' " +
                    "and screen_type = '%s';",cinemaName.replace("'","''"),movieName.replace("'","''"),startTime,screenType), "ticket_price_students");
            return studentPrice;
        }
    }

    public static Double getTicketPriceSeniors(Database db, String cinemaName, String movieName,String startTime, String screenType){
        if (StringUtils.isEmpty(cinemaName)|| StringUtils.isEmpty(movieName)
                ||StringUtils.isEmpty(startTime)||StringUtils.isEmpty(screenType)){
            return null;
        }else {
            Double seniorsPrice = db.sql_getDouble(String.format("select ticket_price_seniors from moviebooking_db.cinema_session " +
                    "where cinema = '%s' and movie = '%s' and start_time = '%s' " +
                    "and screen_type = '%s';",cinemaName.replace("'","''"),movieName.replace("'","''"),startTime,screenType), "ticket_price_seniors");
            return seniorsPrice;
        }
    }
}
