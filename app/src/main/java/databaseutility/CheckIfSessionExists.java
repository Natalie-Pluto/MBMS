package databaseutility;

import MTBMS.Database;

public class CheckIfSessionExists {
    public static boolean checkIfSessionExists(Database db, int sessionID) {
        String sessionIFFromDB = db.sql_getString("select name from moviebooking_db.Cinema_Session where session_id = " + sessionID + ";", "session_id");
        if (sessionIFFromDB == null) return false;
        return sessionIFFromDB.equals(sessionID);
    }
}