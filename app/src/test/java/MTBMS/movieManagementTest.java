package MTBMS;

import databaseutility.*;
import movieManagement.*;
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

public class movieManagementTest {

    //private static final Database dbInstance = new Database("jdbc:postgresql://ls-d4381878930280384f33af335289e24c73224a04.c0apyqxz8x8m.ap-southeast-2.rds.amazonaws.com:5432/postgres","dbmasteruser","A>XV>D*7r-V{y_wL}}I{+U=8zEtj1*T<");
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

   /* @Test
    public void listCinemaTest1() throws InterruptedException {
        ListMovieByCinema.listMovieByCinema(dbInstance, "Warringah Mall", "Andy");
        assertNotNull(getOutput());
    }*/

    @Test
    public void listCinemaTest2() throws InterruptedException {
        ListNowShowingCinema.listNowShowingCinema(dbInstance, "h");
        assertNotNull(getOutput());
    }

    @Test
    public void listCinemaTest3() throws InterruptedException {
        ListNowShowingCinema.listNowShowingCinema(dbInstance, "test");
        assertNotNull(getOutput());
    }

    @Test
    public void listCinemaTest4() throws InterruptedException {
        ListUpcomingByCinema.listUpcomingByCinema(dbInstance, "h");
        assertNotNull(getOutput());
    }

    @Test
    public void listCinemaTest5() throws InterruptedException {
        ListUpcomingByCinema.listUpcomingByCinema(dbInstance, "test");
        assertNotNull(getOutput());
    }


    @Test
    public void UpdateUpcomingTest() {
        new UpdateUpcomingMovieTable.MondayUpdate();
        UpdateUpcomingMovieTable.updateUpcomingMovieTable();
    }

    @Test
    public void listScreenTest1() {
        ListMovieByScreen.listMovieByScreen(dbInstance, "Gold");
        assertNotNull(getOutput());
    }

    @Test
    public void listScreenTest2() {
        ListMovieByScreen.listMovieByScreen(dbInstance, "G");
        assertNotNull(getOutput());
    }

    @Test
    public void listScreenTest3() {
        ListNowShowingScreen.listNowshowingScreen(dbInstance, "G");
        assertNotNull(getOutput());
    }

    @Test
    public void listScreenTest4() {
        ListUpcomingByScreen.listUpcomingByScreen(dbInstance, "G");
        assertNotNull(getOutput());
    }

}
