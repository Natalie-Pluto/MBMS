package databaseutility;

import MTBMS.Database;

public class AddingGiftCard {
    public static boolean addGiftCard(Database db, String number) {
        boolean updateStatus = db.sql_update(String.format("insert into moviebooking_db.Gift_Card values('%s', false) on conflict (number) do nothing;",number));
        return updateStatus;
    }
}