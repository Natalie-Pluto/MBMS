package manageroperations;

import MTBMS.Database;
import databaseutility.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReportGenerator {
    private Database d;
    private Scanner s;

    public ReportGenerator(Scanner scanner, Database db){
        d = db;
        s = scanner;
    }

    public void getCancellationReport(){
        ArrayList<String[]> failedTransactions = ListAllFailedTransactions.listAllFailedTransactions(d);
        boolean success = true;
        try{
            FileWriter myWriter = new FileWriter("CancellationReport");
            for(int i = 0; i < failedTransactions.size(); i++){
                String[] failedTransaction = failedTransactions.get(i);
                String transactionTime = failedTransaction[0];
                String user = failedTransaction[1];
                String reason = failedTransaction[2];
                myWriter.write(String.format("Transaction Time: %s User: %s Reason: %s.", transactionTime, user, reason));
                myWriter.write(System.lineSeparator());
            }
            myWriter.close();
        }catch(IOException e){
            System.out.println("Error: An error occurred.");
            success = false;
            e.printStackTrace();
        }
        if(success){
            System.out.println("Successfully generated the Movie Session Report.");
        }
    }
}
