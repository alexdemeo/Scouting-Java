package org.fullmetalfalcons.data;

import java.util.ArrayList;
import java.util.Arrays;

import org.fullmetalfalcons.ScoutingMain;
import org.fullmetalfalcons.teams.Team;

import com.dd.plist.NSObject;

/**
 * Each separate point of data is given a datapoint.  This tells the program what type of data it should be expecting and also handles
 * How it exports the data to other parts of the program, 
 * 
 * 
 * @author Dan
 *
 */
public class DataPoint {
	
	/**
	 * String to allow humans to identify the data points.
	 */
	private String title;
	/**
	 * String which allows computers to identify the data points
	 */
	private String key;
	/**
	 * The type of data that is expected
	 */
	private DataType type;
	
	/**
	 * How to handle special cases of the data
	 */
	private ArrayList<String> additionalInfo = new ArrayList<String>();
	
	public DataPoint(String title, String key, DataType type){
		this.title  = title;
		this.key = key;
		this.type = type;
	}
	
	/**
	 * 
	 * 
	 * @param data How to handle data, requirements vary based on datatype
	 */
	public void setData(String[] data){
		additionalInfo = new ArrayList<String>(Arrays.asList(data));
	}
	
	/**
	 * Returns a formatted version of the data given with respect to the current <code>DataType</code>
	 * 
	 * 
	 * @param string The data to be formatted
	 * @param t The team that the data is from
	 * @return The formatted string
	 */
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
			String[] multiplicationSections;
			String[] toMultiply;
			
			double subtotal;
			double total = 0;
			
			try{
			
				multiplicationSections = additionalInfo.get(0).split("\\+");
				
				
				
				for (int j = 0; j<multiplicationSections.length;j++){
					
					toMultiply = multiplicationSections[j].split("\\*");
					subtotal = 1;
					
					for (int k = 0; k<toMultiply.length;k++){
						
						try{
							subtotal*=Double.parseDouble(toMultiply[k].trim());
						} catch(NumberFormatException e){
							for (DataPoint p: ScoutingMain.dataPoints){
								
								if (p.getKey().equals(toMultiply[k].trim())){
									
									subtotal*=p.getNumericalValue(t.getDataAt(toMultiply[k].trim()));
									
									break;
								}
								

							}
						}
					}
					
					total+=subtotal;
					
				}
			
			} catch(IndexOutOfBoundsException e){
				
			}
	
			//System.out.println(total);
			t.putDataAt(key, NSObject.wrap(String.valueOf(total)));
			System.out.println(t.getDataAt(key));
			
			return String.valueOf(total);
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
	
	/**
	 * Turns string or boolean values into numbers.  If the data is an int-fail, it returns the int unless it was a failure, in which 
	 * case it returns 0.  Same concept with the int-replace.  Returns 1 if true, 0 if anything else
	 * 
	 * 
	 * @param data The number to be formatted
	 * @return The number
	 */
	private double getNumericalValue(String data) {
		switch(type){
		case BOOLEAN:
			if (Integer.parseInt(data)==1){
				return 1;
			} else{
				return 0;
			}
		case INT:
			return Double.parseDouble(data);
		case INT_FAIL:
			if (Integer.parseInt(data)==Integer.parseInt(additionalInfo.get(0))){
				return 0;
			} else {
				return Integer.parseInt(data);
			}
		case INT_REPLACE:
			break;
		case TRILEAN:
			if (Integer.parseInt(data)==1){
				return 1;
			} else{
				return 0;
			}
		case MATH:
			return Double.parseDouble(data);
		default:
			break;
		
		}
		
		return 0;
	}

	/**
	 * 
	 * @return The title
	 */
	public String getTitle(){
		return title;
	}
	
	/**
	 * 
	 * @return The key
	 */
	public String getKey(){
		return key;
	}
	
	
	
}
