package databaseutility;

import MTBMS.Database;

public class GetCardNumByUserName {
    public static String getCardNumByUserName(Database db, String username){
        String query = "select creditcard from moviebooking_db.users where username = '" + username + "';";
        return db.sql_getString(query, "creditcard");
    }
}
