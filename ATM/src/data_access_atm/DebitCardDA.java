package data_access_atm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

public class DebitCardDA {

	public int generateCardNumber(String table, String field) {
		
		boolean exists = true;
		int primaryKey = 0;
		ResultSet set;
		
		while(exists) {
			try {	
				primaryKey = ThreadLocalRandom.current().nextInt(0, 1048576);
				psGetCardNumber.setInt(1, primaryKey);
				set = this.executeQuery(psGetCardNumber);
				if(!set.next()) {
					exists = false;
				}
				set.close();
			}
			catch(SQLException e) {
				System.out.println(e);
			}
		}
		return primaryKey;
	}
}
