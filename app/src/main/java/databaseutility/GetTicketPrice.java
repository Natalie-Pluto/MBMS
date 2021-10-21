package databaseutility;

import MTBMS.Database;
import utils.StringUtils;

public class GetTicketPrice {
    public static String getTicketPrice(Database db, String cinemaName, String movieName,String startTime, String type){
        if (StringUtils.isEmpty(cinemaName)|| StringUtils.isEmpty(movieName)
                ||StringUtils.isEmpty(startTime)||StringUtils.isEmpty(type)){
            return "";
        }else {
            double adultPrice = db.sql_getDouble(String.format("select ticket_price_adults from cinema_session " +
                    "where cinema = '%s' and movie = '%s' and start_time = to_timestamp(%s) " +
                    "and screen_type = '%s';",cinemaName,movieName,startTime,type), "ticket_price_adults");
            double kidPrice = db.sql_getDouble(String.format("select ticket_price_kids from cinema_session " +
                    "where cinema = '%s' and movie = '%s' and start_time = to_timestamp(%s) " +
                    "and screen_type = '%s';",cinemaName,movieName,startTime,type), "ticket_price_kids");
            double seniorPrice = db.sql_getDouble(String.format("select ticket_price_seniors from cinema_session " +
                    "where cinema = '%s' and movie = '%s' and start_time = to_timestamp(%s) " +
                    "and screen_type = '%s';",cinemaName,movieName,startTime,type), "ticket_price_seniors");

            return adultPrice+"/"+kidPrice+"/"+seniorPrice;
        }
    }
}
