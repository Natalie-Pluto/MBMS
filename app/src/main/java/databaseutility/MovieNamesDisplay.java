package databaseutility;

import MTBMS.Data;
import MTBMS.Database;
import java.sql.*;

public class MovieNamesDisplay {
    public static void displayMovieNames(Data d){
        String query = "SELECT * FROM moviebooking_db.Movie;";
        String result = d.databaseGetter().sql_getString(query, "name");
        int index = 1;

        //display names of the movies
        while(result != null){
            System.out.println(String.format("%d. %s", index, result));
            index++;
        }
    }
}
