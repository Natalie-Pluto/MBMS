package databaseutility;

import MTBMS.Database;

public class MovieInsertion {
    public static void insertMovie(Database d, int movie_id, String name, String classification, String release_date, String synopsis, String directors){
        String query = String.format("INSERT INTO Movie VALUES(%d, %s, %s, %s, %s, %s);", movie_id, name, classification, release_date, synopsis, directors);
        d.sql_update(query);
    }
}
