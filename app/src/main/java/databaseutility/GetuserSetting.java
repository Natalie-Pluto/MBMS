package databaseutility;

import MTBMS.Database;

public class GetuserSetting {
    public static String getUsersetting(Database db, String username){
        return db.sql_getString(String.format("select settings from moviebooking_db.Users " +
                "where username = '%s';",username), "settings");
    }
}
