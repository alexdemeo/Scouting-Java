package org.fullmetalfalcons.teams;

public class TeamID {
	
	private int number;
	private String name;
	private String location;
	
	public TeamID(int number, String name, String location){
		this.name = name;
		this.number = number;
		this.location = location;
	}
	
	public int getNumber(){
		return number;
	}
	
	public String getName(){
		return name;
	}
	
	public String getLocation(){
		return location;
	}
}
