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

public class BookingSystemTest {
    Database dbInstance =  new Database("jdbc:postgresql://localhost:5432/MTBMS", "postgres", "329099");

    /*@Test
    public void testPage(){
        BookingSystem.defaultPage(dbInstance);
    }

    @Test
    public void testLogin() throws InterruptedException {
        BookingSystem.login("xx","xxx");
    }

    @Test
    public void testSignUp() throws InterruptedException {
        BookingSystem.signUp("xxx_id");
    }

    @Test
    public void testLoginOut() throws InterruptedException {
        BookingSystem.logOut();
    }

    @Test
    public void testGretting(){
        BookingSystem.getGreeting(dbInstance);
    }*/


}


