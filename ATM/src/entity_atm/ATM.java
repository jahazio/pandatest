package entity_atm;

public class ATM {
	
	private final int machineID;
	private final String machineAddress;
	private final double currentFundsAvail;
	private final int sessionTimeOut;
	private final boolean sessionActive;
	private final double minCashAllowed;
	private final double maxCashCapacity;
	private final int maxPinEntryAttempts;
	private final int branchNumber;
	
	public ATM(int mID, String mA, double cFA, int sTO, boolean sA,
				double mCA, double mCC, int mPEA, int bN) {
		this.machineID = mID;
		this.machineAddress = mA;
		this.currentFundsAvail = cFA;
		this.sessionTimeOut = sTO;
		this.sessionActive = sA;
		this.minCashAllowed = mCA;
		this.maxCashCapacity = mCC;
		this.maxPinEntryAttempts = mPEA;
		this.branchNumber = bN;
	}
	
	public int getMachineID() {
		return machineID;
	}
	public String getMachineAddress() {
		return machineAddress;
	}
	public double getCurrentFundsAvail() {
		return currentFundsAvail;
	}
	public int getSessionTimeOut() {
		return sessionTimeOut;
	}
	public boolean isSessionActive() {
		return sessionActive;
	}
	public double getMinCashAllowed() {
		return minCashAllowed;
	}
	public double getMaxCashCapacity() {
		return maxCashCapacity;
	}
	public int getMaxPinEntryAttempts() {
		return maxPinEntryAttempts;
	}
	public int getBranchNumber() {
		return branchNumber;
	}
	
	@Override
	public String toString() {
		String str = "Machine ID: " + this.machineID
				   + "\nMachine Address: " + this.machineAddress
				   + "\nCurrent Funds Available: " + this.currentFundsAvail
				   + "\nSession Active: " + this.sessionActive + "\n";
		return str;
	}
}//end ATM
