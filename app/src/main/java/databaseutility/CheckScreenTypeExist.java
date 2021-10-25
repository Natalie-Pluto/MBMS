package databaseutility;

import MTBMS.Database;

public class CheckScreenTypeExist {
    public static boolean checkScreenExist(Database d, String size) {
        // returns false if either the gift card is not redeemed or the gift card is non-existent
        String query = String.format("SELECT * FROM moviebooking_db.cinema_session where screen_type = '%s';", size);
        String screen_size = d.sql_getString(query, "screen_type");
        if(screen_size == null) {
            return false;
        }
        return true;
    }
}
