package staffoperations;

import java.util.Scanner;
import MTBMS.Database;
import databaseutility.*;


public class GiftCardManipulator {
    /*public static void addGiftCard(Database d){

        System.out.println("Note: To quit, Enter \"-Exit-\"");
        Scanner s = new Scanner(System.in);
        String input = "";
        String cardNumber = "";

        //keep running unless user quits
        while(!input.equals("-Exit-")) {

            // retrieve information
            System.out.println("Enter the gift card number to add: ");
            input = s.nextLine();

            //quit if user wants to quit
            if(input.equals("-Exit-")){
                break;
            }
            cardNumber = input;

            //check if card number already exists
            boolean exist = CheckIfGiftCardExists.checkIfGiftCardExists(d, cardNumber);
            if(exist == true){
                System.out.println("Error: gift card number already exists.");
                continue;
            }

            AddingGiftCard.addGiftCard(d, cardNumber);
            System.out.println("Success: gift card added.");
        }
    }

    public static void removeGiftCard(Database d){

        System.out.println("Note: To quit, Enter \"-Exit-\"");
        Scanner s = new Scanner(System.in);
        String input = "";
        String cardNumber = "";

        //keep running unless user quits
        while(!input.equals("-Exit-")) {

            // retrieve information
            System.out.println("Enter the gift card number to remove: ");
            input = s.nextLine();

            //quit if user wants to quit
            if(input.equals("-Exit-")){
                break;
            }
            cardNumber = input;

            //check if card number exists
            boolean exist = CheckIfGiftCardExists.checkIfGiftCardExists(d, cardNumber);
            if(exist == false){
                System.out.println("Error: gift card number does not exist.");
                continue;
            }

            RemovingGiftCard.removeGiftCard(d, cardNumber); // 
            System.out.println("Success: gift card removed.");
        }
    }*/
}
