/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package MTBMS;

import org.junit.Test;

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
                                                     "dbmasteruser","A>XV>D*7r-V{y_wL}}I{+U=8zEtj1*T<");;
    private static final BookingSystem instance = new BookingSystem();
    private final ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
    private final PrintStream systemOutput = System.out;
    private final InputStream systemInput = System.in;

    public void setUpOutput() {
        System.setOut(new PrintStream(testOutput));
    }

    private String getOutput() {
        return testOutput.toString();
    }


    private void input(String data){
        ByteArrayInputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);
    }

    private void reset() {
        System.setIn(systemInput);
        System.setOut(systemOutput);
    }

/*     @Test
    public void loginIn1() throws InterruptedException {
        BookingSystem.login("Bean", "1234");
        BookingSystem.login("Lappland", "1234");
        BookingSystem.login("Demo", "1234");
        assertNotNull(getOutput());
    }

    @Test
    public void loginIn2() throws InterruptedException {
        BookingSystem.login("B", "1");
        assertNotNull(getOutput());
    }

    @Test
    public void guestService() throws InterruptedException {
        Guest guest = new Guest("Bean", "C", " ");
        input("3");
        guest.guestService("C");
        assertNotNull(getOutput());
    } */

    @Test
    public void guestService4() throws InterruptedException {
        Guest guest = new Guest("Bean", "C", " ");
        input("1");
        guest.guestService("C");
        assertNotNull(getOutput());
    }

    @Test
    public void guestService5() throws InterruptedException {
        Guest guest = new Guest("Bean", "C", " ");
        input("2");
        guest.guestService("C");
        assertNotNull(getOutput());
    }

    @Test
    public void guestService1() throws InterruptedException {
        Guest guest = new Guest("Bean", "C", " ");
        input("1");
        guest.guestService("G");
        assertNotNull(getOutput());
    }

/*     @Test
    public void guestService2() throws InterruptedException {
        Guest guest = new Guest("Bean", "C", " ");
        input("2");
        guest.guestService("G");
        assertNotNull(getOutput());
    } */

/*     @Test
    public void guestService3() throws InterruptedException {
        Guest guest = new Guest("Bean", "C", " ");
        input("3");
        guest.guestService("G");
        assertNotNull(getOutput());
    } */

   /* @Test
    public void signUp1() throws InterruptedException {
        input("Cat" + System.lineSeparator() + "1234");
        instance.signUp("C", dbInstance);
        assertNotNull(getOutput());
    }*/

    /*@Test
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

/*     @Test
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
    } */

/*     @Test
    public void greetingTest() throws InterruptedException {
        instance.getGreeting();
        assertNotNull(getOutput());
    } */
}