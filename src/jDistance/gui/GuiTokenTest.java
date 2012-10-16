package jDistance.gui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jDistance.model.Field;
import jDistance.model.Token;

/**
 * Testklasse, welche GuiToken testet.
 * Der Testaufruf ist entsprechend zeitaufwändig, da die GUI mehrfach gestartet wird.
 */
public class GuiTokenTest {
	// Membervariablen, um die GuiToken Klasse zu testen
	private final Field testField = new Field(Integer.valueOf(0),Integer.valueOf(0));
	private final int testPlayer = Integer.valueOf(1);
	private Distance2D testGui = null;
	private GuiToken testMe = null;
	private Token testToken = null;
	
	
	@Before
	public void init() {
		this.testGui = new Distance2D();
		this.testGui.setPlayerMode(GUI.PlayerMode.WHITE_ONLY);
		this.testToken = new Token(this.testPlayer, this.testField);
		this.testMe = new GuiToken(this.testToken, this.testGui);
	}
	
	
	@After
	public void destroy(){
		System.gc();		//Aufräumen
	}
	
		
	@Test
	public void testCompareToToken(){
		assertTrue(this.testMe.compareToToken(this.testToken));
	}


	@Test
	public void testGetToken(){
		assertEquals(this.testToken, this.testMe.getToken());
	}


	@Test
	public void testMove() {
		//wohin solls gehen?
		Field testPosition = new Field(Integer.valueOf(7), Integer.valueOf(7));
		//test linear move
		this.testMe.move(testPosition);
		assertEquals(testPosition, this.testMe.getToken().getPos());
	}


	@Test
	public void testSetLocked(){
		this.testMe.setLocked(Boolean.TRUE);
		assertEquals(Boolean.TRUE, this.testMe.getToken().isLocked());
		this.testMe.setLocked(Boolean.FALSE);
		assertEquals(Boolean.FALSE, this.testMe.getToken().isLocked());
	}
}
