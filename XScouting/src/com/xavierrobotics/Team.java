package com.xavierrobotics;



public class Team {
	private int teamNum;
	private String teamName;
	private String location;
	private String date;
	private String teamColor;
	
	public Team(int teamNum, String teamColor,  String date){
		this.teamNum = teamNum;
		this.teamName = Util.td.get(teamNum);
		this.location = Util.ld.get(teamNum);
		this.date = date;
		this.teamColor = teamColor;
	}


	public int getTeamNum() {
		return teamNum;
	}

	public String getTeamName() {
		return teamName;
	}
	
	public String getLocation() {
		return location;
	}

	public String getDate() {
		return date;
	}
	
	public String getTeamColor() {
		return teamColor;
	}
}
