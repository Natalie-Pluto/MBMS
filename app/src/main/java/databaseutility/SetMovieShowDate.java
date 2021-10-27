package databaseutility;

import MTBMS.Database;
public class SetMovieShowDate {
    public static void setMovieShowDate(Database d, String movieName, String showDate){
            String query = String.format("update moviebooking_db.movie set showing_date = '%s' where name_ = '%s';", showDate, movieName.replace("'","''"));
            //System.out.println(query);
            d.sql_update(query);
    }
}
