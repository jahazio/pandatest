package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

import data_access_atm.AccountDA;

public class Database {

	private Connection connection;
	private ReentrantLock lock;
	
	public Database() {
		
		String connectionPath = "jdbc:sqlite:" + System.getProperty("user.home") 
							  + File.separator + "ATMSubsystem.db";  
		this.lock = new ReentrantLock();
		
		try
		{
		    connection = DriverManager.getConnection(connectionPath);
		    System.out.println("Opened Local Database");
		    connection.setAutoCommit(false);
		}
		catch (Exception e)
		{
			System.out.println(e);
		    System.exit(20);
		}
		
		this.lock.lock();
		
		try (Statement stmt = connection.createStatement();)
		{
		    generateTablesAndTriggers(stmt);
		    connection.commit();
		    System.out.println("Database Initalized");
		}
		catch (SQLException e)
		{
			System.out.println(e);
		    try
		    {
		        connection.rollback();
		    }
		    catch (SQLException e1)
		    {
		    	System.out.println(e1);
		    }
		}
		finally
		{
		    try
		    {
		        connection.setAutoCommit(true);
		    }
		    catch (SQLException e)
		    {
		    	System.out.println(e);
		    }
		}
		
		this.lock.unlock();
		
	}//end Constructor
	
	private static void generateTablesAndTriggers(Statement stmt) throws SQLException {
		//ATM Table Generation
		stmt.executeUpdate( "CREATE TABLE IF NOT EXISTS `ATM` (" + 
							"  `machineID` INT NOT NULL," + 
							"  `machineAddress` VARCHAR(45) NULL," + 
							"  `currentFundsAvail` DOUBLE NOT NULL," + 
							"  `sessionTimeOut` INT NOT NULL," + 
							"  `sessionActive` TINYINT NOT NULL," + 
							"  `minCashAllowed` DOUBLE NOT NULL," + 
							"  `maxCashCapacity` DOUBLE NOT NULL," + 
							"  `maxPinEntryAttempts` INT NOT NULL," + 
							"  `branchNumber` INT NOT NULL," + 
							"  PRIMARY KEY (`machineID`)," + 
							"  CONSTRAINT `ATM_BankBranch`" + 
							"  FOREIGN KEY (`branchNumber`)" + 
							"  REFERENCES `BankBranch` (`branchNumber`)" + 
							"  ON DELETE RESTRICT" + 
							"  ON UPDATE CASCADE)");

		//AccountOpening Table Generation
		stmt.executeUpdate( "CREATE TABLE IF NOT EXISTS `AccountOpening` (" + 
							"  `customerID` INT NOT NULL," + 
							"  `accountNumber` INT NOT NULL," + 
							"  `dateTimeOpening` DATETIME NOT NULL," + 
							"  PRIMARY KEY (`customerID`, `accountNumber`)," +  
							"  CONSTRAINT `AccountOpening_Client`" + 
							"    FOREIGN KEY (`customerID`)" + 
							"    REFERENCES `Client` (`customerID`)" + 
							"    ON DELETE RESTRICT" + 
							"    ON UPDATE CASCADE," + 
							"  CONSTRAINT `AccountOpening_Account`" + 
							"    FOREIGN KEY (`accountNumber`)" + 
							"    REFERENCES `Account` (`accountNumber`)" + 
							"    ON DELETE RESTRICT" + 
							"    ON UPDATE CASCADE);");
		
		//Account Table Generation
		stmt.executeUpdate( "CREATE TABLE IF NOT EXISTS `Account` (" + 
							"  `accountNumber` INTEGER NOT NULL," + 
							"  `accountStatus` TINYINT NULL," + 
							"  `accountBal` DOUBLE NOT NULL," + 
							"  `accountType` INT NOT NULL," + 
							"  `interestRate` DOUBLE NULL," + 
							"  `minReqBalance` DOUBLE NULL," + 
							"  PRIMARY KEY (`accountNumber`));");
		
		//ATMSession Table Generation
		stmt.executeUpdate( "CREATE TABLE IF NOT EXISTS `ATMSession` (" + 
							"  `sessionID` INT NOT NULL," + 
							"  `sessionStartTime` DATETIME NULL," + 
							"  `sessionEndTime` DATETIME NULL," + 
							"  `sessionActive` TINYINT NOT NULL," + 
							"  `machineID` INT NOT NULL," + 
							"  `cardNumber` INT NULL," + 
							"  PRIMARY KEY (`sessionID`)," + 
							"  CONSTRAINT `ATMSession_ATM`" + 
							"    FOREIGN KEY (`machineID`)" + 
							"    REFERENCES `ATM` (`machineID`)" + 
							"    ON DELETE CASCADE" + 
							"    ON UPDATE CASCADE," + 
							"  CONSTRAINT `ATMSession_DebitCard`" + 
							"    FOREIGN KEY (`cardNumber`)" + 
							"    REFERENCES `DebitCard` (`cardNumber`)" + 
							"    ON DELETE SET NULL" + 
							"    ON UPDATE CASCADE);");
		
		//BankBranch Table Generation
		stmt.executeUpdate( "CREATE TABLE IF NOT EXISTS `BankBranch` (" + 
							"  `branchNumber` INT NOT NULL," + 
							"  `branchAddress` VARCHAR(45) NOT NULL," + 
							"  PRIMARY KEY (`branchNumber`));");
		
		//CardActivation Table Generation
		stmt.executeUpdate( "CREATE TABLE IF NOT EXISTS `CardActivation` (" + 
							"  `cardNumber` INT NULL," + 
							"  `accountNumber` INT NULL," + 
							"  `dateTimeActivated` DATETIME NOT NULL," + 
							"  PRIMARY KEY (`cardNumber`, `accountNumber`)," + 
							"  CONSTRAINT `CardActivation_DebitCard`" + 
							"    FOREIGN KEY (`cardNumber`)" + 
							"    REFERENCES `DebitCard` (`cardNumber`)" + 
							"    ON DELETE SET NULL" + 
							"    ON UPDATE RESTRICT," + 
							"  CONSTRAINT `CardActivation_Account`" + 
							"    FOREIGN KEY (`accountNumber`)" + 
							"    REFERENCES `Account` (`accountNumber`)" + 
							"    ON DELETE RESTRICT" + 
							"    ON UPDATE CASCADE);");
		
		//Client Table Generation
		stmt.executeUpdate( "CREATE TABLE IF NOT EXISTS `Client` (" + 
							"  `customerID` INT NOT NULL," + 
							"  `customerName` VARCHAR(45) NOT NULL," + 
							"  `customerAddress` VARCHAR(45) NOT NULL," + 
							"  `customerTel` VARCHAR(45) NOT NULL," + 
							"  `customerDob` DATETIME NOT NULL," + 
							"  `branchNumber` INT NOT NULL," + 
							"  PRIMARY KEY (`customerID`)," + 
							"  CONSTRAINT `Client_BankBranch`" + 
							"    FOREIGN KEY (`branchNumber`)" + 
							"    REFERENCES `BankBranch` (`branchNumber`)" + 
							"    ON DELETE RESTRICT" + 
							"    ON UPDATE CASCADE);");
		
		//DebitCard Table Generation
		stmt.executeUpdate( "CREATE TABLE IF NOT EXISTS `DebitCard` (" + 
							"  `cardNumber` INT NOT NULL," + 
							"  `cardHolderName` VARCHAR(45) NOT NULL," + 
							"  `cardExpDate` DATETIME NOT NULL," + 
							"  `pinNumber` INT NOT NULL," + 
							"  `customerID` INT NOT NULL," + 
							"  `branchNumber` INT NOT NULL," + 
							"  PRIMARY KEY (`cardNumber`)," + 
							"  CONSTRAINT `DebitCard_Client`" + 
							"    FOREIGN KEY (`customerID`)" + 
							"    REFERENCES `Client` (`customerID`)" + 
							"    ON DELETE RESTRICT" + 
							"    ON UPDATE CASCADE," + 
							"  CONSTRAINT `DebitCard_BankBranch`" + 
							"    FOREIGN KEY (`branchNumber`)" + 
							"    REFERENCES `BankBranch` (`branchNumber`)" + 
							"    ON DELETE RESTRICT" + 
							"    ON UPDATE CASCADE);");
		
		//Transaction Table Generation
		stmt.executeUpdate( "CREATE TABLE IF NOT EXISTS `Transaction` (" + 
							"  `transactionID` INT NOT NULL," + 
							"  `timeDateOfTrans` DATETIME NOT NULL," + 
							"  `transactionType` INT NOT NULL," + 
							"  `amount` DOUBLE NOT NULL," + 
							"  `targetAccNumber` INT NULL," + 
							"  `sessionID` INT NOT NULL," + 
							"  `accountNumber` INT NOT NULL," + 
							"  PRIMARY KEY (`transactionID`)," + 
							"  CONSTRAINT `Transaction_ATMSession`" + 
							"    FOREIGN KEY (`sessionID`)" + 
							"    REFERENCES `ATMSession` (`sessionID`)" + 
							"    ON DELETE RESTRICT" + 
							"    ON UPDATE CASCADE," + 
							"  CONSTRAINT `Transaction_Account`" + 
							"    FOREIGN KEY (`accountNumber`)" + 
							"    REFERENCES `Account` (`accountNumber`)" + 
							"    ON DELETE RESTRICT" + 
							"    ON UPDATE CASCADE);");
	}//end generateTablesAndTriggers
	
	public ResultSet executeQuery(PreparedStatement stmt, boolean useLock) throws SQLException {
		
		if(useLock) {
			this.lock.lock();	
		}
		ResultSet set;
		try {
			set = stmt.executeQuery();
		}
		catch (SQLException e) {
			this.lock.unlock();
			throw e;
		}
		if(useLock) {
			this.lock.unlock();	
		}
		return set;
	}//end executeQuery
	
	public void executeStatement(PreparedStatement stmt, boolean useLock) throws SQLException {
		
		if(useLock) {
			this.lock.lock();	
		}
		try {
			  stmt.executeUpdate();
			}
		catch (SQLException e) {
		  this.lock.unlock();
		  throw e;
		}
		if(useLock) {
			this.lock.unlock();	
		}
	}//end executeStatement
	
	public Connection getDatabase() {
		return this.connection;
	}
	
	public void lock() {
		this.lock.lock();
	}
	
	public void unlock() {
		this.lock.unlock();
	}
	
	//Main Method to Test Entire Database
	public static void main(String[] args) throws SQLException {
		
		//Initialize Database
		Database testDB = new Database();
		
		//Testing Account Data Access/Entity
		AccountDA accountTestDA = new AccountDA(testDB);
		System.out.println("Checking: " + accountTestDA.insertCheckingAcc());
		System.out.println("Savings: " + accountTestDA.insertSavingsAcc());
		System.out.println("Account Info: " + accountTestDA.getAccountInfo(5));
	}
}//end Database
