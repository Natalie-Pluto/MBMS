package staffoperations;

import MTBMS.Database;
import databaseutility.*;

import java.io.FileWriter;
import java.io.File;
import java.util.List;
import java.util.Scanner;

public class ReportGenerator {
    private Database d;
    private Scanner s;

    public ReportGenerator(Scanner scanner, Database db){
        d = db;
        s = scanner;
    }

    public List<String> getUpcomingMovieNames(){
        String query = "select name_ from moviebooking_db.upcomingmovie";
        List<String> names = d.sql_getStrings(query,"name_");
        return names;
    }
    public void generateUpcomingMovieReport(){
        List<String> movieNames = getUpcomingMovieNames();
        for(int i = 0; i < movieNames.size(); i++){
            String movieName = movieNames.get(i);
            String classification = GetMovieClassification.getMovieClassification(d, movieName);
            String releaseDate = GetMovieReleaseDate.getMovieReleasedate(d, movieName);
            String synopsis = GetMovieSynopsis.getMovieSynopsis(d, movieName);
            String director = GetMovieDirectors.getDirectors(d, movieName);
            String cast = GetMovieCast.getMovieCast(d, movieName);
        }

    }

    public void generateMovieSessionReport(){

    }

}
