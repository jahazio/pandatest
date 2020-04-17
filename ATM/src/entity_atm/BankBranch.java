package entity_atm;

public class BankBranch {
	
	private final int branchNumber;
	private final String branchAddress;
	
	public BankBranch(int bN, String bA) {
		this.branchNumber = bN;
		this.branchAddress = bA;
	}

	public int getBranchNumber() {
		return branchNumber;
	}

	public String getBranchAddress() {
		return branchAddress;
	}
	
	@Override
	public String toString() {
		String str = "Branch Number: " + this.branchNumber
				   + "\nBranch Address: " + this.branchAddress + "\n";
		return str;
	}
}//end BankBranch
