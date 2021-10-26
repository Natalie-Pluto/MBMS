package databaseutility;
import MTBMS.Database;

public class RemovingCinema {
    public static boolean removeCinema(Database db, String cinemaName) {
        return(db.sql_update("delete from moviebooking_db.Cinema where cinema_name = '" + cinemaName.replace("'", "''") + "';"));
    }
}
