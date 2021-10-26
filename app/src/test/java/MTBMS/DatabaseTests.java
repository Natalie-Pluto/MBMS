package MTBMS;

import org.junit.Test;

import javax.xml.crypto.Data;

import static org.junit.Assert.*;
import java.sql.*;
import java.util.Date;
import static databaseutility.UserAuthenticate.authenticate;
import static databaseutility.AddingUser.addUser;

public class DatabaseTests {
    private String goodDBUrl = "jdbc:postgresql://ls-d4381878930280384f33af335289e24c73224a04.c0apyqxz8x8m.ap-southeast-2.rds.amazonaws.com:5432/postgres";
    private String goodDBUsername = "dbmasteruser";
    private String goodDBPassword = "A>XV>D*7r-V{y_wL}}I{+U=8zEtj1*T<";

    private String badDBUrl = "asdfaslfsalkdjflsafa";
    private String badDBUsername = "123123";
    private String badDBPassword = "fsadfawe";



    @Test
    public void initializationTest(){
        Database d = new Database(goodDBUrl, goodDBUsername, goodDBPassword);
    }

    @Test
    public void authenticationTest() {
        Database d = new Database(goodDBUrl, goodDBUsername, goodDBPassword);
        addUser(d, "ali", "aaa", "c");
        assert (authenticate(d, "ali", "aaa"));
    }

   /* @Test
    public void sql_UpdateTest(){
        Database d = new Database(goodDBUrl, goodDBUsername, goodDBPassword);
        assertTrue(d.sql_update("users"));
    }

    @Test
    public void sql_UpdateTest2(){
        Database d = new Database(badDBUrl, badDBUsername, badDBPassword);
        assertFalse(d.sql_update("users"));
    }*/

/*     @Test
    public void sql_getStringTest1(){
        Database d = new Database(goodDBUrl, goodDBUsername, goodDBPassword);
        assertNotNull(d.sql_getString("select username from movingbooking_db","username"));
    }

    @Test
    public void sql_getStringTest2() {
        Database d = new Database(badDBUrl, badDBUsername, badDBPassword);
        assertNotNull(d.sql_getString("users", "username"));
    }

    @Test
    public void sql_getIntTest2() {
        Database d = new Database(badDBUrl, badDBUsername, badDBPassword);
        assertTrue( d.sql_getInt("Cinema_session", "session_id") == 0);
    }

    @Test
    public void sql_getIntTest1(){
        Database d = new Database(goodDBUrl, goodDBUsername, goodDBPassword);
        assertFalse((int) d.sql_getInt("Cinema_session", "session_id") == 0);
    }
 */
/*     @Test
    public void sql_getDoubleTest1() {
        Database d = new Database(goodDBUrl, goodDBUsername, goodDBPassword);
        assertFalse(d.sql_getDouble("Cinema_session", "ticket_price_adults") == 0.0);
    }

    @Test
    public void sql_getDoubleTest2() {
        Database d = new Database(badDBUrl, badDBUsername, badDBPassword);
        assertTrue(d.sql_getDouble("Cinema_session", "ticket_price_adults") == 0.0);
    }

    @Test
    public void sql_getBooleanTest1() {
        Database d = new Database(goodDBUrl, goodDBUsername, goodDBPassword);
        assertNotNull(d.sql_getBoolean("Gift_Card", "redeemed"));
    }

    @Test
    public void sql_getBooleanTest2() {
        Database d = new Database(badDBUrl, badDBUsername, badDBPassword);
        assertNull(d.sql_getBoolean("Gift_Card", "redeemed"));
    } */

} 

