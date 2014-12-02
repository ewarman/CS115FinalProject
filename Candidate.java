package finalProject;

public class Candidate {

	private String nameOfCandidate;
	private String electionOffice;
	private char party;
	private int votes;
	private double dollarsSpent;
	private String motto;
	private String state;
	
	//constructor
	public Candidate() {
		nameOfCandidate = "none";
		electionOffice = "none";
		party = '\0';
		votes = 0;
		dollarsSpent = 0;
		motto = "none";
		state = "none";
	}

	//accessors
	public String getName() {
		return nameOfCandidate;
	}
	
	public String getOffice() {
		return electionOffice;
	}
	
	public char getParty() {
		return party;
	}
	
	public int getVotes() {
		return votes;
	}
	
	public double getDollarsSpent() {
		return dollarsSpent;
	}
	
	public String getMotto() {
		return motto;
	}
	
	public String getState() {
		return state;
	}
	
	//mutators
	public void setName(String n) {
		nameOfCandidate = n;
	}
	
	public void setOffice(String o) {
		electionOffice = o;
	}
	
	public void setParty(char p) {
		party = p;
	}
	
	public void setVotes(int v) {
		votes = v;
	}
	
	public void setDollarsSpent(double d) {
		dollarsSpent = d;
	}
	
	public void setMotto(String m) {
		motto = m;
	}
	
	public void setState(String s) {
		state = s;
	}
	
	//toString method
	public String toString() {
		return ("Name: " +nameOfCandidate+ "\n"+"Office: " +electionOffice+ "\n"+"Party: " +party+ "\n"+"Dollars Spent: " +dollarsSpent+ "\n"+"Votes: " +votes+ "\n"+"Motto: " +motto);
	}
	
	//display method
	public void display() {
		System.out.println(nameOfCandidate+"\t"+electionOffice+"\t"+party+"\t"+dollarsSpent+"\t"+votes+"\t"+motto);
	}
	
	//equals method
	public boolean equals(Object c) {
		if (!( c instanceof Candidate)) {
			return false;
		}
		else {
			Candidate can = (Candidate)c;
			if (nameOfCandidate.equals(can.getName()) && electionOffice.equals(can.getOffice()) && party==can.party && votes==can.votes && dollarsSpent==can.dollarsSpent && motto.equals(can.getMotto()))
				return true;
			else
				return false;
			}
		
	}
	
}