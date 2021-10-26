package databaseutility;

import MTBMS.Database;

public class CheckIfGiftCardExists {
    public static boolean checkIfGiftCardExists(Database db, String cardNumber) {
        String giftCard = db.sql_getString("select number from moviebooking_db.gift_Card where number = '" + cardNumber + "';", "number");
        if (giftCard == null) return false;
        return giftCard.equals(cardNumber);
    }
}
