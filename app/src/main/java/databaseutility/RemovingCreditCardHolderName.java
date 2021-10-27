package databaseutility;
import MTBMS.Database;

public class RemovingCreditCardHolderName {
    public static boolean removeCreditCardHolderName(Database db, String cardHolderName) {
        return(db.sql_update("delete from moviebooking_db.Credit_Card where cardholder_name = '" + cardHolderName + "';"));
    }
}
