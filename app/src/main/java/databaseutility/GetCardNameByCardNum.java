package databaseutility;

import MTBMS.Database;

public class GetCardNameByCardNum {
    public static String getCardNameByCardNum(Database db, String cardNum){
        String query = "select cardholder_name from moviebooking_db.credit_card where number = '" + cardNum + "';";
        return db.sql_getString(query, "cardholder_name");

    }
}
