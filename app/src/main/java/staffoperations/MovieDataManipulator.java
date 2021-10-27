package staffoperations;

import java.util.Scanner;
import databaseutility.*;
import MTBMS.Database;

public class MovieDataManipulator {

    /*public static void insertMovieData(Database d){

        System.out.println("Note: To quit, Enter \"-Exit-\"");
        Scanner s = new Scanner(System.in);
        String input = "";
        String[] movieProperties = new String[5];
        int index = 0;

        //keep running unless user quits
        while(!input.equals("-Exit-")) {

            // retrieve information
            System.out.println("Enter the name_ of the movie to add: ");
            input = s.nextLine();

            //quit if user wants to quit
            if(input.equals("-Exit-")){
                break;
            }

            //check if movie already exists
            boolean exist = CheckIfMovieExists.checkIfMovieExists(d, input);
            if(exist == true){
                System.out.println("Error: Movie title already exists.");
                continue;
            }

            //collect info into the array to insert movie at the end
            movieProperties[index] = input;
            index++;

            System.out.println("Enter the classification to add: ");
            input = s.nextLine();
            if(input.equals("-Exit-")){
                break;
            }
            movieProperties[index] = input;
            index++;

            System.out.println("Enter the release_date to add in YYYY-MM-DD format: ");
            input = s.nextLine();
            if(input.equals("-Exit-")){
                break;
            }
            movieProperties[index] = input;
            index++;

            System.out.println("Enter the synopsis to add: ");
            input = s.nextLine();
            if(input.equals("-Exit-")){
                break;
            }
            movieProperties[index] = input;
            index++;

            System.out.println("Enter the directors to add: ");
            input = s.nextLine();
            if(input.equals("-Exit-")){
                break;
            }
            movieProperties[index] = input;
            index++;

            //insert movie info into database
            MovieInsertionBuilder inserter = new MovieInsertionBuilder(d, movieProperties[0]);
            inserter.addClassification(movieProperties[1]);
            inserter.addDirectors(movieProperties[2]);
            inserter.addReleaseDate(movieProperties[3]);
            inserter.addSynopsis(movieProperties[4]);
            inserter.insertMovie();
            System.out.println("Success: Movie added.");
        }

        System.out.println("Movie Insertion exit.");
    }


    public static void deleteMovieData(Database d){

        System.out.println("Note: To quit, Enter \"-Exit-\"");
        Scanner s = new Scanner(System.in);
        String input = "";

        //keep running unless user quits
        while(!input.equals("-Exit-")) {

            // retrieve information
            System.out.println("Enter the name_ of the movie to delete: ");
            input = s.nextLine();
            if(input.equals("-Exit-")){
                break;
            }

            //check if movie already exists
            boolean exist = CheckIfMovieExists.checkIfMovieExists(d, input);
            if(exist == false){
                System.out.println("Error: Movie title doesn't exists.");
                continue;
            }

            RemovingMovie.removeMovie(d, input);
            System.out.println("Success: Movie deleted.");
        }

        System.out.println("Movie deletion exit.");

    }

    //modify movie name_ needs to be updated
    //as it is a foreign key in cinema_session table
    public static void modifyMovieData(Database d) {

        System.out.println("Note: To quit, Enter \"-Exit-\"");
        Scanner s = new Scanner(System.in);
        String input = "";
        String originalName = "";
        String newName = "";
        boolean nameChanged = false;

        //keep running unless user quits
        Outer: while(!input.equals("-Exit-")) {

            //get the name_ of the movie to modify to
            System.out.println("Enter the name_ of the movie: ");
            input = s.nextLine();
            if(input.equals("-Exit-")){
                break Outer;
            }

            //check if the provided name_ exists
            boolean existence = CheckIfMovieExists.checkIfMovieExists(d, input);
            if(existence == false){
                System.out.println("Error: Movie does not exist.");
                continue Outer;
            }
            originalName = input;

            //user can choose if they want to keep modify info of the same movie,
            //or to start to modify a new movie or quit the process
            boolean toModifySameMovie = true;
            Inner: while(toModifySameMovie == true) {
                System.out.println("Enter the number of the section to modify: \n1.name_ 2.classification 3.release_date 4.synopsis 5.directors ");
                input = s.nextLine();
                if (input.equals("-Exit-")) {
                    break Outer;
                }

                //enter modified info
                switch (input) {

                    //modify movie name_
                    case "1":
                        System.out.println("\nEnter a new name_: ");
                        input = s.nextLine();
                        if (input.equals("-Exit-")) {
                            break Outer;
                        }

                        //check if the provided new name_ is a duplicate
                        boolean duplicate = CheckIfMovieExists.checkIfMovieExists(d, input);
                        if (duplicate == true) {
                            System.out.println("Error: Movie name_ exists.");
                            continue Outer;
                        }
                        nameChanged = true;
                        newName = input;
                        MovieNameChanger.changeMovieName(d, originalName, newName);

                        //provide user with options to continue
                        System.out.println("Success: Movie name_ changed.");
                        System.out.println("Options: 1.Continue to modify this movie");
                        System.out.println("Options: 2.Modify info of a movie");
                        System.out.println("Enter the option number to continue.");
                        input = s.nextLine();
                        if(input.equals("1")){
                            continue Inner;
                        }else{
                            continue Outer;
                        }
                    case "2":
                        System.out.println("Enter a new classification: ");
                        input = s.nextLine();
                        if (input.equals("-Exit-")) {
                            break Outer;
                        }

                        //check if movie name_ has changed, if changed, use the new name_ to modify movie data
                        if(nameChanged == true){
                            MovieClassificationChanger.changeMovieClassification(d, newName, input);
                        }else{
                            MovieClassificationChanger.changeMovieClassification(d, originalName, input);
                        }

                        //provide user with options to continue
                        System.out.println("Success: Movie classification changed.");
                        System.out.println("Options: 1.Continue to modify this movie");
                        System.out.println("Options: 2.Modify info of a movie");
                        System.out.println("Enter the option number to continue.");
                        input = s.nextLine();
                        if(input.equals("1")){
                            continue Inner;
                        }else{
                            continue Outer;
                        }
                    case "3":
                        System.out.println("Enter a new release_date in YYYY-MM-DD format: ");
                        input = s.nextLine();
                        if (input.equals("-Exit-")) {
                            break Outer;
                        }

                        //check if movie name_ has changed, if changed, use the new name_ to modify movie data
                        if(nameChanged == true){
                            ReleaseDateChanger.changeReleaseDate(d, newName, input);
                        }else{
                            ReleaseDateChanger.changeReleaseDate(d, originalName, input);
                        }

                        //provide user with options to continue
                        System.out.println("Success: Movie release date changed.");
                        System.out.println("Options: 1.Continue to modify this movie");
                        System.out.println("Options: 2.Modify info of a movie");
                        System.out.println("Enter the option number to continue.");
                        input = s.nextLine();
                        if(input.equals("1")){
                            continue Inner;
                        }else{
                            continue Outer;
                        }
                    case "4":
                        System.out.println("Enter a new synopsis: ");
                        input = s.nextLine();
                        if (input.equals("-Exit-")) {
                            break Outer;
                        }

                        //check if movie name_ has changed, if changed, use the new name_ to modify movie data
                        if(nameChanged == true){
                            SynopsisChanger.changeSynopsis(d, newName, input);
                        }else{
                            SynopsisChanger.changeSynopsis(d, originalName, input);
                        }

                        //provide user with options to continue
                        System.out.println("Success: Synopsis changed.");
                        System.out.println("Options: 1.Continue to modify this movie");
                        System.out.println("Options: 2.Modify info of a movie");
                        System.out.println("Enter the option number to continue.");
                        input = s.nextLine();
                        if(input.equals("1")){
                            continue Inner;
                        }else{
                            continue Outer;
                        }
                    case "5":
                        System.out.println("Enter new directors: ");
                        input = s.nextLine();
                        if (input.equals("-Exit-")) {
                            break Outer;
                        }

                        //check if movie name_ has changed, if changed, use the new name_ to modify movie data
                        if(nameChanged == true){
                            DirectorChanger.changeDirectors(d, newName, input);
                        }else{
                            DirectorChanger.changeDirectors(d, originalName, input);
                        }

                        //provide user with options to continue
                        System.out.println("Success: Director changed.");
                        System.out.println("Options: 1.Continue to modify this movie");
                        System.out.println("Options: 2.Modify info of a movie");
                        System.out.println("Enter the option number to continue.");
                        input = s.nextLine();
                        if(input.equals("1")){
                            continue Inner;
                        }else{
                            continue Outer;
                        }

                }
            }
        }


    }*/
}

 