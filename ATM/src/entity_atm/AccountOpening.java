package entity_atm;

import java.time.LocalDateTime;

public class AccountOpening {

	private final int customerID;
	private final int accountNumber;
	private final LocalDateTime dateTimeOpened;
	
	public AccountOpening(int cID, int aN, LocalDateTime dTO) {
		this.customerID = cID;
		this.accountNumber = aN;
		this.dateTimeOpened = dTO;
	}

	public int getCustomerID() {
		return customerID;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	/*
	 * This function returns the date the account was opened.
	 * In other words, function is a getter for dateTimeOpened.
	 */
	public LocalDateTime getDateTimeOpened() {
		return dateTimeOpened;
	}
	
	@Override
	public String toString() {
		String str = "Account Open Date: " + this.dateTimeOpened
				   + "\nAccount Number: " + this.accountNumber 
				   + "\nCustomer ID: " + this.customerID + "\n";
		return str;
	}
}//end AccountOpening
