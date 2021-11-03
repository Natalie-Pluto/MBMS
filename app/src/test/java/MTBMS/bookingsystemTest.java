package MTBMS;

import databaseutility.*;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import utils.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class bookingsystemTest {
    private static final Database dbInstance = new Database("jdbc:postgresql://ls-d4381878930280384f33af335289e24c73224a04.c0apyqxz8x8m.ap-southeast-2.rds.amazonaws.com:5432/postgres","dbmasteruser","A>XV>D*7r-V{y_wL}}I{+U=8zEtj1*T<");
    //private static final Database dbInstance =  new Database("jdbc:postgresql://localhost:5432/postgres", "postgres", "0000");
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

    @Test
    public void signinMsg4test() {
        instance.signinMsg4();
        assertNotNull(getOutput());
    }

    @Test
    public void signinMsg3test() {
        instance.signinMsg3();
        assertNotNull(getOutput());
    }

    @Test
    public void signinMsg1test() {
        instance.signinMsg1();
        assertNotNull(getOutput());
    }

    @Test
    public void signinfailedtest() throws InterruptedException {
        instance.signinFailed();
        assertNotNull(getOutput());
    }

    @Test
    public void greetingTest() {
        BookingSystem.getGreeting(dbInstance);
        assertNotNull(getOutput());
    }

    @Test
    public void wrongInputTest() throws InterruptedException {
        BookingSystem.wrongInput();
        assertNotNull(getOutput());
    }

    @Test
    public void loginMsgTest() {
        instance.loginMsg();
        assertNotNull(getOutput());
    }

    @Test
    public void loginGreetingTest1() throws InterruptedException {
        AddingUser.addUser(dbInstance, "Shawn", "123456", "s");
        instance.loginGreeting("s", "Shawn");
        assertNotNull(getOutput());
        RemovingUser.removeUser(dbInstance, "Shawn");
    }

    @Test
    public void loginGreetingTest2() throws InterruptedException {
        AddingUser.addUser(dbInstance, "Shawn", "123456", "m");
        instance.loginGreeting("m", "Shawn");
        assertNotNull(getOutput());
        RemovingUser.removeUser(dbInstance, "Shawn");
    }

    @Test
    public void loginGreetingTest3() throws InterruptedException {
        AddingUser.addUser(dbInstance, "Shawn", "123456", "c");
        instance.loginGreeting("c", "Shawn");
        assertNotNull(getOutput());
        RemovingUser.removeUser(dbInstance, "Shawn");
    }

    @Test
    public void nowshowingTest() {
        BookingSystem.nowShowing(dbInstance);
        assertNotNull(getOutput());
    }

    @Test
    public void createpwTest() {
        BookingSystem.createPwd();
        assertNotNull(getOutput());
    }

    @Test
    public void createpw2Test() {
        BookingSystem.createPwd2();
        assertNotNull(getOutput());
    }

    @Test
    public void seperatorTest() {
        BookingSystem.seperator();
        assertNotNull(getOutput());
    }

    @Test
    public void msgTest() {
        BookingSystem.msg();
        assertNotNull(getOutput());
    }

    @Test
    public void msg2Test() {
        BookingSystem.msg2();
        assertNotNull(getOutput());
    }

    @Test
    public void msg3Test() {
        BookingSystem.msg3();
        assertNotNull(getOutput());
    }

    @Test
    public void defaultTest() {
        BookingSystem.defaultPage(dbInstance);
        assertNotNull(getOutput());
    }

    @Test
    public void filterMsgTest1() throws InterruptedException {
        BookingSystem.filterMsg(dbInstance,"a", "Warringah Mall");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMsgTest2() throws InterruptedException {
        BookingSystem.filterMsg(dbInstance,"b", "Gold");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMsgTest3() throws InterruptedException {
        BookingSystem.filterMsg(dbInstance,"c", "Warringah Mall");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMsgTest4() throws InterruptedException {
        BookingSystem.filterMsg(dbInstance,"d", "Gold");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMsgTest5() throws InterruptedException {
        BookingSystem.filterMsg(dbInstance,"4", " ");
        assertNotNull(getOutput());
    }

    @Test
    public void listScreenTest() {
        BookingSystem.listScreen();
        assertNotNull(getOutput());
    }

    @Test
    public void listCinemaTest5() {
        BookingSystem.listCinema(dbInstance);
        assertNotNull(getOutput());
    }

    @Test
    public void movieDetailTest() {
        BookingSystem.movieDetail(dbInstance, "Test");
        assertNotNull(getOutput());
    }

    @Test
    public void upcomingFilterTest() throws InterruptedException {
        getInput("6");
        BookingSystem.upcomingFilter();
        assertNotNull(getOutput());
    }

    @Test
    public void nowshowingFilterTest() throws InterruptedException {
        getInput("6");
        BookingSystem.showingFilter();
        assertNotNull(getOutput());
    }

    @Test
    public void csignUpTest() throws InterruptedException {
        instance.cSignup(dbInstance, "abc", "123456");
        assertNotNull(getOutput());
        RemovingUser.removeUser(dbInstance, "abc");
    }

    @Test
    public void signUpHelperTest1() throws InterruptedException {
        instance.signUpHelper(dbInstance, "1", "abc", "123456");
        assertNotNull(getOutput());
        RemovingUser.removeUser(dbInstance, "abc");
    }

    @Test
    public void signUpHelperTest2() throws InterruptedException {
        getInput("shawshank");
        instance.signUpHelper(dbInstance, "2", "abc", "123456");
        assertNotNull(getOutput());
        RemovingUser.removeUser(dbInstance, "abc");
    }

    @Test
    public void signUpHelperTest3() throws InterruptedException {
        getInput("zootopia");
        instance.signUpHelper(dbInstance, "3", "abc", "123456");
        assertNotNull(getOutput());
        RemovingUser.removeUser(dbInstance, "abc");
    }

    @Test
    public void signUpHelperTest4() throws InterruptedException {
        getInput("shaw");
        instance.signUpHelper(dbInstance, "2", "abc", "123456");
        assertNotNull(getOutput());
        RemovingUser.removeUser(dbInstance, "abc");
    }

    @Test
    public void signUpHelperTest5() throws InterruptedException {
        getInput("zoo");
        instance.signUpHelper(dbInstance, "3", "abc", "123456");
        assertNotNull(getOutput());
        RemovingUser.removeUser(dbInstance, "abc");
    }

    @Test
    public void tryLoginTest1() {
        AddingUser.addUser(dbInstance, "abbc", "123456", "c");
        assertTrue(instance.tryLogin(dbInstance, "abbc", "123456"));
        RemovingUser.removeUser(dbInstance, "abbc");
    }

    @Test
    public void tryLoginTest2() {
        AddingUser.addUser(dbInstance, "abc", "123456", "s");
        assertFalse(instance.tryLogin(dbInstance, "abc", "123"));
        RemovingUser.removeUser(dbInstance, "abc");
    }

    @Test
    public void backTest1() throws InterruptedException {
        getInput("Nat");
        instance.backCheck1(dbInstance);
        assertNotNull(getOutput());
    }

    @Test
    public void backTest2() throws InterruptedException {
        getInput("Nat");
        instance.backCheck2(dbInstance);
        assertNotNull(getOutput());
    }

    @Test
    public void pwdTest() {
        assertTrue(BookingSystem.checkPwd("123", "123"));
        assertFalse(BookingSystem.checkPwd("123", "1234"));
    }

    @Test
    public void readPwdTest() throws InterruptedException {
        getInput("123456");
        assertNotNull(BookingSystem.readPwd("1"));
    }

    @Test
    public void filterMovieTest1() throws InterruptedException {
        getInput("1");
        BookingSystem.filterMovie(dbInstance,"U6");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest1p2() throws InterruptedException {
        getInput("100000000");
        BookingSystem.filterMovie(dbInstance,"U6");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest1p3() throws InterruptedException {
        getInput("abc");
        BookingSystem.filterMovie(dbInstance,"U6");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest2() throws InterruptedException {
        getInput("1");
        BookingSystem.filterMovie(dbInstance,"U7");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest2p2() throws InterruptedException {
        getInput("2");
        BookingSystem.filterMovie(dbInstance,"U7");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest2p3() throws InterruptedException {
        getInput("3");
        BookingSystem.filterMovie(dbInstance,"U7");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest2p4() throws InterruptedException {
        getInput("4");
        BookingSystem.filterMovie(dbInstance,"U7");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest3() throws InterruptedException {
        getInput("1");
        BookingSystem.filterMovie(dbInstance,"S6");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest3p1() throws InterruptedException {
        getInput("1000000");
        BookingSystem.filterMovie(dbInstance,"S6");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest3p2() throws InterruptedException {
        getInput("abc");
        BookingSystem.filterMovie(dbInstance,"S6");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest4() throws InterruptedException {
        getInput("1");
        BookingSystem.filterMovie(dbInstance,"S7");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest4p2() throws InterruptedException {
        getInput("2");
        BookingSystem.filterMovie(dbInstance,"S7");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest4p3() throws InterruptedException {
        getInput("3");
        BookingSystem.filterMovie(dbInstance,"S7");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest4p4() throws InterruptedException {
        getInput("4");
        BookingSystem.filterMovie(dbInstance,"S7");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest5() throws InterruptedException {
        BookingSystem.filterMovie(dbInstance,"S");
        assertNotNull(getOutput());
    }

    @Test
    public void filterMovieTest6() throws InterruptedException {
        BookingSystem.filterMovie(dbInstance,"U");
        assertNotNull(getOutput());
    }

    @Test
    public void passwordTest() throws InterruptedException {
        getInput("123456");
        assertNotNull(BookingSystem.password());
    }

    @Test
    public void usernameTest() throws InterruptedException {
        getInput("Nat");
        assertNotNull(BookingSystem.username());
    }

    @Test
    public void optionTest() throws InterruptedException {
        getInput("Caribbean");
        BookingSystem.options();
        assertNotNull(getOutput());
    }

    @Test
    public void util(){
        StringUtils str = new StringUtils();
        assertTrue(str.isEmpty(""));
        assertTrue(StringUtils.isEmpty(""));
        assertTrue(StringUtils.isNotEmpty("abc"));
        assertTrue(str.isNotEmpty("abc"));
    }


}