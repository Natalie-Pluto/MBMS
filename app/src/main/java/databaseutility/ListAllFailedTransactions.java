package databaseutility;

import MTBMS.Database;
import java.util.List;
import java.util.ArrayList;

public class ListAllFailedTransactions {
    public static ArrayList<String[]> listAllFailedTransactions(Database d){
        String query = "SELECT * FROM moviebooking_db.canceled_transaction ORDER BY transaction_time ASC, user ASC, reason ASC;";
        List<String> transactionTime = d.sql_getStrings(query, "transaction_time");
        List<String> user = d.sql_getStrings(query, "user");
        List<String> reason = d.sql_getStrings(query, "reason");
        ArrayList<String[]> failedTransactions = new ArrayList<String[]>();
        if(transactionTime == null){
            return failedTransactions;
        }
        for(int i = 0; i < transactionTime.size(); i++){
            String[] failedTransaction = new String[10];
            failedTransaction[0] = transactionTime.get(i);
            failedTransaction[1] = user.get(i);
            failedTransaction[2] = reason.get(i);
            failedTransactions.add(failedTransaction);
        }
        return failedTransactions;
    }
}
