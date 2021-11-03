package staffoperations;

import MTBMS.Database;
import databaseutility.CheckIfMovieExists;
import databaseutility.MovieClassificationChanger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class MovieModifierTest {

    //class to be tested
    private MovieModifier movieModifier;

    private ByteArrayInputStream in;
    private InputStream sysInBackup;
    private Scanner scanner;
    private Database db;

    @Before
    public void setup(){
        db = new Database("", "", "");
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
        movieModifier = new MovieModifier(scanner, db);
        try(MockedStatic<CheckIfMovieExists> movieChecker = Mockito.mockStatic(CheckIfMovieExists.class)){
            movieChecker.when(() -> CheckIfMovieExists.checkIfMovieExists(db, userInput)).thenReturn(true);
            Assert.assertEquals(movieModifier.collectMovieName(), userInput);
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
        movieModifier = new MovieModifier(scanner, db);
        try(MockedStatic<CheckIfMovieExists> movieChecker = Mockito.mockStatic(CheckIfMovieExists.class)){
            movieChecker.when(() -> CheckIfMovieExists.checkIfMovieExists(any(), anyString())).thenReturn(false, true);
            Assert.assertEquals(movieModifier.collectMovieName(), movieName);
        }
    }

    @Test
    public void collectMovieClassification_classificationValidOnFirstAttempt_returnClassification() {

        //mock user input
        String userInput = "pg";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        movieModifier = new MovieModifier(scanner, db);
        Assert.assertEquals(movieModifier.collectMovieClassification(), userInput);
    }

    @Test
    public void collectMovieClassification_classificationValidOnSecondAttempt_returnClassification() {

        //mock user input
        String userInput = "psg" + System.lineSeparator() + "pg";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String classification = "pg";

        movieModifier = new MovieModifier(scanner, db);
        Assert.assertEquals(movieModifier.collectMovieClassification(), classification);
    }

    @Test
    public void collectMovieReleaseDate_dateValidOnFirstAttempt_returnReleaseDate() {

        //mock user input
        String userInput = "2021-11-03";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        movieModifier = new MovieModifier(scanner, db);
        Assert.assertEquals(movieModifier.collectMovieReleaseDate(), userInput);
    }

    @Test
    public void collectMovieReleaseDate_dateValidOnSecondAttempt_returnReleaseDate() {

        //mock user input
        String userInput = "1234-22-33" + System.lineSeparator() + "2021-11-03";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String releaseDate = "2021-11-03";

        movieModifier = new MovieModifier(scanner, db);
        Assert.assertEquals(movieModifier.collectMovieReleaseDate(), releaseDate);
    }

    @Test
    public void collectMovieReleaseDate_dateValidOnThirdAttempt_returnReleaseDate() {

        //mock user input
        String userInput = "12342233" + System.lineSeparator() + "2021-22-33" + System.lineSeparator() + "2021-11-03";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String releaseDate = "2021-11-03";

        movieModifier = new MovieModifier(scanner, db);
        Assert.assertEquals(movieModifier.collectMovieReleaseDate(), releaseDate);
    }

    @Test
    public void collectMovieSynopsis() {

        //mock user input
        String userInput = "Synopsis";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        movieModifier = new MovieModifier(scanner, db);
        Assert.assertEquals(movieModifier.collectMovieSynopsis(), userInput);
    }

    @Test
    public void collectMovieDirectors() {

        //mock user input
        String userInput = "Directors";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        movieModifier = new MovieModifier(scanner, db);
        Assert.assertEquals(movieModifier.collectMovieDirectors(), userInput);
    }

    @Test
    public void collectMovieGenre() {

        //mock user input
        String userInput = "Genre";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        movieModifier = new MovieModifier(scanner, db);
        Assert.assertEquals(movieModifier.collectMovieGenre(), userInput);
    }

    @Test
    public void collectMovieCasts() {

        //mock user input
        String userInput = "Casts";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        movieModifier = new MovieModifier(scanner, db);
        Assert.assertEquals(movieModifier.collectMovieCasts(), userInput);
    }

    @Test
    public void collectMovieShowingDate_dateValidOnFirstAttempt_returnReleaseDate() {

        //mock user input
        String userInput = "2021-11-03";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        movieModifier = new MovieModifier(scanner, db);
        Assert.assertEquals(movieModifier.collectMovieShowingDate(), userInput);
    }

    @Test
    public void collectMovieShowingDate_dateValidOnSecondAttempt_returnReleaseDate() {

        //mock user input
        String userInput = "1234-22-33" + System.lineSeparator() + "2021-11-03";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String showDate = "2021-11-03";

        movieModifier = new MovieModifier(scanner, db);
        Assert.assertEquals(movieModifier.collectMovieShowingDate(), showDate);
    }

    @Test
    public void collectMovieShowingDate_dateValidOnThirdAttempt_returnReleaseDate() {

        //mock user input
        String userInput = "12342233" + System.lineSeparator() + "2021-22-33" + System.lineSeparator() + "2021-11-03";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
        String showDate = "2021-11-03";

        movieModifier = new MovieModifier(scanner, db);
        Assert.assertEquals(movieModifier.collectMovieShowingDate(), showDate);
    }

    @Test
    public void run_modifyClassification_returnTrue() {

        //mock user input
        String userInput = "1";
        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);

        MovieModifier movieModifier = new MovieModifier(scanner, db);
        MovieModifier spyMovieModifier = Mockito.spy(movieModifier);

        Mockito.doReturn(("Ip Man 3")).when(spyMovieModifier).collectMovieName();
        Mockito.doReturn("pg").when(spyMovieModifier).collectMovieClassification();

        try(MockedStatic<MovieClassificationChanger> classificationChanger = Mockito.mockStatic(MovieClassificationChanger.class)){
            classificationChanger.when(() -> MovieClassificationChanger.changeMovieClassification(any(), anyString(), anyString())).thenReturn(true);
            Assert.assertEquals(spyMovieModifier.run(), true);
        }
    }
}