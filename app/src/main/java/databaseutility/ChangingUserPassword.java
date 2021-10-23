package databaseutility;

import MTBMS.Database;
public class ChangingUserPassword {
    public static void changeCreditCardBalance(Database d, String username, String newPassword){
            d.sql_update(String.format("UPDATE moviebooking_db.Users SET password_ = '%s' WHERE username = %s;", newPassword, username));
    }
}
