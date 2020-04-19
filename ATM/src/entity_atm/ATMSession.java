package entity_atm;

import java.time.LocalDateTime;

public class ATMSession {

	private int sessionID;
	private LocalDateTime sessionStartTime;
	private LocalDateTime sessionEndTime;
	private boolean sessionActive;
	private int machineID;
	private int cardNumber;
	
	public ATMSession(int sID, LocalDateTime sST, LocalDateTime sET,
						boolean sA, int mID, int cN) {
		this.sessionID = sID;
		this.sessionStartTime = sST;
		this.sessionEndTime = sET;
		this.sessionActive = sA;
		this.machineID = mID;
		this.cardNumber = cN;
	}

	public int getSessionID() {
		return sessionID;
	}

	public LocalDateTime getSessionStartTime() {
		return sessionStartTime;
	}

	public LocalDateTime getSessionEndTime() {
		return sessionEndTime;
	}

	public boolean isSessionActive() {
		return sessionActive;
	}

	public int getMachineID() {
		return machineID;
	}

	public int getCardNumber() {
		return cardNumber;
	}
	
	/* 
	 * Should end the ATMSession as a result of 2 behaviors.
	 * 1. sessionTimeout value is reached/exceeded
	 * 2. User cancels the session by pressing 'Cancel' in GUI
	 * 
	 * This function should assign a value to sessionEndTime
	 */
	public void terminateSession() {
		
	}
	
	@Override
	public String toString() {
		String str = "Session ID: " + this.sessionID
				   + "\nStart Time: " + this.sessionStartTime
				   + "\nEnd Time: " + this.sessionEndTime
				   + "\nCard Number: " + this.cardNumber + "\n";
		return str;
	}
}//end ATMSession
