package databaseutility;

import MTBMS.Database;
import static databaseutility.MoviesCounter.countMovies;
import static databaseutility.CheckIfMovieExists.checkIfMovieExists;
import java.util.List;
import java.util.Arrays;


public class MovieInsertionBuilder {
    private static final String[] validClassifications = {"r18+","g","pg","ma15+","m"}; 
    private Database db;
    private String name = "";
    private String classification = "";
    private String releaseDate = "";
    private String synopsis = "";
    private String directors = "";

    public MovieInsertionBuilder(Database db, String name) {this.db = db;}

    public boolean addClassification(String classification) {
        if (Arrays.asList(validClassifications).contains(classification)) {
            this.classification = classification;
        }
        return false;
        
    }
    public void addReleaseDate(String releaseDate) {this.releaseDate = releaseDate;}
    public void addDirectors(String directors) {this.directors = directors;}
    public void addSynopsis(String Synopsis) {this.synopsis = synopsis;}

    private int generateUniqueMovieID() {
        int movieID = countMovies(db) + 1;
        while (checkIfMovieExists(db, movieID)) movieID++;
        return movieID;
    }
    
    public boolean insertMovie() {
        if (classification == "") return false;
        String query = String.format("INSERT INTO moviebooking_db.Movie VALUES(%d, %s, %s, %s, %s, %s);", generateUniqueMovieID(), name, classification, releaseDate, synopsis, directors);
        this.db.sql_update(query);
        return true;
    }
}
