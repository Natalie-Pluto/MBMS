package staffoperations;

import MTBMS.Database;
import databaseutility.AddingGiftCard;
import databaseutility.CheckIfGiftCardExists;
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

public class GiftCardInserterTest {

    //class to be tested
    private GiftCardInserter giftCardInserter;

    private ByteArrayInputStream in;
    private InputStream sysInBackup;
    private Scanner scanner;
    private Database db;

    @Before
    public void setup(){
        db = new Database("", "", "");
    }

    @Test
    public void collectGiftCardNumber_cardNumberValidAndNotAlreadyExistsOnFirstAttempt_returnCardNumber() {

        //mock user input on screen type
        String userInput = "1234123412341234GC";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        giftCardInserter = new GiftCardInserter(scanner ,db);
        try(MockedStatic<CheckIfGiftCardExists> giftCardChecker = Mockito.mockStatic(CheckIfGiftCardExists.class)){
            giftCardChecker.when(() -> CheckIfGiftCardExists.checkIfGiftCardExists(db, userInput)).thenReturn(false);
            Assert.assertEquals(giftCardInserter.collectGiftCardNumber(), userInput);
        }
    }

    @Test
    public void collectGiftCardNumber_cardNumberInvalidAndNotAlreadyExistsOnFirstAttempt_returnCardNumber() {

        //mock user input on screen type
        String userInput = "1234GC" + System.lineSeparator() + "1234123412341234GC";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String giftCardNumber = "1234123412341234GC";

        giftCardInserter = new GiftCardInserter(scanner ,db);
        try(MockedStatic<CheckIfGiftCardExists> giftCardChecker = Mockito.mockStatic(CheckIfGiftCardExists.class)){
            giftCardChecker.when(() -> CheckIfGiftCardExists.checkIfGiftCardExists(db, userInput)).thenReturn(false);
            Assert.assertEquals(giftCardInserter.collectGiftCardNumber(), giftCardNumber);
        }
    }

    @Test
    public void collectGiftCardNumber_cardNumberValidAndAlreadyExistsOnFirstAttempt_returnCardNumber() {

        //mock user input on screen type
        String userInput = "1234123412341234GC" + System.lineSeparator() + "1234123412341234GC";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String giftCardNumber = "1234123412341234GC";

        giftCardInserter = new GiftCardInserter(scanner ,db);
        try(MockedStatic<CheckIfGiftCardExists> giftCardChecker = Mockito.mockStatic(CheckIfGiftCardExists.class)){
            giftCardChecker.when(() -> CheckIfGiftCardExists.checkIfGiftCardExists(any(), anyString())).thenReturn(true, false);
            Assert.assertEquals(giftCardInserter.collectGiftCardNumber(), giftCardNumber);
        }
    }

    @Test
    public void collectGiftCardNumber_cardNumberInValidAndAlreadyExistsOnFirstAttempt_returnCardNumber() {

        //mock user input on screen type
        String userInput = "1234GC" + System.lineSeparator() + "1234123412341234GC" + System.lineSeparator() + "1234123412341234GC";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String giftCardNumber = "1234123412341234GC";

        giftCardInserter = new GiftCardInserter(scanner ,db);
        try(MockedStatic<CheckIfGiftCardExists> giftCardChecker = Mockito.mockStatic(CheckIfGiftCardExists.class)){
            giftCardChecker.when(() -> CheckIfGiftCardExists.checkIfGiftCardExists(any(), anyString())).thenReturn(true, false);
            Assert.assertEquals(giftCardInserter.collectGiftCardNumber(), giftCardNumber);
        }
    }

    @Test
    public void run_cardNumberValidAndCardNotAlreadyExistsOnFirstAttempt_returnTrue() {

        scanner = new Scanner(System.in);

        //create a spy "giftCardInserter" for CinemaSessionInserter instance
        GiftCardInserter giftCardInserter = new GiftCardInserter(scanner, db);
        GiftCardInserter spyGiftCardInserter = Mockito.spy(giftCardInserter);

        Mockito.doReturn("1234123412341234GC").when(spyGiftCardInserter).collectGiftCardNumber();

        //run() relies on checkIfGiftCardExists() in databaseutility package
        //here, checkIfGiftCardExists() is stubbed to return false when it is passed with valid parameters

        try(MockedStatic<AddingGiftCard> addGiftCard = Mockito.mockStatic(AddingGiftCard.class)){
            addGiftCard.when(() -> AddingGiftCard.addGiftCard(db, "1234123412341234GC")).thenReturn(true);
            Assert.assertEquals(spyGiftCardInserter.run(), true);
        }

    }




}