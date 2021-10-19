package databaseutility;

import MTBMS.Database;

public class UserAuthenticate {
    public static Boolean authenticate(Database db, String username, String password) {
        String query =  String.format("select * from moviebooking_db.users where username = '" + username + "' and password_ = '" + password + "';", "username");
        String user = db.sql_getString(query, "username");
        if (user == null) return false;
        return true;
    }
}
