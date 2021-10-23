package databaseutility;

import MTBMS.Database;
public class ChangingIdentity {
    public static void changeCreditCardBalance(Database d, String username, String newIdentity){
            d.sql_update(String.format("UPDATE moviebooking_db.Users SET identity_ = '%s' WHERE username = %s;", newIdentity, username));
    }
}
