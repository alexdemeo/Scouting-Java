package org.fullmetalfalcons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.fullmetalfalcons.teams.Team;
import org.fullmetalfalcons.teams.TeamID;


public class TeamUtils {
	public static final HashMap<Integer,TeamID> TEAM_IDS = new HashMap<>();
	
	public static final ArrayList<Team> TEAMS = new ArrayList<>();
	
	public static void init() {
		loadTeamInfo();
	}
	
	private static void loadTeamInfo() {
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(TeamUtils.class.getResourceAsStream("/org/fullmetalfalcons/files/teams.txt")));
			String s = br.readLine();
			String[] info = new String[3];
			int counter = 0;
			while (s!=null){
				info = s.split(",");
				TEAM_IDS.put(Integer.parseInt(info[0]), new TeamID(Integer.parseInt(info[0]),info[1],info[2]));
				s = br.readLine();
				counter++;
			}
			System.out.println(counter + " teams loaded.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
