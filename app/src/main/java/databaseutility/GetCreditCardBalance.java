package databaseutility;

import MTBMS.Database;

public class GetCreditCardBalance {
    public static Double getCreditCardBalance(Database db, String cardNumber) {
        String query = "select balance from moviebooking_db.Movie where number = '" + cardNumber + "';";
        return(db.sql_getDouble(query,"balance"));
    }
}
