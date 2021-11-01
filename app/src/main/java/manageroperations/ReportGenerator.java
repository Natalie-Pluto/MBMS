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

    // Regular
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";


    // Bold
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE

    // Background
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW


    // Bold High Intensity
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE

    public ReportGenerator(Scanner scanner, Database db){
        d = db;
        s = scanner;
    }

    public void getCancellationReport(){
        ArrayList<String[]> failedTransactions = ListAllFailedTransactions.listAllFailedTransactions(d);
        if(failedTransactions == null){
            System.out.println(PURPLE_BOLD_BRIGHT + "Cancellation History is empty. (｡･ω･｡)ﾉ" + ANSI_RESET);
            return;
        }
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
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: An error occurred. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            success = false;
            e.printStackTrace();
        }
        if(success){
            System.out.println(PURPLE_BOLD_BRIGHT + "Successfully generated the Movie Session Report. (｡･ω･｡)ﾉ" + ANSI_RESET);
        }
    }
}
