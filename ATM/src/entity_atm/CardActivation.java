package entity_atm;

import java.time.LocalDateTime;

public class CardActivation {
	
	private int cardNumber;
	private int accountNumber;
	private LocalDateTime dateTimeActivated;
	
	public CardActivation(int cN, int aN, LocalDateTime dTA) {
		this.cardNumber = cN;
		this.accountNumber = aN;
		this.dateTimeActivated = dTA;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public int getAccountNumber() {
		return accountNumber;
	}
	
	/*
	 * This function returns the date the card was activated.
	 * In other words, function is a getter for dateTimeActivated.
	 */
	public LocalDateTime viewActivationDetails() {
		return dateTimeActivated;
	}
	
	@Override
	public String toString() {
		String str = "Card Number: " + this.cardNumber
				   + "\nAccount Number: " + this.accountNumber
				   + "\nActivation Date: " + this.dateTimeActivated + "\n";
		return str;
	}
}//end CardActivation
