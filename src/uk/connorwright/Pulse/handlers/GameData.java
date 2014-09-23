/**
 * The class contains all of the scores
 * for each level. Scores are saved to file
 * whenever the LevelSelectState is loaded.
 * This class also keeps track of the current
 * score while playing a level.
 */

package uk.connorwright.Pulse.handlers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class GameData {
	
	// temporary saves
	private static int cumulativeScore;
	
	// things I need to save
	private static int[] levelScores;
	
	public static void resetScore() {
		cumulativeScore = 0;
	}
	
	public static void addScore(int i) {
		cumulativeScore += i;
		if(cumulativeScore < 0) cumulativeScore = 0;
	}
	
	public static void setFinalScore(int i) {
		if(levelScores[i] < cumulativeScore) levelScores[i] = cumulativeScore;
	}
	
	public static int getScore(int i) {
		return levelScores[i];
	}
	
	public static int getCurrentScore() {
		return cumulativeScore;
	}
	
	public static void save() {
		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream("save.dat"));
			for(int i = 0; i < levelScores.length; i++) {
				out.writeInt(levelScores[i]);
			}
			out.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void load() {
		try {
			File f = new File("save.dat");
			if(!f.exists()) {
				defaultInit();
				return;
			}
			DataInputStream in = new DataInputStream(new FileInputStream(f));
			levelScores = new int[LevelData.MAX_LEVELS];
			for(int i = 0; i < levelScores.length; i++) {
				levelScores[i] = in.readInt();
			}
			in.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			defaultInit();
		}
	}
	
	private static void defaultInit() {
		levelScores = new int[LevelData.MAX_LEVELS];
		save();
	}
	
}