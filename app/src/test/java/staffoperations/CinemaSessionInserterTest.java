package staffoperations;

import MTBMS.Database;
import databaseutility.AddMovieSession;
import databaseutility.CheckIfCinemaExists;
import databaseutility.CheckIfMovieExists;
import databaseutility.CheckIfSessionExists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;


public class CinemaSessionInserterTest {

    //class to be tested
    private CinemaSessionInserter cinemaSessionInserter;

    private ByteArrayInputStream in;
    private InputStream sysInBackup;
    private Scanner scanner;
    private Database db;

    @Before
    public void setup(){
        db = new Database("", "", "");
    }

    @Test
    public void collectCinemaName_cinemaExistsOnFirstAttempt_returnCinemaName() {

        //mock user input on cinema name
        String userInput = "Town Hall";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        //collectCinemaName() relies on checkIfCinemaExists() in databaseutility package
        //so here, checkIfCinemaExists() is stubbed to return true when it is passed with "Town Hall"
        //so that it does not need to connect to the database to verify
        cinemaSessionInserter = new CinemaSessionInserter(scanner, db);
        try(MockedStatic<CheckIfCinemaExists> cinemaChecker = Mockito.mockStatic(CheckIfCinemaExists.class)){
            cinemaChecker.when(() -> CheckIfCinemaExists.checkIfCinemaExists(db, userInput)).thenReturn(true);
            Assert.assertEquals(cinemaSessionInserter.collectCinemaName(), userInput);
        }
    }

    @Test
    public void collectCinemaName_cinemaExistsOnSecondAttempt_returnCinemaName() {

        //mock user input on cinema name
        String userInput = "Westfield" + System.lineSeparator() + "Town Hall";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String secondCinema = "Town Hall";

        //collectCinemaName() relies on checkIfCinemaExists() in databaseutility package
        //so here, checkIfCinemaExists() is stubbed to firstly return false when it is passed with "Westfield" which is non-existent
        //then it will return true when user enter "Town Hall" which is a valid cinema name
        cinemaSessionInserter = new CinemaSessionInserter(scanner, db);
        try(MockedStatic<CheckIfCinemaExists> cinemaChecker = Mockito.mockStatic(CheckIfCinemaExists.class)){
            cinemaChecker.when(() -> CheckIfCinemaExists.checkIfCinemaExists(any(), anyString())).thenReturn(false, true);
            Assert.assertEquals(cinemaSessionInserter.collectCinemaName(), secondCinema);
        }
    }

    @Test
    public void collectMovieName_movieExistsOnFirstAttempt_returnMovieName() {

        //mock user input on movie name
        String userInput = "Ip Man 3";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        //collectMovieName() relies on checkIfMovieExists() in databaseutility package
        //here, checkIfCinemaExists() is stubbed to return true when it is passed with "Ip Man 3" which exists in the database
        cinemaSessionInserter = new CinemaSessionInserter(scanner, db);
        try(MockedStatic<CheckIfMovieExists> movieChecker = Mockito.mockStatic(CheckIfMovieExists.class)){
            movieChecker.when(() -> CheckIfMovieExists.checkIfMovieExists(db, userInput)).thenReturn(true);
            Assert.assertEquals(cinemaSessionInserter.collectMovieName(), userInput);
        }
    }

    @Test
    public void collectMovieName_movieExistsOnSecondAttempt_returnMovieName() {

        //mock user input on movie name
        String userInput = "IpMan6" + System.lineSeparator() + "Ip Man 3";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String movieName = "Ip Man 3";

        //collectMovieName() relies on checkIfMovieExists() in databaseutility package
        //here, checkIfCinemaExists() is stubbed to firstly return false when it is passed with "Ipman6" which doesn't exist in the database
        //then, it will return true when it is passed with exiting name "Ip Man 3"
        cinemaSessionInserter = new CinemaSessionInserter(scanner, db);
        try(MockedStatic<CheckIfMovieExists> movieChecker = Mockito.mockStatic(CheckIfMovieExists.class)){
            movieChecker.when(() -> CheckIfMovieExists.checkIfMovieExists(any(), anyString())).thenReturn(false, true);
            Assert.assertEquals(cinemaSessionInserter.collectMovieName(), movieName);
        }
    }

    @Test
    public void collectScreenType_screenTypeValidOnFirstAttempt_returnScreenType() {

        //mock user input on screen type
        String userInput = "Silver";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        cinemaSessionInserter = new CinemaSessionInserter(scanner, db);
        assertEquals(cinemaSessionInserter.collectScreenType(), userInput);
    }

    @Test
    public void collectScreenType_screenTypeValidOnSecondAttempt_returnScreenType() {

        //mock user input on screen type
        String userInput = "Black" + System.lineSeparator() + "Silver";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String screenType = "Silver";

        cinemaSessionInserter = new CinemaSessionInserter(scanner, db);
        assertEquals(cinemaSessionInserter.collectScreenType(), screenType);
    }

    @Test
    public void collectKidTicketPrice_ticketPriceValidOnFirstAttempt_returnTicketPrice() {

        //mock user input on screen type
        String userInput = "35.5";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        cinemaSessionInserter = new CinemaSessionInserter(scanner, db);
        assertEquals(cinemaSessionInserter.collectKidTicketPrice(), userInput);
    }

    @Test
    public void collectKidTicketPrice_ticketPriceValidOnSecondAttempt_returnTicketPrice() {

        //mock user input on screen type
        String userInput = "s35,5" + System.lineSeparator() + "35.5";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String ticketPrice = "35.5";

        cinemaSessionInserter = new CinemaSessionInserter(scanner, db);
        assertEquals(cinemaSessionInserter.collectKidTicketPrice(), ticketPrice);
    }

    @Test
    public void collectAdultTicketPrice_ticketPriceValidOnFirstAttempt_returnTicketPrice() {

        //mock user input on screen type
        String userInput = "35.5";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        cinemaSessionInserter = new CinemaSessionInserter(scanner, db);
        assertEquals(cinemaSessionInserter.collectAdultTicketPrice(), userInput);
    }

    @Test
    public void collectAdultTicketPrice_ticketPriceValidOnSecondAttempt_returnTicketPrice() {

        //mock user input on screen type
        String userInput = "s35,5" + System.lineSeparator() + "35.5";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String ticketPrice = "35.5";

        cinemaSessionInserter = new CinemaSessionInserter(scanner, db);
        assertEquals(cinemaSessionInserter.collectAdultTicketPrice(), ticketPrice);
    }

    @Test
    public void collectSeniorTicketPrice_ticketPriceValidOnFirstAttempt_returnTicketPrice() {

        //mock user input on screen type
        String userInput = "35.5";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        cinemaSessionInserter = new CinemaSessionInserter(scanner, db);
        assertEquals(cinemaSessionInserter.collectSeniorTicketPrice(), userInput);
    }

    @Test
    public void collectSeniorTicketPrice_ticketPriceValidOnSecondAttempt_returnTicketPrice() {

        //mock user input on screen type
        String userInput = "s35,5" + System.lineSeparator() + "35.5";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String ticketPrice = "35.5";

        cinemaSessionInserter = new CinemaSessionInserter(scanner, db);
        assertEquals(cinemaSessionInserter.collectSeniorTicketPrice(), ticketPrice);
    }

    @Test
    public void collectStudentTicketPrice_ticketPriceValidOnFirstAttempt_returnTicketPrice() {

        //mock user input on screen type
        String userInput = "35.5";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        cinemaSessionInserter = new CinemaSessionInserter(scanner, db);
        assertEquals(cinemaSessionInserter.collectStudentTicketPrice(), userInput);
    }

    @Test
    public void collectStudentTicketPrice_ticketPriceValidOnSecondAttempt_returnTicketPrice() {

        //mock user input on screen type
        String userInput = "s35,5" + System.lineSeparator() + "35.5";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String ticketPrice = "35.5";

        cinemaSessionInserter = new CinemaSessionInserter(scanner, db);
        assertEquals(cinemaSessionInserter.collectStudentTicketPrice(), ticketPrice);
    }

    @Test
    public void collectStartTime_startTimeValidOnFirstAttempt_returnStartTime() {

        //mock user input on screen type
        String userInput = "2021-11-03 17:09:22";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        cinemaSessionInserter = new CinemaSessionInserter(scanner, db);
        assertEquals(cinemaSessionInserter.collectStartTime(), userInput);
    }

    @Test
    public void run_allInputsValidAndSessionNotExistsOnFirstAttempt_returnTrue() {

        scanner = new Scanner(System.in);

        //create a spy "spySessionInserter" for CinemaSessionInserter instance
        CinemaSessionInserter cinemaSessionInserter = new CinemaSessionInserter(scanner, db);
        CinemaSessionInserter spySessionInserter = Mockito.spy(cinemaSessionInserter);

        Mockito.doReturn("Town Hall").when(spySessionInserter).collectCinemaName();
        Mockito.doReturn("Ip Man 3").when(spySessionInserter).collectMovieName();
        Mockito.doReturn("Silver").when(spySessionInserter).collectScreenType();
        Mockito.doReturn("2021-11-03 18:16:16").when(spySessionInserter).collectStartTime();
        Mockito.doReturn("35.5").when(spySessionInserter).collectKidTicketPrice();
        Mockito.doReturn("35.5").when(spySessionInserter).collectAdultTicketPrice();
        Mockito.doReturn("35.5").when(spySessionInserter).collectSeniorTicketPrice();
        Mockito.doReturn("35.5").when(spySessionInserter).collectStudentTicketPrice();

        //run() relies on checkIfSessionExists() in databaseutility package
        //here, checkIfSessionExists() is stubbed to return false when it is passed with valid parameters
        try (MockedStatic<CheckIfSessionExists> movieChecker = Mockito.mockStatic(CheckIfSessionExists.class)) {
            movieChecker.when(() -> CheckIfSessionExists.checkIfSessionExists(db, "Town Hall", "Ip Man 3", "Silver", "2021-11-03 18:16:16")).thenReturn(false);
            try(MockedStatic<AddMovieSession> addSession = Mockito.mockStatic(AddMovieSession.class)){
                addSession.when(() -> AddMovieSession.addMovieSession(db, "Town Hall", "Ip Man 3", "Silver", "2021-11-03 18:16:16",
                        "35.5", "35.5", "35.5", "35.5")).thenReturn(true);
                Assert.assertEquals(spySessionInserter.run(), true);
            }
        }
    }

    @Test
    public void run_allInputsValidAndSessionAlreadyExistsOnFirstAttempt_returnFalse() {

        scanner = new Scanner(System.in);

        //create a spy "spySessionInserter" for CinemaSessionInserter instance
        CinemaSessionInserter cinemaSessionInserter = new CinemaSessionInserter(scanner, db);
        CinemaSessionInserter spySessionInserter = Mockito.spy(cinemaSessionInserter);

        Mockito.doReturn("Town Hall").when(spySessionInserter).collectCinemaName();
        Mockito.doReturn("Ip Man 3").when(spySessionInserter).collectMovieName();
        Mockito.doReturn("Silver").when(spySessionInserter).collectScreenType();
        Mockito.doReturn("2021-11-03 18:16:16").when(spySessionInserter).collectStartTime();
        Mockito.doReturn("35.5").when(spySessionInserter).collectKidTicketPrice();
        Mockito.doReturn("35.5").when(spySessionInserter).collectAdultTicketPrice();
        Mockito.doReturn("35.5").when(spySessionInserter).collectSeniorTicketPrice();
        Mockito.doReturn("35.5").when(spySessionInserter).collectStudentTicketPrice();

        //run() relies on checkIfSessionExists() in databaseutility package
        //here, checkIfSessionExists() is stubbed to return false when it is passed with valid parameters
        try (MockedStatic<CheckIfSessionExists> movieChecker = Mockito.mockStatic(CheckIfSessionExists.class)) {
            movieChecker.when(() -> CheckIfSessionExists.checkIfSessionExists(db, "Town Hall", "Ip Man 3", "Silver", "2021-11-03 18:16:16")).thenReturn(true);
            try(MockedStatic<AddMovieSession> addSession = Mockito.mockStatic(AddMovieSession.class)){
                addSession.when(() -> AddMovieSession.addMovieSession(db, "Town Hall", "Ip Man 3", "Silver", "2021-11-03 18:16:16",
                        "35.5", "35.5", "35.5", "35.5")).thenReturn(true);
                Assert.assertEquals(spySessionInserter.run(), false);
            }
        }
    }
}