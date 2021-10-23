package databaseutility;

import MTBMS.Database;

public class DeleteAllUpcoming {
    public static boolean deleteUpcoming(Database db) {
        String query = "delete from upcomingmovie";
        boolean delete = db.sql_update(query);
        return delete;
    }

}
