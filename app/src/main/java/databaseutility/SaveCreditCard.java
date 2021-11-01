package databaseutility;

import MTBMS.Database;

public class SaveCreditCard {
    public static void saveCreditCard(Database db, String cardNum, String username){
        String query = String.format("update moviebooking_db.users set creditcard = '%s' where username= '%s'", cardNum.replace("'", "''"), username.replace("'", "''"));
        db.sql_update(query);
    }
}
