/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package MTBMS;

import databaseutility.AddMovieSession;
import databaseutility.AddingCinema;
import databaseutility.RemovingCinema;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class AppTest {
    private static final Database dbInstance = new Database("jdbc:postgresql://ls-d4381878930280384f33af335289e24c73224a04.c0apyqxz8x8m.ap-southeast-2.rds.amazonaws.com:5432/postgres",
            "dbmasteruser","A>XV>D*7r-V{y_wL}}I{+U=8zEtj1*T<");
    //private static final Database dbInstance = new Database("jdbc:postgresql://localhost:5432/MTBMS", "postgres", "329099");
    private static final BookingSystem instance = new BookingSystem();
    private final ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
    private final PrintStream systemOutput = System.out;
    private final InputStream systemInput = System.in;

    @BeforeEach
    public void setUpOutput() {
        System.setOut(new PrintStream(testOutput));
    }

    private String getOutput() {
        return testOutput.toString();
    }

    private void getInput(String data) {
        //sources from https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println/1119559#1119559
        ByteArrayInputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);
    }

    @AfterEach
    private void reset() {
        System.setIn(systemInput);
        System.setOut(systemOutput);
    }


    /*private void input(String data){


    }*/

    /*@Test
    public void loginIn1() throws InterruptedException {
        BookingSystem.login("Bean", "1234", dbInstance);
        BookingSystem.login("Lappland", "1234", dbInstance);
        BookingSystem.login("Demo", "1234", dbInstance);
        assertNotNull(getOutput());
    }

    @Test
    public void loginIn2() throws InterruptedException {
        BookingSystem.login("B", "1", dbInstance);
        assertNotNull(getOutput());
    }*/

    /*@Test
    public void guestService() throws InterruptedException {
        String data = "3" + System.getProperty("line.separator") + "1" + System.getProperty("line.separator");
        InputStream systemInput = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Guest guest = new Guest("Bean", "C", " ");
        guest.guestService("C");
        System.setIn(systemInput);
        assertNotNull(getOutput());
    }*/

   /* @Test
    public void guestService4() throws InterruptedException {
        Guest guest = new Guest("Bean", "C", " ");
        getInput("1");
        guest.guestService();
        assertNotNull(getOutput());
    }*/

    /*@Test
    public void guestService5() throws InterruptedException {
        Guest guest = new Guest("Bean", "C", " ");
        getInput("2");
        guest.guestService();
        assertNotNull(getOutput());
    }*/

    /*@Test
    public void guestService1() throws InterruptedException {
        Guest guest = new Guest("Bean", "C", " ");
        getInput("1");
        guest.guestService();
        assertNotNull(getOutput());
    }*/

    /*@Test
    public void guestService2() throws InterruptedException {
        Guest guest = new Guest("Bean", "C", " ");
        input("2");
        input("Balala");
        input("1234");
        input("1");
        guest.guestService("G");
        assertNotNull(getOutput());
    }*/

    /*@Test
    public void guestService3() throws InterruptedException {
        Guest guest = new Guest("Bean", "C", " ");
        input("3");
        input("1");
        guest.guestService("G");
        assertNotNull(getOutput());
    }*/

   /*@Test
    public void signUp1() throws InterruptedException {
        input("Cat" + System.lineSeparator() + "1234");
        instance.signUp("C", dbInstance);
        assertNotNull(getOutput());
    }

    @Test
    public void signUp2() throws InterruptedException {
        input("Demo" + "\n555" + "\n1234" + "\n1");
        instance.signUp("NA", dbInstance);
        assertNotNull(getOutput());
    }

    @Test
    public void signUp3() throws InterruptedException {
        input("balabala" + "\n1234" + "\n2" + "\nY");
        instance.signUp("NA", dbInstance);
        assertNotNull(getOutput());
    }

    @Test
    public void signUp4() throws InterruptedException {
        input("balabalala" + "\n1234" + "\n2" + "\nN");
        instance.signUp("NA", dbInstance);
        assertNotNull(getOutput());
    }

    @Test
    public void signUp5() throws InterruptedException {
        input("balabalalaha" + "\n1234" + "\n2" + "\nB" + "\nB" + "\nB");
        instance.signUp("NA", dbInstance);
        assertNotNull(getOutput());
    }

    @Test
    public void signUp6() throws InterruptedException {
        input("balabalalala" + "\n1234" + "\n4" + "\n4" + "\n4");
        instance.signUp("NA", dbInstance);
        assertNotNull(getOutput());
    }*/

   /* @Test
    public void defaultTest1() throws InterruptedException {
        input("1");
        instance.defaultPage();
        assertNotNull(getOutput());
    }

    @Test
    public void defaultTest2() throws InterruptedException {
        input("2");
        instance.defaultPage();
        assertNotNull(getOutput());
    }

    @Test
    public void defaultTest3() throws InterruptedException {
        input("3");
        instance.defaultPage();
        assertNotNull(getOutput());
    }

    @Test
    public void defaultTest4() throws InterruptedException {
        input("4");
        instance.defaultPage();
        assertNotNull(getOutput());
    }*/


    @Test
    public void greetingTest() throws InterruptedException {
       BookingSystem.getGreeting(dbInstance);
       //assertNotNull(getOutput());
    }

    @Test
    public void getPaymentTypeTest_1() throws InterruptedException {
        getInput("1");
        assertEquals("1", Guest.getPaymentType());


    }

    /*@Test public void updateSeatsTest() throws InterruptedException {
        getInput("2" + System.lineSeparator() + "00000000000001GC");
        Guest.updateSeats("Test", "Test", "2021-10-27 18:00","silver", 1,"front");
    }*/

    @Test public void getPaymentTypeTest_2(){
        getInput("2");
        assertEquals("Wrong output","2", Guest.getPaymentType());
    }

    /*@Test public void getPaymentTypeTest_w(){
        getInput("wrong input");
        assertNotEquals("Wrong output", Guest.getPaymentType(), "wrong input");
    } */

   /* @Test public void checkPaymentTest_2() throws InterruptedException {
        getInput("00000000000006GC");
        assertTrue(Guest.checkPayment("2"));
    }*/
/* 
    @Test public void checkMovieName_1() throws InterruptedException {
        getInput("Test");
        assertEquals(Guest.checkMovieName(), "Test");
    }

    @Test public void checkCinemaName_1() throws  InterruptedException {
        getInput("Blacktown");
        assertEquals(Guest.checkCinemaName("Ip Man"), "Blacktown");
    }

    @Test public void checkStartTimeTest() throws InterruptedException{
        getInput("2021-10-28 21:00:00");
        assertEquals("2021-10-28 21:00:00", Guest.checkStartTime("Ip Man", "Blacktown"));
    }

    @Test public void getAudienceNumTest() {

    }

    @Test public void getSeatLocationTest() throws InterruptedException {
        getInput("front");
        assertEquals("front", Guest.getSeatLocation());
    }

    @Test public void getCardNumTest() throws InterruptedException{
        getInput("0000");
        assertEquals("0000", Guest.getCardNum());
    }

    @Test public void cardNumberCheckTest_r() throws  InterruptedException{
        getInput("00000");
        assertTrue(Guest.cardNumberCheck());
    }

    @Test public void getCardHolderNameTest() throws InterruptedException{
        getInput("test");
        assertEquals("test", Guest.getCardHolderName());
    }

    /*@Test public void cardHolderNameCheckTest_r() throws InterruptedException{
        getInput("johnny");
        assertTrue(Guest.cardHolderNameCheck());
    }*/

    /* @Test public void saveCreditCardTest() {
        getInput("1");
        Guest.saveCreditCard("alien", "00001");
    } */
} 


