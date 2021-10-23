package databaseutility;
import MTBMS.Database;

public class RemovingCreditCard {
    public static boolean removeCreditCard(Database db, String cardNumber) {
        return(db.sql_update("delete from moviebooking_db.Credit_Card where number = " + cardNumber + ";"));
    }
}
