package MTBMS;

import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.*;
import java.util.Date;
import static databaseutility.AddingUser.addUser;
import static databaseutility.CheckStaff.getIdentity;
import static databaseutility.CheckStaff.isManager;
import static databaseutility.CheckStaff.isStaff;
import static databaseutility.DirectorChanger.changeDirectors;
import static databaseutility.MovieClassificationChanger.changeMovieClassification;
import static databaseutility.MovieNameChanger.changeMovieName;
import static databaseutility.MoviesCounter.countMovies;
import static databaseutility.ReleaseDateChanger.changeReleaseDate;
import static databaseutility.SynopsisChanger.changeSynopsis;
import static databaseutility.UserAuthenticate.authenticate;
import static databaseutility.RemovingUser.removeUser;
import static databaseutility.RemovingMovie.removeMovie;
import static databaseutility.GetMovieDirectors.getDirectors;
import static databaseutility.CheckIfUserExists.checkIfUserExists;
import static databaseutility.CheckIfMovieExists.checkIfMovieExists;
import static databaseutility.GetMovieClassification.getMovieClassification;
import databaseutility.GetMovieDirectors;
import databaseutility.MovieInsertionBuilder;


public class databaseUtilityTests {
    static Database dbInstance = new Database("jdbc:postgresql://ls-d4381878930280384f33af335289e24c73224a04.c0apyqxz8x8m.ap-southeast-2.rds.amazonaws.com:5432/postgres",
    "dbmasteruser","A>XV>D*7r-V{y_wL}}I{+U=8zEtj1*T<");

    @Test
    public void AddingUser_1() {
        removeUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa");
        addUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa", "ghj", "c");
        assert(checkIfUserExists(dbInstance, "aaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void AddingUser_2() {
        addUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa", "ghj", "c");
        assert(checkIfUserExists(dbInstance, "aaaaaaaaaaaaaaaaaaaa"));
        addUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa", "ghj", "c");
    }

    @Test
    public void RemovingUser_1() {
        removeUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa");
        assertFalse(checkIfUserExists(dbInstance, "aaaaaaaaaaaaaaaaaaaa"));
        removeUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa");
    }

    @Test
    public void RemovingUser_2() {
        addUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa", "ghj", "c");
        assert(checkIfUserExists(dbInstance, "aaaaaaaaaaaaaaaaaaaa"));
        removeUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa");
        assertFalse(checkIfUserExists(dbInstance, "aaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void CheckStaff_1() {
        removeUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa");
        addUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa", "ghj", "s");
        assert(isStaff(dbInstance, "aaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void CheckStaff_2() {
        removeUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa");
        addUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa", "ghj", "u");
        assertFalse(isStaff(dbInstance, "aaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void CheckStaff_3() {
        removeUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa");
        assertFalse(isStaff(dbInstance, "aaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void DirectorChanger_1() {
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        System.out.println(checkIfMovieExists(dbInstance, "vscode, the movie" + " hehe"));
        String initialDirectors = getDirectors(dbInstance, "vscode, the movie");
        String newDirectors = initialDirectors + " ali";
        changeDirectors(dbInstance, "vscode, the movie", newDirectors);
        assertFalse(getDirectors(dbInstance,"vscode, the movie").equals(initialDirectors));
    }

    @Test
    public void DirectorChanger_2() {
        removeMovie(dbInstance, "movie");
        changeDirectors(dbInstance,"movie", "afhd");
    }

    @Test 
    public void MovieClassificationChanger_1() {
        removeMovie(dbInstance, "vscode, the movie");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        changeMovieClassification(dbInstance, "vscode, the movie", "pg");
        assert(getMovieClassification(dbInstance, "vscode, the movie").equals("pg"));
    }

    @Test 
    public void MovieClassificationChanger_2() {
        removeMovie(dbInstance, "vscode, the movie");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        changeMovieClassification(dbInstance, "vscode, the movie", "invalid classification");
        assert(getMovieClassification(dbInstance, "vscode, the movie").equals("r18+"));
    } 

    @Test
    public void MovieClassificationChanger_3() {
        removeMovie(dbInstance, "vscode, the movie");
        changeMovieClassification(dbInstance, "vscode, the movie", "invalid classification");
    }

    @Test 
    public void MovieInsertionBuilder_1() {}

    @Test
    public void MovieInsertionBuilder_2() {}

    @Test
    public void MovieInsertionBuilder_3() {}

    @Test
    public void MovieInsertionBuilder_4() {}

    @Test
    public void MovieInsertionBuilder_5() {}

    @Test
    public void MovieInsertionBuilder_6() {}

    @Test
    public void MovieInsertionBuilder_7() {}

    @Test
    public void MovieInsertionBuidler_8() {}

    @Test
    public void MovieInsertionBuilder_9() {}

    @Test
    public void AddingCinema_1() {}

    @Test
    public void AddingCinema_2() {}

    @Test 
    public void AddingCreditCard_1() {}

    @Test 
    public void AddingCreditCard_2() {}

    @Test
    public void AddingGiftCard_1() {}

    @Test
    public void AddingGiftCard_2() {}

    @Test
    public void AddingUpcomingMovie_1() {}

    @Test
    public void AddMovieSession_1() {}

    @Test
    public void AddMovieSession_2() {}

    @Test
    public void changeSeatCapacity_1() {}

    @Test 
    public void changeSeatCapacity_2() {}
    

}