package databaseutility;

import MTBMS.Database;

public class SetTicketPrice {
    public static boolean setKidsTicketPrice(Database db, String movieName, String startTime, String cinemaName, String screenType, double newPrice) {
        boolean updateStatus = db.sql_update(String.format("UPDATE moviebooking_db.Cinema_Session SET ticket_price_kids = %s where cinema = '%s' and screen_type = '%s' and movie = '%s' and start_time = '%s';", newPrice, cinemaName.replace("'","''"), screenType, movieName.replace("'","''"), startTime));
        return updateStatus;
    }

    public static boolean setAdultsTicketPrice(Database db, String movieName, String startTime, String cinemaName, String screenType, double newPrice) {
        boolean updateStatus = db.sql_update(String.format("UPDATE moviebooking_db.Cinema_Session SET ticket_price_adults = %s where cinema = '%s' and screen_type = '%s' and movie = '%s' and start_time = '%s';", newPrice, cinemaName.replace("'","''"), screenType, movieName.replace("'","''"), startTime));
        return updateStatus;
    }

    public static boolean setSeniorsTicketPrice(Database db, String movieName, String startTime, String cinemaName, String screenType, double newPrice) {
        boolean updateStatus = db.sql_update(String.format("UPDATE moviebooking_db.Cinema_Session SET ticket_price_seniors = %s where cinema = '%s' and screen_type = '%s' and movie = '%s' and start_time = '%s';", newPrice, cinemaName.replace("'","''"), screenType, movieName.replace("'","''"), startTime));
        return updateStatus;
    }

    public static boolean setStudentsTicketPrice(Database db, String movieName, String startTime, String cinemaName, String screenType, double newPrice) {
        boolean updateStatus = db.sql_update(String.format("UPDATE moviebooking_db.Cinema_Session SET ticket_price_students = %s where cinema = '%s' and screen_type = '%s' and movie = '%s' and start_time = '%s';", newPrice, cinemaName.replace("'","''"), screenType, movieName.replace("'","''"), startTime));
        return updateStatus;
    }
}