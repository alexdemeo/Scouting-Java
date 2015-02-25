package org.fullmetalfalcons;

import java.util.HashMap;

import static org.fullmetalfalcons.DictionaryKeys.*;

import org.fullmetalfalcons.fields.AllianceColor;
import org.fullmetalfalcons.fields.StartingPosition;

import com.dd.plist.NSObject;



public class Team {
	
	private boolean hasData = false;
	
	//General
	private String location = null;
	private String name = null;
	private int number = 0;
	private AllianceColor color;
	private int matchNum;
	
	//Autonomous
	
	private StartingPosition start;
	//2=tried but Failed
	private int autoZone;
	private int toteInteraction;
	private int toteAutozone;
	private int binAutozone;
	//3 = tried but failed
	private int stackSize;
	private double autTotalScore;
	
	//Tele-Op
	private int totesScored;
	private int litterScored;
	private int litterStack;
	private int litterLandfill;
	private int recycleStack;
	//4 is t/f
	private int coopPlatform;
	//2 is t/f
	private int coopStack;
	private int totesKnocked;
	private int fouls;
	private int telTotalPoints;
	private double telTotalScore;
	
	//Human
	
	private int humanFouls;
	private int humanBins;
	private int humanNoodles;
	private int humanTotalScore;
	
	public HashMap<String, NSObject> keys;
	
	public Team(Integer number, String name, String location){
		this.number = number;
		this.name = name;
		this.location = location;
	}
	
	public void setKeys(HashMap<String, NSObject> keys){
		hasData = true;
		this.keys = keys;
		getValues();
	}
	
	private void getValues() {
		if (String.valueOf(keys.get(KEY_TEAM_COLOR)).equals("blue")){
			color = AllianceColor.BLUE;
		} else {
			color = AllianceColor.RED;
		}
		
		if (number==0){
			number = valueAt(KEY_TEAM_NUMBER);
		}
		matchNum = valueAt(KEY_MATCH_NUMBER);
		
		
		start = StartingPosition.getPosition(valueAt(KEY_AUT_STARTING_POSITION));
		autoZone = valueAt(KEY_AUT_DOES_AUTOZONE);
		toteInteraction = valueAt(KEY_AUT_YELLOW_TOTES_INTERACTED_WITH);
		toteAutozone = valueAt(KEY_AUT_YELLOW_TOTES_MOVED_TO_AUTOZONE);
		binAutozone = valueAt(KEY_AUT_MOVES_BINS_TO_AUTOZONE);
		stackSize = valueAt(KEY_AUT_DOES_STACK);
		
		autTotalScore = toteAutozone * 6 + 
				stackSize * 20 + 
				binAutozone * (3/8);
		if (autoZone == 1){
			autTotalScore+=4;
		}
		
		totesScored = valueAt(KEY_TELEOP_TOTES_SCORED);
		litterScored = valueAt(KEY_TELEOP_LITTERBINS_SCORED);
		litterStack = valueAt(KEY_TELEOP_LITTERBINS_HIGHESTSTACK);
		litterLandfill = valueAt(KEY_TELEOP_LITTER_TO_LANDFULL);
		recycleStack = valueAt(KEY_TELEOP_RECYCLINGBINS_STACKED);
		coopPlatform = valueAt(KEY_TELEOP_COOPERTITION_SETSONPLATFORM);
		coopStack = valueAt(KEY_TELEOP_COOPERTITION_STACKS_ATTEMPTEDMADE);
		totesKnocked = valueAt(KEY_TELEOP_KNOCKED_TOTESSTACKS);
		fouls = valueAt(KEY_TELEOP_TOTAL_FOULSAGAINST);
		telTotalPoints = valueAt(KEY_TELEOP_TOTAL_POINTS);
		
		telTotalScore = totesScored * 2 + 
				litterScored * litterStack * 4 + 
				litterLandfill - totesKnocked * 4 + 
				coopPlatform * 20 +
				coopStack * 40;
		
		humanFouls = valueAt(KEY_HUMAN_FOULSAGAINST);
		humanBins = valueAt(KEY_HUMAN_BINSONFIELD);
		humanNoodles = valueAt(KEY_HUMAN_NOODLES);
		
		humanTotalScore =  humanNoodles * 4 +
				humanBins * 2 -
				humanFouls * 6;
		
		
	}
	
	private int valueAt(String dictKey){
		return Integer.parseInt(String.valueOf(keys.get(dictKey)));
	}
	
	/**
	 * @return The location of the team
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @return The name of the team
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return Get team number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @return Whether or not the team has Scouting info registered
	 */
	public boolean hasData() {
		return hasData;
	}

	/**
	 * @return What color the team is using
	 */
	public String getColor() {
		return color.toString();
	}

	public int getMatchNum() {
		return matchNum;
	}

	public String getStart() {
		return start.toString();
	}

	public int getAutoZone() {
		return autoZone;
	}

	public int getToteInteraction() {
		return toteInteraction;
	}

	public int getToteAutozone() {
		return toteAutozone;
	}

	public int getBinAutozone() {
		return binAutozone;
	}

	public int getStackSize() {
		return stackSize;
	}

	public double getAutTotalScore() {
		return autTotalScore;
	}

	public int getTotesScored() {
		return totesScored;
	}

	public int getLitterScored() {
		return litterScored;
	}

	public int getLitterStack() {
		return litterStack;
	}

	public int getLitterLandfill() {
		return litterLandfill;
	}

	public int getRecycleStack() {
		return recycleStack;
	}

	public int getCoopPlatform() {
		return coopPlatform;
	}

	public int getCoopStack() {
		return coopStack;
	}

	public int getTotesKnocked() {
		return totesKnocked;
	}

	public int getFouls() {
		return fouls;
	}

	public int getTelTotalPoints() {
		return telTotalPoints;
	}

	public double getTelTotalScore() {
		return telTotalScore;
	}

	public int getHumanFouls() {
		return humanFouls;
	}

	public int getHumanBins() {
		return humanBins;
	}

	public int getHumanNoodles() {
		return humanNoodles;
	}

	public int getHumanTotalScore() {
		return humanTotalScore;
	}
}
