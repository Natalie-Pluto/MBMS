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

    public String getUserAuthenticated() {return this.userAuthentiated;}

    public Boolean isStaff(String username) {
        return this.database.sql_getBoolean("select * from moviebooking_db where username = '" + username + "';", "is_staff");
    }
    
    public Boolean isManager(String username) {
        return this.database.sql_getBoolean("select * from moviebooking_db where username = '" + username + "';", "is_manager");
    }
    
    

}
