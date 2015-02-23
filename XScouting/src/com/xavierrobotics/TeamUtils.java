package com.xavierrobotics;

import java.util.HashMap;


public class TeamUtils {
	/**<b><i>team dictionary</b></i>*/
	public static HashMap<Integer, String> teamDictionary = new HashMap<>();
	/**<b><i>location dictionary</i></b>*/
	public static HashMap<Integer, String> locationDictionary = new HashMap<>();
	
	public static void init() {
		teamDictionary();
		locationDictionary();
	}
	
	private static void teamDictionary() {
		teamDictionary.put(4557, "Xavier Falcons");
		teamDictionary.put(78, "AIR Strike");
		teamDictionary.put(121, "Rhode Warriors");
		teamDictionary.put(125, "NUTRONS");
		teamDictionary.put(155, "The Technonuts");
		teamDictionary.put(175, "Buzz Robotics");
		teamDictionary.put(178, "The 2nd Law Enforcers");
		teamDictionary.put(181, "Birds of Prey");
		teamDictionary.put(195, "Cyber Knights");
		teamDictionary.put(228, "GUS Robotics");
		teamDictionary.put(230, "Gaelhawks");
		teamDictionary.put(236, "Techno-Ticks");
		teamDictionary.put(237, "Black Magic Robots");
		teamDictionary.put(558, "Robo Squad");
		teamDictionary.put(571, "Team Paragon");
		teamDictionary.put(1099, "DiscoTechs");
		teamDictionary.put(1124, "ÜberBots");
		teamDictionary.put(1699, "Robocats");
		teamDictionary.put(1991, "Dragons");
		teamDictionary.put(2064, "The Panther Project");
		teamDictionary.put(2067, "Apple Pi");
		teamDictionary.put(2168, "Aluminum Falcons");
		teamDictionary.put(2170, "Titanium Tomahawks");
		teamDictionary.put(2836, "Team Beta"); 
		teamDictionary.put(3182, "Athena's Warriors");
		teamDictionary.put(3555, "Aluminati");
		teamDictionary.put(3634, "Hard Botties");
		teamDictionary.put(3654, "TechTigers (Mercy)");
		teamDictionary.put(3718, "Junkyard Battalion");
		teamDictionary.put(3719, "STEM Whalers");
		teamDictionary.put(4055, "Northwestern Robotic Gearheads");
		teamDictionary.put(5142, "Robo Dominators");
		teamDictionary.put(5112, "The Gongoliers");
	}
	
	private static void locationDictionary() {
		locationDictionary.put(4557, "Middletown CT");
		locationDictionary.put(78, "Newport County RI");
		locationDictionary.put(121, "Newport County RI");
		locationDictionary.put(125, "Boston MA");
		locationDictionary.put(155, "Berlin CT");
		locationDictionary.put(175, "Enfield CT");
		locationDictionary.put(178, "Farmington CT");
		locationDictionary.put(181, "Hartford CT");
		locationDictionary.put(195, "Southington CT");
		locationDictionary.put(228, "Meriden CT");
		locationDictionary.put(230, "Shelton CT");
		locationDictionary.put(236, "Old Lyme CT");
		locationDictionary.put(237, "Watertown CT");
		locationDictionary.put(558, "New Haven CT");
		locationDictionary.put(571, "Windsor CT");
		locationDictionary.put(1099, "Brookfield CT");
		locationDictionary.put(1124, "Avon CT");
		locationDictionary.put(1699, "Colchester CT");
		locationDictionary.put(1991, "Hartford CT");
		locationDictionary.put(2064, "Soutbury/Middlebury CT");
		locationDictionary.put(2067, "Guilford CT");
		locationDictionary.put(2168, "Groton CT");
		locationDictionary.put(2170, "Glastonbury CT");
		locationDictionary.put(2836, "Woodbury CT");
		locationDictionary.put(3182, "West Hartford CT");
		locationDictionary.put(3555, "Storrs CT");
		locationDictionary.put(3634, "Bridgeport CT");
		locationDictionary.put(3654, "Middletown CT");
		locationDictionary.put(3718, "New Britian CT");
		locationDictionary.put(3719, "New London CT");
		locationDictionary.put(4055, "Winsted CT");
		locationDictionary.put(5142, "Hamden CT");
		locationDictionary.put(5112, "North Scituate RI");
	}
	public static String getTeamName(Object teamNum) {
		String name = TeamUtils.teamDictionary.get(teamNum);
		if (name == null) return "N/A"; 
		else return TeamUtils.teamDictionary.get(teamNum);
	}
}
