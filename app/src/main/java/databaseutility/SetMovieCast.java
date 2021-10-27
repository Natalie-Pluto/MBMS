package databaseutility;

import MTBMS.Database;
public class SetMovieCast {
    public static void setMovieCast(Database d, String movieName, String newCast){
            String query = String.format("update moviebooking_db.movie set cast_ = '%s' where name_ = '%s';", newCast.replace("'","''"), movieName.replace("'","''"));
            System.out.println(query);
            d.sql_update(query);
    }
}
