package com.xavierrobotics;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;

import com.dd.plist.NSDictionary;
import com.dd.plist.NSObject;
import com.dd.plist.PropertyListParser;

public class ScoutingMain {
	public static ScoutingMain instance = new ScoutingMain();
	public static NSDictionary keys;

	public static ArrayList<Team> teams = new ArrayList<>();
	public String[] labels = {
			"Team Name", 
			"Team Color", 
			"Team Location", 
			"Date & Time"
	};

	public static String directoryPath = null;

	private static boolean debug = true;
	public static void main(String[] args) {
		directoryPath = debug ? "/Users/alexdemeo/Desktop/Scout/" : args[0] + "/";
		Util.init();
		instance.init();
	}

	public void init() {
		File dir = new File(directoryPath + "/");

		System.out.println("current directory is: " + dir.getPath() + "\n");
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				System.out.println(child.getPath());
				if (FilenameUtils.getExtension(child.getName()).equals("plist")) {
					addFile(child);
				}
			}
		} else {
			System.out.println("failed to load directory... yea this is an issue... *insert sad face here*");
		}
		new Excel(instance).writeToXls(dir.getPath());
	}

	public void addFile(File file) {
		try {
			keys = (NSDictionary) PropertyListParser.parse(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*Integer teamNum = intFromNSObject(keys.get(Strings.ID_teamNum));
		String teamColor = stringFromNSObject(keys.get(Strings.ID_teamColor));
		String date = stringFromNSObject(keys.get(Strings.ID_date));*/
		
		teams.add(null);

	}

	public Integer intFromNSObject(NSObject obj) {
		if (obj == null) return null;
		return Integer.parseInt(obj.toString());
	}

	public String stringFromNSObject(NSObject obj) {
		if (obj == null) return null;
		return obj.toString();
	}


}
