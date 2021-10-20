package databaseutility;

import MTBMS.Database;

public class RemovingUser {
    public static boolean removeUser(Database db, String username) {
        boolean updateStatus = db.sql_update("delete from moviebooking_db.users where username = '" + username + "';");
        return updateStatus;
    }
}