package MTBMS;

import java.util.ArrayList;
import java.util.Date;

public class Movies {
    private String movieName;
    private String cinemaName;
    private String screenSize;

    public Movies(String movieName, String cinemaName, String screenSize) {
        this.movieName = movieName;
        this.cinemaName = cinemaName;
        this.screenSize = screenSize;
    }

    // List all the upcoming movies by calling the method in DB class
    public void listAllmovies() {
        // TODO
    }

    // Filter and print out the upcoming movies through cinema name
    public void filterMovies1(String cinemaName){
        // TODO
    }

    // Filter and print out the upcoming movies through screen size
    public void filterMovies2(String screenSize){
        // TODO
    }

    // After customer has booked their tickets, update num of seats available and booked in Cinema table
    public void updateSeats(int num) {
        // TODO
    }

    // Change the ticket price in Cinema table
    public void changePrice(int num) {
        // TODO
    }

    // Change the release time of the movie in Movie table
    public void changeRtime(Date time) {
        // TODO
    }

    // Change the upcoming time of the movie in movie table
    public void changeUtime(Date time) {
        // TODO
    }

    // Change the screen size in the Cinema table
    public void changeScreenSize(String size) {
        // TODO
    }

    // Update the casts in Movie table
    public void updateCast(ArrayList<String> name) {
        // TODO
    }

    // Change the director in Movie table
    public void changeDirector(String name) {
        // TODO
    }

    // Change the classification in Movie table
    public void changeClassification(String classification) {
        // TODO
    }

    // Update the synopsis in Movie table
    public void changeSynopsis(String synopsis) {
        // TODO
    }

    // Get the price of the ticket in Cinema table
    public void getPrice(String movieName, String screenSize) {
        // TODO
    }

    // Delete a movie in both Cinema and Movie table
    public void deleteMovie(String movieName, String cinemaName) {
        // TODO
    }

    // Add a movie in both Cinema and Movie table
    public void addMovie(String movieInfo) {
        // TODO
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }
}

