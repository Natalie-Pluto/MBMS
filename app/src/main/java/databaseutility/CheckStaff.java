package databaseutility;

import MTBMS.Database;

public class CheckStaff {
    public static  boolean isStaff(Database db, String username) {
        String id =  getIdentity(db, username);
        if (id == null) return false;
        return id.equals("s");
    }

    public static boolean isManager(Database db, String username) {
        if (getIdentity(db, username) == null) return false;
        return getIdentity(db, username).equals("m");
    }

    public static String getIdentity(Database db, String username) {
        String query = String.format("select * from moviebooking_db.users where username = '" + username + "';", "identity_");
        String id = db.sql_getString(query,"identity_");
        if (id == null) return "";
        return id;
    }
}
