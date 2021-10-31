package staffoperations;

import databaseutility.AddingGiftCard;
import databaseutility.CheckIfGiftCardExists;
import MTBMS.Database;
import databaseutility.RedeemingGiftCard;

import java.util.Scanner;

public class GiftCardRedeemTool {
    private Scanner s;
    private Database d;

    public GiftCardRedeemTool(Scanner scanner, Database db){
        s = scanner;
        d = db;
    }

    public String collectGiftCardNumber(){
        System.out.println("Enter the gift card number:");
        String giftCardNumber = s.nextLine();
        boolean exist = CheckIfGiftCardExists.checkIfGiftCardExists(d, giftCardNumber);
        if(!exist){
            System.out.println("Error: Gift card does not exist.");
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


