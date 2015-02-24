package org.fullmetalfalcons.fields;

public enum AllianceColor {
	RED("Red"),BLUE("Blue");
	
	private String name;
	
	private AllianceColor(String name){
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
	
}
