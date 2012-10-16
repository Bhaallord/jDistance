/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jDistance.util.test;

import jDistance.model.Field;
import jDistance.model.Token;
import jDistance.model.TokenMove;
import jDistance.util.ExpectTokenMoveEvent;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 *
 */
public class ExpectTokenMoveEventTest {
    
    @Test
    public void testExpectTokenMoveEvent() {
        ExpectTokenMoveEvent event = new ExpectTokenMoveEvent(3, new ArrayList<Token>(Arrays.asList(new Token(2, new Field(3, 4)))), new ArrayList<TokenMove>(Arrays.asList(new TokenMove(new Token(2, new Field(3, 4)), new Field(3, 5)), new TokenMove(new Token(2, new Field(3, 4)), new Field(1, 2)))));
        assertEquals(3, event.getPlayer());
        assertEquals(true, event.getToken().contains(new Token(2, new Field(3, 4))));
        assertEquals(true, event.getTokenMove().contains(new TokenMove(new Token(2, new Field(3, 4)), new Field(1, 2))));
    }
    
    @Test
    public void testGetPlayer() {
        ExpectTokenMoveEvent event = new ExpectTokenMoveEvent(3, new ArrayList<Token>(Arrays.asList(new Token(2, new Field(3, 4)))), new ArrayList<TokenMove>(Arrays.asList(new TokenMove(new Token(2, new Field(3, 4)), new Field(3, 5)), new TokenMove(new Token(2, new Field(3, 4)), new Field(1, 2)))));
        assertEquals(3, event.getPlayer());
    }
    
    @Test
    public void testGetToken() {
        ExpectTokenMoveEvent event = new ExpectTokenMoveEvent(3, new ArrayList<Token>(Arrays.asList(new Token(2, new Field(3, 4)), new Token(1, new Field(3, 2)), new Token(2, new Field(1, 4)))), new ArrayList<TokenMove>(Arrays.asList(new TokenMove(new Token(2, new Field(3, 4)), new Field(3, 5)), new TokenMove(new Token(2, new Field(3, 4)), new Field(1, 2)))));
        assertEquals(3, event.getToken().size());
        assertEquals(true, event.getToken().contains(new Token(1, new Field(3, 2))));
    }
    
    @Test
    public void testGetTokenMove() {
        ExpectTokenMoveEvent event = new ExpectTokenMoveEvent(3, new ArrayList<Token>(Arrays.asList(new Token(2, new Field(3, 4)), new Token(1, new Field(3, 2)), new Token(2, new Field(1, 4)))), new ArrayList<TokenMove>(Arrays.asList(new TokenMove(new Token(2, new Field(3, 4)), new Field(3, 5)), new TokenMove(new Token(2, new Field(3, 4)), new Field(1, 2)))));
        assertEquals(2, event.getTokenMove().size());
        assertEquals(true, event.getTokenMove().contains(new TokenMove(new Token(2, new Field(3, 4)), new Field(1, 2))));
    }
    
    @Test
    public void testToString() {
        ExpectTokenMoveEvent event = new ExpectTokenMoveEvent(3, new ArrayList<Token>(Arrays.asList(new Token(2, new Field(3, 4)), new Token(1, new Field(3, 2)), new Token(2, new Field(1, 4)))), new ArrayList<TokenMove>(Arrays.asList(new TokenMove(new Token(2, new Field(3, 4)), new Field(3, 5)), new TokenMove(new Token(2, new Field(3, 4)), new Field(1, 2)))));
        assertEquals(true, event.toString().contains(new Token(2, new Field(3, 4)).toString()));
        assertEquals(true, event.toString().contains(new TokenMove(new Token(2, new Field(3, 4)), new Field(3, 5)).toString()));
    }
    
    @Test
    public void testHashCode() {
        ExpectTokenMoveEvent event = new ExpectTokenMoveEvent(3, new ArrayList<Token>(Arrays.asList(new Token(2, new Field(3, 4)))), new ArrayList<TokenMove>(Arrays.asList(new TokenMove(new Token(2, new Field(3, 4)), new Field(3, 5)), new TokenMove(new Token(2, new Field(3, 4)), new Field(1, 2)))));
        assertEquals(-1192862944, event.hashCode());
    }
    
    @Test
    public void testEquals() {
        ExpectTokenMoveEvent event1 = new ExpectTokenMoveEvent(3, new ArrayList<Token>(Arrays.asList(new Token(2, new Field(3, 4)))), new ArrayList<TokenMove>(Arrays.asList(new TokenMove(new Token(2, new Field(3, 4)), new Field(3, 5)), new TokenMove(new Token(2, new Field(3, 4)), new Field(1, 2)))));
        assertEquals(new ExpectTokenMoveEvent(3, new ArrayList<Token>(Arrays.asList(new Token(2, new Field(3, 4)))), new ArrayList<TokenMove>(Arrays.asList(new TokenMove(new Token(2, new Field(3, 4)), new Field(3, 5)), new TokenMove(new Token(2, new Field(3, 4)), new Field(1, 2))))), event1);
        assertEquals(false, event1.equals(new ExpectTokenMoveEvent(3, new ArrayList<Token>(Arrays.asList(new Token(2, new Field(3, 4)))), new ArrayList<TokenMove>(Arrays.asList(new TokenMove(new Token(2, new Field(3, 4)), new Field(3, 5)), new TokenMove(new Token(2, new Field(3, 2)), new Field(1, 2)))))));
        assertEquals(false, event1.equals(new Object()));
        assertEquals(false, event1.equals(null));
    }
}
