package databaseutility;

import MTBMS.Database;

public class GetUserPassword {
    public static String getUserPassword(Database db, String username){
            return db.sql_getString(String.format("select password_ from moviebooking_db.Users " +
                    "where username = '%s';",username), "password_");
    }
}
