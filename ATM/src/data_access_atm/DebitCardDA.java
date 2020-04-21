package data_access_atm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import database.Database;
import entity_atm.DebitCard;

public class DebitCardDA 
{
    private Database db;
    private PreparedStatement psGetDebitCardInfo;
    private PreparedStatement psInsertDebitCard;
    
    public DebitCardDA(Database database) throws SQLException{
        this.db = database;
        generateStatements();
    }
    
    private void generateStatements() throws SQLException {
        psGetDebitCardInfo = db.getDatabase().prepareStatement("SELECT `cardNumber`, `cardHolderName`, `cardExpDate`, `pinNumber`,"
                     	   + "`customerID`, `branchNumber` FROM `DebitCard` WHERE `cardNumber` = ? LIMIT 1;");
        psInsertDebitCard = db.getDatabase().prepareStatement("INSERT INTO `` (`cardHolderName`, `cardExpDate`, `pinNumber`, `customerID`, `branchNumber`) "
        				  + "VALUES (?, ?, ?, ?, ?);");
    }
    
    public DebitCard getDebitCardInfo(int cardNumber){
        DebitCard d = null;
        try {
            psGetDebitCardInfo.setInt(1, cardNumber);
            ResultSet set = db.executeQuery(psGetDebitCardInfo, true);
            if(set.next())
            {
                d = new DebitCard(set.getInt(1), set.getString(2), set.getTimestamp(3).toLocalDateTime(), set.getInt(4), set.getInt(5),
                        set.getInt(6));
            }
            set.close();
        }
        catch(SQLException e)
        {
            System.out.println(e);            
        }
        return d;
    }//end getDebitCardInfo
    
    public int insertDebitCardInfo(String cardHolderName, LocalDateTime cardExpDate, int pin, int customerID, int branchNumber) {
		ResultSet set;
		int primaryKey = 0;
		db.lock();
		
		try {
			psInsertDebitCard.setString(1, cardHolderName);
			psInsertDebitCard.setTimestamp(2, Timestamp.valueOf(cardExpDate));
			psInsertDebitCard.setInt(3, pin);
			psInsertDebitCard.setInt(4, customerID);
			psInsertDebitCard.setInt(5, branchNumber);
			db.executeStatement(psInsertDebitCard, false);
			set = psInsertDebitCard.getGeneratedKeys();
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
	}//end insertDebitCard
}//end DebitCardDA
