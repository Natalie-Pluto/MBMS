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

    @Test
    public void ChangingCreditCardBalance_1() {}

    @Test
    public void ChangingCreditCardBalance_2() {}

    @Test
    public void ChangingIdentity_1() {}
    
    @Test
    public void ChangingIdentity_2() {}

    @Test
    public void ChangingUserPassword_1() {}

    @Test
    public void ChangingUserPassword_2() {}

    @Test
    public void ChangingUserPassword_3() {}

    @Test 
    public void CheckIfUserExists_1() {}

    @Test
    public void CheckIfUserExists_2() {}

    @Test
    public void DeleteAllUpcoming_1() {}

    @Test 
    public void FilterCinema_1() {}

    @Test
    public void FilterScreenSize_1() {}

    @Test
    public void GetEndTime_1() {}

    @Test
    public void GetEndTime_2() {}

    @Test
    public void GetMovieCast_1() {}

    @Test
    public void GetMovieCast_2() {}

    @Test 
    public void GetMovieClassification_1() {}

    @Test
    public void GetMovieClassification_2() {}

    @Test
    public void GetMovieDirectors_1() {}

    @Test
    public void GetMovieDirectors_2() {}

    @Test
    public void GetMovieNames_1() {}

    @Test 
    public void GetMovieReleaseDate_1() {}

    @Test
    public void GetMovieReleaseDate_2() {}

    @Test
    public void GetMovieScreenSize_1() {}

    @Test
    public void GetMovieShowDate_1() {}

    @Test
    public void GetMovieShowDate_2() {}

    @Test
    public void GetMovieShowingTime_1() {}

    @Test
    public void GetMovieSynopsis_1() {}

    @Test
    public void GetMovieSynopsis_2() {}

    @Test
    public void GetNewUpcomingMovie_1() {}

    @Test
    public void GetNowShowing_1() {}

    @Test
    public void GetStartTime_1() {}

    @Test
    public void GetStartTime_2() {}

    @Test
    public void GetTicketPrice_kids_1() {}

    @Test
    public void GetTicketPrice_kids_2() {}

    @Test
    public void GetTicketPrice_adults_1() {}

    @Test
    public void GetTicketPrice_adults_2() {}

    @Test
    public void GetTicketPrice_students_1() {}

    @Test
    public void GetTicketPrice_students_2() {}

    @Test
    public void GetUpcomingMovies_1() {}

    @Test
    public void MovieNameChanger_1() {}

    @Test
    public void MovieNameChanger_2() {}

    @Test
    public void MoviesCounter_1() {}

    @Test
    public void RedeemedCheck_1() {}

    @Test
    public void RedeemedCheck_2() {}

    @Test
    public void RedeemingGiftCard_1() {}

    @Test
    public void RedeemingGiftCard_2() {}

    @Test
    public void ReleaseDateChanger_1() {}

    @Test
    public void ReleaseDateChanger_2() {}

    @Test
    public void RemovingCinema_1() {}

    @Test
    public void RemovingCinema_2() {}

    @Test
    public void RemovingCreditCard_1() {}

    @Test
    public void RemovingCreditCard_2() {}

    @Test
    public void RemovingGiftCard_1() {}

    @Test
    public void RemovingGiftCard_2() {}

    @Test
    public void RemovingMovie_1() {}

    @Test
    public void RemovingMovie_2() {}

    @Test
    public void RemovingSession_1() {}

    @Test
    public void RemovingSession_2() {}

    @Test
    public void SetSessionTime_setStartTime_1() {}

    @Test
    public void SetSessionTime_setStartTime_2() {}

    @Test
    public void SetSessionTime_setEndTime_1() {}

    @Test
    public void SetSessionTimee_setEndTime_2() {}

    @Test
    public void SetTicketPrice_kids_1() {}

    @Test
    public void SetTicketPrice_kids_2() {}

    @Test
    public void SetTicketPrice_adults_1() {}

    @Test
    public void SetTicketPrice_adults_2() {}

    @Test
    public void SetTicketPrice_students_1() {}

    @Test
    public void SetTicketPrice_students_2() {}

    @Test
    public void SetTicketPrice_seniors_1() {}

    @Test
    public void SetTicketPrice_seniors_2() {}

    @Test
    public void SynopsisChanger_1() {}

    @Test
    public void SynopsisChanger_2() {}

    @Test
    public void UpdateScreenSize_1() {}

    @Test
    public void UpdateScreenSize_2() {}

    @Test
    public void UpdateSeats_1() {}

    @Test
    public void UpdateSeats_2() {}

    @Test
    public void UpdateSeats_3() {}

    @Test
    public void UpdateSeats_4() {}

    @Test
    public void UserAuthenticate_1() {}

    @Test
    public void UserAuthenticate_2() {}


}