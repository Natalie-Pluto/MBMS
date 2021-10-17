package MTBMS;

import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.*;

public class DataTests {
    private String goodDBUrl = "jdbc:postgresql://ls-d4381878930280384f33af335289e24c73224a04.c0apyqxz8x8m.ap-southeast-2.rds.amazonaws.com:5432/postgres";
    private String goodDBUsername = "dbmasteruser";
    private String goodDBPassword = "A>XV>D*7r-V{y_wL}}I{+U=8zEtj1*T<";

    @Test
    public void authenticateValidUserAndPass() {
        Data x = new Data(goodDBUrl, goodDBUsername, goodDBPassword);
        assertTrue(x.authenticate("ali","password1"));
    }
}
