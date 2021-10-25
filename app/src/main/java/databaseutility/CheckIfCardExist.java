package databaseutility;

import MTBMS.Database;

public class CheckIfCardExist{
    public static boolean checkIfCardExist(Database d, String cardNumber) {
        // returns false if either the gift card is not redeemed or the gift card is non-existent
        String query = String.format("SELECT number FROM moviebooking_db.credit_card where number = '%s';", cardNumber);
        Boolean i = d.sql_getBoolean(query, "number");
        if (i ==  null) return false;
        return i;
    }
}
