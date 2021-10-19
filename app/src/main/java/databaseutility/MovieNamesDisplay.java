package databaseutility;

import MTBMS.Database;

public class MovieNamesDisplay {
    public static void displayMovieNames(Database d){
        String query = "SELECT * FROM moviebooking_db.Movie;";
        String result = d.sql_getString(query, "name");
        int index = 1;

        //display names of the movies
        while(result != null){
            System.out.println(String.format("%d. %s", index, result));
            index++;
        }
    }
}
