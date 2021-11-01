package databaseutility;

import MTBMS.Database;

public class CastsChanger {
    public static boolean changeCasts(Database d, String movieName, String newCasts){
        String query = String.format("UPDATE moviebooking_db.Movie SET cast_ = '%s' WHERE name_ = '%s';", newCasts.replace("'", "''"), movieName.replace("'", "''"));
        boolean success = d.sql_update(query);
        return success;
    }
}
