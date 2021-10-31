package databaseutility;

import MTBMS.Database;

public class SetUserSetting {
    public static boolean setUserSetting(Database db, String username, String cinemaName) {
        return db.sql_update(String.format("UPDATE moviebooking_db.users SET settings = '%s' where username = '%s';", cinemaName.replace("'","''"), username));
    }
}
