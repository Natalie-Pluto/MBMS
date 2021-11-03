package staffoperations;

import MTBMS.Database;
import databaseutility.CheckIfGiftCardExists;
import databaseutility.RedeemingGiftCard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class GiftCardRedeemToolTest {

    //class to be tested
    private GiftCardRedeemTool giftCardRedeemTool;

    private ByteArrayInputStream in;
    private InputStream sysInBackup;
    private Scanner scanner;
    private Database db;

    @Before
    public void setup(){
        db = new Database("", "", "");
    }

    @Test
    public void collectGiftCardNumber_cardNumberValidAndAlreadyExistsOnFirstAttempt_returnCardNumber() {

        //mock user input on screen type
        String userInput = "1234123412341234GC";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        giftCardRedeemTool = new GiftCardRedeemTool(scanner ,db);
        try(MockedStatic<CheckIfGiftCardExists> giftCardChecker = Mockito.mockStatic(CheckIfGiftCardExists.class)){
            giftCardChecker.when(() -> CheckIfGiftCardExists.checkIfGiftCardExists(db, userInput)).thenReturn(true);
            Assert.assertEquals(giftCardRedeemTool.collectGiftCardNumber(), userInput);
        }
    }

    @Test
    public void collectGiftCardNumber_cardNumberInvalidAndAlreadyExistsOnFirstAttempt_returnCardNumber() {

        //mock user input on screen type
        String userInput = "1234GC" + System.lineSeparator() + "1234123412341234GC";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String giftCardNumber = "1234123412341234GC";

        giftCardRedeemTool = new GiftCardRedeemTool(scanner ,db);
        try(MockedStatic<CheckIfGiftCardExists> giftCardChecker = Mockito.mockStatic(CheckIfGiftCardExists.class)){
            giftCardChecker.when(() -> CheckIfGiftCardExists.checkIfGiftCardExists(db, giftCardNumber)).thenReturn(true);
            Assert.assertEquals(giftCardRedeemTool.collectGiftCardNumber(), giftCardNumber);
        }
    }

    @Test
    public void collectGiftCardNumber_cardNumberValidAndNotAlreadyExistsOnFirstAttempt_returnCardNumber() {

        //mock user input on screen type
        String userInput = "1111111111111111GC" + System.lineSeparator() + "1234123412341234GC";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String giftCardNumber = "1234123412341234GC";

        giftCardRedeemTool = new GiftCardRedeemTool(scanner ,db);
        try(MockedStatic<CheckIfGiftCardExists> giftCardChecker = Mockito.mockStatic(CheckIfGiftCardExists.class)){
            giftCardChecker.when(() -> CheckIfGiftCardExists.checkIfGiftCardExists(any(), anyString())).thenReturn(false, true);
            Assert.assertEquals(giftCardRedeemTool.collectGiftCardNumber(), giftCardNumber);
        }
    }

    @Test
    public void collectGiftCardNumber_cardNumberInValidAndNotAlreadyExistsOnFirstAttempt_returnCardNumber() {

        //mock user input on screen type
        String userInput = "1234GC" + System.lineSeparator() + "1111111111111111GC" + System.lineSeparator() + "1234123412341234GC";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String giftCardNumber = "1234123412341234GC";

        giftCardRedeemTool = new GiftCardRedeemTool(scanner ,db);
        try(MockedStatic<CheckIfGiftCardExists> giftCardChecker = Mockito.mockStatic(CheckIfGiftCardExists.class)){
            giftCardChecker.when(() -> CheckIfGiftCardExists.checkIfGiftCardExists(any(), anyString())).thenReturn(false, true);
            Assert.assertEquals(giftCardRedeemTool.collectGiftCardNumber(), giftCardNumber);
        }
    }

    @Test
    public void run_cardNumberValidAndCardNotAlreadyExistsOnFirstAttempt_returnTrue() {

        scanner = new Scanner(System.in);

        //create a spy "giftCardInserter" for CinemaSessionInserter instance
        GiftCardRedeemTool giftCardRedeemTool = new GiftCardRedeemTool(scanner, db);
        GiftCardRedeemTool spyGiftCardRedeemTool = Mockito.spy(giftCardRedeemTool);

        Mockito.doReturn("1234123412341234GC").when(spyGiftCardRedeemTool).collectGiftCardNumber();

        //run() relies on checkIfGiftCardExists() in databaseutility package
        //here, checkIfGiftCardExists() is stubbed to return false when it is passed with valid parameters

        try(MockedStatic<RedeemingGiftCard> redeemGiftCard = Mockito.mockStatic(RedeemingGiftCard.class)){
            redeemGiftCard.when(() -> RedeemingGiftCard.redeemGiftCard(db, "1234123412341234GC")).thenReturn(true);
            Assert.assertEquals(spyGiftCardRedeemTool.run(), true);
        }

    }




}