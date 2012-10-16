/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jDistance.util.test;

import jDistance.model.Field;
import jDistance.model.Token;
import jDistance.util.TokenLockedEvent;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * 
 */
public class TokenLockedEventTest {

    @Test
    public void testTokenLockedEvent() {
        TokenLockedEvent event1 = new TokenLockedEvent(null);
        assertEquals(null, event1.getToken());
        TokenLockedEvent event2 = new TokenLockedEvent(new Token(1, new Field(3,4)));
        assertEquals(new Token(1, new Field(3,4)), event2.getToken());
    }

    @Test
    public void testGetToken() {
        TokenLockedEvent event1 = new TokenLockedEvent(null);
        assertEquals(null, event1.getToken());
        TokenLockedEvent event2 = new TokenLockedEvent(new Token(1, new Field(3,4)));
        assertEquals(new Token(1, new Field(3,4)), event2.getToken());
    }
        
    @Test
    public void testEquals() {
        TokenLockedEvent event1 = new TokenLockedEvent(new Token(1, new Field(3,4)));
        TokenLockedEvent event2 = new TokenLockedEvent(new Token(1, new Field(3,4)));
        assertEquals(event1, event2);
    }
    
    @Test
    public void testHashCode() {
        TokenLockedEvent event1 = new TokenLockedEvent(new Token(1, new Field(3,4)));
        assertEquals(63976693, event1.hashCode());
    }
    
    @Test
    public void testToString() {
        TokenLockedEvent event1 = new TokenLockedEvent(new Token(1, new Field(3,4)));
        assertEquals(true, event1.toString().contains(event1.getToken().toString()));
    }
}
