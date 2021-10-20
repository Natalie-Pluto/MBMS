package databaseutility;

import MTBMS.Database;
import static databaseutility.MoviesCounter.countMovies;
import static databaseutility.CheckIfMovieExists.checkIfMovieExists;

public class MovieInsertionBuilder {
    private Database db;
    private String name = "";
    private String classification = "";
    private String releaseDate = "";
    private String synopsis = "";
    private String directors = "";

    public MovieInsertionBuilder(Database db, String name) {this.db = db;}

    public void addClassification(String classification) {this.classification = classification;}
    public void addReleaseDate(String releaseDate) {this.releaseDate = releaseDate;}
    public void addDirectors(String directors) {this.directors = directors;}

    private int generateUniqueMovieID() {
        int movieID = countMovies(db) + 1;
        while (checkIfMovieExists(db, movieID)) movieID++;
        return movieID;
    }
    
    public void insertMovie() {
        String query = String.format("INSERT INTO moviebooking_db.Movie VALUES(%d, %s, %s, %s, %s, %s);", generateUniqueMovieID(), name, classification, releaseDate, synopsis, directors);
        this.db.sql_update(query);
    }
}
