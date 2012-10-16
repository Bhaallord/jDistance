package jDistance.settings.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import jDistance.settings.Settings;


public class SettingsTest extends Settings {
	
	public SettingsTest() {
		super();
	}
	
	
	@Before
	public void setUp() throws Exception {
		this.is2D = true;
		this.difficulty = 1;
		this.whitePlayerType = PlayerType.AI_HARD;
		this.blackPlayerType = PlayerType.HUMAN;
		this.antialiasing = false;
	}


	@Test
	public void testSettings() {
		assertNotNull(this);
	}

	
	@Test
	public void testWriteSettings() {
		this.writeSettings();
		
		Properties settings = new Properties();
		FileReader ini = null;
		
		try{
			ini = new FileReader(Settings.fileName);
			settings.load(ini);
			}
		catch(FileNotFoundException e){fail("Fehler beim öffnen von settings.ini");}
		catch(IOException e){fail("Fehler beim lesen von settings.ini");}

		assertTrue(settings.getProperty(String.valueOf("is2D")).equals("true"));
		assertTrue(settings.getProperty(String.valueOf("antialiasing")).equals("false"));
		assertTrue(settings.getProperty(String.valueOf("PlayerWhiteType")).equals("AI_HARD"));
		assertTrue(settings.getProperty(String.valueOf("PlayerBlackType")).equals("HUMAN"));
	}

	
	@Test
	public void testReadSettings() {
		assertTrue(this.readSettings());
		
//		Settings.fileName = "Bla.ini";
//		
//		assertFalse(this.readSettings());
	}
}
