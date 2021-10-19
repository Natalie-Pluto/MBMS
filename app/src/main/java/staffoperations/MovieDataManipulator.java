package staffoperations;

import MTBMS.Data;
import java.util.Scanner;
import databaseutility.*;
import MTBMS.Database;

public class MovieDataManipulator {

    public static void insertMovieData(Data d){

        // retrieve information
        System.out.println("Enter the name of the movie: ");
        Scanner s = new Scanner(System.in);
        String movieName = s.nextLine();
        System.out.println("Enter the classification: ");
        s = new Scanner(System.in);
        String classification = s.nextLine();
        System.out.println("Enter the release_date in YYYY-MM-DD format: ");
        s = new Scanner(System.in);
        String releaseDate = s.nextLine();
        System.out.println("Enter the synopsis: ");
        s = new Scanner(System.in);
        String synopsis = s.nextLine();
        System.out.println("Enter the directors: ");
        s = new Scanner(System.in);
        String directors = s.nextLine();

        //generate movie_id
        int count = MoviesCounter.countMovies(d);
        int movie_id = count + 1;

        //insert movie info into database
        MovieInsertion.insertMovie(d, movie_id, movieName, classification, releaseDate, synopsis, directors);

    }

    public static void deleteMovieData(Data d){
        //enter which movie to delete
        System.out.println("Enter name of the movie to delete: ");
    }

    public static void modifyMovieData(Data d){
        //display movies in database
        MovieNamesDisplay.displayMovieNames(d);

        //get the name of the movie to modify to
        System.out.print("/nEnter the name of the movie: ");
        Scanner s = new Scanner(System.in);
        String movieName = s.nextLine();

        //display all columns of the movie
        MovieColumnsDisplay.displayMovieColumns(d);

        System.out.print("/nEnter the name of the section: ");
        s = new Scanner(System.in);
        String nameOfColumn = s.nextLine();

        //enter modified info
        switch(nameOfColumn){
            case "movie_id":
                System.out.println("movie_id can not be modified.");
                break;
            case "name":
                System.out.print("/nEnter a new name: ");
                s = new Scanner(System.in);
                String newName = s.nextLine();
                MovieNameChanger.changeMovieName(d, movieName, newName);
                movieName = newName;
                break;
            case "classification":
                System.out.print("/nEnter a new classification: ");
                s = new Scanner(System.in);
                String newClassification = s.nextLine();
                MovieClassificationChanger.changeMovieClassification(d, movieName, newClassification);
                break;
            case "release_date":
                System.out.print("/nEnter a new release_date in YYYY-MM-DD format: ");
                s = new Scanner(System.in);
                String newDate = s.nextLine();
                ReleaseDateChanger.changeReleaseDate(d, movieName, newDate);
                break;
            case "synopsis":
                System.out.print("/nEnter a new synopsis: ");
                s = new Scanner(System.in);
                String newSynopsis = s.nextLine();
                SynopsisChanger.changeSynopsis(d, movieName, newSynopsis);
                break;
            case "directors":
                System.out.print("/nEnter new directors: ");
                s = new Scanner(System.in);
                String newDirectors = s.nextLine();
                DirectorChanger.changeDirectors(d, movieName, newDirectors);
                break;

        }



    }
}
