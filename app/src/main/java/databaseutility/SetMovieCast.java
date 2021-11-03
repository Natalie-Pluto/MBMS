package databaseutility;

import MTBMS.Database;
public class SetMovieCast {
    public static boolean setMovieCast(Database d, String movieName, String newCast){
            String query = String.format("update moviebooking_db.movie set cast_ = '%s' where name_ = '%s';", newCast.replace("'","''"), movieName.replace("'","''"));
            return d.sql_update(query);
    }
}
