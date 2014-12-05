package finalProject;

import java.util.*;
import java.io.*;

public class ArrayListTest {

	public static void main(String[] args) throws IOException{
		File cip = new File("/Users/Emily/Desktop/cipcs115.txt");
		Scanner canScan = new Scanner(cip);
		Scanner scan = new Scanner(System.in);
		
		ArrayList<Candidate> candidates = new ArrayList<Candidate>();
		ArrayList<Integer> counter = new ArrayList<Integer>();
		String line = "";
		String state;
		int numReps;
		line = canScan.nextLine();
		while(!line.equals("END_OF_FILE")) {
			state = line;
			numReps = canScan.nextInt();
			canScan.nextLine();
			for(int i=0;i<numReps;i++){
				line = canScan.nextLine();
				//calls constructor for candidate in array
				Candidate c = new Candidate();
				//splits line into parts
				String [] parts = line.split("\t");
				//uses mutators to set values from line info
				c.setName(parts[0]);
				c.setOffice(parts[1]);
				c.setParty(parts[2].charAt(0));
				c.setVotes(Integer.parseInt(parts[3]));
				c.setDollarsSpent(Double.parseDouble(parts[4]));
				c.setMotto(parts[5]);
				c.setState(state);
				candidates.add(c);			
			}
			line = canScan.nextLine();
		}
		candidates.trimToSize();
		for (int i = 0; i<8; i++) {
			counter.add(0);
		}
		String userInput = "";
		while (!userInput.equals("menu") && !userInput.equals("final stats")) {
			System.out.println("Menu (m) or final stats (q)?");
			userInput = scan.next();
			if (userInput.equalsIgnoreCase("m")) {
				menu(candidates,counter);
			}
			else if (userInput.equalsIgnoreCase("q")) {
				finalStats(counter);
				break;
			}
			else {
				System.out.println("Try again");
			}
		}
	}
	
	/*The listAll method takes an arrayList of candidates and displays all candidates' names, parties, and mottos*/
	public static void listAll(ArrayList<Candidate> alc) {
		for(int i = 0; i< alc.size(); i++) {
			Candidate oC = alc.get(i);
			System.out.printf( "%-15s %-5s %-15s %n", oC.getName(), oC.getParty(), oC.getMotto());
		}
	}
	
	public static boolean menu(ArrayList<Candidate> alc, ArrayList<Integer> counter) {
		Scanner scan = new Scanner(System.in);
		String input;
		char a = '\0';
		while(a != 'q' && a != 'Q') {
			System.out.println("\tOPTIONS\n Enter L to list all \n Enter C for candidate report \n Enter V for vote report \n Enter S for state report \n Enter D for dollars spent report \n Enter P for party report \n Enter Q to quit");
			input = scan.next();
			if (input.length() != 1) {
				a = 'x';
			}
			else {
				a = input.charAt(0);
			}
			switch (a) {
				case 'L':
				case 'l':
					counter.set(0, counter.get(0)+1);
					listAll(alc);
					break;
				case 'C':
				case 'c':
					counter.set(1, counter.get(1)+1);
					candidateReport(alc);	
					break;
				case 'V':
				case 'v':
					counter.set(2, counter.get(2)+1);
					voteReport(alc);
					break;
				case 'S':
				case 's':
					counter.set(3, counter.get(3)+1);
					stateReport(alc);
					break;
				case 'D':
				case 'd':
					counter.set(4, counter.get(4)+1);
					dollarsSpentReport(alc);
					break;
				case 'P':
				case 'p':
					counter.set(5, counter.get(5)+1);
					partyReport(alc);
					break;
				default:
					if (a=='q' || a=='Q'){
						counter.set(6, counter.get(6)+1);
						break;
					}
					else {
						counter.set(7, counter.get(7)+1);
						System.out.println("Not an option.");
						break;
					}
				}
			}
		return true;
	}
	
	public static boolean candidateReport(ArrayList<Candidate> alc) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a name: ");
		String searchName = scan.nextLine().toLowerCase();
		int counter = 0;
		int iVal = 0;
		for (int i = 0; i<alc.size(); i++) {
			String testName = alc.get(i).getName().toLowerCase();
				if (testName.equals(searchName)) {
					counter++;
					iVal = i;
				}
		}
		if (counter>0){
			alc.get(iVal).display();
			return true;
		}
		else {
			System.out.println("No candidate found");
			return false;
		}
	}
	
	public static boolean voteReport(ArrayList<Candidate> alc) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter state and office (ex. illinois president) OR 'all':");
		String userInput = scan.nextLine().toLowerCase();
		//check for two words
		int spaceCounter = 0;
		for( int i=0; i<userInput.length(); i++ ) {
		    if( userInput.charAt(i) == ' ' ) {
		        spaceCounter++;
		    } 
		}
		if (spaceCounter == 1 || userInput.equals("all")) {
			if (userInput.equals("all")) {
				System.out.println("you typed in all");
			}
			else {
			String [] parts = userInput.split(" ");
			String state = parts[0];
			String office = parts [1];
			int canCounter = 0;
			for (int i = 0; i<alc.size(); i++) {
				Candidate oC = alc.get(i);
					if (oC.getState().toLowerCase().equals(state) && oC.getOffice().toLowerCase().equals(office))	{
						canCounter++;
					}
			}
			//if no instances of state and election race are found in a two word entry, return false
				if (canCounter == 0) {
					System.out.println("No election race found");
				}
				
				if (canCounter > 0) {
					System.out.println(state+ " " +office+ "'s race");
					ArrayList <Candidate> election= new ArrayList<Candidate>();
					double sumVotes = 0;
					for (int i = 0; i<alc.size(); i++) {
						Candidate oC = alc.get(i);
							if (oC.getState().toLowerCase().equals(state) && oC.getOffice().toLowerCase().equals(office))	{
								election.add(oC);
								sumVotes+=oC.getVotes();
							}
					}
					for (int i = 0; i<election.size(); i++) {
						Candidate oC = election.get(i);
						double percentVotes = oC.getVotes()/sumVotes*100;
						System.out.printf("%-19s %-11s %-27.3f %n", oC.getName(), oC.getVotes(), percentVotes);
					}
				}
				
			}
		}
		
		else {
			System.out.println("No election race found");
		}
		return true;
	}
	
	public static boolean stateReport(ArrayList<Candidate> alc) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter state OR 'all':");
		String userInput = scan.nextLine().toLowerCase();
		String state = "";
		ArrayList <String> states = new ArrayList<String>();
		for (int i = 0; i<alc.size(); i++) {
			if (alc.get(i).getState().equalsIgnoreCase(state)==false) {
				state = alc.get(i).getState().toLowerCase();
				states.add(state);
			}
		}
		System.out.println(states.size());
		states.trimToSize();
		if (userInput.equals("all")) {
			int count = 0;
			for(int i = 0; i<states.size();i++) {
				String thisState = states.get(i);
				double tDollarsSpent = 0;
				System.out.println("\n"+thisState.toUpperCase());
				for (int j = 0; j<alc.size(); j++) {
					if(alc.get(j).getState().equalsIgnoreCase(thisState)){
						System.out.printf("%-19s %-15s %-6s %14s %n", alc.get(j).getName(), alc.get(j).getOffice(), alc.get(j).getParty(), alc.get(j).getDollarsSpent());
						tDollarsSpent += alc.get(j).getDollarsSpent();
						count++;
					}
				}
				System.out.printf("Total dollars spent: $%.2f %n", tDollarsSpent);
				System.out.printf("Average dollars spent: $%.2f %n", (tDollarsSpent/count));
			}
			return true;
		}
		else if (states.contains(userInput)) {
			double tDollarsSpent = 0;
			int count = 0;
			for (int i = 0; i<alc.size(); i++) {
					if(alc.get(i).getState().toLowerCase().equals(userInput)) {
						System.out.printf("%-19s %-15s %-6s %14s %n", alc.get(i).getName(), alc.get(i).getOffice(), alc.get(i).getParty(), alc.get(i).getDollarsSpent());
						tDollarsSpent += alc.get(i).getDollarsSpent();
						count++;
				}
			}
			System.out.printf("Total dollars spent: $%.2f %n", tDollarsSpent);
			System.out.printf("Average dollars spent: $%.2f %n", (tDollarsSpent/count));
			return true;
		}
		else {
			return false;
		}
		}
	
	public static boolean dollarsSpentReport(ArrayList<Candidate> alc) {
		ArrayList <Candidate> democrats = new ArrayList<Candidate>();
		ArrayList <Candidate> republicans = new ArrayList<Candidate>();
		ArrayList <Candidate> independent = new ArrayList<Candidate>();
		ArrayList <Candidate> other = new ArrayList<Candidate>();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the candidate name OR 'all': ");
		String searchName = scan.nextLine().toLowerCase();
		//sorting by parties
		for (int i = 0; i<alc.size(); i++) {
			char party = alc.get(i).getParty();
			switch (party) {
			case 'd':
				democrats.add(alc.get(i));
				break;
			case 'r':
				republicans.add(alc.get(i));
				break;
			case 'i':
				independent.add(alc.get(i));
				break;
			case 'o':
				other.add(alc.get(i));
				break;
			}
		}	
		if (searchName.equals("all")) {
			
			System.out.println("\nDemocrat Spending:");
			for (int i = 0; i<democrats.size(); i++) {
				Candidate oC = democrats.get(i);
				System.out.printf("%-19s %-15s %-6s %14s %n", oC.getName(),oC.getOffice(),oC.getParty(),oC.getDollarsSpent());
			}
			System.out.println("\nRepublican Spending:");
			for (int i = 0; i<republicans.size(); i++) {
				Candidate oC = republicans.get(i);
				System.out.printf("%-19s %-15s %-6s %14s %n", oC.getName(),oC.getOffice(),oC.getParty(),oC.getDollarsSpent());
			}
			System.out.println("\nIndependent Spending:");
			for (int i = 0; i<independent.size(); i++) {
				Candidate oC = independent.get(i);
				System.out.printf("%-19s %-15s %-6s %14s %n", oC.getName(),oC.getOffice(),oC.getParty(),oC.getDollarsSpent());
			}
			System.out.println("\nOther Spending:");
			for (int i = 0; i<other.size(); i++) {
				Candidate oC = other.get(i);
				System.out.printf("%-19s %-15s %-6s %14s %n", oC.getName(),oC.getOffice(),oC.getParty(),oC.getDollarsSpent());
			}
			return true;
		}
		int counter = 0;
		int iVal = 0;
		for (int i = 0; i<alc.size(); i++) {
			String testName = alc.get(i).getName().toLowerCase();
				if (testName.equals(searchName)) {
					counter++;
					iVal = i;
				}
		}
		if (counter>0){
			Candidate oC = alc.get(iVal);
			System.out.printf("%-19s %-15s %-6s %14s %n", oC.getName(),oC.getOffice(),oC.getParty(),oC.getDollarsSpent());
			return true;
		}
		else {
			System.out.println("No candidate found");
			return false;
		}
	}
	
	public static boolean partyReport(ArrayList<Candidate> alc) {
		ArrayList <Candidate> democrats = new ArrayList<Candidate>();
		ArrayList <Candidate> republicans = new ArrayList<Candidate>();
		ArrayList <Candidate> independent = new ArrayList<Candidate>();
		ArrayList <Candidate> other = new ArrayList<Candidate>();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter party you'd like to search OR 'all': ");
		String searchName = scan.nextLine().toLowerCase();
		//sorting by parties
		for (int i = 0; i<alc.size(); i++) {
			char party = alc.get(i).getParty();
			switch (party) {
			case 'd':
				democrats.add(alc.get(i));
				break;
			case 'r':
				republicans.add(alc.get(i));
				break;
			case 'i':
				independent.add(alc.get(i));
				break;
			case 'o':
				other.add(alc.get(i));
				break;
			}
		}	
		if (searchName.equals("all")) {
			
			System.out.println("Democrats:");
			for (int i = 0; i<democrats.size(); i++) {
				Candidate oC = democrats.get(i);
				System.out.printf("%-19s %-15s %-10s %n", oC.getName(),oC.getOffice(),oC.getState());
			}
			System.out.println("Republicans:");
			for (int i = 0; i<republicans.size(); i++) {
				Candidate oC = republicans.get(i);
				System.out.printf("%-19s %-15s %-10s %n", oC.getName(),oC.getOffice(),oC.getState());
			}
			System.out.println("Independents:");
			for (int i = 0; i<independent.size(); i++) {
				Candidate oC = independent.get(i);
				System.out.printf("%-19s %-15s %-10s %n", oC.getName(),oC.getOffice(),oC.getState());
			}
			System.out.println("Others:");
			for (int i = 0; i<other.size(); i++) {
				Candidate oC = other.get(i);
				System.out.printf("%-19s %-15s %-10s %n", oC.getName(),oC.getOffice(),oC.getState());
			}
			return true;
		}
		char partyLetter = searchName.charAt(0);
		String partyName = " ";
		ArrayList <Candidate> sortParameter;
		switch (partyLetter){
		case 'd':
			sortParameter = democrats;
			partyName = "Democrats";
			break;
		case 'r':
			sortParameter = republicans;
			partyName = "Republicans";
			break;
		case 'i':
			sortParameter = independent;
			partyName = "Independents";
			break;
		case 'o':
			partyName = "Others";
			sortParameter = other;
			break;
		default:
			System.out.println("No party found");
			return false;
		}
		if (partyName != " ") {
		System.out.println(partyName);
		for (int i = 0; i<sortParameter.size(); i++) {
			Candidate oC = sortParameter.get(i);
			System.out.printf("%-19s %-15s %-10s %n", oC.getName(),oC.getOffice(),oC.getState());
		}
		}
		return true;
		
	}
	
	public static boolean finalStats(ArrayList<Integer>counter) {
		System.out.println("Final Stats:");
		System.out.printf("%-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %n", 'L', 'C', 'V', 'S', 'D', 'P', 'Q', "Bad");
		System.out.printf("%-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s", counter.get(0), counter.get(1), counter.get(2),counter.get(3), counter.get(4), counter.get(5), counter.get(6), counter.get(7));
		return true;
	}
}
