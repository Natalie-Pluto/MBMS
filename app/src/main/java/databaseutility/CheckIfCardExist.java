package databaseutility;

import MTBMS.Database;

public class CheckIfCardExist{
    public static boolean checkIfCardExist(Database d, String cardNumber) {
        // returns false if either the gift card is not redeemed or the gift card is non-existent
        String query = String.format("SELECT number FROM moviebooking_db.credit_card where number = '%s';", cardNumber);
        String i = d.sql_getString(query, "number");
        if (i == null) return false;
        return true;
    }
}
