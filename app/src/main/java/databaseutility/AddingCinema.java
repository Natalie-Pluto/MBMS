package databaseutility;

import MTBMS.Database;

public class AddingCinema {
    public static boolean addCinema(Database db, String cinemaName) {
        boolean updateStatus = db.sql_update(String.format("insert into moviebooking_db.Cinema values('%s') on conflict (cinema_name) do nothing;;",cinemaName));
        return updateStatus;
    }
}