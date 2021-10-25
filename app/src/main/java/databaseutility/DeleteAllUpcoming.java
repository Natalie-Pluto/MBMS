package databaseutility;

import MTBMS.Database;

public class DeleteAllUpcoming {
    public static boolean deleteUpcoming(Database db) {
        String query = "delete from moviebooking_db.upcomingmovie";
        return db.sql_update(query);
    }

}
