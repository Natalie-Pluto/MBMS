package databaseutility;

import MTBMS.Database;
import static databaseutility.MoviesCounter.countMovies;
import static databaseutility.CheckIfMovieExists.checkIfMovieExists;
import java.util.List;
import java.util.Arrays;


public class MovieInsertionBuilder {
    private static final String[] validClassifications = {"r18+","g","pg","ma15+","m"}; 
    private Database db;
    private String name = "null";
    private String classification = "";
    private String releaseDate = "null";
    private String synopsis = "null";
    private String directors = "null";

    public MovieInsertionBuilder(Database db, String name) {
        this.db = db;
        this.name = name;
    }

    public boolean addClassification(String classification) {
        if (Arrays.asList(validClassifications).contains(classification.toLowerCase())) {
            this.classification = classification;
            return true;
        }
        return false;
    }

    public void addReleaseDate(String releaseDate) {this.releaseDate = releaseDate;}
    public void addDirectors(String directors) {this.directors = directors;}
    public void addSynopsis(String Synopsis) {this.synopsis = synopsis;}
    
    public boolean insertMovie() {
        if (this.classification == "") return false;
        if (!releaseDate.equals("null")) this.releaseDate = "'" + this.releaseDate + "'";
        if (!synopsis.equals("null")) this.synopsis = "'" + this.synopsis.replace("'","''") + "'";
        if (!directors.equals("null")) this.directors = "'" + this.directors.replace("'","''") + "'";
        String query = String.format("INSERT INTO moviebooking_db.Movie VALUES('%s', '%s', %s, %s, %s) on conflict (name_) DO NOTHING;", name, classification, releaseDate, synopsis, directors);
        this.db.sql_update(query);
        return true;
    }
}
