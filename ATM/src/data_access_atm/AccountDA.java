package data_access_atm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.Database;
import entity_atm.Account;

public class AccountDA {
	
	private Database db;
	private PreparedStatement psGetAccountInfo;
	private PreparedStatement psInsertSavings;
	private PreparedStatement psInsertChecking;
	
	public AccountDA(Database database) throws SQLException {
		this.db = database;
		generateStatements();
	}
	
	private void generateStatements() throws SQLException {
		
		 psGetAccountInfo = db.getDatabase().prepareStatement("SELECT `accountNumber`, `accountStatus`, "
		 				  										+ "`accountBal`, `accountType`, `interestRate`, `minReqBalance` "
		 				  										+ "FROM Account WHERE `accountNumber` = ? LIMIT 1;");
		 psInsertSavings = db.getDatabase().prepareStatement("INSERT INTO `Account` (`accountStatus`, `accountBal`, `accountType`, `interestRate`)"
		 														+ "VALUES(1, 0.0, 0, 0.5);");
		 psInsertChecking = db.getDatabase().prepareStatement("INSERT INTO `Account` (`accountStatus`,`accountBal`,`accountType`,`minReqBalance`) "
		 														+ "VALUES(1, 0.0, 1, 100.0);");
	}
	
	public Account getAccountInfo(int accountNumber) {
		
		//Returns null if Account with given accountNumber DNE
		Account acc = null;
		try {
			psGetAccountInfo.setInt(1, accountNumber);
			ResultSet set = db.executeQuery(psGetAccountInfo, true);
			
			if(set.next()) {
				acc = new Account(set.getInt(1), set.getBoolean(2), set.getDouble(3), set.getInt(4),
									set.getDouble(5), set.getDouble(6));
			}
			set.close();
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		return acc;
	}//end getAccountInfo
	
	public int insertCheckingAcc() {
		
		ResultSet set;
		int primaryKey = 0;
		db.lock();
		
		try {
			db.executeStatement(psInsertChecking, false);
			set = psInsertChecking.getGeneratedKeys();
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
	}//end insertCheckingAcc	
	
	public int insertSavingsAcc() {
		
		ResultSet set;
		int primaryKey = 0;
		db.lock();
		
		try {
			db.executeStatement(psInsertSavings, false);
			set = psInsertSavings.getGeneratedKeys();
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
	}//end insertCheckingAcc
}//end AccountDA
