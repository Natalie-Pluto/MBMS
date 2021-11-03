/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package MTBMS;

import databaseutility.*;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static databaseutility.AddMovieSession.addMovieSession;
import static databaseutility.AddingCinema.addCinema;
import static databaseutility.CheckIfSessionExists.checkIfSessionExists;
import static databaseutility.RemovingSession.removeSession;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class AppTest {
    //private static final Database dbInstance = new Database("jdbc:postgresql://ls-d4381878930280384f33af335289e24c73224a04.c0apyqxz8x8m.ap-southeast-2.rds.amazonaws.com:5432/postgres", "dbmasteruser","A>XV>D*7r-V{y_wL}}I{+U=8zEtj1*T<");
    //private static final Database dbInstance = new Database("jdbc:postgresql://localhost:5432/MTBMS", "postgres", "329099");
    private static final Database dbInstance =  new Database("jdbc:postgresql://localhost:5432/postgres", "postgres", "0000");
    private static final Guest instance = new Guest("Andy", "c", " ");
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

    @Test public void getUsernameTest(){
        Guest guest = new Guest("Test", "c", "");
        assertEquals("Test", guest.getUsername());
    }

    @Test public void getIdentityTest(){
        Guest guest = new Guest("Test", "c", "");
        assertEquals("c", guest.getIdentity());
    }

    @Test public void getSettingTest(){
        Guest guest = new Guest("Test", "c", "Test");
        assertEquals("Test", guest.getSettings());
    }

    @Test public void wrongInputTest() throws InterruptedException {
        Guest.wrongInput();
        assertNotNull(getOutput());
    }

    @Test public void continueServiceTest_2() throws InterruptedException {
        getInput("2");
        instance.getContinueService();
        assertNotNull(getOutput());
    }

   @Test public void customerHomePageTest() throws InterruptedException {
        instance.customerHomePage();
        assertNotNull(getOutput());
    }

    @Test public void personalSettingsMsgsTest() throws InterruptedException {
        getInput("Town Hall");
        instance.personalSettingsMsgs();
        assertNotNull(getOutput());
    }

    @Test public void WrongpersonalSettingsMsgsTest() throws InterruptedException {
        getInput("Town Hall");
        instance.wrongpersonalSettingsMsgs();
        assertNotNull(getOutput());
    }

    @Test public void setCinemaPreferenceTest() throws InterruptedException {
        getInput("1");
        instance.setCinemaPreference(dbInstance);
        assertNotNull(getOutput());
    }

    @Test public void CinemaPreferenceTest(){
        getInput("Town Hall");
        instance.cinemaPreferenceMsg(dbInstance);
        assertNotNull(getOutput());
    }

    @Test public void setSuccessfulTest1() {
        List<String> cinemas = BookingSystem.listCinema(dbInstance);
        assertFalse(instance.setSuccessful("1000000", cinemas));
    }

    @Test public void setSuccessfulTest2() {
        List<String> cinemas = BookingSystem.listCinema(dbInstance);
        assertFalse(instance.setSuccessful("abc", cinemas));
    }

    @Test public void setSuccessfulTest3() {
        List<String> cinemas = BookingSystem.listCinema(dbInstance);
        assertTrue(instance.setSuccessful("1", cinemas));
    }

    @Test public void checkPwdTest1() {
        assertFalse(instance.checkPwd("1", "1", "1"));
    }

    @Test public void checkPwdTest2() {
        assertFalse(instance.checkPwd("2", "3", "1"));
    }

    @Test public void checkPwdTest3() {
        assertTrue(instance.checkPwd("2", "2", "1"));
    }


    @Test public void nowShowingCus(){
        Guest.nowShowingCus(dbInstance);
        assertNotNull(getOutput());
    }

    @Test public void CshowingFilterTest() throws InterruptedException {
        getInput("test");
        assertEquals("test", instance.CshowingFilter());
    }

    @Test public void CupcomingFilterTest() throws InterruptedException {
        getInput("test");
        assertEquals("test", instance.CupcomingFilter());
    }

    @Test public void wrongInputMsgTest(){
        Guest.wrongInputMsg();
        assertNotNull(getOutput());
    }

    @Test public void filterMsgTest() throws InterruptedException {
        Guest.filterMsg(dbInstance, "e","test");
        assertNotNull(getOutput());
    }

    @Test public void guestServiceTest_2() throws InterruptedException{
        getInput("2");

    }

    @Test
    public void greetingTest() throws InterruptedException {
        BookingSystem.getGreeting(dbInstance);
        assertNotNull(getOutput());
    }

    @Test
    public void getPaymentTypeTest_1() throws InterruptedException {
        getInput("1");
        assertEquals("1", instance.getPaymentType());
    }

    @Test
    public void getAudiencesTest() {
        List<Integer> audienceNum = new ArrayList<>();
        audienceNum.add(1);
        audienceNum.add(2);
        instance.getAudiences(audienceNum);
    }

    @Test
    public void getpriceTest() throws InterruptedException {
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20","0","0","0","0");
        List<Integer> audienceNum = new ArrayList<>();
        audienceNum.add(1);
        audienceNum.add(2);
        instance.getTotalPrice(dbInstance, "vscode, the movie", "ali's cinema", "2017-03-31 9:30:20", "gold", audienceNum);
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
    }

    @Test
    public void bookSuccessTest() {
        instance.bookSuccess();
        assertNotNull(getOutput());
    }

    @Test
    public void getPaymentTypeTest1() throws InterruptedException {
        getInput("1");
        instance.getPaymentType();
        assertNotNull(getOutput());
    }

    @Test
    public void getPaymentTypeTest2() throws InterruptedException {
        getInput("2");
        instance.getPaymentType();
        assertNotNull(getOutput());
    }


    @Test
    public void getPaymentTypeWrongMsgTest(){
        Guest.getPaymentTypeWrongMsg();
    }

    @Test
    public void setSettingsTest(){
        Guest test = new Guest("Test", "c", "None");
        test.setSettings("Test");
        assertEquals("Test", test.getSettings());
    }

    @Test
    public void movieDetailTest() throws InterruptedException {
        Guest.movieDetail(dbInstance, "Test");
        assertNotNull(getOutput());
    }

    @Test
    public void bookingHelperTest1() throws InterruptedException {
        getInput("1");
        instance.bookingHelper(dbInstance);
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest1() throws InterruptedException {
        getInput("1");
        instance.filterMovies(dbInstance,"U5");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest1p1() throws InterruptedException {
        getInput("100000");
        instance.filterMovies(dbInstance,"U5");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest1p2() throws InterruptedException {
        getInput("abc");
        instance.filterMovies(dbInstance,"U5");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest2() throws InterruptedException {
        getInput("1");
        instance.filterMovies(dbInstance,"U6");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest2p2() throws InterruptedException {
        getInput("2");
        instance.filterMovies(dbInstance,"U6");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest2p3() throws InterruptedException {
        getInput("3");
        instance.filterMovies(dbInstance,"U6");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest2p4() throws InterruptedException {
        getInput("4");
        instance.filterMovies(dbInstance,"U6");
        assertNotNull(getOutput());
    }


    @Test
    public void filterMovieTest3() throws InterruptedException {
        getInput("1");
        instance.filterMovies(dbInstance,"S5");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest3p1() throws InterruptedException {
        getInput("10000");
        instance.filterMovies(dbInstance,"S5");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest3p2() throws InterruptedException {
        getInput("abc");
        instance.filterMovies(dbInstance,"S5");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest4() throws InterruptedException {
        getInput("1");
        instance.filterMovies(dbInstance,"S6");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest4p2() throws InterruptedException {
        getInput("2");
        instance.filterMovies(dbInstance,"S6");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest4p3() throws InterruptedException {
        getInput("3");
        instance.filterMovies(dbInstance,"S6");
        assertNotNull(getOutput());
    }

   @Test
    public void filterMovieTest4p4() throws InterruptedException {
        getInput("4");
        instance.filterMovies(dbInstance,"S6");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest5() throws InterruptedException {
        instance.filterMovies(dbInstance,"S");
        assertNotNull(getOutput());
    }

    @Test
    public void MsgTest() {
        instance.Msg1();
        assertNotNull(getOutput());
    }

    @Test
    public void checkGiftCardTest1() throws InterruptedException {
        assertEquals("1", instance.checkGiftCard(null));
    }

    @Test
    public void checkGiftCardTest2() throws InterruptedException {
        assertEquals("2", instance.checkGiftCard("cancel"));
    }

    @Test
    public void checkGiftCardTest3() throws InterruptedException {
        assertEquals("3", instance.checkGiftCard("111111111111111222GC"));
    }

    @Test
    public void checkGiftCardTest3_3() throws InterruptedException {
        assertEquals("3", instance.checkGiftCard("1111111111111112FC"));
    }

    @Test
    public void checkGiftCardTest4() throws InterruptedException {
        AddingGiftCard.addGiftCard(dbInstance, "1111111111111112GC");
        assertEquals("4", instance.checkGiftCard("1111111111111112GC"));
        RemovingGiftCard.removeGiftCard(dbInstance, "1111111111111112GC");
    }

    @Test
    public void checkGiftCardTest5() throws InterruptedException {
        AddingGiftCard.addGiftCard(dbInstance, "1111111111111112GC");
        RedeemingGiftCard.redeemGiftCard(dbInstance, "1111111111111112GC");
        assertEquals("5", instance.checkGiftCard("1111111111111112GC"));
        RemovingGiftCard.removeGiftCard(dbInstance, "1111111111111112GC");
    }

    @Test
    public void getMovieNameTest() throws InterruptedException {
        getInput("1");
        instance.getMovieName(dbInstance, "Warringah Mall");

    }

    @Test
    public void getScreenTypeTest() throws InterruptedException {
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20","0","0","0","0");
        getInput("1");
        instance.getScreenType(dbInstance, "ali's cinema","vscode, the movie");
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");

    }

    @Test
    public void getStartTimeTest() throws InterruptedException {
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20","0","0","0","0");
        getInput("1");
        instance.getStartTime(dbInstance, "ali's cinema", "vscode, the movie", "gold");
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
    }

    @Test
    public void bookNumHelperTest1() throws InterruptedException {
        getInput("1");
        instance.bookNumHelper("1");
    }

    @Test
    public void bookNumHelperTest2() throws InterruptedException {
        getInput("1");
        instance.bookNumHelper("2");
    }

    @Test
    public void bookNumHelperTest3() throws InterruptedException {
        getInput("1");
        instance.bookNumHelper("3");
    }

    @Test
    public void bookNumHelperTest4() throws InterruptedException {
        getInput("1");
        instance.bookNumHelper("4");
    }

    @Test
    public void bookNumHelper2Test() {
        instance.bookNumHelper2("1", "0", "1", "0");
    }

    @Test
    public void getTicketPricesTest() throws InterruptedException {
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20","0","0","0","0");
        instance.getTicketPrices (dbInstance, "ali's cinema", "vscode, the movie", "2017-03-31 9:30:20", "gold");
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
    }

    @Test
    public void getCardNumTest() throws InterruptedException {
        getInput("1");
        instance.getCardNum();
    }

    @Test
    public void getCardHolderNameTest() throws InterruptedException {
        getInput("1");
       instance.getCardHolderName();
    }

    @Test
    public void getCardHelpeTest1() throws InterruptedException {
        instance.getCardHelper("1");
    }

    @Test
    public void getCardHelpeTest2() throws InterruptedException {
        assertNull(instance.getCardHelper(null));
    }

    @Test
    public void getCardHelpeTest3() throws InterruptedException {
        assertNull(instance.getCardHelper("cancel"));
    }

    @Test
    public void saveCreditCardTest() throws InterruptedException {
        AddingUser.addUser(dbInstance, "Natalie", "000000", "c");
        instance.saveCreditCard(dbInstance, "111", "Natalie");
        RemovingUser.removeUser(dbInstance, "Natalie");
    }

    @Test
    public void saveCardHelpeTest1() throws InterruptedException {
        AddingUser.addUser(dbInstance, "Natalie", "000000", "c");
        instance.saveCardHelper(dbInstance, "1", "111", "Natalie");
        RemovingUser.removeUser(dbInstance, "Natalie");
    }

    @Test
    public void saveCardHelpeTest2() throws InterruptedException {
        AddingUser.addUser(dbInstance, "Natalie", "000000", "c");
        instance.saveCardHelper(dbInstance, "2", "111", "Natalie");
        RemovingUser.removeUser(dbInstance, "Natalie");
    }

    @Test
    public void saveCardHelpeTest3() throws InterruptedException {
        AddingUser.addUser(dbInstance, "Natalie", "000000", "c");
        instance.saveCardHelper(dbInstance, null, "111", "Natalie");
        RemovingUser.removeUser(dbInstance, "Natalie");
    }

}


