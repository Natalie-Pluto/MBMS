package databaseutility;

import MTBMS.Database;

public class AddingUser {
    public static boolean addUser(Database db, String username, String password, String identity) {
        String updateArgs = "'" + username + "', '" + password + "', '" + identity + "'";
        boolean updateStatus = db.sql_update("insert into moviebooking_db.users(username,password_,identity_) values(" + updateArgs + ");");
        return updateStatus;
    }
}
