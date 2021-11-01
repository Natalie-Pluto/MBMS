package databaseutility;

import MTBMS.Database;

public class AddingUser {
    public static boolean addUser(Database db, String username, String password, String identity) {
        String updateArgs = "'" + username.replace("'", "''") + "', '" + password.replace("'", "''") + "', '" + identity + "'";
        boolean updateStatus = db.sql_update("insert into moviebooking_db.users(username,password_,identity_) values(" + updateArgs + ") on conflict (username) do nothing;;");
        return updateStatus;
    }
}