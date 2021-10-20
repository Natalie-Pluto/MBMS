package MTBMS;

import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.*;
import java.util.Date;
import MTBMS.Database;
import static databaseutility.UserAuthenticate.authenticate;
import static databaseutility.AddingUser.addUser;

public class DatabaseTests {
    static Database dbInstance = new Database("jdbc:postgresql://ls-d4381878930280384f33af335289e24c73224a04.c0apyqxz8x8m.ap-southeast-2.rds.amazonaws.com:5432/postgres",
    "dbmasteruser","A>XV>D*7r-V{y_wL}}I{+U=8zEtj1*T<");

    @Test
    public void authenticateworks()  {
        addUser(dbInstance, "ali");
        authenticate(dbInstance, "ali");
    }
}

