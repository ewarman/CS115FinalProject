package finalProject;

public class CandidateTest {

	public static void main(String [] args) {
		// TODO Auto-generated method stub
		Candidate emily = new Candidate();
		emily.setName("Emily");
		emily.setOffice("Office");
		emily.setDollarsSpent(1000000.99);
		emily.setParty('o');
		emily.setVotes(300);
		emily.setMotto("Go fuck yourself");
		
		System.out.println(emily.getName());
		System.out.println(emily.getOffice());
		System.out.println(emily.getDollarsSpent());
		System.out.println(emily.getParty());
		System.out.println(emily.getVotes());
		System.out.println(emily.getMotto());
		
		System.out.println(emily.display());
		
		Candidate henry = new Candidate();
		henry.setName("Emily");
		henry.setOffice("Office");
		henry.setDollarsSpent(1000000.99);
		henry.setParty('o');
		henry.setVotes(300);
		henry.setMotto("Go fuck yourself");
		System.out.println(henry.compareTo(emily));
	}
}
