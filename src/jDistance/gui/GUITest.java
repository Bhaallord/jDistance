package jDistance.gui;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;

import jDistance.model.Field;
import jDistance.model.King;
import jDistance.model.Slave;
import jDistance.model.Token;
import jDistance.model.TokenMove;
import jDistance.util.CloseEvent;
import jDistance.util.ExpectTokenMoveEvent;
import jDistance.util.GameStartedEvent;
import jDistance.util.TokenLockedEvent;
import jDistance.util.TokenMovedEvent;
import jDistance.util.TokenSwapedEvent;
import jDistance.util.WinEvent;

import org.junit.Test;

/**
 * Testklasse für 2D GUI
 */
public class GUITest extends Distance2D {
	
	public GUITest() {
		super();
		this.setPlayerMode(GUI.PlayerMode.BLACK_ONLY);
	}
	
	/**
	 * Test der GUI Methoden zur Koordniaten-Umrechung
	 */
	@Test
	public void testMethods() {
		//Field to Point werte sind absolut 155
		assertEquals(new Point(Integer.valueOf(155), Integer.valueOf(155)), this.fieldToPoint(new Field(Integer.valueOf(1), Integer.valueOf(1))));
		//FieldWith ist fest auf 65 gesetzt
		assertSame(Integer.valueOf(65), this.getFieldWidth());
	}

	@SuppressWarnings("static-access")
	@Test
    public void testDistance2D(){
        Distance2D gui = new Distance2D();

        javax.swing.JFrame frame = new JFrame();
        frame.getContentPane().add(gui.getPanel());
        frame.setSize(700,800);
        frame.setVisible(true);
        
        ArrayList<Token> tokens = new ArrayList<Token>(Arrays.asList(new King(2, new Field(3, 4)),new Slave(1,new Field(7,7))));

        Thread newThread=new Thread(gui);
        newThread.start();
        gui.addEvent(new GameStartedEvent(tokens));
        
        try { Thread.currentThread().sleep(2000); } 
        catch (InterruptedException e) { e.printStackTrace(); }
        
        gui.addEvent(new TokenMovedEvent(new King(2, new Field(3, 4)), new Field(0,0)));

        try { Thread.currentThread().sleep(2000); } 
        catch (InterruptedException e) { e.printStackTrace(); }
        
        gui.addEvent(new TokenSwapedEvent(new TokenMove(new King(2, new Field(0, 0)),new Field(7,7)), new TokenMove(new Slave(1,new Field(7,7)),new Field(0,0))));

        try { Thread.currentThread().sleep(2000); } 
        catch (InterruptedException e) { e.printStackTrace(); }
        
        gui.addEvent(new TokenLockedEvent(new Slave(1,new Field(0,0))));

        try { Thread.currentThread().sleep(2000); } 
        catch (InterruptedException e) { e.printStackTrace(); }
        
        Slave slave = new Slave(1,new Field(0,0));
        slave.setLocked(true);
        gui.addEvent(new TokenLockedEvent(slave));

        try { Thread.currentThread().sleep(2000); } 
        catch (InterruptedException e) { e.printStackTrace(); }
        
        gui.addEvent(new ExpectTokenMoveEvent(1, tokens, new ArrayList<TokenMove>()));
        gui.addEvent(new WinEvent(1));

        try { Thread.currentThread().sleep(2000); } 
        catch (InterruptedException e) { e.printStackTrace(); }
        
        gui.setPlayerMode(PlayerMode.WHITE_ONLY);
        gui.addEvent(new ExpectTokenMoveEvent(1, tokens, new ArrayList<TokenMove>()));
        gui.addEvent(new WinEvent(1));

        try { Thread.currentThread().sleep(2000); } 
        catch (InterruptedException e) { e.printStackTrace(); }
        
        gui.addEvent(new ExpectTokenMoveEvent(2, tokens, new ArrayList<TokenMove>()));
        gui.addEvent(new WinEvent(2));

        try { Thread.currentThread().sleep(2000); } 
        catch (InterruptedException e) { e.printStackTrace(); }
        
        gui.addEvent(new CloseEvent());

        try { Thread.currentThread().sleep(10000); } 
        catch (InterruptedException e) { e.printStackTrace(); }
        
        assertEquals(false,newThread.isAlive());
        
        
    }
}
