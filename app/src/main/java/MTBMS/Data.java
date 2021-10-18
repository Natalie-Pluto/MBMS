package MTBMS;
import java.sql.*;

public class Data {
    private Database database;
    private String userAuthentiated;

    public Data(String dbUrl, String dbUsername, String dbPassword) {
        this.database = new Database(dbUrl, dbUsername, dbPassword);
    }

    public Database databaseGetter(){
        return database;
    }

    public Boolean authenticate(String username, String password) {
        String user =  this.database.sql_getString("select * from moviebooking_db.users where username = '" + username + "' and password_ = '" + password + "';", "username");
        if (user == null) return false;
        this.userAuthentiated = "user";
        return true;
    }

    public String getUserAuthenticated() {
        return this.userAuthentiated;
    }

    public Boolean isStaff(String username) {
        Boolean isStaff = this.database.sql_getBoolean("select * from moviebooking_db where username = '" + username + "';", "is_staff");
        if (isStaff == null) return false;
        return isStaff;
    }
    
    public Boolean isManager(String username) {
        Boolean isManager =  this.database.sql_getBoolean("select * from moviebooking_db where username = '" + username + "';", "is_manager");
        if (isManager == null) return false;
        return isManager;
    }
    
    

}
