/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jDistance.util.test;

import jDistance.model.Field;
import jDistance.model.King;
import jDistance.model.Token;
import jDistance.util.GameStartedEvent;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * 
 */
public class GameStartedEventTest {

    @Test
    public void testGameStartedEvent() {
        GameStartedEvent event = new GameStartedEvent(new ArrayList<Token>(Arrays.asList(new Token(2, new Field(3, 4)), new King(1, new Field(1, 0)))));
        assertEquals(2, event.getToken().size());
        assertEquals(true, event.getToken().contains(new King(1, new Field(1, 0))));
    }

    @Test
    public void testGetToken() {
        GameStartedEvent event = new GameStartedEvent(new ArrayList<Token>(Arrays.asList(new Token(2, new Field(5, 6)), new Token(1, new Field(7, 3)), new Token(2, new Field(3, 4)), new King(1, new Field(1, 0)))));
        assertEquals(4, event.getToken().size());
        assertEquals(true, event.getToken().contains(new Token(1, new Field(7, 3))));
    }
    
    @Test
    public void testEquals() {
        GameStartedEvent event1 = new GameStartedEvent(new ArrayList<Token>(Arrays.asList(new Token(2, new Field(5, 6)), new Token(1, new Field(7, 3)), new Token(2, new Field(3, 4)), new King(1, new Field(1, 0)))));
        GameStartedEvent event2 = new GameStartedEvent(new ArrayList<Token>(Arrays.asList(new Token(2, new Field(5, 6)), new Token(1, new Field(7, 3)), new Token(2, new Field(3, 4)), new King(1, new Field(1, 0)))));
        assertEquals(event1, event2);
        assertEquals(false, event1.equals(null));
        assertEquals(false, event1.equals(new Object()));
    }
    
        
    @Test
    public void testHashCode() {
        GameStartedEvent event1 = new GameStartedEvent(new ArrayList<Token>(Arrays.asList(new Token(2, new Field(5, 6)), new Token(1, new Field(7, 3)), new Token(2, new Field(3, 4)), new King(1, new Field(1, 0)))));
        assertEquals(194630207,event1.hashCode());
    }
    
       
    @Test
    public void testToString() {
        GameStartedEvent event1 = new GameStartedEvent(new ArrayList<Token>(Arrays.asList(new Token(2, new Field(5, 6)), new Token(1, new Field(7, 3)), new Token(2, new Field(3, 4)), new King(1, new Field(1, 0)))));
        assertEquals(true,event1.toString().contains(event1.getToken().toString()));
    }
}
