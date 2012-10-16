package jDistance.settings;

import java.io.*;
import java.util.Properties;

//import com.sun.org.apache.xalan.internal.xsltc.compiler.util.BooleanType;

public class Settings {
	
	public static enum PlayerType {HUMAN, AI_EASY, AI_NORMAL, AI_HARD}
	
	//is2D = true -> 2D GUI
	//is2D = false -> 3D GUI
	public boolean is2D = true;
	
	//difficultyt = 1 -> leichte KI
	//difficultyt = 2 -> mittlere KI
	//difficultyt = 3 -> schwere KI
	public int difficulty = 1;
	
	//Opponent = 1 -> Mensch vs. Computer
	//Opponent = 2 -> Mensch vs. Mensch
	//Opponent = 3 -> Computer vs. Computer
	public int opponent = 1;


	public PlayerType whitePlayerType = PlayerType.AI_NORMAL;
	public PlayerType blackPlayerType = PlayerType.HUMAN;

	
	//Antialiasing setzen
	public boolean antialiasing = false;
	
	protected static String fileName = "settings.ini";
	
	public Settings() {
		
		if (readSettings() == false ) {
			//erstellt eine default Settings Datei, bei nicht vorhandener settings.ini Datei.
			writeSettings();
		}
	}
	
	public void writeSettings () {

		Properties prop = new Properties();
		try {
			FileWriter propOutFile = new FileWriter( fileName, false);
			
			prop.setProperty( "is2D", String.valueOf( is2D ) );
			prop.setProperty( "antialiasing", String.valueOf( antialiasing ) );
			prop.setProperty( "PlayerWhiteType", whitePlayerType.name() );
			prop.setProperty( "PlayerBlackType", blackPlayerType.name() );
			
			prop.store( propOutFile, null );
			
			propOutFile.flush();
			propOutFile.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public boolean readSettings () {

		File propertiesFile = new File( fileName );
		Properties prop = new Properties();
		
		try {
			FileReader bis = new FileReader(propertiesFile );
			prop.load( bis );
			bis.close();
			is2D = Boolean.parseBoolean( prop.getProperty( "is2D" ) );
			antialiasing = Boolean.parseBoolean( prop.getProperty( "antialiasing" ) );
				whitePlayerType = PlayerType.valueOf(prop.getProperty("PlayerWhiteType"));
			blackPlayerType = PlayerType.valueOf(prop.getProperty( "PlayerBlackType" ) );
			
			
		} catch (Exception e) {

			return false;
		}
		
		return true;
	}
	
}