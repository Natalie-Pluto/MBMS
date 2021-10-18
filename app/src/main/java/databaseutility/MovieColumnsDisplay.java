package databaseutility;

import MTBMS.Data;
import MTBMS.Database;
import java.sql.*;

public class MovieColumnsDisplay {
    public static void displayMovieColumns(Data d){
        String query = "SELECT * FROM information_schema.columns WHERE table_schema = 'moviebooking_db' AND table_name = 'movie';";
        String result = d.databaseGetter().sql_getString(query, "column_name");
        int index = 1;
        while(result != null){
            System.out.println(String.format("%d. %s", index, result));
            index++;
        }
    }
}
