package org.fullmetalfalcons;

import java.util.HashMap;

import com.dd.plist.NSObject;

import static org.fullmetalfalcons.DictionaryKeys.*;

public class Excel {
	@SuppressWarnings("unused")
	private ScoutingMain instance;

	public Excel(ScoutingMain instance) {
		this.instance = instance;
	}


	public void evaluateDictionaries(String path) {
		System.out.println("\nBeginning to write...\n");
		for (Team team : ScoutingMain.teams) {
			HashMap<String, NSObject> keys = team.keys;
			System.out.println("For team " + keys.get(KEY_TEAM_NUMBER) + " information is\n\t" + keys + "\n");
			String teamColor = String.valueOf(keys.get(KEY_TEAM_COLOR));
			//AUTONOMOUS VALUES
			int aut_movesToAutozone = valueAt(keys, KEY_AUT_DOES_AUTOZONE);
			int aut_movesToteToAutozone = valueAt(keys, KEY_AUT_YELLOW_TOTES_MOVED_TO_AUTOZONE);
			int aut_totesStacked = valueAt(keys, KEY_AUT_DOES_STACK);
			int aut_binsToAutozone = valueAt(keys, KEY_AUT_MOVES_BINTS_TO_AUTOZONE);
			
			double aut_SCORE = aut_movesToAutozone * 4 + aut_movesToteToAutozone * 6 + aut_totesStacked * 20 + aut_binsToAutozone * (3/8);
			
			//TELEOP VALUES
			int teleop_totesScored = valueAt(keys, KEY_TELEOP_TOTES_SCORED);
			int teleop_recyclingBinsScored = valueAt(keys, KEY_TELEOP_RECYCLINGBINS_STACKED);
			int teleop_litterToLandfill = valueAt(keys, KEY_TELEOP_LITTER_TO_LANDFULL);
			int teleop_litterBinsScored = valueAt(keys, KEY_TELEOP_LITTERBINS_SCORED);
			int teleop_litterBinsHighestStack = valueAt(keys, KEY_TELEOP_LITTERBINS_HIGHESTSTACK);
			int teleop_coopertitionSetsAttemptedMade = valueAt(keys, KEY_TELEOP_COOPERTITION_STACKS_ATTEMPTEDMADE);
			int teleop_coopertitionStaxAttemptedMade = valueAt(keys, KEY_TELEOP_COOPERTITION_SETSONPLATFORM);
			int teleop_knockedOverTotesStax = valueAt(keys, KEY_TELEOP_KNOCKED_TOTESSTACKS);
			int teleop_foulsAgainst = valueAt(keys, KEY_TELEOP_TOTAL_FOULSAGAINST);
			int teleop_totalPoints = valueAt(keys, KEY_TELEOP_TOTAL_POINTS);
			
			double teleop_SCORE = teleop_totesScored * 2 + 
					teleop_litterBinsScored * teleop_litterBinsHighestStack * 4 + 
					teleop_litterToLandfill - teleop_knockedOverTotesStax * 4 + 
					teleop_coopertitionSetsAttemptedMade * 20 +
					teleop_coopertitionStaxAttemptedMade * 40;
			
			//HUMAN VALUES
			int human_foulsAgainst = valueAt(keys, KEY_HUMAN_FOULSAGAINST);
			int human_binsOnField = valueAt(keys, KEY_HUMAN_BINSONFIELD);
			int human_noodlesThrown = valueAt(keys, KEY_HUMAN_NOODLES);
			
			double human_SCORE = human_noodlesThrown * 4 +
					human_binsOnField * 2 -
					human_foulsAgainst * 6;
			
			
		}
	}
	
	private Integer valueAt(HashMap<String, NSObject> keys, String key) {
		return Integer.parseInt(String.valueOf(keys.get(key)));
	}
}
