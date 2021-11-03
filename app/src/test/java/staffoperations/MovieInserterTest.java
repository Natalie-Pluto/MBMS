package staffoperations;

import MTBMS.Database;
import databaseutility.CheckIfMovieExists;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieInserterTest {

    //class to be tested
    private MovieInserter movieInserter;

    private ByteArrayInputStream in;
    private InputStream sysInBackup;
    private Scanner scanner;
    private Database db;

    @Before
    public void setup(){
        db = new Database("", "", "");
    }

    @Test
    public void collectMovieName_movieNotExistsOnFirstAttempt_returnMovieName() {

        //mock user input on movie name
        String userInput = "Ip Man 3";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        //collectMovieName() relies on checkIfMovieExists() in databaseutility package
        //here, checkIfCinemaExists() is stubbed to return true when it is passed with "Ip Man 3" which exists in the database
        movieInserter = new MovieInserter(scanner, db);
        try(MockedStatic<CheckIfMovieExists> movieChecker = Mockito.mockStatic(CheckIfMovieExists.class)){
            movieChecker.when(() -> CheckIfMovieExists.checkIfMovieExists(db, userInput)).thenReturn(false);
            Assert.assertEquals(movieInserter.collectMovieName(), userInput);
        }
    }

    @Test
    public void collectMovieName_movieNotExistsOnSecondAttempt_returnMovieName() {

        //mock user input on movie name
        String userInput = "IpMan6" + System.lineSeparator() + "Ip Man 3";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String movieName = "Ip Man 3";

        //collectMovieName() relies on checkIfMovieExists() in databaseutility package
        //here, checkIfCinemaExists() is stubbed to firstly return false when it is passed with "Ipman6" which doesn't exist in the database
        //then, it will return true when it is passed with exiting name "Ip Man 3"
        movieInserter = new MovieInserter(scanner, db);
        try(MockedStatic<CheckIfMovieExists> movieChecker = Mockito.mockStatic(CheckIfMovieExists.class)){
            movieChecker.when(() -> CheckIfMovieExists.checkIfMovieExists(any(), anyString())).thenReturn(true, false);
            Assert.assertEquals(movieInserter.collectMovieName(), movieName);
        }
    }

    @Test
    public void collectMovieClassification_classificationValidOnFirstAttempt_returnClassification() {

        //mock user input
        String userInput = "pg";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        movieInserter = new MovieInserter(scanner, db);
        Assert.assertEquals(movieInserter.collectMovieClassification(), userInput);
    }

    @Test
    public void collectMovieClassification_classificationValidOnSecondAttempt_returnClassification() {

        //mock user input
        String userInput = "psg" + System.lineSeparator() + "pg";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String classification = "pg";

        movieInserter = new MovieInserter(scanner, db);
        Assert.assertEquals(movieInserter.collectMovieClassification(), classification);
    }

    @Test
    public void collectMovieReleaseDate_dateValidOnFirstAttempt_returnReleaseDate() {

        //mock user input
        String userInput = "2021-11-03";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        movieInserter = new MovieInserter(scanner, db);
        Assert.assertEquals(movieInserter.collectMovieReleaseDate(), userInput);
    }

    @Test
    public void collectMovieReleaseDate_dateValidOnSecondAttempt_returnReleaseDate() {

        //mock user input
        String userInput = "1234-22-33" + System.lineSeparator() + "2021-11-03";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String releaseDate = "2021-11-03";

        movieInserter = new MovieInserter(scanner, db);
        Assert.assertEquals(movieInserter.collectMovieReleaseDate(), releaseDate);
    }

    @Test
    public void collectMovieReleaseDate_dateValidOnThirdAttempt_returnReleaseDate() {

        //mock user input
        String userInput = "12342233" + System.lineSeparator() + "2021-22-33" + System.lineSeparator() + "2021-11-03";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String releaseDate = "2021-11-03";

        movieInserter = new MovieInserter(scanner, db);
        Assert.assertEquals(movieInserter.collectMovieReleaseDate(), releaseDate);
    }

    @Test
    public void collectMovieSynopsis() {

        //mock user input
        String userInput = "Synopsis";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        movieInserter = new MovieInserter(scanner, db);
        Assert.assertEquals(movieInserter.collectMovieSynopsis(), userInput);
    }

    @Test
    public void collectMovieDirectors() {

        //mock user input
        String userInput = "Directors";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        movieInserter = new MovieInserter(scanner, db);
        Assert.assertEquals(movieInserter.collectMovieDirectors(), userInput);
    }

    @Test
    public void collectMovieGenre() {

        //mock user input
        String userInput = "Genre";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        movieInserter = new MovieInserter(scanner, db);
        Assert.assertEquals(movieInserter.collectMovieGenre(), userInput);
    }

    @Test
    public void collectMovieCasts() {

        //mock user input
        String userInput = "Casts";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        movieInserter = new MovieInserter(scanner, db);
        Assert.assertEquals(movieInserter.collectMovieCasts(), userInput);
    }

    @Test
    public void collectMovieShowingDate_dateValidOnFirstAttempt_returnReleaseDate() {

        //mock user input
        String userInput = "2021-11-03";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        movieInserter = new MovieInserter(scanner, db);
        Assert.assertEquals(movieInserter.collectMovieShowingDate(), userInput);
    }

    @Test
    public void collectMovieShowingDate_dateValidOnSecondAttempt_returnReleaseDate() {

        //mock user input
        String userInput = "1234-22-33" + System.lineSeparator() + "2021-11-03";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String showDate = "2021-11-03";

        movieInserter = new MovieInserter(scanner, db);
        Assert.assertEquals(movieInserter.collectMovieShowingDate(), showDate);
    }

    @Test
    public void collectMovieShowingDate_dateValidOnThirdAttempt_returnReleaseDate() {

        //mock user input
        String userInput = "12342233" + System.lineSeparator() + "2021-22-33" + System.lineSeparator() + "2021-11-03";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String showDate = "2021-11-03";

        movieInserter = new MovieInserter(scanner, db);
        Assert.assertEquals(movieInserter.collectMovieShowingDate(), showDate);
    }

    @Test
    public void run() {

        scanner = new Scanner(System.in);
        db = mock(Database.class);

        //create a spy "spyMovieInserter" for MovieInserter instance
        MovieInserter movieInserter = new MovieInserter(scanner, db);
        MovieInserter spyMovieInserter = Mockito.spy(movieInserter);

        //stub database method sql_update()
        when(db.sql_update(anyString())).thenReturn(true);

        Mockito.doReturn("Ip Man 6").when(spyMovieInserter).collectMovieName();
        Mockito.doReturn("pg").when(spyMovieInserter).collectMovieClassification();
        Mockito.doReturn("2021-11-03").when(spyMovieInserter).collectMovieReleaseDate();
        Mockito.doReturn("Genre").when(spyMovieInserter).collectMovieGenre();
        Mockito.doReturn("Synopsis").when(spyMovieInserter).collectMovieSynopsis();
        Mockito.doReturn("Directors").when(spyMovieInserter).collectMovieDirectors();
        Mockito.doReturn("Casts").when(spyMovieInserter).collectMovieCasts();
        Mockito.doReturn("2021-11-03").when(spyMovieInserter).collectMovieShowingDate();

        assertEquals(spyMovieInserter.run(), true);
    }
}