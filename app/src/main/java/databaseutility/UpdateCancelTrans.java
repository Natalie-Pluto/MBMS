package databaseutility;
import MTBMS.Database;
import java.sql.Timestamp;

public class UpdateCancelTrans {
    public static void updateCancelTrans(Database db, String username, String reason){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String query = String.format("insert into moviebooking_db.canceled_transaction VALUES('%s', '%s', '%s')", timestamp.toString().replace("'", "''"), username.replace("'", "''"), reason.replace("'", "''"));
        db.sql_update(query);
    }
}
