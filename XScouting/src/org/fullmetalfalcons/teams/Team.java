package org.fullmetalfalcons.teams;

import java.util.HashMap;

import com.dd.plist.NSObject;



/**
 * I like this class now
 * 
 * @author Dan
 *
 */
public class Team {

	private TeamID id;
	
	private HashMap<String,NSObject> data = new HashMap<>();
	
	public Team(TeamID id, HashMap<String,NSObject> data){
		this.id = id;
		this.data = data;
	}
	
	public TeamID getID(){
		return id;
	}
	
	public String getDataAt(String s){
		return String.valueOf(data.get(s));
	}
	
	public void putDataAt(String s, NSObject o){
		System.out.println(s);
		System.out.println(String.valueOf(o));
		data.put(s, o);
	}
}
