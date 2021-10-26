package databaseutility;

import MTBMS.Database;

public class CheckIfHolderNameExist{
    public static boolean checkIfHolderNameExist(Database d, String cardHolderName) {
        // returns false if either the cardholder name is non-existent
        String query = String.format("SELECT cardholder_name FROM moviebooking_db.credit_card where number = '%s';", cardHolderName);
        Boolean i = d.sql_getBoolean(query, "cardholder_name");
        if (i ==  null) return false;
        return i;
    }
}
