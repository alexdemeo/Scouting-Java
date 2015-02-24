package org.fullmetalfalcons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


public class TeamUtils {
	public static final HashMap<Integer,Team> TEAMS = new HashMap<>();
	
	public static void init() {
		teamDictionary();
	}
	
	private static void teamDictionary() {
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(TeamUtils.class.getResourceAsStream("/org/fullmetalfalcons/files/teams.txt")));
			String s = br.readLine();
			String[] info = new String[3];
			int counter = 0;
			while (s!=null){
				info = s.split(",");
				TEAMS.put(Integer.parseInt(info[0]), new Team(Integer.parseInt(info[0]),info[1],info[2]));
				s = br.readLine();
				counter++;
			}
			System.out.println(counter + " teams loaded.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
