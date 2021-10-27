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
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class bookingsystemTest {
    //private static final Database dbInstance = new Database("jdbc:postgresql://ls-d4381878930280384f33af335289e24c73224a04.c0apyqxz8x8m.ap-southeast-2.rds.amazonaws.com:5432/postgres",
    //  "dbmasteruser","A>XV>D*7r-V{y_wL}}I{+U=8zEtj1*T<");
    //private static final Database dbInstance = new Database("jdbc:postgresql://localhost:5432/MTBMS", "postgres", "329099");
    private static final Database dbInstance =  new Database("jdbc:postgresql://localhost:5432/postgres", "postgres", "0000");
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
        BookingSystem.listCinema();
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
        instance.signUpHelper(dbInstance, "1", "abc", "123456");
        assertNotNull(getOutput());
        RemovingUser.removeUser(dbInstance, "abc");
    }

    @Test
    public void signUpHelperTest3() throws InterruptedException {
        instance.signUpHelper(dbInstance, "1", "abc", "123456");
        assertNotNull(getOutput());
        RemovingUser.removeUser(dbInstance, "abc");
    }


    @Test
    public void loginIn1() throws InterruptedException {

    }

    @Test
    public void loginIn2() throws InterruptedException {

    }
}