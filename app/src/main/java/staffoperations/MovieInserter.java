package staffoperations;

import databaseutility.CheckIfMovieExists;
import MTBMS.Database;
import java.util.Scanner;

public class MovieInserter {
    private Scanner s;
    private Database d;

    public MovieInserter(Scanner scanner, Database db){
        s = scanner;
        d = db;
    }

    public String collectMovieName(){
        System.out.println("Enter the name of the movie: ");
        String movieName = s.nextLine();
        boolean movieExist = CheckIfMovieExists.checkIfMovieExists(d, movieName);
        if(movieExist){
            System.out.println("Error: Movie already exists.");
            movieName = collectMovieName();
        }
        return movieName;
    }

    public String collectMovieClassification(){
        System.out.println("Enter the classification: ");
        String classification = s.nextLine();
        boolean contain = false;
        String[] validClassifications = {"r18+","g","pg","ma15+","m"};
        for(String i : validClassifications){
            if(i.equals(classification.toLowerCase())){
                contain = true;
                break;
            }
        }
        if(!contain){
            System.out.println("Error: Classification is invalid.");
            classification = collectMovieClassification();
        }
        return classification;

    }

    public String collectMovieReleaseDate(){
        System.out.println("Enter the release date in YYYY-MM-DD format:");
        String releaseDate = s.nextLine();
        if(releaseDate.length() != 10){
            System.out.println("Error: Invalid date format.");
            releaseDate = collectMovieReleaseDate();
        }
        if(releaseDate.charAt(4) != '-' || releaseDate.charAt(7) != '-'){
            System.out.println("Error: Invalid date format.");
            releaseDate = collectMovieReleaseDate();
        }
        String year = releaseDate.substring(0, 4);
        String month = releaseDate.substring(5, 7);
        String day = releaseDate.substring(8);
        int monthInt = 0;
        int dayInt = 0;
        try{
            Integer.parseInt(year);
            monthInt = Integer.parseInt(month);
            dayInt = Integer.parseInt(day);
        }catch(NumberFormatException e){
            System.out.println("Error: Invalid date format.");
            releaseDate = collectMovieReleaseDate();
        }
        if(monthInt > 12 ||dayInt > 31){
            System.out.println("Error: Invalid date format.");
            releaseDate = collectMovieReleaseDate();
        }
        return releaseDate;
    }

    public String collectMovieSynopsis(){
        System.out.println("Enter the synopsis:");
        String synopsis = s.nextLine();
        return synopsis;
    }

    public String collectMovieDirectors(){
        System.out.println("Enter the directors:");
        String directors = s.nextLine();
        return directors;
    }

    public String collectMovieGenre(){
        System.out.println("Enter the genre:");
        String genre = s.nextLine();
        if(genre.length() > 100){
            System.out.println("Error: Text is too long.");
            genre = collectMovieGenre();
        }
        return genre;
    }

    public String collectMovieCasts(){
        System.out.println("Enter the casts:");
        String casts = s.nextLine();
        return casts;
    }

    public String collectMovieShowingDate(){
        System.out.println("Enter the showing date in YYYY-MM-DD format:");
        String showingDate = s.nextLine();
        if(showingDate.length() != 10){
            System.out.println("Error: Invalid date format.");
            showingDate = collectMovieShowingDate();
        }
        if(showingDate.charAt(4) != '-' || showingDate.charAt(7) != '-'){
            System.out.println("Error: Invalid date format.");
            showingDate = collectMovieShowingDate();
        }
        String year = showingDate.substring(0, 4);
        String month = showingDate.substring(5, 7);
        String day = showingDate.substring(8);
        int monthInt = 0;
        int dayInt = 0;
        try{
            Integer.parseInt(year);
            monthInt = Integer.parseInt(month);
            dayInt = Integer.parseInt(day);
        }catch(NumberFormatException e){
            System.out.println("Error: Invalid date format.");
            showingDate = collectMovieShowingDate();
        }
        if(monthInt > 12 ||dayInt > 31){
            System.out.println("Error: Invalid date format.");
            showingDate = collectMovieShowingDate();
        }
        return showingDate;
    }

    public boolean run(){
        String movieName = collectMovieName();
        String classification = collectMovieClassification();
        String releaseDate = collectMovieReleaseDate();
        String synopsis = collectMovieSynopsis();
        String directors = collectMovieDirectors();
        String genre = collectMovieGenre();
        String casts = collectMovieCasts();
        String showingDate = collectMovieShowingDate();
        String query = String.format("INSERT INTO moviebooking_db.Movie VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s') on conflict (name_) DO NOTHING;", movieName, classification, releaseDate, synopsis, directors, genre, casts, showingDate);
        boolean success = d.sql_update(query);
        return success;
    }
}
