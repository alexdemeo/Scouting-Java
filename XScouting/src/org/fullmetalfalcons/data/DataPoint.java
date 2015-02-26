package org.fullmetalfalcons.data;

import java.util.ArrayList;
import java.util.Arrays;

import org.fullmetalfalcons.teams.Team;

public class DataPoint {
	
	private String title;
	private String key;
	private DataType type;
	
	private ArrayList<String> additionalInfo = new ArrayList<String>();
	
	public DataPoint(String title, String key, DataType type){
		this.title  = title;
		this.key = key;
		this.type = type;
	}
	
	public void setData(String[] data){
		additionalInfo = new ArrayList<String>(Arrays.asList(data));
	}
	
	public String parseData(String string, Team t){
		int i;
		int replaceIndex;
		switch(type){
		case BOOLEAN:
			i = Integer.parseInt(string);
			if (i==0){
				return "No";
			} else {
				return "Yes";
			}
		case INT:
			i = Integer.parseInt(string);
			return String.valueOf(i);
		case INT_FAIL:
			i = Integer.parseInt(string);
			replaceIndex = Integer.parseInt(additionalInfo.get(0));
			if (i==replaceIndex){
				return "Tried but Failed";
			} else {
				return String.valueOf(i);
			}
		case INT_REPLACE:
			i = Integer.parseInt(string);
			replaceIndex = Integer.parseInt(additionalInfo.get(0));
			if (i==replaceIndex){
				return additionalInfo.get(1);
			} else {
				return String.valueOf(i);
			}
		case MATH:
			double result = 0;
			
			
			for (int j=0;j<additionalInfo.size();j++){
				result+= Double.parseDouble(t.getDataAt(additionalInfo.get(j++))) * Double.parseDouble(additionalInfo.get(j));
				
			}
			
			
			return String.valueOf(result);
		case STRING:
			return String.valueOf(string);
		case TRILEAN:
			i = Integer.parseInt(string);
			if (i==0){
				return "No";
			} else if (i==1){
				return "Yes";
			} else {
				return additionalInfo.get(0);
			}
		default:
			break;
		
		}
		
		return null;
		
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getKey(){
		return key;
	}
	
	
	
}
