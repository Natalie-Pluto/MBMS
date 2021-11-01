package databaseutility;

import MTBMS.Database;

public class CheckCreditCard{
    public static boolean checkCreditCard(Database d, String cardNumber, String cardholderName) {
        // returns false if card not exist
        String query = String.format("SELECT number FROM moviebooking_db.credit_card where number = '%s' and card_holder_name = '%s';", cardNumber,cardholderName);
        String i = d.sql_getString(query, "number");
        if (i == null) return false;
        return i.equals(cardNumber);
    }
}
