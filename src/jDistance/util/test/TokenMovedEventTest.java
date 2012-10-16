/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jDistance.util.test;

import jDistance.model.Field;
import jDistance.model.King;
import jDistance.util.TokenMovedEvent;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * 
 */
public class TokenMovedEventTest {
    @Test
        public void  testTokenMovedEvent(){
        TokenMovedEvent move1= new TokenMovedEvent(null, null);
        assertEquals(null, move1.getToken());
        assertEquals(null, move1.getDestination());
        TokenMovedEvent move2= new TokenMovedEvent(new King(1,new Field(3,4)), new Field(2,4));
        assertEquals(new King(1,new Field(3,4)), move2.getToken());
        assertEquals(new Field(2,4), move2.getDestination());
    }

    @Test
    public void testToString() {
        TokenMovedEvent move2= new TokenMovedEvent(new King(1,new Field(3,4)), new Field(2,4));
        assertEquals(true, move2.toString().contains(new King(1,new Field(3,4)).toString()));
        assertEquals(true, move2.toString().contains(new Field(2,4).toString()));
    }
    
}
