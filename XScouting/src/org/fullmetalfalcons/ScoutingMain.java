package org.fullmetalfalcons;

import java.io.File;
import java.util.HashMap;

import org.apache.commons.io.FilenameUtils;

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

	private static boolean debug = true;
	public static void main(String[] args) {
		directoryPath = debug ? "Scout" : args[0] + "/";
		TeamUtils.init();
		Excel.init();
		instance.init();
		Excel.export();
	}

	public void init() {
		File dir = new File(directoryPath + "/");

		System.out.println("current directory is: " + dir.getPath() + "\n");
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				if (FilenameUtils.getExtension(child.getName()).equals("plist")) {
					//System.out.println(child.getPath());
					addFile(child);
				}
			}
		} else {
			System.out.println("failed to load directory... yea this is an issue... *insert sad face here*");
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
		
		int number =Integer.parseInt(String.valueOf(keys.get(DictionaryKeys.KEY_TEAM_NUMBER)));
		
		//Dirty Dirty code
		if (TeamUtils.TEAM_INFO.containsKey(number)){
			Team oldTeam = TeamUtils.TEAM_INFO.get(number);
			Team newTeam = new Team(oldTeam.getNumber(),oldTeam.getName(),oldTeam.getLocation());
			newTeam.setKeys((HashMap<String, NSObject>)keys.toJavaObject());
			TeamUtils.TEAMS.add(newTeam);
		} else {
			Team newTeam = new Team(number,null,null);
			newTeam.setKeys((HashMap<String, NSObject>)keys.toJavaObject());
			TeamUtils.TEAMS.add(newTeam);

		}
		
		System.out.println("Team " + number + " loaded successfully");
	}


}
