package staffoperations;

import databaseutility.AddingGiftCard;
import databaseutility.CheckIfGiftCardExists;
import MTBMS.Database;
import databaseutility.RedeemingGiftCard;

import java.util.Scanner;

public class GiftCardRedeemTool {
    private Scanner s;
    private Database d;

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

    public GiftCardRedeemTool(Scanner scanner, Database db){
        s = scanner;
        d = db;
    }

    public String collectGiftCardNumber(){
        System.out.println(PURPLE_BOLD + "Enter the gift card number:" + ANSI_RESET);
        String giftCardNumber = s.nextLine();
        if (giftCardNumber.length() != 18 || !(giftCardNumber.endsWith("GC"))) {
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Gift card format is invalid. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            giftCardNumber = collectGiftCardNumber();
        }
        boolean exist = CheckIfGiftCardExists.checkIfGiftCardExists(d, giftCardNumber);
        if(!exist){
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Gift card does not exist. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            giftCardNumber = collectGiftCardNumber();
        }
        return giftCardNumber;
    }

    public boolean run(){
        String giftCardNumber = collectGiftCardNumber();
        boolean success = RedeemingGiftCard.redeemGiftCard(d, giftCardNumber);
        return success;
    }

}


