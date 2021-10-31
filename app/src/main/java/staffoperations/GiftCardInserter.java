package staffoperations;

import databaseutility.AddingGiftCard;
import databaseutility.CheckIfGiftCardExists;
import databaseutility.CheckIfMovieExists;
import MTBMS.Database;
import java.util.Scanner;

public class GiftCardInserter {
    private Scanner s;
    private Database d;

    public GiftCardInserter(Scanner scanner, Database db){
        s = scanner;
        d = db;
    }

    public String collectGiftCardNumber(){
        System.out.println("Enter the gift card number:");
        String giftCardNumber = s.nextLine();
        boolean exist = CheckIfGiftCardExists.checkIfGiftCardExists(d, giftCardNumber);
        if(exist){
            System.out.println("Error: Gift card exists.");
            giftCardNumber = collectGiftCardNumber();
        }
        return giftCardNumber;
    }

    public boolean run(){
        String giftCardNumber = collectGiftCardNumber();
        boolean success = AddingGiftCard.addGiftCard(d, giftCardNumber);
        return success;
    }

}
