package databaseutility;

import MTBMS.Database;

public class CheckStaff {
    public static  boolean isStaff(Database db, String username) {
        return getIdentity(db, username).equals("s");
    }

    public static  boolean isManager(Database db, String username) {
        return getIdentity(db, username).equals("m");
    }

    public static String getIdentity(Database db, String username) {
        String query = String.format("select * from moviebooking_db.users where username = '" + username + "';", "identity_");
        String id = db.sql_getString(query,"username");
        return id;
    }
}