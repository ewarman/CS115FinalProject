package finalProject;

import java.util.*;
import java.io.*;

public class ArrayListTest {

	public static void main(String[] args) throws IOException{
			
		File cip = new File("/Users/Emily/Desktop/cipcs115.txt");
		Scanner canScan = new Scanner(cip);
		
		ArrayList<Candidate> candidates = new ArrayList<Candidate>();
		String line = "";
		int numCandidate = 0;
		while(!line.equals("END_OF_FILE")) {
			line = canScan.nextLine();
			if (line.contains("\t")) {
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
				candidates.add(c);
				numCandidate++;
			}
		}
		
		//calling static methods in main example
		//listAll(candidates);
		menu(candidates);
	}
	
	public static void listAll(ArrayList<Candidate> alc) {
		for(int i = 0; i< alc.size(); i++) {
			Candidate oC = alc.get(i);
			System.out.println(oC.getName()+"\n"+oC.getParty()+"\n"+oC.getMotto()+"\n");
		}
	}
	
	public static ArrayList<Integer> menu(ArrayList<Candidate> alc) {
		//COUNTER -- instantiate arrayList of 6
		ArrayList<Integer> counter = new ArrayList<Integer>();
		for (int i = 0; i<7; i++) {
			counter.add(0);
		}
		
		//the menu
		Scanner scan = new Scanner(System.in);
		System.out.println("Learn more about your candidates:\n\tOPTIONS\n Enter L to list all \n Enter C for candidate report \n Enter V for vote report \n Enter S for state report \n Enter D for dollars spent report \n Enter P for party report \n Enter Q to quit");
		
		char a = '\0';
		while(a != 'q' && a != 'Q') {
			a = scan.next().charAt(0);
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
						break;
					}
					else {
						counter.set(6, counter.get(6)+1);
						System.out.println("Not an option.");
						break;
					}
				}
			}
		return counter;
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
//		ArrayList <Candidate> presidents = new ArrayList<Candidate>();
//		ArrayList <Candidate> governers = new ArrayList<Candidate>();
//		ArrayList <Candidate> mayors = new ArrayList<Candidate>();
//		ArrayList <Candidate> instructors = new ArrayList<Candidate>();
//		Scanner scan = new Scanner(System.in);
//		System.out.println("Enter the candidate name OR 'all':");
//		String searchName = scan.nextLine().toLowerCase();
		
		return true;
	}
	
	public static boolean stateReport(ArrayList<Candidate> alc) {
		return true;
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
			
			System.out.println("Democrat Spending:");
			for (int i = 0; i<democrats.size(); i++) {
				Candidate oC = democrats.get(i);
				System.out.println(oC.getName()+"\t"+oC.getOffice()+"\t"+oC.getParty()+"\t"+oC.getDollarsSpent());
			}
			System.out.println("Republican Spending:");
			for (int i = 0; i<republicans.size(); i++) {
				Candidate oC = republicans.get(i);
				System.out.println(oC.getName()+"\t"+oC.getOffice()+"\t"+oC.getParty()+"\t"+oC.getDollarsSpent());
			}
			System.out.println("Independent Spending:");
			for (int i = 0; i<independent.size(); i++) {
				Candidate oC = independent.get(i);
				System.out.println(oC.getName()+"\t"+oC.getOffice()+"\t"+oC.getParty()+"\t"+oC.getDollarsSpent());
			}
			System.out.println("Other Spending:");
			for (int i = 0; i<other.size(); i++) {
				Candidate oC = other.get(i);
				System.out.println(oC.getName()+"\t"+oC.getOffice()+"\t"+oC.getParty()+"\t"+oC.getDollarsSpent());
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
			System.out.println(oC.getName()+"\t"+oC.getOffice()+"\t"+oC.getParty()+"\t"+oC.getDollarsSpent());
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
		System.out.println("Enter name you'd like to search OR 'all': ");
		String searchName = scan.nextLine();
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
				System.out.println(oC.getName()+"\t"+oC.getOffice());
			}
			System.out.println("Republicans:");
			for (int i = 0; i<republicans.size(); i++) {
				Candidate oC = republicans.get(i);
				System.out.println(oC.getName()+"\t"+oC.getOffice());
			}
			System.out.println("Independents:");
			for (int i = 0; i<independent.size(); i++) {
				Candidate oC = independent.get(i);
				System.out.println(oC.getName()+"\t"+oC.getOffice());
			}
			System.out.println("Others:");
			for (int i = 0; i<other.size(); i++) {
				Candidate oC = other.get(i);
				System.out.println(oC.getName()+"\t"+oC.getOffice());
			}
			return true;
		}
		//GET NAME
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
			System.out.println(oC.getName()+"\t"+oC.getOffice());
			return true;
		}
		else {
			System.out.println("No candidate found");
			return false;
		}
	}
	
	public static String finalStats() {
		return "FINISHED";
	}
}
