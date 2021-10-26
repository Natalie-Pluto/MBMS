package databaseutility;

import MTBMS.Database;

public class AddingCreditCard {
    public static boolean addCreditCard(Database db, String cardNumber, String cardHolderName, String pin) {
        boolean updateStatus = db.sql_update(String.format("insert into moviebooking_db.Credit_Card values('%s','%s','%s',0) on conflict (number) do nothing;",cardNumber, cardHolderName, pin));
        return updateStatus;
    }
}