package databaseutility;

import MTBMS.Database;

public class UpdateCardBalance {
    public static void updateCardBalance(Database db, String cardHolderName, String cardNum, int newBalance){
        String query = String.format("update moviebooking_db.credit_card set balance = %s where number = '%s' and cardholer_name = '%s'", newBalance, cardNum.replace("'", "''"), cardHolderName.replace("'", "''"));
        db.sql_update(query);
    }
}
