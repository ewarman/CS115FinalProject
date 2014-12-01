package finalProject;

import java.util.*;
import java.io.*;

public class WarmanEmily {

	public static void main(String[] args) throws IOException{
			
		File cip = new File("/Users/Emily/Desktop/cipcs115.txt");
		Scanner canScan = new Scanner(cip);
		Scanner intScan = new Scanner(cip);
		Scanner stateScan = new Scanner(cip);
		
		ArrayList<Candidate> candidates = new ArrayList<Candidate>();
		String line = "";
		String state = "";
		int cPerState = 0;
		int numCandidate = 0;
			state = stateScan.nextLine();
			System.out.println(state);
			cPerState = intScan.nextInt();
			System.out.println(cPerState);
			for(int i = 0; i<cPerState; i++) {
				line = canScan.nextLine();
				System.out.println(line);
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
		}
		
		//calling static methods in main example
		//listAll(candidates);
	}

}
