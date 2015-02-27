package org.fullmetalfalcons;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.commons.io.FilenameUtils;
import org.fullmetalfalcons.data.DataPoint;
import org.fullmetalfalcons.data.DataType;
import org.fullmetalfalcons.teams.Team;
import org.fullmetalfalcons.teams.TeamID;

import com.dd.plist.NSDictionary;
import com.dd.plist.NSObject;
import com.dd.plist.PropertyListParser;

/**
 * Handles the flow of the program and loading the .plist files that contain the raw data
 * 
 * 
 * @author Dan
 * @author Demeo
 */
public class ScoutingMain {
	public static ScoutingMain instance = new ScoutingMain();

	public static String directoryPath = null;

	public static ArrayList<DataPoint> dataPoints = new ArrayList<>();
	
	private static boolean debug = true;
	public static void main(String[] args) {
		directoryPath = debug ? "Scout" : args[0] + "/";
		TeamUtils.init();
		instance.init();
		Excel.init();
		Excel.export();
	}

	public void init() {
		File dir = new File(directoryPath + "/");

		System.out.println("current directory is: " + dir.getPath() + "\n");
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				if (FilenameUtils.getExtension(child.getName()).equals("plist")) {
					addFile(child);
				}
			}
		} else {
			System.out.println("failed to load directory... yea this is an issue... *insert sad face here*");
		}
		
		try(InputStream fieldsIn = this.getClass().getResourceAsStream("/org/fullmetalfalcons/files/fields.csv");
				BufferedReader fieldReader = new BufferedReader (new InputStreamReader(fieldsIn))){
			
			String line;
			String[] info;
			DataPoint dataPoint;
			DataType dataType;
			String[] data;
			while((line=fieldReader.readLine())!=null){
				info = line.split(",");
				dataType = DataType.getDataType(info[2]);
				dataPoint = new DataPoint(info[0],info[1],dataType);
				if (info.length>3){
					data = Arrays.copyOfRange(info,3,info.length);
					dataPoint.setData(data);
				}
				dataPoints.add(dataPoint);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void addFile(File file) {
		NSDictionary keys = null;
		try {
			keys = (NSDictionary) PropertyListParser.parse(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int number =Integer.parseInt(String.valueOf(keys.get("team_number")));
		
		if (TeamUtils.TEAM_IDS.containsKey(number)){
			TeamUtils.TEAMS.add(new Team(TeamUtils.TEAM_IDS.get(number),(HashMap<String, NSObject>) keys.toJavaObject()));
		} else {
			TeamUtils.TEAMS.add(new Team(new TeamID(number,"unknown","unknown"),(HashMap<String,NSObject>) keys.toJavaObject()));
		}
		
		System.out.println("Team " + number + " loaded successfully");
	}


}
