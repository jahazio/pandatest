package data_access_atm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Database;
import entity_atm.AccountOpening;

public class AccountOpeningDA {
	 
	private Database db;
	private PreparedStatement psGetAccountOpeningInfo;
	    
    public AccountOpeningDA(Database database) throws SQLException {
        this.db = database;
        generateStatements();
    }
	    
    private void generateStatements() throws SQLException {      
        
    	psGetAccountOpeningInfo = db.getDatabase().prepareStatement("SELECT `customerID`, `accountNumber`,"
    							+ " `dateTimeOpening` FROM AccountOpening WHERE `customerID` = ? AND `accountNumber` = ? LIMIT 1;");
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
}//end AccountOpeningDA
