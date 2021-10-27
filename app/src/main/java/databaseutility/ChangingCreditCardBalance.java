package databaseutility;

import MTBMS.Database;
public class ChangingCreditCardBalance {
    public static void changeCreditCardBalance(Database d, String cardNumber, double newBalance){
            d.sql_update(String.format("UPDATE moviebooking_db.Credit_Card SET balance = %s WHERE number = '%s';", newBalance, cardNumber));
    }
}
