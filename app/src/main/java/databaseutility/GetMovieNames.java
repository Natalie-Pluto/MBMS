 package databaseutility;

import MTBMS.Database;

import java.util.List;

 public class GetMovieNames {
    public static List<String> getMovieNames(Database d){
        String query = "SELECT * FROM moviebooking_db.Movie;";
        return d.sql_getStrings(query, "name_");
    }
}
 