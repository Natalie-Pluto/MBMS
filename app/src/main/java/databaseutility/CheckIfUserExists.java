package databaseutility;

import MTBMS.Database;

public class CheckIfUserExists {
    public static boolean checkIfUserExists(Database db, String username) {
        String userFromDB = db.sql_getString("select username from moviebooking_db.users where username = '" + username + "';", "username");
        if (userFromDB == null) return false;
        return userFromDB.equals(username);
    }
}