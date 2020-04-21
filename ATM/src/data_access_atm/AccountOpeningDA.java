package data_access_atm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Database;
import entity_atm.AccountOpening;

public class AccountOpeningDA {
	 
	private Database db;
	private PreparedStatement psGetAccountOpeningInfo;
	private PreparedStatement psInsertAccountOpening;
	    
    public AccountOpeningDA(Database database) throws SQLException {
        this.db = database;
        generateStatements();
    }
	    
    private void generateStatements() throws SQLException {      
        
    	psGetAccountOpeningInfo = db.getDatabase().prepareStatement("SELECT `customerID`, `accountNumber`,"
    							+ " `dateTimeOpening` FROM AccountOpening WHERE `customerID` = ? AND `accountNumber` = ? LIMIT 1;");
    	psInsertAccountOpening = db.getDatabase().prepareStatement("INSERT INTO `AccountOpening` (`customerID`, `accountNumber`, `dateTimeOpening`) VALUES (?, ?, DateTime('now'));");
    }
    
	//There are two foreign keys functioning as composite key (Association Table)
	public AccountOpening getAccountOpeningInfo(int customerID, int accountNumber) {
	    
		AccountOpening acc = null;
	    try {
	        psGetAccountOpeningInfo.setInt(1, customerID);
	        psGetAccountOpeningInfo.setInt(2, accountNumber);
	        ResultSet set = db.executeQuery(psGetAccountOpeningInfo, true);
	        if(set.next()) {                                                          
	            acc = new AccountOpening(set.getInt(1), set.getInt(2), set.getTimestamp(3).toLocalDateTime());
	        }
	        set.close();
	    }
	    catch(SQLException e) {
	        System.out.println(e);    
	    }
	    return acc;
	}//end getAccountOpeningInfo
	
	public int insertAccountOpening(int customerID, int accountNumber) {
		ResultSet set;
		int primaryKey = 0;
		db.lock();
		
		try {
			psGetAccountOpeningInfo.setInt(1, customerID);
			psGetAccountOpeningInfo.setInt(2, accountNumber);
			db.executeStatement(psGetAccountOpeningInfo, false);
			set = psGetAccountOpeningInfo.getGeneratedKeys();
			if(set.next()) {
				primaryKey = set.getInt(1);
			}
			else {
				System.out.println("ERROR: Failed to Retrieve Primary Key.");
			}
			set.close();
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		db.unlock();
		return primaryKey;
	}//end insertAccountOpenning
	
}//end AccountOpeningDA
