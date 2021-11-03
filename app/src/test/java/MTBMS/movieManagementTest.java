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

import static databaseutility.AddMovieSession.addMovieSession;
import static databaseutility.AddingCinema.addCinema;
import static databaseutility.RemovingSession.removeSession;
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

    @Test
    public void ListSeatTest() throws InterruptedException {
       /*removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20","0","0","0","0");
        ChangeSeatCapacity.changeFrontSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 10);
        ChangeSeatCapacity.changeBackSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 10);
        ChangeSeatCapacity.changeMidSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 10);
        UpdateSeats.updateSeats(dbInstance, "ali's cinema", "vscode, the movie", "2017-03-31 9:30:20", "gold", 1, "front");
        UpdateSeats.updateSeats(dbInstance, "ali's cinema", "vscode, the movie", "2017-03-31 9:30:20", "gold", 1, "mid");
        UpdateSeats.updateSeats(dbInstance, "ali's cinema", "vscode, the movie", "2017-03-31 9:30:20", "gold", 1, "back");*/
        ListSeats.listSeats(dbInstance,"Warringah Mall", "Black Box", "Bronze","2021-11-07 10:00:00");
        //removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
    }

}
