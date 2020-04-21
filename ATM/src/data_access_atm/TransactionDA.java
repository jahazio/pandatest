package data_access_atm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Database;
import entity_atm.Transaction;

public class TransactionDA {

	private Database db;
	private PreparedStatement psGetTransactionInfo;
	private PreparedStatement psInsertWithdrawlTransaction;// 0
	private PreparedStatement psInsertDepositTransaction;// 1
	private PreparedStatement psInsertTransferTransaction;// 2

	public TransactionDA(Database database) throws SQLException {
		this.db = database;
		generateStatements();
	}

	private void generateStatements() throws SQLException {
		psGetTransactionInfo = db.getDatabase().prepareStatement("SELECT `transactionID`, `timeDateOfTrans`, `transactionType`"
							 + "`amount`, `targetAccNumber`, `sessionID`, `accountNumber` FROM Transaction WHERE `transactionID` = ? LIMIT 1;");

		psInsertWithdrawlTransaction = db.getDatabase().prepareStatement("INSERT INTO `Transaction` (`timeDateofTrans`, `transactionType`, "
									 + " `amount`, `targetAccNumber`, `sessionID`, `accountNumber`) VALUES (DateTime('now'),0, ?, null, ?, ? );");

		psInsertDepositTransaction = db.getDatabase().prepareStatement("INSERT INTO `Transaction` (`timeDateofTrans`, `transactionType`, "
								   + " `amount`, `targetAccNumber`, `sessionID`, `accountNumber`) VALUES (DateTime('now'),1, ?, null, ?, ? );");

		psInsertTransferTransaction = db.getDatabase().prepareStatement("INSERT INTO `Transaction` (`timeDateOfTrans`, `transactionType`,"
									+ "`amount`, `targetAccNumber`, `sessionID`, `accountNumber`) VALUES (DateTime('now'), 2, ?, ?, ?, ?);");

	}

	public Transaction getTransactionInfo(int transactionID) {
		
		Transaction trans = null;
		try {
			psGetTransactionInfo.setInt(1, transactionID);
			ResultSet set = db.executeQuery(psGetTransactionInfo, true);
			if (set.next()) { // i think time stamp is date time
				trans = new Transaction(set.getInt(1), set.getTimestamp(2).toLocalDateTime(), set.getInt(3),
						set.getDouble(4), set.getInt(5), set.getInt(6), set.getInt(7));
			}
			set.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return trans;
	}

	public int InsertWithdrawlTransaction(int amount, int sessionID, int accountNumber) {
		
		ResultSet set;
		int withdrawlKey = 0;
		db.lock(); // Lock other threads out of the DB until we unlock it
		try {
			psInsertWithdrawlTransaction.setInt(1, amount);
			psInsertWithdrawlTransaction.setInt(2, sessionID);
			psInsertWithdrawlTransaction.setInt(3, accountNumber);

			set = db.executeQuery(psInsertTransferTransaction, false);
			if (set.next()) {
				withdrawlKey = set.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: Failed to Retrieve the Primary Key");
		}
		return withdrawlKey;
	}

	public int InsertDepositTransaction(int amount, int sessionID, int accountNumber) {
		
		ResultSet set;
		int depositKey = 0;
		db.lock(); // Lock other threads out of the DB until we unlock it
		try {
			psInsertDepositTransaction.setInt(1, amount);
			psInsertDepositTransaction.setInt(2, sessionID);
			psInsertDepositTransaction.setInt(3, accountNumber);
			set = db.executeQuery(psInsertTransferTransaction, false);
			if (set.next()) {
				depositKey = set.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: Failed to Retrieve the Primary Key");
		}
		db.unlock();
		return depositKey;
	}

	public int InsertTransferTransaction(int amount, int targetAccNumber, int sessionID, int accountNumber) {
		
		ResultSet set;
		int transferKey = 0;
		db.lock(); // Lock other threads out of the DB until we unlock it
		try {
			psInsertTransferTransaction.setInt(1, amount);
			psInsertTransferTransaction.setInt(2, targetAccNumber);
			psInsertTransferTransaction.setInt(3, sessionID);
			psInsertTransferTransaction.setInt(4, accountNumber);
			set = db.executeQuery(psInsertTransferTransaction, false);
			if (set.next()) {
				transferKey = set.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: Failed to Retrieve the Primary Key");
		}
		db.unlock();
		return transferKey;
	}
}//end TransactionDA