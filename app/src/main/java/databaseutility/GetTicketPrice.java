package databaseutility;

import MTBMS.Database;
import utils.StringUtils;

public class GetTicketPrice {
    public static Double getTicketPriceKids(Database db, String cinemaName, String movieName,String startTime, String screenType){
        if (StringUtils.isEmpty(cinemaName)|| StringUtils.isEmpty(movieName)
                ||StringUtils.isEmpty(startTime)){
            return null;
        }else {
            double kidPrice = db.sql_getDouble(String.format("select ticket_price_kids from cinema_session " +
                    "where cinema = '%s' and movie = '%s' and start_time = to_timestamp(%s) " +
                    "and screen_type = '%s';",cinemaName,movieName,startTime,screenType), "ticket_price_kids");
            return kidPrice;
        }
    }

    public static Double getTicketPriceAdults(Database db, String cinemaName, String movieName,String startTime, String screenType){
        if (StringUtils.isEmpty(cinemaName)|| StringUtils.isEmpty(movieName)
                ||StringUtils.isEmpty(startTime)||StringUtils.isEmpty(screenType)){
            return null;
        }else {
            double adultPrice = db.sql_getDouble(String.format("select ticket_price_adults from cinema_session " +
                    "where cinema = '%s' and movie = '%s' and start_time = to_timestamp(%s) " +
                    "and screen_type = '%s';",cinemaName,movieName,startTime,screenType), "ticket_price_adults");
            return adultPrice;
        }
    }

    public static Double getTicketPriceStudents(Database db, String cinemaName, String movieName,String startTime, String screenType){
        if (StringUtils.isEmpty(cinemaName)|| StringUtils.isEmpty(movieName)
                ||StringUtils.isEmpty(startTime)||StringUtils.isEmpty(screenType)){
            return null;
        }else {
            double studentPrice = db.sql_getDouble(String.format("select ticket_price_students from cinema_session " +
                    "where cinema = '%s' and movie = '%s' and start_time = to_timestamp(%s) " +
                    "and screen_type = '%s';",cinemaName,movieName,startTime,screenType), "ticket_price_students");
            return studentPrice;
        }
    }

    public static Double getTicketPriceSeniors(Database db, String cinemaName, String movieName,String startTime, String screenType){
        if (StringUtils.isEmpty(cinemaName)|| StringUtils.isEmpty(movieName)
                ||StringUtils.isEmpty(startTime)||StringUtils.isEmpty(screenType)){
            return null;
        }else {
            double seniorsPrice = db.sql_getDouble(String.format("select ticket_price_seniors from cinema_session " +
                    "where cinema = '%s' and movie = '%s' and start_time = to_timestamp(%s) " +
                    "and screen_type = '%s';",cinemaName,movieName,startTime,screenType), "ticket_price_seniors");
            return seniorsPrice;
        }
    }
}
