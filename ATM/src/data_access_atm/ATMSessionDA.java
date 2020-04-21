package data_access_atm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Database;
import entity_atm.ATMSession;

public class ATMSessionDA
{
    private Database db;
    private PreparedStatement psGetATMSessionInfo;
    private PreparedStatement psInsertATMSession;
    
    public ATMSessionDA(Database database) throws SQLException{
        this.db = database;
        generateStatements();
        
    }
    private void generateStatements() throws SQLException{
        psGetATMSessionInfo = db.getDatabase().prepareStatement("Select `sessionID`, `sessionStartTime`, `sessionEndTime`,"
        			+ "`sessionActive`, `machineID`, `cardNumber` FROM ATMSession WHERE `sessionID` = ? LIMIT 1;");
        psInsertATMSession = db.getDatabase().prepareStatement("INSERT INTO `ATMSession` (`sessionStartTime`, `sessionEndTime`, " + 
        		"`sessionActive`, `machineID`, `cardNumber`) VALUES (DateTime('now'), null, 1, ?, ?);");
    }
    
    public ATMSession getATMSessionInfo(int sessionID)
    {
        ATMSession session = null;
        try {
            psGetATMSessionInfo.setInt(1,  sessionID);
            ResultSet set = db.executeQuery(psGetATMSessionInfo, true);
            if(set.next())
            {
                session = new ATMSession(set.getInt(1), set.getTimestamp(2).toLocalDateTime(), set.getTimestamp(3).toLocalDateTime(),
                        				 set.getBoolean(4), set.getInt(5), set.getInt(6));
            }
            set.close();
        }
        catch(SQLException e)
        {
            System.out.println(e);            
        }
        return session;
    }//end getATMSessionInfo
    
    public int insertATMSession(int machineID, int cardNumber) {
    	ResultSet set;
    	int primaryKey = 0;
    	db.lock();//Lock other threads out of Database until unlock called
    	try {
    		//Plug in all Data for ? in Statement
    		psInsertATMSession.setInt(1, machineID);
    		psInsertATMSession.setInt(2, cardNumber);
    		//Execute Statement, returning ResultSet
    		set = db.executeQuery(psInsertATMSession, false);
    		//If set.next Returns True, then there is a Primary Key to retrieve
    		if(set.next()) {
    			primaryKey = set.getInt(1);
    		}
    		else {
    			System.out.println("ERROR: Failed to Retrieve Primary Key.");
    		}
    		set.close();
    	}
    	catch (SQLException e) {
    		System.out.println(e);
    	}
    	db.unlock();//Release previous Lock
    	return primaryKey;
    }//end InsertATMSession
}//end ATMSessionDA
