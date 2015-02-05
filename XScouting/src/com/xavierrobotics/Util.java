package com.xavierrobotics;

import java.util.HashMap;


public class Util {
	/**<b><i>team dictionary</b></i>*/
	public static HashMap<Integer, String> td = new HashMap<>();
	/**<b><i>location dictionary</i></b>*/
	public static HashMap<Integer, String> ld = new HashMap<>();
	
	public static void init() {
		teamDictionary();
		locationDictionary();
	}
	
	private static void teamDictionary() {
		td.put(4557, "Xavier Falcons");
		td.put(78, "AIR Strike");
		td.put(121, "Rhode Warriors");
		td.put(125, "NUTRONS");
		td.put(155, "The Technonuts");
		td.put(175, "Buzz Robotics");
		td.put(178, "The 2nd Law Enforcers");
		td.put(181, "Birds of Prey");
		td.put(195, "Cyber Knights");
		td.put(228, "GUS Robotics");
		td.put(230, "Gaelhawks");
		td.put(236, "Techno-Ticks");
		td.put(237, "Black Magic Robots");
		td.put(558, "Robo Squad");
		td.put(571, "Team Paragon");
		td.put(1099, "DiscoTechs");
		td.put(1124, "ÜberBots");
		td.put(1699, "Robocats");
		td.put(1991, "Dragons");
		td.put(2064, "The Panther Project");
		td.put(2067, "Apple Pi");
		td.put(2168, "Aluminum Falcons");
		td.put(2170, "Titanium Tomahawks");
		td.put(2836, "Team Beta"); 
		td.put(3182, "Athena's Warriors");
		td.put(3555, "Aluminati");
		td.put(3634, "Hard Botties");
		td.put(3654, "TechTigers (Mercy)");
		td.put(3718, "Junkyard Battalion");
		td.put(3719, "STEM Whalers");
		td.put(4055, "Northwestern Robotic Gearheads");
		td.put(5142, "Robo Dominators");
		td.put(5112, "The Gongoliers");
	}
	
	private static void locationDictionary() {
		ld.put(4557, "Middletown CT");
		ld.put(78, "Newport County RI");
		ld.put(121, "Newport County RI");
		ld.put(125, "Boston MA");
		ld.put(155, "Berlin CT");
		ld.put(175, "Enfield CT");
		ld.put(178, "Farmington CT");
		ld.put(181, "Hartford CT");
		ld.put(195, "Southington CT");
		ld.put(228, "Meriden CT");
		ld.put(230, "Shelton CT");
		ld.put(236, "Old Lyme CT");
		ld.put(237, "Watertown CT");
		ld.put(558, "New Haven CT");
		ld.put(571, "Windsor CT");
		ld.put(1099, "Brookfield CT");
		ld.put(1124, "Avon CT");
		ld.put(1699, "Colchester CT");
		ld.put(1991, "Hartford CT");
		ld.put(2064, "Soutbury/Middlebury CT");
		ld.put(2067, "Guilford CT");
		ld.put(2168, "Groton CT");
		ld.put(2170, "Glastonbury CT");
		ld.put(2836, "Woodbury CT");
		ld.put(3182, "West Hartford CT");
		ld.put(3555, "Storrs CT");
		ld.put(3634, "Bridgeport CT");
		ld.put(3654, "Middletown CT");
		ld.put(3718, "New Britian CT");
		ld.put(3719, "New London CT");
		ld.put(4055, "Winsted CT");
		ld.put(5142, "Hamden CT");
		ld.put(5112, "North Scituate RI");
	}
	public static String getTeamName(Object teamNum) {
		String name = Util.td.get(teamNum);
		if (name == null) return "N/A"; 
		else return Util.td.get(teamNum);
	}
}
