package databaseutility;

import MTBMS.Database;

public class MovieColumnsDisplay {
    public static void displayMovieColumns(Database d){
        String query = "SELECT * FROM information_schema.columns WHERE table_schema = 'moviebooking_db' AND table_name = 'movie';";
        String result = d.sql_getString(query, "column_name");
        int index = 1;
        while(result != null){
            System.out.println(String.format("%d. %s", index, result));
            index++;
        }
    }
    
}
