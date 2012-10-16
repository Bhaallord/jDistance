/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jDistance.gui3d.test;
import jDistance.gui3d.Distance3D;
import jDistance.model.Field;
import jDistance.model.King;
import jDistance.model.Slave;
import jDistance.model.Token;
import jDistance.model.TokenMove;
import jDistance.util.CloseEvent;
import jDistance.util.GameStartedEvent;
import jDistance.util.TokenLockedEvent;
import jDistance.util.TokenMovedEvent;
import jDistance.util.TokenSwapedEvent;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * 
 */
public class Distance3DTest {
    
    @SuppressWarnings("static-access")
	@Test
    public void testDistance3D(){
        Distance3D gui = new Distance3D();

	    gui.getCanvas3D().setPreferredSize(new Dimension(800, 800));
        javax.swing.JFrame frame = new JFrame();
        frame.getContentPane().add(gui.getCanvas3D());
        frame.setSize(800,800);
        frame.setVisible(true);

        Thread newThread=new Thread(gui);
        newThread.start();
        gui.addEvent(new GameStartedEvent(new ArrayList<Token>(Arrays.asList(new King(2, new Field(3, 4)),new Slave(1,new Field(7,7))))));
        gui.addEvent(new TokenMovedEvent(new King(2, new Field(3, 4)), new Field(0,0)));
        gui.addEvent(new TokenSwapedEvent(new TokenMove(new King(2, new Field(0, 0)),new Field(7,7)), new TokenMove(new Slave(1,new Field(7,7)),new Field(0,0))));
        gui.addEvent(new TokenLockedEvent(new King(2,new Field(7,7))));
        King king = new King(2,new Field(7,7));
        king.setLocked(true);
        gui.addEvent(new TokenLockedEvent(king));
        gui.addEvent(new CloseEvent());
        try {
			Thread.currentThread().sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        assertEquals(false,newThread.isAlive());
        
        
    }
}
