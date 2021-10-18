package databaseutility;

import MTBMS.Data;
import MTBMS.Database;
import java.sql.*;

public class MovieInsertion {
    public static void insertMovie(Data d, int movie_id, String name, String classification, String release_date, String synopsis, String directors){
        String query = String.format("INSERT INTO Movie VALUES(%d, %s, %s, %s, %s, %s);", movie_id, name, classification, release_date, synopsis, directors);
        d.databaseGetter().sql_update(query);
    }
}
