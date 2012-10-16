/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jDistance.util.test;

import jDistance.model.Field;
import jDistance.model.Token;
import jDistance.model.TokenMove;
import jDistance.util.TokenSwapedEvent;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * 
 */
public class TokenSwapedEventTest {

    @Test
    public void testTokenSwapedEvent() {
        TokenSwapedEvent event1 = new TokenSwapedEvent(null, null);
        assertEquals(null, event1.getFirstTokenMove());
        assertEquals(null, event1.getSecondTokenMove());

        TokenSwapedEvent event2 = new TokenSwapedEvent(new TokenMove(new Token(1, new Field(4, 5)), new Field(1, 5)), new TokenMove(new Token(2, new Field(1, 5)), new Field(4, 5)));
        assertEquals(new TokenMove(new Token(1, new Field(4, 5)), new Field(1, 5)), event2.getFirstTokenMove());
        assertEquals(new TokenMove(new Token(2, new Field(1, 5)), new Field(4, 5)), event2.getSecondTokenMove());
    }

    @Test
    public void testGetFirstTokenMove() {
        TokenSwapedEvent event1 = new TokenSwapedEvent(new TokenMove(new Token(1, new Field(4, 5)), new Field(1, 5)), new TokenMove(new Token(2, new Field(1, 5)), new Field(4, 5)));
        assertEquals(new TokenMove(new Token(1, new Field(4, 5)), new Field(1, 5)), event1.getFirstTokenMove());
    }

    @Test
    public void testGetSecondTokenMove() {

        TokenSwapedEvent event1 = new TokenSwapedEvent(new TokenMove(new Token(1, new Field(4, 5)), new Field(1, 5)), new TokenMove(new Token(2, new Field(1, 5)), new Field(4, 3)));
        assertEquals(new TokenMove(new Token(2, new Field(1, 5)), new Field(4, 3)), event1.getSecondTokenMove());
    }

    @Test
    public void testEquals() {
        TokenSwapedEvent event1 = new TokenSwapedEvent(new TokenMove(new Token(1, new Field(4, 5)), new Field(1, 5)), new TokenMove(new Token(2, new Field(1, 5)), new Field(4, 3)));
        TokenSwapedEvent event2 = new TokenSwapedEvent(new TokenMove(new Token(1, new Field(4, 5)), new Field(1, 5)), new TokenMove(new Token(2, new Field(1, 5)), new Field(4, 3)));
        assertEquals(event1, event2);
        assertEquals(false, event1.equals(new TokenSwapedEvent(new TokenMove(new Token(1, new Field(3, 5)), new Field(1, 5)), new TokenMove(new Token(2, new Field(1, 5)), new Field(4, 3)))));
        assertEquals(false, event1.equals(null));
        assertEquals(false, event1.equals(new Object()));
    }

    @Test
    public void testHashCode() {
        TokenSwapedEvent event1 = new TokenSwapedEvent(new TokenMove(new Token(1, new Field(4, 5)), new Field(1, 5)), new TokenMove(new Token(2, new Field(1, 5)), new Field(4, 3)));
        assertEquals(412033644, event1.hashCode());
    }

    @Test
    public void testToString() {
        TokenSwapedEvent event1 = new TokenSwapedEvent(new TokenMove(new Token(1, new Field(4, 5)), new Field(1, 5)), new TokenMove(new Token(2, new Field(1, 5)), new Field(4, 3)));
        assertEquals(true, event1.toString().contains(new TokenMove(new Token(1, new Field(4, 5)), new Field(1, 5)).toString()));        
        assertEquals(true, event1.toString().contains(new TokenMove(new Token(2, new Field(1, 5)), new Field(4, 3)).toString()));
    }
}
