package org.fullmetalfalcons;

import static org.fullmetalfalcons.DictionaryKeys.KEY_AUT_DOES_AUTOZONE;
import static org.fullmetalfalcons.DictionaryKeys.KEY_AUT_DOES_STACK;
import static org.fullmetalfalcons.DictionaryKeys.KEY_AUT_MOVES_BINS_TO_AUTOZONE;
import static org.fullmetalfalcons.DictionaryKeys.KEY_AUT_STARTING_POSITION;
import static org.fullmetalfalcons.DictionaryKeys.KEY_AUT_YELLOW_TOTES_INTERACTED_WITH;
import static org.fullmetalfalcons.DictionaryKeys.KEY_AUT_YELLOW_TOTES_MOVED_TO_AUTOZONE;
import static org.fullmetalfalcons.DictionaryKeys.KEY_HUMAN_BINSONFIELD;
import static org.fullmetalfalcons.DictionaryKeys.KEY_HUMAN_FOULSAGAINST;
import static org.fullmetalfalcons.DictionaryKeys.KEY_HUMAN_NOODLES_OPPLANDFILL;
import static org.fullmetalfalcons.DictionaryKeys.KEY_HUMAN_NOODLES_OTHER_SIDE;
import static org.fullmetalfalcons.DictionaryKeys.KEY_HUMAN_NOODLES_OWNLANDFILL;
import static org.fullmetalfalcons.DictionaryKeys.KEY_MATCH_NUMBER;
import static org.fullmetalfalcons.DictionaryKeys.KEY_TEAM_COLOR;
import static org.fullmetalfalcons.DictionaryKeys.KEY_TEAM_NUMBER;
import static org.fullmetalfalcons.DictionaryKeys.KEY_TELEOP_CAN_RUNOVERNOODLES;
import static org.fullmetalfalcons.DictionaryKeys.KEY_TELEOP_COOPERTITION_SETSONPLATFORM;
import static org.fullmetalfalcons.DictionaryKeys.KEY_TELEOP_COOPERTITION_STACKS_ATTEMPTEDMADE;
import static org.fullmetalfalcons.DictionaryKeys.KEY_TELEOP_KNOCKED_TOTESSTACKS;
import static org.fullmetalfalcons.DictionaryKeys.KEY_TELEOP_LITTERBINS_HIGHESTSTACK;
import static org.fullmetalfalcons.DictionaryKeys.KEY_TELEOP_LITTERBINS_SCORED;
import static org.fullmetalfalcons.DictionaryKeys.KEY_TELEOP_LITTER_TO_LANDFULL;
import static org.fullmetalfalcons.DictionaryKeys.KEY_TELEOP_RECYCLINGBINS_STACKED;
import static org.fullmetalfalcons.DictionaryKeys.KEY_TELEOP_TOTAL_FOULSAGAINST;
import static org.fullmetalfalcons.DictionaryKeys.KEY_TELEOP_TOTAL_POINTS;
import static org.fullmetalfalcons.DictionaryKeys.KEY_TELEOP_TOTES_SCORED;
import static org.fullmetalfalcons.DictionaryKeys.KEY_TELEOP_UNPROCESSED_LITTER;

import java.util.HashMap;

import org.fullmetalfalcons.fields.AllianceColor;

import com.dd.plist.NSObject;


/**
 * I'd like to go on record saying I hate this class and everything in it.
 * It's ugly, there are too many variables, there's so many better ways to do this,
 * but I don't feel like rewriting everything right now.
 * 
 * @author Dan
 *
 */
public class Team {
	
	private boolean hasData = false;
	
	//General
	private String location = null;
	private String name = null;
	private int number = 0;
	private AllianceColor color;
	private int matchNum;
	
	//Autonomous
	
	private int start;
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
	private int unprocessedLitter;
	private int recycleStack;
	//4 is t/f
	private int coopPlatform;
	//2 is t/f
	private int coopStack;
	private int totesKnocked;
	private int canNoodles;
	private int fouls;
	private int telTotalPoints;
	private double telTotalScore;
	
	//Human
	
	private int humanFouls;
	private int humanBins;
	private int humanNoodlesOwn;
	private int humanNoodlesOpponent;
	private int humanNoodlesOther;
	private double humanTotalScore;
	
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
		
		
		start = valueAt(KEY_AUT_STARTING_POSITION);
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
		unprocessedLitter = valueAt(KEY_TELEOP_UNPROCESSED_LITTER);
		recycleStack = valueAt(KEY_TELEOP_RECYCLINGBINS_STACKED);
		coopPlatform = valueAt(KEY_TELEOP_COOPERTITION_SETSONPLATFORM);
		coopStack = valueAt(KEY_TELEOP_COOPERTITION_STACKS_ATTEMPTEDMADE);
		totesKnocked = valueAt(KEY_TELEOP_KNOCKED_TOTESSTACKS);
		canNoodles = valueAt(KEY_TELEOP_CAN_RUNOVERNOODLES);
		fouls = valueAt(KEY_TELEOP_TOTAL_FOULSAGAINST);
		telTotalPoints = valueAt(KEY_TELEOP_TOTAL_POINTS);
		
		telTotalScore = totesScored * 2 + 
				litterScored * litterStack * 4 + 
				litterLandfill - totesKnocked * 4 + 
				coopPlatform * 20 +
				coopStack * 40;
		
		humanFouls = valueAt(KEY_HUMAN_FOULSAGAINST);
		humanBins = valueAt(KEY_HUMAN_BINSONFIELD);
		humanNoodlesOwn = valueAt(KEY_HUMAN_NOODLES_OWNLANDFILL);
		humanNoodlesOpponent = valueAt(KEY_HUMAN_NOODLES_OPPLANDFILL);
		humanNoodlesOther = valueAt(KEY_HUMAN_NOODLES_OTHER_SIDE);
		
		//TODO human total score
		humanTotalScore = 7.5;
		
		
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

	public int getStart() {
		return start;
	}

	public String getAutoZone() {
		switch(autoZone){
		case 0:
			return "No";
		case 1:
			return "Yes";
		case 2:
			return "Tried but failed";
		}
		return null;

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
	
	public int getUnprocessedLitter(){
		return unprocessedLitter;
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
	
	public String getCanNoodles(){
		switch(canNoodles){
		case 0:
			return "No";
		case 1:
			return "Yes";
		case 2:
			return "Tried but failed";
		}
		
		return null;
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

	public int getHumanNoodlesOwn(){
		return humanNoodlesOwn;
	}
	
	public int getHumanNoodlesOpponent(){
		return humanNoodlesOpponent;
	}
	
	public int getHumanNoodlesOther(){
		return humanNoodlesOther;
	}

	public double getHumanTotalScore() {
		return humanTotalScore;
	}
}
