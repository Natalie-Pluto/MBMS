package MTBMS;
import java.sql.*;

public class Data {
    private Database database;
    private String userAuthentiated;

    public Data(String dbUrl, String dbUsername, String dbPassword) {
        this.database = new Database(dbUrl, dbUsername, dbPassword);
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

    public String getIdentity(String username) {return this.database.sql_getString("select * from moviebooking_db.users where username = '" + username + "';", "identity_");}

    public boolean isStaff(String username) {return getIdentity(username).equals("s");}
    
    public boolean isManager(String username) {return getIdentity(username).equals("m");}
   
    public boolean addUser(String username, String password, String identity) {
        String updateArgs = "'" + username + "', '" + password + "', '" + identity + "'";
        boolean updateStatus = this.database.sql_update("insert into moviebooking_db.users(username,password_,identity_) values(" + updateArgs + ");");
        return updateStatus;
    }
}
