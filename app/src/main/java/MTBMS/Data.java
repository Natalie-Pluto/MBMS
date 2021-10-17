package MTBMS;
import java.sql.*;

public class Data {
    private Database database;
    private String userAuthentiated;

    public Data(String dbUrl, String dbUsername, String dbPassword) {
        this.database = new Database(dbUrl, dbUsername, dbPassword);
    }

    public Boolean authenticate(String username, String password) {
        Boolean validCombination =  this.database.sql_getString("select * from moviebooking_db.users where username = '" + username + "' and password_ = '" + password + "';", "username").equals(username);
        if (validCombination) this.userAuthentiated = "user";
        return validCombination;
    }
    
    

}
