package databaseutility;

import MTBMS.Database;

public class RedeemingGiftCard{
    public static boolean redeemGiftCard(Database d, String giftCardNumber) {
        // returns false if either the gift card is not redeemed or the gift card is non-existent
        String query = String.format("UPDATE moviebooking_db.Gift_Card SET redeemed = true where number = '%s';", giftCardNumber);
        Boolean i = d.sql_getBoolean(query, "count");
        if (i ==  null) return false;
        return i;
    }
}
