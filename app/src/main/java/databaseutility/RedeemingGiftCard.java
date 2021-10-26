package databaseutility;

import MTBMS.Database;

public class RedeemingGiftCard{
    public static void redeemGiftCard(Database db, String giftCardNumber) {
        // returns false if either the gift card is not redeemed or the gift card is non-existent
        boolean query = db.sql_update(String.format("UPDATE moviebooking_db.Gift_Card SET redeemed = true where number = '%s';", giftCardNumber));
        return;
        //Boolean i = db.sql_getBoolean(query, "redeemed");
        //if (i ==  null) return false;
        //return i;
    }
}
