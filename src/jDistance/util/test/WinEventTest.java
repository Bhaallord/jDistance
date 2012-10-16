/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jDistance.util.test;

import jDistance.util.WinEvent;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * 
 */
public class WinEventTest {

    @Test
    public void testWinEvent() {
        WinEvent event= new WinEvent(1);
        assertEquals(1, event.getPlayer());
    }

    @Test
    public void testGetPlayer() {
        WinEvent event= new WinEvent(1);
        assertEquals(1, event.getPlayer());
    }

    @Test
    public void testToString() {
        WinEvent event= new WinEvent(3);
        assertEquals(true, event.toString().contains("3"));
    }
    
    @Test
    public void testHashCode() {
        WinEvent event= new WinEvent(3);
        assertEquals(206, event.hashCode());
    }
}
