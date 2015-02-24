package org.fullmetalfalcons;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.io.FilenameUtils;

import com.dd.plist.NSDictionary;
import com.dd.plist.NSObject;
import com.dd.plist.PropertyListParser;

public class ScoutingMain {
	public static ScoutingMain instance = new ScoutingMain();

	public static String directoryPath = null;

	private static boolean debug = true;
	public static void main(String[] args) {
		directoryPath = debug ? "Scout" : args[0] + "/";
		TeamUtils.init();
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
		//new Excel(instance).evaluateDictionaries(dir.getPath());
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
		
		//Get the team by its number then pass it the value
		try{
			TeamUtils.TEAMS.get(number).setKeys((HashMap<String, NSObject>)keys.toJavaObject());
		} catch (NullPointerException e){
			//if the team number is not in the list
			System.out.println("Team number " + number + " is not in our directory, creating a blank one");
			TeamUtils.TEAMS.put(number, new Team(number,null,null));
			TeamUtils.TEAMS.get(number).setKeys((HashMap<String, NSObject>)keys.toJavaObject());
		}
		
		System.out.println("Team " + number + " loaded successfully");
	}


}
