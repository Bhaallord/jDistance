/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jDistance.util.test;

import jDistance.util.CloseEvent;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * 
 */
public class CloseEventTest {
    
    @Test
    public void testCloseEvent() {
        /**
         * Very boring Test
         */
        
        assertNotNull(new CloseEvent());
        
    }
    
    @Test
    public void testToString() {
        assertEquals(true,new CloseEvent().toString().contains("CloseEvent"));
        
    }
    
    @Test
    public void testEqualsObject() {
        assertEquals(true,new CloseEvent().equals(new CloseEvent()));
        assertEquals(false,new CloseEvent().equals(new Object()));
        assertEquals(false,new CloseEvent().equals(null));
    }
    
    @Test
    public void testHashCode(){
        assertEquals(5,new CloseEvent().hashCode());
    }
}


    