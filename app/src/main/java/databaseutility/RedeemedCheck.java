package databaseutility;

import MTBMS.Database;

public class RedeemedCheck{
    public static boolean giftCardRedeemed(Database d, String giftCardNumber) {
        // returns false if either the gift card is not redeemed or the gift card is non-existent
        String query = String.format("SELECT redeemed FROM moviebooking_db.Gift_Card where number = '%s';", giftCardNumber);
        Boolean i = d.sql_getBoolean(query, "redeemed");
        if (i ==  null) return false;
        return i;
    }
}
