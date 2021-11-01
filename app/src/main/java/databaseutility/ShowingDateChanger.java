package databaseutility;

import MTBMS.Database;

public class ShowingDateChanger {
    public static boolean changeShowingDate(Database d, String movieName, String newShowingDate){
        String query = String.format("UPDATE moviebooking_db.Movie SET showing_date = '%s' WHERE name_ = '%s';", newShowingDate, movieName);
        boolean success = d.sql_update(query);
        return success;
    }
}
