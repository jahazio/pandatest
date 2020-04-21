package testing;

import java.sql.SQLException;

import data_access_atm.ATMDA;
import data_access_atm.AccountDA;
import data_access_atm.BankBranchDA;
import database.Database;

public class DatabaseTest {

	public static void main(String[] args) throws SQLException {
		//Initialize Database
				Database testDB = new Database();
				
				//Testing BankBranch Data Access/Entity
				BankBranchDA bankBranchTestDA = new BankBranchDA(testDB);
				System.out.println("Creating Bank Branch... ");
				int bankBranch = bankBranchTestDA.insertBankBranch("3801 W Temple Ave, Pomona, CA 91768");
				System.out.println("Bank Branch Info: " + bankBranchTestDA.getBankBranchInfo(bankBranch));
				
				//Testing ATM Data Access/Entity
				ATMDA atmTestDA = new ATMDA(testDB);
				System.out.println("Creating ATM... ");
				int atm_one = atmTestDA.insertATM("3801 W Temple Ave, Pomona, CA 91768 - Bldg. 9 West Entrance", bankBranch);
				System.out.println("ATM Info: " + atmTestDA.getATMInfo(atm_one));
				
				//Testing Account Data Access/Entity
				AccountDA accountTestDA = new AccountDA(testDB);
				System.out.println("Creating Checking... ");
				int checking_acc = accountTestDA.insertCheckingAcc();
				System.out.println("Checking Info: " + accountTestDA.getAccountInfo(checking_acc));
				System.out.println("Creating Savings... ");
				int savings_acc = accountTestDA.insertSavingsAcc();
				System.out.println("Savings Info: " + accountTestDA.getAccountInfo(savings_acc));
				
				//Testing ATM Data Access/Entity
				ATMDA atmTestDA = new ATMDA(testDB);
				System.out.println("Creating ATM... ");
				int atm_one = atmTestDA.insertATM("3801 W Temple Ave, Pomona, CA 91768 - Bldg. 9 West Entrance", bankBranch);
				System.out.println("ATM Info: " + atmTestDA.getATMInfo(atm_one));
	}

}
