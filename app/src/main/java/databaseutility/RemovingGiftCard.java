package databaseutility;
import MTBMS.Database;

public class RemovingGiftCard {
    public static boolean removegiftCard(Database db, String giftCardNumber) {
        return(db.sql_update("delete from moviebooking_db.Gift_Card where number = " + giftCardNumber + ";"));
    }
}
