package MTBMS;

import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.*;

import static databaseutility.AddingUser.addUser;
import static databaseutility.RemovingUser.removeUser;
import static databaseutility.CheckStaff.isStaff;
import static databaseutility.CheckStaff.isManager;
import static databaseutility.CheckStaff.getIdentity;
import static databaseutility.DirectorChanger.changeDirectors;
import static databaseutility.MovieClassificationChanger.changeMovieClassification;
import static databaseutility.AddingCinema.addCinema;
import static databaseutility.AddingCreditCard.addCreditCard;
import static databaseutility.AddingGiftCard.addGiftCard;
import static databaseutility.AddingUpcomingMovie.addUpcomingMovie;
import static databaseutility.AddMovieSession.addMovieSession;
import static databaseutility.ChangeSeatCapacity.changeFrontSeatCapacity;
import static databaseutility.ChangeSeatCapacity.changeMidSeatCapacity;
import static databaseutility.ChangeSeatCapacity.changeBackSeatCapacity;
import static databaseutility.ChangingCreditCardBalance.changeCreditCardBalance;
import static databaseutility.ChangingIdentity.changeIdentity;
import static databaseutility.ChangingUserPassword.changeUserPassword;
import static databaseutility.CheckIfMovieExists.checkIfMovieExists;
import static databaseutility.CheckIfUserExists.checkIfUserExists;
import static databaseutility.DeleteAllUpcoming.deleteUpcoming;
import static databaseutility.FilterCinema.filterCinema;
import static databaseutility.FilterScreenSize.filterScreenSize;
import static databaseutility.GetEndTime.getEndTime;
import static databaseutility.GetMovieCast.getMovieCast;
import static databaseutility.GetMovieClassification.getMovieClassification;
import static databaseutility.GetMovieDirectors.getDirectors;
import static databaseutility.GetMovieNames.getMovieNames;
import static databaseutility.GetMovieReleaseDate.getMovieReleasedate;
import static databaseutility.GetMovieScreensize.getMovieScreensize;
import static databaseutility.GetMovieShowDate.getMovieShowDate;
import static databaseutility.GetMovieShowingTime.getShowingTime;
import static databaseutility.GetMovieSynopsis.getMovieSynopsis;
import static databaseutility.GetNewUpcomingMovie.getNewUpcomingMovie;
import static databaseutility.GetNowShowing.getNowShowing;
import static databaseutility.GetStartTime.getStartTime;
import static databaseutility.GetTicketPrice.getTicketPriceKids;
import static databaseutility.GetTicketPrice.getTicketPriceStudents;
import static databaseutility.GetTicketPrice.getTicketPriceAdults;
import static databaseutility.GetTicketPrice.getTicketPriceSeniors;
import static databaseutility.GetUpcomingMovies.getUpcomingMovies;
import static databaseutility.MoviesCounter.countMovies;
import static databaseutility.RedeemedCheck.giftCardRedeemed;
import static databaseutility.RedeemingGiftCard.redeemGiftCard;
import static databaseutility.ReleaseDateChanger.changeReleaseDate;
import static databaseutility.RemovingCinema.removeCinema;
import static databaseutility.RemovingCreditCard.removeCreditCard;
import static databaseutility.RemovingGiftCard.removeGiftCard;
import static databaseutility.RemovingMovie.removeMovie;
import static databaseutility.RemovingSession.removeSession;
import static databaseutility.SetSessionTime.setEndTime;
import static databaseutility.SetTicketPrice.setKidsTicketPrice;
import static databaseutility.SetTicketPrice.setAdultsTicketPrice;
import static databaseutility.SetTicketPrice.setStudentsTicketPrice;
import static databaseutility.SetTicketPrice.setSeniorsTicketPrice;
import static databaseutility.SynopsisChanger.changeSynopsis;
import static databaseutility.UpdateSeats.updateSeats;
import static databaseutility.UserAuthenticate.authenticate;
import static databaseutility.CheckIfCinemaExists.checkIfCinemaExists;
import static databaseutility.CheckIfHolderNameExist.checkIfHolderNameExist;
import static databaseutility.CheckIfCreditCardExists.checkIfCreditCardExists;
import static databaseutility.CheckIfGiftCardExists.checkIfGiftCardExists;
import static databaseutility.GetSeatCapacity.getFrontSeatCapacity;
import static databaseutility.GetSeatCapacity.getMidSeatCapacity;
import static databaseutility.GetSeatCapacity.getBackSeatCapacity;
import static databaseutility.CheckIfSessionExists.checkIfSessionExists;
import static databaseutility.GetCreditCardBalance.getCreditCardBalance;
import static databaseutility.GetUserPassword.getUserPassword;
import static databaseutility.SetMovieCast.setMovieCast;
import static databaseutility.SetMovieShowDate.setMovieShowDate;
import static databaseutility.GetNumberOfSeatsBooked.getNumberOfFrontSeatsBooked;
import static databaseutility.GetNumberOfSeatsBooked.getNumberOfMidSeatsBooked;
import static databaseutility.GetNumberOfSeatsBooked.getNumberOfBackSeatsBooked;

import databaseutility.MovieInsertionBuilder;

public class databaseUtilityTests {
    static Database dbInstance = new Database("jdbc:postgresql://ls-d4381878930280384f33af335289e24c73224a04.c0apyqxz8x8m.ap-southeast-2.rds.amazonaws.com:5432/postgres",
    "dbmasteruser","A>XV>D*7r-V{y_wL}}I{+U=8zEtj1*T<");

    //static Database dbInstance =  new Database("jdbc:postgresql://localhost:5432/postgres", "postgres", "0000");

    //static Database dbInstance = new Database("jdbc:postgresql://localhost:5432/MTBMS", "postgres", "329099");
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
    public void CheckStaff_isStaff_1() {
        removeUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa");
        addUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa", "ghj", "s");
        assert(isStaff(dbInstance, "aaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void CheckStaff_isStaff_isStaff_2() {
        removeUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa");
        addUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa", "ghj", "z");
        assertFalse(isStaff(dbInstance, "aaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void CheckStaff_isStaff_3() {
        removeUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa");
        assertFalse(isStaff(dbInstance, "aaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void CheckStaff_isManager_1() {
        removeUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa");
        addUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa", "ghj", "m");
        assert(isManager(dbInstance, "aaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void CheckStaff_isManager_2() {
        removeUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa");
        addUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa", "ghj", "z");
        assertFalse(isManager(dbInstance, "aaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void CheckStaff_isManager_3() {
        removeUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa");
        assertFalse(isManager(dbInstance, "aaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void CheckStaff_getIdentity_1() {
        removeUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa");
        addUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa", "ghj", "c");
        assert(getIdentity(dbInstance, "aaaaaaaaaaaaaaaaaaaa").equals("c"));
    }

    @Test
    public void CheckStaff_getIdentity_2() {
        removeUser(dbInstance, "aaaaaaaaaaaaaaaaaaaa");
        getIdentity(dbInstance, "aaaaaaaaaaaaaaaaaaaa").equals("c");
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
    public void MovieInsertionBuilder_1() {
        removeMovie(dbInstance, "vscode, the movie");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.insertMovie();
        assertFalse(checkIfMovieExists(dbInstance, "vscode, the movie"));
    }

    @Test
    public void MovieInsertionBuilder_2() {
        removeMovie(dbInstance, "vscode, the movie");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("pg");
        inserter.addReleaseDate("2001-02-26");
        inserter.addSynopsis("As ali writes database utility tests, he discovers..");
        inserter.addDirectors("ali");
        inserter.insertMovie();
        assert(checkIfMovieExists(dbInstance, "vscode, the movie"));
    }

    @Test
    public void MovieInsertionBuilder_3() {
        removeMovie(dbInstance, "vscode, the movie");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("pg");
        inserter.addReleaseDate("2001-02-26");
        inserter.addSynopsis("As ali writes database utility tests, he discovers..");
        inserter.insertMovie();
        assert(checkIfMovieExists(dbInstance, "vscode, the movie"));
    }

    @Test
    public void MovieInsertionBuilder_4() {
        removeMovie(dbInstance, "vscode, the movie");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("pg");
        inserter.addReleaseDate("2001-02-26");
        inserter.addDirectors("ali");
        inserter.insertMovie();
        assert(checkIfMovieExists(dbInstance, "vscode, the movie"));
    }

    @Test
    public void MovieInsertionBuilder_5() {
        removeMovie(dbInstance, "vscode, the movie");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("pg");
        inserter.addReleaseDate("2001-02-26");
        inserter.insertMovie();
        assert(checkIfMovieExists(dbInstance, "vscode, the movie"));
    }

    @Test
    public void MovieInsertionBuilder_6() {
        removeMovie(dbInstance, "vscode, the movie");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("pg");
        inserter.addSynopsis("As ali writes database utility tests, he discovers..");
        inserter.addDirectors("ali");
        inserter.insertMovie();
        assert(checkIfMovieExists(dbInstance, "vscode, the movie"));
    }

    @Test
    public void MovieInsertionBuilder_7() {
        removeMovie(dbInstance, "vscode, the movie");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("pg");
        inserter.addSynopsis("As ali writes database utility tests, he discovers..");
        inserter.insertMovie();
        assert(checkIfMovieExists(dbInstance, "vscode, the movie"));
    }
    
    @Test
    public void MovieInsertionBuidler_8() {
        removeMovie(dbInstance, "vscode, the movie");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("pg");
        inserter.addDirectors("ali");
        inserter.insertMovie();
        assert(checkIfMovieExists(dbInstance, "vscode, the movie"));
    }

    @Test
    public void MovieInsertionBuilder_9() {
        removeMovie(dbInstance, "vscode, the movie");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("pg");
        inserter.insertMovie();
        assert(checkIfMovieExists(dbInstance, "vscode, the movie"));
    }

    @Test 
    public void MovieInsertionBuilder_10() {
        removeMovie(dbInstance, "vscode, the movie");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("invalid classification");
        inserter.insertMovie();
        assertFalse(checkIfMovieExists(dbInstance, "vscode, the movie"));
    }

    @Test
    public void AddingCinema_1() {
        removeCinema(dbInstance, "Ali's cinema");
        addCinema(dbInstance, "Ali's cinema");
        assert(checkIfCinemaExists(dbInstance, "Ali's cinema"));
    }

    @Test
    public void AddingCinema_2() {
        addCinema(dbInstance, "Ali's cinema");
        addCinema(dbInstance, "Ali's cinema");
    }

    @Test 
    public void AddingCreditCard_1() {
        removeCreditCard(dbInstance, "23322");
        addCreditCard(dbInstance, "23322", "ali", "1111");
        assert(checkIfCreditCardExists(dbInstance, "23322"));
    }

    @Test 
    public void AddingCreditCard_2() {
        addCreditCard(dbInstance, "23322", "ali", "1111");
        addCreditCard(dbInstance, "23322", "ali", "1111");
    }

    @Test
    public void AddingGiftCard_1() {
        removeGiftCard(dbInstance, "111111111111111111");
        addGiftCard(dbInstance, "111111111111111111");
        assert(checkIfGiftCardExists(dbInstance, "111111111111111111"));
    }

    @Test
    public void AddingGiftCard_2() {
        addGiftCard(dbInstance, "111111111111111111");
        addGiftCard(dbInstance, "111111111111111111");
    }

    @Test
    public void AddingGiftCard_3() {
        //TODO
    }

    @Test
    public void AddingGiftCard_4() {
        //TODO
    }

    @Test
    public void AddingUpcomingMovie_1() {
        // TODO: description
    }

    @Test
    public void AddMovieSession_1() {
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        assert(checkIfSessionExists(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20"));
    }

    @Test
    public void AddMovieSession_2() {
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
    }

    @Test
    public void changeSeatCapacity_front_1() {
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        changeFrontSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 10);
    }

    @Test 
    public void changeSeatCapacity_front_2() {
        removeMovie(dbInstance, "vscode, the movie");
        removeCinema(dbInstance, "ali's cinema");
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        assertFalse(checkIfSessionExists(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20"));
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        changeFrontSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 0);
        assert(getFrontSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20") == 0);
        changeBackSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 10);
        changeMidSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 5);
        changeFrontSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 3);
        assert(getFrontSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20") == 3);
    }

   @Test
    public void changeSeatCapacity_front_3() {
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        assertFalse(checkIfSessionExists(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20"));
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        changeFrontSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 0);
        assert(getFrontSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20") == 0);
        changeBackSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 10);
        changeMidSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 5);
        changeFrontSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 17);
        assert(getFrontSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20") == 0);
    }

    @Test
    public void changeSeatCapacity_mid_1() {
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        changeFrontSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 10);
    }

    @Test 
    public void changeSeatCapacity_mid_2() {
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        assertFalse(checkIfSessionExists(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20"));
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        changeFrontSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 0);
        assert(getFrontSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20") == 0);
        changeBackSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 10);
        changeMidSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 5);
        changeMidSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 3);
        assert(getMidSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20") == 3);
    }

    @Test
    public void changeSeatCapacity_mid_3() {
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        assertFalse(checkIfSessionExists(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20"));
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        changeFrontSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 0);
        assert(getFrontSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20") == 0);
        changeBackSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 10);
        changeMidSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 5);
        changeMidSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 30);
        assert(getMidSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20") == 5);
    }

    @Test
    public void changeSeatCapacity_mid_4() {
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        assertFalse(checkIfSessionExists(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20"));
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        changeBackSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 10);
        changeMidSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 5);
        changeFrontSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 1);
        assert(getFrontSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20") == 1);
        changeMidSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 0);
        assert(getMidSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20") == 5);
    }

    @Test
    public void changeSeatCapacity_back_1() {
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        changeFrontSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 10);
    }

    @Test 
    public void changeSeatCapacity_back_2() {
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        assertFalse(checkIfSessionExists(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20"));
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        changeFrontSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 0);
        assert(getFrontSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20") == 0);
        changeBackSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 10);
        changeMidSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 5);
        changeBackSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 7);
        assert(getBackSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20") == 7);
    }

     @Test 
    public void ChangeSeatCapacity_back_3() {
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        assert(checkIfMovieExists(dbInstance, "vscode, the movie"));
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        assertFalse(checkIfSessionExists(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20"));
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        changeFrontSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 0);
        assert(getFrontSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20") == 0);
        changeBackSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 10);
        changeMidSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 5);
        changeBackSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", 3);
        assert(getBackSeatCapacity(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20") == 10); 
    } 

    @Test
    public void ChangingCreditCardBalance_1() {
        addCreditCard(dbInstance, "11111", "ali", "1110");
        changeCreditCardBalance(dbInstance, "11111", 100.0);
        assert(getCreditCardBalance(dbInstance, "11111") == 100.0);
        changeCreditCardBalance(dbInstance, "11111", 10.0);
        assert(getCreditCardBalance(dbInstance, "11111") == 10.0);
    }

    @Test
    public void ChangingCreditCardBalance_2() {
        removeCreditCard(dbInstance, "11111");
        changeCreditCardBalance(dbInstance, "11111", 100.0);
    }

    @Test
    public void ChangingIdentity_1() {
        removeUser(dbInstance, "ali");
        addUser(dbInstance, "ali", "10e9rsidfh", "c");
        changeIdentity(dbInstance, "ali", "m");
        assert(isManager(dbInstance, "ali"));
        changeIdentity(dbInstance, "ali", "c");
        assertFalse(isManager(dbInstance, "ali"));
    }
    
    @Test
    public void ChangingIdentity_2() {
        removeUser(dbInstance, "ali");
        changeIdentity(dbInstance, "ali", "m");
    }

    @Test
    public void ChangingUserPassword_1() {
        removeUser(dbInstance, "ali");
        addUser(dbInstance, "ali", "10e9rsidfh", "c");
        changeUserPassword(dbInstance, "ali", "new pass");
        assert(getUserPassword(dbInstance, "ali").equals("new pass"));
    }

    @Test
    public void ChangingUserPassword_2() {
        removeUser(dbInstance, "ali");
        changeUserPassword(dbInstance, "ali", "new pass");
    }

    @Test 
    public void CheckIfUserExists_1() {
        addUser(dbInstance, "ali", "10e9rsidfh", "c");
        assert(checkIfUserExists(dbInstance, "ali"));
    }

    @Test
    public void CheckIfUserExists_2() {
        removeUser(dbInstance, "ali");
        assertFalse(checkIfUserExists(dbInstance, "ali"));
    }

    @Test
    public void DeleteAllUpcoming_1() {
        // TODO
    }

    @Test 
    public void FilterCinema_1() {
        // TODO
    }

    @Test
    public void FilterScreenSize_1() {
        // TODO
    }

    @Test
    public void GetEndTime_1() {
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        setEndTime(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", "2017-03-31 11:20:20");
        assert(getEndTime(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20").equals("2017-03-31 11:20:20"));
    }

    @Test
    public void GetEndTime_2() {
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        getEndTime(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
    }

    @Test
    public void GetMovieCast_1() {
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        setMovieCast(dbInstance, "vscode, the movie" ,"ali");
        assert(getMovieCast(dbInstance, "vscode, the movie").equals("ali"));
    }

    @Test
    public void GetMovieCast_2() {
        removeMovie(dbInstance, "vscode, the movie");
        getMovieCast(dbInstance, "vscode, the movie");
    }

    @Test 
    public void GetMovieClassification_1() {
        removeMovie(dbInstance, "vscode, the movie");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        assert(getMovieClassification(dbInstance, "vscode, the movie").equals("r18+"));
    }

    @Test
    public void GetMovieClassification_2() {
        removeMovie(dbInstance, "vscode, the movie");
        getMovieClassification(dbInstance, "vscode, the movie");
    }

    @Test
    public void GetMovieDirectors_1() {
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        changeDirectors(dbInstance, "vscode, the movie", "ali");
        assert(getDirectors(dbInstance, "vscode, the movie").equals("ali"));
        
        
    }

    @Test
    public void GetMovieDirectors_2() {
        removeMovie(dbInstance, "vscode, the movie");
        getDirectors(dbInstance, "vscode, the movie");
    }

    @Test
    public void GetMovieNames_1() {
        getMovieNames(dbInstance);
    }

    @Test 
    public void GetMovieReleaseDate_1() {
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        changeReleaseDate(dbInstance, "vscode, the movie", "2017-03-31");
        System.out.println(getMovieReleasedate(dbInstance, "vscode, the movie"));
        assert(getMovieReleasedate(dbInstance, "vscode, the movie").equals("2017-03-31"));
    }

    @Test
    public void GetMovieReleaseDate_2() {
        removeMovie(dbInstance, "vscode, the movie");
        getMovieReleasedate(dbInstance, "vscode, the movie");
    }

    @Test
    public void GetMovieScreenSize_1() {
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        getMovieScreensize(dbInstance, "vscode, the movie");
    }

    @Test
    public void GetMovieScreenSize_2() {
        removeMovie(dbInstance, "vscode, the movie");
        getMovieScreensize(dbInstance, "vscode, the movie");
    }

    @Test
    public void GetMovieShowDate_1() {
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        setMovieShowDate(dbInstance, "vscode, the movie","2017-03-31");
        assert(getMovieShowDate(dbInstance, "vscode, the movie").equals("2017-03-31"));
        
    }

    @Test
    public void GetMovieShowDate_2() {
        removeMovie(dbInstance, "vscode, the movie");
        getMovieShowDate(dbInstance, "vscode, the movie");
    }

    @Test
    public void GetMovieShowingTime_1() {
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        addCinema(dbInstance, "ali's cinema");
        getShowingTime(dbInstance, "vscode, the movie", "ali's cinema");
    }

    @Test
    public void GetMovieShowingTime_2() {
        removeMovie(dbInstance, "vscode, the movie");
        addCinema(dbInstance, "ali's cinema");
        getShowingTime(dbInstance, "vscode, the movie", "ali's cinema");
    }

    @Test
    public void GetMovieShowingTime_3() {
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        removeCinema(dbInstance, "ali's cinema");
        getShowingTime(dbInstance, "vscode, the movie", "ali's cinema");
    }

    @Test
    public void GetMovieSynopsis_1() {
        removeMovie(dbInstance, "vscode, the movie");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.addSynopsis("this is a synopsis");
        inserter.insertMovie();
        changeSynopsis(dbInstance, "vscode, the movie", "this is a synopsis");
        assert(getMovieSynopsis(dbInstance, "vscode, the movie").equals("this is a synopsis"));
    }

    @Test
    public void GetMovieSynopsis_2() {
        removeMovie(dbInstance, "vscode, the movie");
        getMovieSynopsis(dbInstance, "vscode, the movie");
    }

    @Test
    public void GetNewUpcomingMovie_1() {
        getNewUpcomingMovie(dbInstance);
    }

    @Test
    public void GetNowShowing_1() {
        getNowShowing(dbInstance);
    }

    @Test
    public void GetStartTime_1() {
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        System.out.println(getStartTime(dbInstance, "ali's cinema", "vscode, the movie"));
        assert(getStartTime(dbInstance, "ali's cinema", "vscode, the movie").equals("2017-03-31 09:30:20"));
    }   

    @Test
    public void GetStartTime_2() {
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        getStartTime(dbInstance, "ali's cinema", "vscode, the movie");
    }

    @Test
    public void GetTicketPrice_kids_1() {
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        setKidsTicketPrice(dbInstance, "vscode, the movie", "2017-03-31 9:30:20", "ali's cinema", "gold", 100.0);
        assert(getTicketPriceKids(dbInstance, "ali's cinema", "vscode, the movie", "2017-03-31 9:30:20","gold") == 100.0);
    }

    @Test
    public void GetTicketPrice_kids_2() {
        removeSession(dbInstance, "alis's cinema", "vscode, the movie", "gold", "2017-03-31 9:30:20");
        getTicketPriceKids(dbInstance, "alis's cinema", "vscode, the movie", "2017-03-31 9:30:20", "gold");
    }

    @Test
    public void GetTicketPrice_adults_1() {
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        setAdultsTicketPrice(dbInstance, "vscode, the movie", "2017-03-31 9:30:20", "ali's cinema", "gold", 100.0);
        assert(getTicketPriceAdults(dbInstance, "ali's cinema", "vscode, the movie", "2017-03-31 9:30:20","gold") == 100.0);
    }

    @Test
    public void GetTicketPrice_adults_2() {
        removeSession(dbInstance, "alis's cinema", "vscode, the movie", "gold", "2017-03-31 9:30:20");
        getTicketPriceAdults(dbInstance, "alis's cinema", "vscode, the movie", "2017-03-31 9:30:20", "gold");
    }

    @Test
    public void GetTicketPrice_students_1() {
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        setStudentsTicketPrice(dbInstance, "vscode, the movie", "2017-03-31 9:30:20", "ali's cinema", "gold", 100.0);
        assert(getTicketPriceStudents(dbInstance, "ali's cinema", "vscode, the movie", "2017-03-31 9:30:20","gold") == 100.0);
    }

    @Test
    public void GetTicketPrice_students_2() {
        removeSession(dbInstance, "alis's cinema", "vscode, the movie", "gold", "2017-03-31 9:30:20");
        getTicketPriceStudents(dbInstance, "alis's cinema", "vscode, the movie", "2017-03-31 9:30:20", "gold");
    }

    @Test
    public void GetTicketPrice_seniors_1() {
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        setSeniorsTicketPrice(dbInstance, "vscode, the movie", "2017-03-31 9:30:20", "ali's cinema", "gold", 100.0);
        assert(getTicketPriceSeniors(dbInstance, "ali's cinema", "vscode, the movie", "2017-03-31 9:30:20","gold") == 100.0);
    }

    @Test
    public void GetTicketPrice_seniors_2() {
        removeSession(dbInstance, "alis's cinema", "vscode, the movie", "gold", "2017-03-31 9:30:20");
        getTicketPriceSeniors(dbInstance, "alis's cinema", "vscode, the movie", "2017-03-31 9:30:20", "gold");
    }

    @Test
    public void GetUpcomingMovies_1() {
        getUpcomingMovies(dbInstance);
    }

    @Test
    public void MoviesCounter_1() {
        countMovies(dbInstance);
    }

    @Test
    public void RedeemedCheck_1() {
        addGiftCard(dbInstance, "7392729382739173GC");
        assertFalse(giftCardRedeemed(dbInstance,"7392729382739173GC"));
        redeemGiftCard(dbInstance, "7392729382739173GC");
        assert(giftCardRedeemed(dbInstance, "7392729382739173GC"));
    }

    @Test
    public void RedeemedCheck_2() {
        removeGiftCard(dbInstance, "7392729382739173GC");
        giftCardRedeemed(dbInstance, "7392729382739173GC");
    }

    @Test
    public void RedeemingGiftCard_1() {
        addGiftCard(dbInstance, "7392729382739173GC");
        assertFalse(giftCardRedeemed(dbInstance,"7392729382739173GC"));
        redeemGiftCard(dbInstance, "7392729382739173GC");
        assert(giftCardRedeemed(dbInstance, "7392729382739173GC"));
    }

    @Test
    public void RedeemingGiftCard_2() {
        removeGiftCard(dbInstance, "7392729382739173GC");
        redeemGiftCard(dbInstance, "7392729382739173GC");
    }

    @Test
    public void ReleaseDateChanger_1() {
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        changeReleaseDate(dbInstance, "vscode, the movie", "2017-03-31 9:30:20");
        assert(getMovieReleasedate(dbInstance, "vscode, the movie").equals("2017-03-31"));
        changeReleaseDate(dbInstance, "vscode, the movie", "2017-05-31");
        assert(getMovieReleasedate(dbInstance, "vscode, the movie").equals("2017-05-31"));
        assertFalse(getMovieReleasedate(dbInstance, "vscode, the movie").equals("2030-05-31"));
    }

    @Test
    public void ReleaseDateChanger_2() {
        removeMovie(dbInstance, "vscode, the movie");
        changeReleaseDate(dbInstance, "vscode, the movie", "2017-05-31 9:30:20");
    }

    @Test
    public void RemovingCinema_1() {
        addCinema(dbInstance, "ali's cinema");
        assert(checkIfCinemaExists(dbInstance, "ali's cinema"));
        removeCinema(dbInstance, "ali's cinema");
        assertFalse(checkIfCinemaExists(dbInstance, "ali's cinema"));
    }

    @Test
    public void RemovingCinema_2() {
        removeCinema(dbInstance, "vscode, the movie");
        removeCinema(dbInstance, "vscode, the movie");
    }

    @Test
    public void RemovingCreditCard_1() {
        addCreditCard(dbInstance, "83593", "alien", "7862");
        removeCreditCard(dbInstance, "83593");
        assertFalse(checkIfCreditCardExists(dbInstance, "83593"));
    }

    @Test
    public void RemovingCreditCard_2() {
        removeCreditCard(dbInstance, "83593");
        removeCreditCard(dbInstance, "83593");
    }

    @Test
    public void RemovingGiftCard_1() {
        addGiftCard(dbInstance, "0987654312123456GC");
        assert(checkIfGiftCardExists(dbInstance, "0987654312123456GC"));
        removeGiftCard(dbInstance, "0987654312123456GC");
        assertFalse(checkIfGiftCardExists(dbInstance, "0987654312123456GC"));
    }

    @Test
    public void RemovingGiftCard_2() {
        removeGiftCard(dbInstance, "0987654312123456GC");
        removeGiftCard(dbInstance, "0987654312123456GC");
    }

    @Test
    public void RemovingGiftCard_3() {
        //TODO
    }

    @Test
    public void RemovingGiftCard_4() {
        //TODO
    }

    @Test
    public void RemovingMovie_1() {
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        assert(checkIfMovieExists(dbInstance, "vscode, the movie"));
        removeMovie(dbInstance, "vscode, the movie");
        assertFalse(checkIfMovieExists(dbInstance, "vscode, the movie"));
    }

    @Test
    public void RemovingMovie_2() {
        removeMovie(dbInstance, "vscode, the movie");
        removeMovie(dbInstance, "vscode, the movie");
    }   

    @Test
    public void RemovingSession_1() {
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        assert(checkIfSessionExists(dbInstance, "ali's cinema", "vscode, the movie", "gold", "2017-03-31 9:30:20"));
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold", "2017-03-31 9:30:20");
        assertFalse(checkIfSessionExists(dbInstance, "ali's cinema", "vscode, the movie", "gold", "2017-03-31 9:30:20"));
    }

    @Test
    public void RemovingSession_2() {
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold", "2017-03-31 9:30:20");
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold", "2017-03-31 9:30:20");
    }

    @Test
    public void SetSessionTime_setEndTime_1() {
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        setEndTime(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", "2017-03-31 11:30:20");
        assert(getEndTime(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20").equals("2017-03-31 11:30:20"));
        setEndTime(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", "2017-03-31 13:30:20");
        assert(getEndTime(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20").equals("2017-03-31 13:30:20"));
    }

    @Test
    public void SetSessionTime_setEndTime_2() {
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold", "2017-03-31 9:30:20");
        setEndTime(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20", "2017-03-31 11:30:20");
    }

    @Test
    public void SetTicketPrice_kids_1() {

    }

    @Test
    public void SetTicketPrice_kids_2() {

    }

    @Test
    public void SetTicketPrice_adults_1() {

    }

    @Test
    public void SetTicketPrice_adults_2() {
        
    }

    @Test
    public void SetTicketPrice_students_1() {

    }

    @Test
    public void SetTicketPrice_students_2() {

    }

    @Test
    public void SetTicketPrice_seniors_1() {

    }

    @Test
    public void SetTicketPrice_seniors_2() {

    }

    @Test
    public void SynopsisChanger_1() {

    }

    @Test
    public void SynopsisChanger_2() {

    }

    /* @Test
    public void UpdateScreenSize_1() {

    }

    @Test
    public void UpdateScreenSize_2() {

    } */

    @Test
    public void UpdateSeats_1() {
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        updateSeats(dbInstance, "ali's cinema", "vscode, the movie", "2017-03-31 9:30:20", "gold", 100, "front");
        assert(getNumberOfFrontSeatsBooked(dbInstance, "ali's cinema", "vscode, the movie", "gold", "2017-03-31 9:30:20") == 100);
        updateSeats(dbInstance, "ali's cinema", "vscode, the movie", "2017-03-31 9:30:20", "gold", 50, "front");
        assert(getNumberOfFrontSeatsBooked(dbInstance, "ali's cinema", "vscode, the movie", "gold", "2017-03-31 9:30:20") == 50);
    }

    @Test
    public void UpdateSeats_2() {
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        updateSeats(dbInstance, "ali's cinema", "vscode, the movie", "2017-03-31 9:30:20", "gold", 100, "mid");
        assert(getNumberOfMidSeatsBooked(dbInstance, "ali's cinema", "vscode, the movie", "gold", "2017-03-31 9:30:20") == 100);
        updateSeats(dbInstance, "ali's cinema", "vscode, the movie", "2017-03-31 9:30:20", "gold", 50, "mid");
        assert(getNumberOfMidSeatsBooked(dbInstance, "ali's cinema", "vscode, the movie", "gold", "2017-03-31 9:30:20") == 50);
    }

    @Test
    public void UpdateSeats_3() {
        addCinema(dbInstance, "ali's cinema");
        MovieInsertionBuilder inserter = new MovieInsertionBuilder(dbInstance, "vscode, the movie");
        inserter.addClassification("r18+");
        inserter.insertMovie();
        addMovieSession(dbInstance, "ali's cinema", "vscode, the movie", "gold","2017-03-31 9:30:20");
        updateSeats(dbInstance, "ali's cinema", "vscode, the movie", "2017-03-31 9:30:20", "gold", 100, "back");
        assert(getNumberOfBackSeatsBooked(dbInstance, "ali's cinema", "vscode, the movie", "gold", "2017-03-31 9:30:20") == 100);
        updateSeats(dbInstance, "ali's cinema", "vscode, the movie", "2017-03-31 9:30:20", "gold", 50, "back");
        assert(getNumberOfBackSeatsBooked(dbInstance, "ali's cinema", "vscode, the movie", "gold", "2017-03-31 9:30:20") == 50);
    }

    @Test
    public void UpdateSeats_4() {
        removeSession(dbInstance, "ali's cinema", "vscode, the movie", "gold", "2017-03-31 9:30:20");
        updateSeats(dbInstance, "ali's cinema", "vscode, the movie", "2017-03-31 9:30:20", "gold", 50, "back");
        updateSeats(dbInstance, "ali's cinema", "vscode, the movie", "2017-03-31 9:30:20", "gold", 50, "front");
        updateSeats(dbInstance, "ali's cinema", "vscode, the movie", "2017-03-31 9:30:20", "gold", 50, "mid");
    }

    @Test
    public void UserAuthenticate_1() {

    }

    @Test
    public void UserAuthenticate_2() {

    }

    @Test
    public void CheckIfCinemaExists_1() {

    }

    @Test
    public void CheckIfCinemaExists_2() {

    }

    @Test
    public void CheckIfHolderNameExists_1() {

    }

    @Test
    public void CheckIfHolderNameExists_2() {

    }

    @Test
    public void CheckIfCreditCardExists_1() {

    }

    @Test
    public void CheckIfCreditCardExists_2() {

    }

    @Test
    public void CheckIfGiftCardExists_1() {

    }

    @Test
    public void CheckIfGiftCardExists_2() {

    }

    @Test
    public void GetCreditCardBalance_1() {

    }

    @Test
    public void getCreditCardBalance_2() {

    }

    @Test
    public void GetUserPassword_1() {
        
    }

    @Test
    public void GetUserPassword_2() {

    }

    @Test
    public void SetMovieCast_1() {

    }
    

    @Test
    public void SetMovieCast_2() {

    }

    @Test
    public void GetNumberOfSeatsBooked_front_1() {

    }

    @Test
    public void GetNumberOfSeatsBooked_front_2() {

    }

    @Test
    public void GetNumberOfSeatsBooked_mid_1() {

    }

    @Test
    public void GetNumberOfSeatsBooked_mid_2() {

    }

    @Test
    public void GetNumberOfSeatsBooked_back_1() {

    }

    @Test
    public void GetNumberOfSeatsBooked_Back_2() {

    }

}