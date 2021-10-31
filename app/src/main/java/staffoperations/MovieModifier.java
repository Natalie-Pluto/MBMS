package staffoperations;

import databaseutility.*;
import MTBMS.Database;

import java.util.Scanner;

public class MovieModifier {
    private Scanner s;
    private Database d;

    // Regular
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";


    // Bold
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE

    // Background
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW


    // Bold High Intensity
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE


    public MovieModifier(Scanner scanner, Database db){
        s = scanner;
        d = db;
    }

    public String collectMovieName(){
        System.out.println(PURPLE_BOLD + "Enter the name of the movie: " + ANSI_RESET);
        String movieName = s.nextLine();
        boolean movieExist = CheckIfMovieExists.checkIfMovieExists(d, movieName);
        if(!movieExist){
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Movie does not exists. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            movieName = collectMovieName();
        }
        return movieName;
    }

    public String collectMovieClassification(){
        System.out.println(PURPLE_BOLD + "Enter the classification: " + ANSI_RESET);
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
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Classification is invalid. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            classification = collectMovieClassification();
        }
        return classification;
    }

    public String collectMovieReleaseDate(){
        System.out.println(PURPLE_BOLD + "Enter the release date in YYYY-MM-DD format:" + ANSI_RESET);
        String releaseDate = s.nextLine();
        if(releaseDate.length() != 10){
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Invalid date format. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            releaseDate = collectMovieReleaseDate();
        }
        if(releaseDate.charAt(4) != '-' || releaseDate.charAt(7) != '-'){
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Invalid date format. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
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
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Invalid date format. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            releaseDate = collectMovieReleaseDate();
        }
        if(monthInt > 12 ||dayInt > 31){
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Invalid date format. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            releaseDate = collectMovieReleaseDate();
        }
        return releaseDate;
    }

    public String collectMovieSynopsis(){
        System.out.println(PURPLE_BOLD + "Enter the synopsis:" + ANSI_RESET);
        String synopsis = s.nextLine();
        return synopsis;
    }

    public String collectMovieDirectors() {
        System.out.println(PURPLE_BOLD + "Enter the directors:" + ANSI_RESET);
        String directors = s.nextLine();
        return directors;
    }

    public String collectMovieGenre(){
        System.out.println(PURPLE_BOLD + "Enter the genre:" + ANSI_RESET);
        String genre = s.nextLine();
        if(genre.length() > 100){
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Text is too long. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            genre = collectMovieGenre();
        }
        return genre;
    }

    public String collectMovieCasts(){
        System.out.println(PURPLE_BOLD + "Enter the casts:" + ANSI_RESET);
        String casts = s.nextLine();
        return casts;
    }

    public String collectMovieShowingDate(){
        System.out.println(PURPLE_BOLD + "Enter the showing date in YYYY-MM-DD format:" + ANSI_RESET);
        String showingDate = s.nextLine();
        if(showingDate.length() != 10){
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Invalid date format. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            showingDate = collectMovieShowingDate();
        }
        if(showingDate.charAt(4) != '-' || showingDate.charAt(7) != '-'){
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Invalid date format. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
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
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Invalid date format. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            showingDate = collectMovieShowingDate();
        }
        if(monthInt > 12 ||dayInt > 31){
            System.out.println("\n============================================");
            System.out.println(RED_BOLD + "Error: Invalid date format. (｡´︿`｡)" + ANSI_RESET);
            System.out.println("============================================\n");
            showingDate = collectMovieShowingDate();
        }
        return showingDate;
    }

    public boolean run(){
        String movieName = collectMovieName();
        System.out.println(PURPLE_BOLD + "1.Enter the number of the section to modify: " + ANSI_RESET);
        System.out.println("1.Classification 2.Release Date 3.Synopsis 4.Directors 5.Genre 6.Casts 7.Showing Date");
        String sectionNumber = s.nextLine();
        boolean success = true;
        switch(sectionNumber){
            case "1":
                String classification = collectMovieClassification();
                success = MovieClassificationChanger.changeMovieClassification(d, movieName, classification);
                return success;
            case "2":
                String releaseDate = collectMovieReleaseDate();
                success = ReleaseDateChanger.changeReleaseDate(d, movieName, releaseDate);
                return success;
            case "3":
                String synopsis = collectMovieSynopsis();
                success = SynopsisChanger.changeSynopsis(d, movieName, synopsis);
                return success;
            case "4":
                String directors = collectMovieDirectors();
                success = DirectorChanger.changeDirectors(d, movieName, directors);
                return success;
            case "5":
                String genre = collectMovieGenre();
                success = GenreChanger.changeGenre(d, movieName, genre);
                return success;
            case "6":
                String casts = collectMovieCasts();
                success = CastsChanger.changeCasts(d, movieName, casts);
                return success;
            case "7":
                String showingDate = collectMovieShowingDate();
                success = ShowingDateChanger.changeShowingDate(d, movieName, showingDate);
                return success;
            default:
                System.out.println("\n============================================");
                System.out.println(RED_BOLD + "Error: Invalid input. (｡´︿`｡)" + ANSI_RESET);
                System.out.println("============================================\n");
                success = false;
                return success;
        }
    }




}
